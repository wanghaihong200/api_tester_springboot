package com.tester.api_tester_springboot.service.api.platform;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tester.api_tester_springboot.enums.platform.RiskTagCheckFieldEnum;
import com.tester.api_tester_springboot.enums.platform.RiskTagEnum;
import com.tester.api_tester_springboot.model.db.domain.tester.ApiTesterData;
import com.tester.api_tester_springboot.model.db.mapper.ApiTesterDataMapper;
import com.tester.api_tester_springboot.model.qo.platform.MarketingWhiteListQo;
import com.tester.api_tester_springboot.model.qo.platform.RuleStrictOrderPush;
import com.tester.api_tester_springboot.service.api.BaseService;
import com.tester.api_tester_springboot.util.FakerUtil;
import com.tester.api_tester_springboot.util.JsonUtils;
import com.tester.api_tester_springboot.util.http.UnirestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: Platform api服务
 * @author: 王海虹
 * @create: 2022-08-09 17:04
 */
public class PlatformService extends BaseService {

    //private static final String platformSzUrl = envConfig.getPlatformSzUrl();

    //private static final String platformCookie = envConfig.getPlatformCookie();
    //
    private static final String highRiskSaveUrl = "/namelist/highRisk/save";

    private static final String marketingTagIdUrl = "/marketing/tag/id";

    //
    //private static final String marketingTagAddUrl = platformUrl + "/marketing/tag/add";
    private static final String marketingTagAddUrl = "/marketing/tag/add";

    /**
     * 全公司名单保存接口
     *
     * @param: params: 添加的名单信息
     * @return: 返回 全公司名单保存接口 出参 及 入参
     * @date: 2022/10/26
     */
    public static ArrayList<JSONObject> highRiskSave(HashMap<String, Object> params, String platformUrl) {
        ArrayList<JSONObject> arrayList = new ArrayList<>();
        String requestUrl = platformUrl + highRiskSaveUrl;

        String fileName = "data/platform/high_risk_namelist_save.json";
        JSONObject jsonParams = JsonUtils.fileToJson(fileName);
        jsonParams.putAll(params);

        JSONObject res = UnirestUtils.post(requestUrl, jsonParams.toJSONString());
        arrayList.add(res);
        arrayList.add(jsonParams);
        return arrayList;
    }

    public static ArrayList<JSONObject> createRiskTagNamList(String riskType, String checkField,
                                                             String riskLevel, String platformUrl) {
        String checkValue = null;
        return createRiskTagNamList(riskType, checkField, riskLevel, checkValue, platformUrl);
    }

    /**
     * 创建 全公司名单
     *
     * @param: riskType(风险类型): RiskTagEnum.riskType, checkField(名单维度) : RiskTagCheckFieldEnum.checkField,
     * riskLevel : 风险等级/分数
     * @return:
     * @date: 2022/8/10
     * @toDo:
     */
    public static ArrayList<JSONObject> createRiskTagNamList(String riskType, String checkField,
                                                             String riskLevel, String checkValue,
                                                             String platformUrl
    ) {
        HashMap<String, Object> hashMap = new HashMap<>();
        ArrayList<Object> arrayList = new ArrayList<>();
        String service = null;

        ArrayList<String> checkValues = new ArrayList<>();
        // 获取riskType的 service 枚举值
        for (RiskTagEnum riskTag :
                RiskTagEnum.values()) {
            if (riskType.equals(riskTag.getRiskType())) {
                service = riskTag.getService();
            }
        }

        if (checkValue == null) {
            // 根据checkField，构造测试数据
            switch (checkField) {
                case "device":
                    checkValue = FakerUtil.generateUUID();
                    break;
                case "unionId":
                    checkValue = FakerUtil.getRandomString("ohmd", 24);
                    break;
                case "memberId":
                    checkValue = FakerUtil.getRandomNumber("240", 12);
                    break;
                case "phone":
                    checkValue = FakerUtil.generatePhone();
                    break;
                case "ip":
                    checkValue = FakerUtil.generateIpV4();
                    break;
                case "hotelId":
                    checkValue = FakerUtil.getRandomNumber("1", 6);
                    break;
                case "payAccount":
                    checkValue = FakerUtil.getRandomString("dcbuyer", 12);
                    break;
                default:
                    checkValue = FakerUtil.generateMacAddress();
            }
        }

        checkValues.add(checkValue);
        String checkFieldValue = "";
        // 获取checkField的 枚举值
        for (RiskTagCheckFieldEnum riskTagCheckFieldEnum :
                RiskTagCheckFieldEnum.values()) {
            if (checkField.equals(riskTagCheckFieldEnum.getCheckField())) {
                checkFieldValue = riskTagCheckFieldEnum.getValue();
            }
        }
        // 组装接口入参
        hashMap.put("riskType", riskType);
        hashMap.put("service", service);
        hashMap.put("checkField", checkFieldValue);
        hashMap.put("checkValue", checkValue);
        hashMap.put("checkValues", checkValues);
        hashMap.put("riskLevel", riskLevel);

        return highRiskSave(hashMap, platformUrl);
    }

