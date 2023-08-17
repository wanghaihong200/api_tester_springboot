package com.tester.api_tester_springboot.service.api.namelist;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tester.api_tester_springboot.model.db.domain.tester.ApiTesterData;
import com.tester.api_tester_springboot.model.db.mapper.ApiTesterDataMapper;
import com.tester.api_tester_springboot.model.qo.platform.BatchAddNameValueDTOListItem;
import com.tester.api_tester_springboot.model.qo.platform.UnifyNameAddItem;
import com.tester.api_tester_springboot.model.qo.platform.UnifyNameEdit;
import com.tester.api_tester_springboot.model.qo.platform.UnifyNameQuery;
import com.tester.api_tester_springboot.service.api.BaseService;
import com.tester.api_tester_springboot.util.http.UnirestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 王海虹
 * @create: 2023-02-24 11:23
 */

public class NameListService extends BaseService {

     /**
     * 添加 通用名单
     * @param:
     * @return:
     * @date: 2023/1/17
     */
    public static JSONObject addCommonNameList(Map<String,Object> mapParams, String apiName, ApiTesterDataMapper apiTesterDataMapper, String nameListUrl){
        // 从数据库获取测试数据
        LambdaQueryWrapper<ApiTesterData>  lq = new LambdaQueryWrapper<>();
        lq.eq(ApiTesterData::getSystemUk, "oceanxinjian1.java.fraud.data.platform")
                .eq(ApiTesterData::getApiName, apiName);
        ApiTesterData whiteAdd = apiTesterDataMapper.selectOne(lq);

        String requestUrl = nameListUrl + whiteAdd.getRequestUrl();

        JSONObject params = JSONObject.parseObject(whiteAdd.getParamsJson());
        params.put("expirationTime", DateUtil.offset(new Date(), DateField.HOUR_OF_DAY, 1));
        params.putAll(mapParams);

        return UnirestUtils.post(requestUrl, params.toJSONString());
    }

    /**
     * 批量新增通用名单
     * @param:
     * @return:
     * @date: 2023/3/9
     */
    public JSONObject addCommonNameList(List<UnifyNameAddItem> unifyNameAdd, String nameListUrl){
        String requestUrl = nameListUrl+"/unify/name/addBatch";

        return UnirestUtils.post(requestUrl, JSON.toJSONString(unifyNameAdd));
    }

    /**
     * 批量新增通用名单
     * @param: nameDimension: memberId、dcBuyerAccount、mhotelId、deviceId
     *         nameType: 0-普通名单、1-黑名单
     * @return:
     * @date: 2023/3/9
     */
    public static JSONObject addCommonNameList(String menuOne,String menuTwo, String menuThree,
                                        String nameType, String nameDimension, String nameValue,
                                        String nameListUrl,String nameValueExtend,String nameDimensionExtend
    ){
        String requestUrl = nameListUrl+"/unify/name/addBatch";
        List<UnifyNameAddItem> unifyNameAddItems = new ArrayList<>();
        List<BatchAddNameValueDTOListItem> batchAddNameValueDTOListItems = new ArrayList<>();
        UnifyNameAddItem unifyNameAddItem = new UnifyNameAddItem();
        unifyNameAddItem.setMenuOne(menuOne);
        unifyNameAddItem.setMenuTwo(menuTwo);
        unifyNameAddItem.setMenuThree(menuThree);
        unifyNameAddItem.setNameType(nameType);

        if (nameValueExtend != null && nameDimensionExtend != null){
            BatchAddNameValueDTOListItem batchAddNameValueDTOListItemExtend = BatchAddNameValueDTOListItem.builder()
                    .nameDimensionExtend(nameDimensionExtend)
                    .nameValueExtend(nameValueExtend)
                    .nameDimension(nameDimension)
                    .nameValue(nameValue)
                    .build();
            batchAddNameValueDTOListItems.add(batchAddNameValueDTOListItemExtend);
        }else{
            BatchAddNameValueDTOListItem batchAddNameValueDTOListItem = BatchAddNameValueDTOListItem.builder()
                    .nameDimension(nameDimension)
                    .nameValue(nameValue)
                    .build();
            batchAddNameValueDTOListItems.add(batchAddNameValueDTOListItem);
        }


        unifyNameAddItem.setBatchAddNameValueDTOList(batchAddNameValueDTOListItems);
        unifyNameAddItems.add(unifyNameAddItem);


        return UnirestUtils.post(requestUrl, JSON.toJSONString(unifyNameAddItems));
    }

    public static JSONObject addCommonNameList(String menuOne,String menuTwo, String menuThree,
                                               String nameType, String nameDimension, String nameValue,
                                               String nameListUrl
    ){
        return addCommonNameList(menuOne,menuTwo,menuThree,nameType,nameDimension,
                nameValue,nameListUrl,null,null);
    }

    /**
     * 查询 通用名单
     * @param:
     * @return:
     * @date: 2023/1/28
     */
    public static JSONObject queryCommonNameList(UnifyNameQuery unifyNameQuery, String nameListUrl){
        String requestUrl = nameListUrl+"/unify/name/list";
        return UnirestUtils.post(requestUrl, JSON.toJSONString(unifyNameQuery));
    }

    /**
     * 编辑通用名单
     * @param:
     * @return:
     * @date: 2023/3/22
     */
    public JSONObject editCommonNameList(UnifyNameEdit unifyNameEdit, String nameListUrl){
        String requestUrl = nameListUrl+"/unify/name/edit";
        return UnirestUtils.post(requestUrl, JSON.toJSONString(unifyNameEdit));
    }
}
