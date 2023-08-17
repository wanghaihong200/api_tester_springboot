package com.tester.api_tester_springboot.efficiency;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tester.api_tester_springboot.model.db.mapper.ApiTesterDataMapper;
import com.tester.api_tester_springboot.model.dto.MemberInfoData;
import com.tester.api_tester_springboot.model.dto.MemberInfoResponse;
import com.tester.api_tester_springboot.service.api.platform.PlatformService;
import com.tester.api_tester_springboot.util.http.UnirestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 测试日常小工具
 * @author: 王海虹
 * @create: 2023-05-22 14:48
 */
public class TesterTools {
    /**
     * 认证解限小工具： 去除手机号对应的memberId和unionId白名单
     * 通过手机号查询 会员接口 ，获取4个维度的memberId和unionId
     * 查询 unionId 是否营销自定义名单和标签白名单中，若存在则 置为无效
     * 查询 memberId 是否在标签白名单中，若存在则 置为无效
     *
     * @param: key: phone
     * @return: 需要拉白的 list
     * @date: 2023/05/22
     */
    public static List<String> searchAndDeleteDeconstructionWhiteList(String phone,ApiTesterDataMapper apiTesterDataMapper,
                                                              String platformUrl, String memberUrl) {
        String memberQueryUrl = memberUrl+"/etrelation/member/getTcElMemberIdByMobile";
        Map<String,String> headers = new HashMap<>();
        headers.put("appKey", "bjplatform.java.rc.gallery.api");
        headers.put("appSecret", "46568a172d4556aa");
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", phone);
        JSONObject resMemberInfo = UnirestUtils.get(memberQueryUrl,params,headers);
        MemberInfoResponse memberInfoResponse = JSON.parseObject(JSON.toJSONString(resMemberInfo), MemberInfoResponse.class);
        MemberInfoData memberInfoData = memberInfoResponse.getData();
        List<String> whiteList = new ArrayList<>();
        whiteList.add(memberInfoData.getElMemberIdApp());
        whiteList.add(memberInfoData.getElMemberIdWx());
        whiteList.add(memberInfoData.getTcMemberIdApp());
        whiteList.add(memberInfoData.getTcMemberIdWx());
        whiteList.add(memberInfoData.getUnionId());

        whiteList.forEach(value->{
            if (!value.equals("")){
                Integer id;
                JSONObject resMarketingWhiteListQuery = PlatformService.marketingWhiteListQuery("", value, platformUrl);
                JSONArray records = resMarketingWhiteListQuery.getJSONObject("data").getJSONArray("records");
                if (records.size()>=1){
                    id = records.getJSONObject(0).getInteger("id");
                    // 将标签白名单中的 memberId或 unionId 置为失效
                    PlatformService.marketingWhiteEditState(id,"0",platformUrl);
                }
            }
        });

        // unionId 查询 营销自定义名单，若存在，则将名单置为无效
        Map<String, String> map = new HashMap<>();

        if (!memberInfoData.getUnionId().equals("")){
            map.put("unionid", memberInfoData.getUnionId());
            JSONObject res = PlatformService.marketingListQuery(map, apiTesterDataMapper,platformUrl);
            JSONArray records = res.getJSONObject("data").getJSONArray("records");
            System.out.println(records);
            if (records != null) {
                for (int i = 0; i < records.size(); i++) {
                    JSONObject record = records.getJSONObject(i);
                    long id;
                    try {
                        id = record.getLongValue("id");
                    } catch (Exception e) {
                        id = Long.parseLong(record.getString("id"));
                    }

                    if (id != 0) {
                        // 若id有值,将对应的名单置为无效
                        PlatformService.updateOrderFormListState(String.valueOf(id), "1", "marketing_list_state_edit"
                        ,apiTesterDataMapper, platformUrl);
                    }
                }
            }
        }

        return whiteList;

    }

    /**
     * 创建 全公司名单
     *
     * @param: riskType(风险类型): RiskTagEnum.riskType, checkField(名单维度) : RiskTagCheckFieldEnum.checkField,
     * riskLevel : 风险等级/分数
     * @return:
     * @date: 2022/8/10
     *
     */
    public static void createRiskTagNamList(String riskType, String checkField,
                                            String riskLevel, String platformUrl) {
        PlatformService.createRiskTagNamList(riskType, checkField, riskLevel, platformUrl);
    }

    // checkValue不传 ，则自动根据 checkField 随机生成
    public static void createRiskTagNamList(String riskType, String checkField,
                                            String riskLevel, String checkValue, String platformUrl) {
        PlatformService.createRiskTagNamList(riskType, checkField, riskLevel, checkValue, platformUrl);
    }
}