    // 获取 accessID
    public static String marketingTagId(String platformUrl) {
        String requestUrl = platformUrl + marketingTagIdUrl;
        JSONObject res = UnirestUtils.get(requestUrl);
        return res.getString("data");
    }

    // 添加 接入配置 accessId
    public static JSONObject marketingTagAdd(JSONObject params, String accessId, String platformUrl) {
        // 获取accessId
        if (StrUtil.isBlank(accessId)) {
            accessId = marketingTagId(platformUrl);
        }
        params.put("accessId", accessId);
        System.out.println(params);
        String requestUrl = platformUrl + marketingTagAddUrl;
        return UnirestUtils.post(requestUrl, params.toJSONString());
    }

    /**
     * redis crud 接口
     * 接口入参： {"action":"add","type":"factorRedisClusterTemplate","key":"ohmdTt_e4dYjfM1RPPvPvUI_Rbco:risk_distribution","value":"1"}
     *
     * @param: action: search 、add 、delete ; type: factorRedisClusterTemplate,bjRedisClusterTemplate,
     * nameListRedisClusterTemplate,augurRedisClusterTemplate,poseidonRedisClusterTemplate
     * @return:
     * @date: 2022/8/12
     * @toDo:
     */
    public static JSONObject redisOperate(Map<String, String> params, ApiTesterDataMapper apiTesterDataMapper,
                                          String platformUrl) {
        // 从数据库获取测试数据
        ApiTesterData redisQuery = apiTesterDataMapper.queryTesterParam("oceanxinjian1.java.fraud.data.platform", "redis_operate");

        String requestUrl = platformUrl + redisQuery.getRequestUrl();
        // 组装入参
        JSONObject paramJson = JSONObject.parseObject(redisQuery.getParamsJson());
        if (params != null) {
            paramJson.putAll(params);
        }

        return UnirestUtils.post(requestUrl, paramJson.toJSONString());
    }

    /**
     * 查询名单
     *
     * @param: checkValue： 名单值  , apiName: orderform_list--成单名单，cashback_list--提现返现名单
     * compensation_list -- 赔付名单, promotion_user_list -- 促销用户名单
     * @return:
     * @date: 2022/10/25
     */
    public static JSONObject getOrderFormList(String checkValue, String apiName,
                                              ApiTesterDataMapper apiTesterDataMapper, String platformUrl) {
        // 从数据库获取测试数据
        ApiTesterData nameListQuery = apiTesterDataMapper.queryTesterParam("oceanxinjian1.java.fraud.data.platform", apiName);

        String requestUrl = platformUrl + nameListQuery.getRequestUrl();
        // 组装入参
        JSONObject paramJson = JSONObject.parseObject(nameListQuery.getParamsJson());
        Map<String, String> params = new HashMap<>();
        params.put("checkValue", checkValue);
        paramJson.putAll(params);

        return UnirestUtils.post(requestUrl, paramJson.toJSONString());
    }

