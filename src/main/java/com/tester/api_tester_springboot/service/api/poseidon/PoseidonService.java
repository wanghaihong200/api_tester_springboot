package com.tester.api_tester_springboot.service.api.poseidon;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tester.api_tester_springboot.model.db.domain.tester.ApiTesterData;
import com.tester.api_tester_springboot.model.db.mapper.ApiTesterDataMapper;
import com.tester.api_tester_springboot.model.qo.order.HotelOrderDto;
import com.tester.api_tester_springboot.service.api.BaseService;
import com.tester.api_tester_springboot.util.FakerUtil;
import com.tester.api_tester_springboot.util.http.UnirestUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * @description: poseidon api接口服务
 * @author: 王海虹
 * @create: 2022-09-16 18:51
 */
public class PoseidonService extends BaseService {

    /**
     * 信用住一阶段 接口，包含app和微信
     *
     * @param: params：需要修改的入参， apiName: mysql表 api_tester_data.apiName
     * @return:
     * @date: 2023/1/16
     */
    public static JSONObject creditHotel(Map<String, Object> params, String apiName,
                                         ApiTesterDataMapper apiTesterDataMapper, String poseidonUrl) {

        ApiTesterData creditHotel = apiTesterDataMapper.queryTesterParam("oceanxinjian1.rc.poseidon", apiName);
        String requestUrl = poseidonUrl + creditHotel.getRequestUrl();

        JSONObject requestParamsJson = JSONObject.parseObject(creditHotel.getParamsJson());
        String actionTime = new DateTime().toString();
        requestParamsJson.put("actionTime", actionTime);
        requestParamsJson.put("traceId", UUID.randomUUID().toString());
        requestParamsJson.putAll(params);

        return UnirestUtils.post(requestUrl, requestParamsJson.toJSONString());
    }



    /**
     * 返现接口， /open/api/cashback/order
     *
     * @param: params：需要修改的入参， apiName: mysql表 api_tester_data.apiName
     * @return:
     * @date: 2023/07/04
     */
    public static JSONObject cashBack(Map<String, Object> params, String amount,
                                      ApiTesterDataMapper apiTesterDataMapper, String poseidonUrl) {

        ApiTesterData cashBack = apiTesterDataMapper.queryTesterParam("oceanxinjian1.rc.poseidon", "cash_back");
        String requestUrl = poseidonUrl + cashBack.getRequestUrl();

        JSONObject requestParamsJson = JSONObject.parseObject(cashBack.getParamsJson());
        JSONObject data = requestParamsJson.getJSONObject("data");
        data.put("guid", FakerUtil.generateUUID());
        data.put("amount", amount);

        requestParamsJson.put("actionTime", DateUtil.now());
        requestParamsJson.put("data", data);

        requestParamsJson.putAll(params);

        return UnirestUtils.post(requestUrl, requestParamsJson.toJSONString());
    }

    /**
     * 现付订单创单并支付
     *
     * @param:
     * @return:
     * @date: 2023/7/5
     */
    public static ArrayList<Object> domesticHotelOrder(Map<String, Object> requestParams, Map<String, String> payParams,
                                                       ApiTesterDataMapper apiTesterDataMapper, String poseidonUrl
    ) {
        ApiTesterData domesticHotelOrder = apiTesterDataMapper.queryTesterParam("oceanxinjian1.rc.poseidon", "create_order_domestic_hotel");
        ArrayList<Object> arrayList = new ArrayList<>();

        if (domesticHotelOrder != null) {
            String requestUrl = poseidonUrl + domesticHotelOrder.getRequestUrl();

            JSONObject res = null;

            // 组装入参
            HotelOrderDto hotelOrderDto = JSONObject.parseObject(domesticHotelOrder.getParamsJson(), HotelOrderDto.class);
            if (requestParams != null) {
                hotelOrderDto = PoseidonService.changeHotelOrderParams(requestParams, domesticHotelOrder.getParamsJson());
            }

            // 修改订单号
            String relationValue = FakerUtil.generateOrderId();
            hotelOrderDto.setRelationValue(relationValue);
            hotelOrderDto.getData().setOrderId(relationValue);

            String params = JSON.toJSONString(hotelOrderDto);

            // N单, 返现不要支付，是现场付款
            res = UnirestUtils.post(requestUrl, params);

            arrayList.add(res);
            arrayList.add(params);
            arrayList.add(requestUrl);
        }

        return arrayList;
    }
}
