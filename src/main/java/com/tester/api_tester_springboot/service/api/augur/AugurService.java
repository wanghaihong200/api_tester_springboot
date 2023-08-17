package com.tester.api_tester_springboot.service.api.augur;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tester.api_tester_springboot.model.db.domain.tester.ApiTesterData;
import com.tester.api_tester_springboot.model.db.mapper.ApiTesterDataMapper;
import com.tester.api_tester_springboot.model.qo.augur.Params;
import com.tester.api_tester_springboot.model.qo.augur.RuleEngineQO;
import com.tester.api_tester_springboot.service.api.BaseService;
import com.tester.api_tester_springboot.util.FakerUtil;
import com.tester.api_tester_springboot.util.http.UnirestUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 风控引擎服务
 * @author: 王海虹
 * @create: 2023-05-09 10:26
 */

@Service
public class AugurService extends BaseService {

    /**
     * @param: systemUk: 系统标识， apiName： 接口名， flowName: 决策流名称
     * @return:
     * @date: 2023/1/4
     */
    public static Map<String, Object> ruleEngineFlow(String systemUk, String apiName, String flowName, Map<String, String> params,
                                                     ApiTesterDataMapper apiTesterDataMapper, String augurUrl
                                                     ) {
        Map<String, Object> map = new HashMap<>();

        // 生成一些参数
        String actionTime = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN);

        // 获取url
        LambdaQueryWrapper<ApiTesterData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ApiTesterData::getApiName, apiName)
                .eq(ApiTesterData::getSystemUk,systemUk);
        ApiTesterData apiTesterData = apiTesterDataMapper.selectOne(lambdaQueryWrapper);
        String requestUrl = augurUrl + apiTesterData.getRequestUrl();
        // 获取接口入参
        RuleEngineQO ruleEngineQO = JSONObject.parseObject(apiTesterData.getParamsJson(), RuleEngineQO.class);
        Params params1 = ruleEngineQO.getParam();

        String uniqueId = FakerUtil.getRandomNumber("191", 8);
        ruleEngineQO.setActionTime(actionTime);
        ruleEngineQO.setTraceId(FakerUtil.generateUUID());
        ruleEngineQO.setUniqueId(uniqueId);
        ruleEngineQO.setFlowName(flowName);

        params1.setOrderId(uniqueId);
        if (!params.containsKey("scheduledTime")){
            params1.setScheduledTime(DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN));
        }else{
            params1.setScheduledTime(params.get("scheduledTime"));
        }

        if (params.containsKey("orderType")){
            params1.setOrderType(params.get("orderType"));
        }

        if (params.containsKey("createLongtitude")){
            params1.setCreateLongtitude(params.get("createLongtitude"));
        }

        if (params.containsKey("mhotelId")){
            params1.setMhotelId(params.get("mhotelId"));
        }

        if (params.containsKey("deviceId")){
            params1.setDeviceId(params.get("deviceId"));
        }

        if (params.containsKey("orderState")){
            params1.setOrderState(params.get("orderState"));
        }

        if (params.containsKey("appClientType")){
            params1.setOrderState(params.get("appClientType"));
        }

        if (params.containsKey("medal")){
            params1.setMedal(params.get("medal"));
        }

        if (params.containsKey("memberId")){
            params1.setMemberId(params.get("memberId"));
        }

        if (params.containsKey("createLatitude")){
            params1.setCreateLatitude(params.get("createLatitude"));
        }

        if (params.containsKey("product")){
            params1.setProduct(params.get("product"));
        }

        if (params.containsKey("amount")){
            params1.setAmount(params.get("amount"));
        }

        if (params.containsKey("quantity")){
            params1.setQuantity(params.get("quantity"));
        }

        if (params.containsKey("unionId")){
            params1.setUnionId(params.get("unionId"));
        }

        if (params.containsKey("hotelcity")){
            params1.setHotelcity(params.get("hotelcity"));
        }

        if (params.containsKey("orderform")){
            params1.setOrderform(params.get("orderform"));
        }

        if (params.containsKey("star")){
            params1.setStar(params.get("star"));
        }

        if (params.containsKey("contactName")){
            params1.setContactName(params.get("contactName"));
        }

        if (params.containsKey("memberLevel")){
            params1.setMemberLevel(params.get("memberLevel"));
        }

        if (params.containsKey("businessSystemId")){
            params1.setBusinessSystemId(params.get("businessSystemId"));
        }

        if (params.containsKey("ip")){
            params1.setIp(params.get("ip"));
        }

        if (params.containsKey("actualAmount")){
            params1.setActualAmount(params.get("actualAmount"));
        }

        if (params.containsKey("hotelName")){
            params1.setHotelName(params.get("hotelName"));
        }

        if (params.containsKey("dcBuyerAccount")){
            params1.setDcBuyerAccount(params.get("dcBuyerAccount"));
        }

        if (params.containsKey("phone")){
            params1.setPhone(params.get("phone"));
        }

        if (params.containsKey("days")){
            params1.setDays(params.get("days"));
        }

        if (params.containsKey("dataSource")){
            params1.setDataSource(params.get("dataSource"));
        }


        ruleEngineQO.setParam(params1);
        JSONObject res = UnirestUtils.post(requestUrl, JSON.toJSONString(ruleEngineQO));
        map.put("result", res);
        map.put("uniqueId", uniqueId);
        return map;
    }
}
