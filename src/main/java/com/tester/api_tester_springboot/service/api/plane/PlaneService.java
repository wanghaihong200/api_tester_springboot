package com.tester.api_tester_springboot.service.api.plane;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.tester.api_tester_springboot.model.db.domain.tester.ApiTesterData;
import com.tester.api_tester_springboot.model.db.mapper.ApiTesterDataMapper;
import com.tester.api_tester_springboot.service.api.BaseService;
import com.tester.api_tester_springboot.util.FakerUtil;
import com.tester.api_tester_springboot.util.http.UnirestUtils;

import java.util.Map;
import java.util.UUID;

/**
 * @description:
 * @author: 王海虹
 * @create: 2023-07-17 16:45
 */
public class PlaneService extends BaseService {

    /**
     * 机票风控接口， /open/api/plane/ticket/order
     *
     * @param:
     * @return:
     * @date: 2023/2/23
     */
    public static JSONObject airPlaneTicket(Map<String, Object> params, ApiTesterDataMapper apiTesterDataMapper, String planeUrl) {
        ApiTesterData airPlane = apiTesterDataMapper.queryTesterParam("pbssuzhou.java.rc.business.plane", "air_plane_ticket");
        String requestUrl = planeUrl + airPlane.getRequestUrl();

        JSONObject requestParamsJson = JSONObject.parseObject(airPlane.getParamsJson());
        String actionTime = new DateTime().toString();
        requestParamsJson.put("actionTime", actionTime);
        requestParamsJson.put("traceId", UUID.randomUUID().toString());
        requestParamsJson.put("operateIp", FakerUtil.generateIpV4());
        requestParamsJson.put("orderId", FakerUtil.getRandomNumber("OTEM", 10));
        requestParamsJson.putAll(params);

        return UnirestUtils.post(requestUrl, requestParamsJson.toJSONString());
    }
}