    /**
     * 编辑名单状态
     *
     * @param: state：0 -- 无效， 1 -- 有效, apiName: orderform_list_state_edit--编辑成单名单状态
     * cashback_list_state_edit -- 编辑 提现返现名单状态， compensation_list_state_edit -- 赔付名单编辑
     * promotion_user_list_state_edit -- 促销用户名单状态, marketing_list_state_edit -- 营销自定义名单
     * @return:
     * @date: 2022/10/25
     */
    public static JSONObject updateOrderFormListState(String id, String state, String apiName,
                                                      ApiTesterDataMapper apiTesterDataMapper, String platformUrl) {
        // 从数据库获取测试数据
        ApiTesterData nameListQuery = apiTesterDataMapper.queryTesterParam("oceanxinjian1.java.fraud.data.platform", apiName);

        String requestUrl = platformUrl + nameListQuery.getRequestUrl();
        // 组装入参
        JSONObject paramJson = JSONObject.parseObject(nameListQuery.getParamsJson());
        Map<String, Object> params = new HashMap<>();
        if (apiName.equals("orderform_list_state_edit")) {
            params.put("id", id);
        } else {
            params.put("id", Long.parseLong(id));
        }
        if (apiName.equals("marketing_list_state_edit")) {
            params.put("state", state);
        } else {
            params.put("state", Integer.parseInt(state));
        }
        paramJson.putAll(params);

        return UnirestUtils.post(requestUrl, paramJson.toJSONString());
    }

    /**
     * 查询 营销风控自定义名单，包含三方和
     *
     * @param: params: "unionid":"ohmdTt3c-_OiGOBcksJmv5LkUw_0"
     * @return:
     * @date: 2022/10/26
     */
    public static JSONObject marketingListQuery(Map<String, String> params,ApiTesterDataMapper apiTesterDataMapper, String platformUrl) {
        // 从数据库获取测试数据
        ApiTesterData marketingListQuery = apiTesterDataMapper.queryTesterParam("oceanxinjian1.java.fraud.data.platform", "marketing_list_query");

        String requestUrl = platformUrl + marketingListQuery.getRequestUrl();
        // 组装入参
        JSONObject paramJson = JSONObject.parseObject(marketingListQuery.getParamsJson());
        if (params != null) {
            paramJson.putAll(params);
        }


        return UnirestUtils.post(requestUrl, paramJson.toJSONString());
    }


    /**
    * 查询标签白名单
    * @param:
    * @return:
    * @date: 2023/5/22
    */
    public static JSONObject marketingWhiteListQuery(String dimension,String value, String platformUrl) {
        // 从数据库获取测试数据
        String requestUrl = platformUrl + "/marketing/white/list";
        MarketingWhiteListQo marketingWhiteListQo = MarketingWhiteListQo.builder()
                .dimension(dimension)
                .value(value)
                .build();

        String paramJson = JSON.toJSONString(marketingWhiteListQo);

        return UnirestUtils.post(requestUrl, paramJson);
    }



    /**
     * 修改标签白名单状态
     * @param:  state: 0--失效， 1--生效
     * @return:
     * @date: 2023/5/22
     */
    public static JSONObject marketingWhiteEditState(Integer id, String state,String platformUrl) {
        // 从数据库获取测试数据
        String requestUrl = platformUrl + "/marketing/white/edit/state";
        Map<String,Object> params = new HashMap<>();
        params.put("id", id);
        params.put("operator", "tester-1207663");
        params.put("state", state);


        return UnirestUtils.postForm(requestUrl, params);
    }

