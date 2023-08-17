package com.tester.api_tester_springboot.service.api.gallery;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.tester.api_tester_springboot.model.db.domain.tester.ApiTesterData;
import com.tester.api_tester_springboot.model.db.mapper.ApiTesterDataMapper;
import com.tester.api_tester_springboot.service.api.BaseService;
import com.tester.api_tester_springboot.util.FakerUtil;
import com.tester.api_tester_springboot.util.JsonUtils;
import com.tester.api_tester_springboot.util.http.UnirestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: gallery api服务
 * @author: 王海虹
 * @create: 2022-07-29 11:46
 */
public class GalleryService extends BaseService {

    private static final String marketingRiskUrl = "/marketing/risk";

    // 营销风控标签 查询接口
    public static JSONObject marketingRisk(String galleryUrl) {
        Map<String, Object> params = new HashMap<>();
        return marketingRisk(params, galleryUrl);
    }

    public static JSONObject marketingRisk(Map<String, Object> params, String galleryUrl) {
        // 生成一些参数
        String traceId = FakerUtil.generateUUID();
        Date date = new Date(System.currentTimeMillis());
        String actionTime = DateUtil.format(date, DatePattern.NORM_DATETIME_PATTERN);

        // 获取url
        String requestUrl = galleryUrl + marketingRiskUrl;

        // 构造入参
        String fileName = "data/gallery/marketing_risk.json";
        JSONObject json = JsonUtils.fileToJson(fileName);
        params.put("traceId", traceId);
        params.put("actionTime", actionTime);
        json.putAll(params);

        return UnirestUtils.post(requestUrl, json.toJSONString());
    }

    // 对 marking_risk接口 关键入参 进行初始化赋值
    public static Map<String, Object> initializeMarketingRiskParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("openId", FakerUtil.getRandomString("openId", 12));
        map.put("unionId", FakerUtil.getRandomString("ohmd", 24));
        map.put("memberId", FakerUtil.getRandomNumber("240", 12));
        map.put("phone", FakerUtil.generatePhone());
        map.put("fptoken", FakerUtil.generateUUID());
        map.put("deviceId", FakerUtil.generateUUID());
        map.put("hotelId", FakerUtil.getRandomNumber("1", 6));
        map.put("payAccount", FakerUtil.getRandomString("dcbuyer", 12));
        return map;
    }

    /**
     * @param: systemUk: 系统标识， token： 营销风控token
     * @return:
     * @date: 2023/1/4
     */
    public static JSONObject fraudMarketing(String systemUk, String token, Map<String, Object> params
            , ApiTesterDataMapper apiTesterDataMapper, String galleryUrl) {
        // 生成一些参数
        Date date = new Date(System.currentTimeMillis());
        String actionTime = DateUtil.format(date, DatePattern.NORM_DATETIME_PATTERN);

        // 获取url

        ApiTesterData apiTesterData = apiTesterDataMapper.queryTesterDataByToken(systemUk, token);
        String requestUrl = galleryUrl + apiTesterData.getRequestUrl();
        // 获取接口入参
        JSONObject paramsJson = JSONObject.parseObject(apiTesterData.getParamsJson());

        paramsJson.put("actionTime", actionTime);
        if (!params.isEmpty()) {
            paramsJson.putAll(params);
        }

        return UnirestUtils.post(requestUrl, paramsJson.toJSONString());
    }
}