    /**
     * 规则特征值 及 命中情况查询
     *
     * @param: orderId： 订单号
     * @return:
     * @date: 2022/11/17
     */
    public static JSONObject getAugurResult(String orderId, String platformUrl) {
        JSONObject params = new JSONObject();
        params.put("orderId", orderId);
        String requestUrl = platformUrl + "/augur/getAugurResult";
        return UnirestUtils.post(requestUrl, params.toJSONString());
    }

    /**
     * 获取 factorName、factorValue键值对
     *
     * @param: augurResult: getAugurResult, businessLine : consumerCoupon,unusualAndMalice, ruleStrict
     * @return:
     * @date: 2022/11/17
     */
    public static HashMap<String, String> getFactorItem(JSONObject augurResult, String businessLine) {
        HashMap<String, String> resMap = new HashMap<>();
        try {
            JSONArray businessFeatures = augurResult.getJSONObject("data").getJSONArray("businessFeatures");
            for (int i = 0; i < businessFeatures.size(); i++) {
                if (businessFeatures.getJSONObject(i).getString("businessLine").equals(businessLine)) {
                    JSONArray factors = businessFeatures.getJSONObject(i).getJSONArray("factors");
                    for (int j = 0; j < factors.size(); j++) {
                        String factorName = factors.getJSONObject(j).getString("factorName");
                        String factorValue = factors.getJSONObject(j).getString("factorValue");
                        resMap.put(factorName, factorValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resMap;
    }

    /**
     * @param: switchName: poseidon开关名称， switchValue: 1--打开，2--关闭(不一定)
     * @return:
     * @date: 2023/1/17
     */
    public static Boolean changePoseidonSwitchState(String switchName, Integer switchValue, String platformUrl) {
        String requestUrl = platformUrl + "/system/tSwitch/list";
        String editUrl = platformUrl + "/system/tSwitch/edit";
        String refreshUrl = platformUrl + "/system/tSwitch/refreshCache";

        // 获取poseidon开关数据
        Map<String, Object> mapParams = new HashMap<>();
        mapParams.put("switchname", switchName);
        mapParams.put("switchvalue", "");
        mapParams.put("pageSize", "10");
        mapParams.put("pageNo", "1");

        JSONObject response = UnirestUtils.post(requestUrl, JSON.toJSONString(mapParams));

        Integer switchId = 0;
        String switchDesc = "";
        if (response != null && response.getJSONObject("data") != null) {
            switchId = response.getJSONObject("data").getJSONArray("records").getJSONObject(0).getInteger("id");
            switchDesc = response.getJSONObject("data").getJSONArray("records").getJSONObject(0).getString("switchdesc");

            mapParams.clear();

            // 修改 poseidon开关状态
            mapParams.put("id", switchId);
            mapParams.put("switchname", switchName);
            mapParams.put("switchdesc", switchDesc);
            mapParams.put("switchvalue", switchValue);
            JSONObject resEdit = UnirestUtils.post(editUrl, JSON.toJSONString(mapParams));

            // 刷新 poseidon开关缓存
            Map<String, Object> mapRefresh = new HashMap<>();
            mapRefresh.put("id", switchId);
            JSONObject resRefresh = UnirestUtils.post(refreshUrl, JSON.toJSONString(mapRefresh));

            return resEdit.getString("msg").equals("success") &&
                    resRefresh.getString("msg").equals("success");
        }
        return false;
    }

    /**
     * http://wiki.17usoft.com/pages/viewpage.action?pageId=105482641
     * 订单推送恶意下单黑名单到风控接口
     *
     * @param:
     * @return:
     * @date: 2023/3/9
     */
    public static JSONObject ruleStrictOrderPush(RuleStrictOrderPush ruleStrictOrderPush, String platformUrl) {
        String requestUrl = platformUrl + "/api/ruleStrict/order/push";
        return UnirestUtils.post(requestUrl, JSON.toJSONString(ruleStrictOrderPush));
    }


}
