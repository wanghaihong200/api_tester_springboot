package com.tester.api_tester_springboot.service.api;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.tester.api_tester_springboot.model.qo.order.*;
import com.tester.api_tester_springboot.model.qo.order.pay.CardsItem;
import com.tester.api_tester_springboot.model.qo.order.pay.OrderPayDto;
import com.tester.api_tester_springboot.model.qo.order.pay.PayData;
import com.tester.api_tester_springboot.util.FakerUtil;
import com.tester.api_tester_springboot.util.http.UnirestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: api接口服务类的
 * @author: 王海虹
 * @create: 2022-08-09 17:09
 */

public class BaseService {

    /**
     * @param: map: 需要修改的入参
     * @return: 返回 修改好的 订单接口入参
     * @date: 2022/9/19
     */
    public static HotelOrderDto changeHotelOrderParams(Map<String, Object> map, String hotelOrderParams) {
        // 将 hotel_order接口的 入参 从json转到 java 映射类中
        HotelOrderDto hotelOrderDto = JSON.parseObject(hotelOrderParams, HotelOrderDto.class);
        DataBean data = hotelOrderDto.getData();
        List<TravelersItem> travelersItemList = data != null ? data.getTravelers() : null;
        List<PriceListItem> priceListItemList = data != null ? data.getPriceList() : null;

        // 产品线编号 例如 1005 预付
        if (map.containsKey("product")) {
            hotelOrderDto.setProduct((String) map.get("product"));
        }

        // 订单号
        if (map.containsKey("relationValue")) {
            String relationValue = (String) map.get("relationValue");
            hotelOrderDto.setRelationValue(relationValue);
            if (data != null) {
                data.setOrderId(relationValue);
            }
        }

        // 订单来源
        if (map.containsKey("orderFrom")) {
            if (data != null) {
                data.setOrderFrom(Integer.parseInt((String) map.get("orderFrom")));
            }
        }

        if (map.containsKey("appClientType")) {
            if (data != null) {
                data.setAppClientType(Integer.parseInt((String) map.get("appClientType")));
            }
        }

        if (map.containsKey("businessSystemId")) {
            if (data != null) {
                data.setBusinessSystemId(Integer.parseInt((String) map.get("businessSystemId")));
            }
        }

        if (map.containsKey("ip")) {
            if (data != null) {
                data.setIp((String) map.get("ip"));
                data.setOperationIP((String) map.get("ip"));
            }
        }
        // 订单状态，新单 N、已审 V、已确认 A、已入住 F、已结账 C、取消 E、NoShow B、删除 D
        if (map.containsKey("orderState")) {
            if (data != null) {
                data.setOrderState((String) map.get("orderState"));
            }
        }
        // 订单类型， 1000   非扫码住订单  1001   扫码住订单
        if (map.containsKey("orderType")) {
            if (data != null) {
                data.setOrderType((String) map.get("orderType"));
            }
        }
        //  同一会员（会员卡号、手机号、unionid、设备号、支付账号）
        if (map.containsKey("phone")) {
            if (data != null) {
                data.setPhone((String) map.get("phone"));
            }
        }

        if (map.containsKey("member")) {
            if (data != null) {
                MemberInfo memberInfo = data.getMemberInfo();
                if (memberInfo != null) {
                    memberInfo.setMember((String) map.get("member"));
                }
            }
        }

        if (map.containsKey("memberLevel")) {
            if (data != null) {
                MemberInfo memberInfo = data.getMemberInfo();
                if (memberInfo != null) {
                    memberInfo.setMemberLevel((String) map.get("memberLevel"));
                }
            }
        }

        if (map.containsKey("memberType")) {
            if (data != null) {
                MemberInfo memberInfo = data.getMemberInfo();
                if (memberInfo != null) {
                    memberInfo.setMemberType((String) map.get("memberType"));
                }
            }
        }

        if (map.containsKey("openId")) {
            if (data != null) {
                MemberInfo memberInfo = data.getMemberInfo();
                if (memberInfo != null) {
                    memberInfo.setOpenId((String) map.get("openId"));
                }
            }
        }

        if (map.containsKey("unionId")) {
            if (data != null) {
                MemberInfo memberInfo = data.getMemberInfo();
                if (memberInfo != null) {
                    memberInfo.setUnionId((String) map.get("unionId"));
                }
            }
        }

        if (map.containsKey("deviceId")) {
            if (data != null) {
                data.setDeviceId((String) map.get("deviceId"));
            }
        }
        // sku: sHotelId+sRoomTypeId+rpId  sku_new：sHotelId+sRoomTypeId
        if (map.containsKey("sHotelId")) {
            if (data != null) {
                data.setSHotelId((String) map.get("sHotelId"));
            }
        }

        if (map.containsKey("sRoomTypeId")) {
            if (data != null) {
                data.setSRoomTypeId((String) map.get("sRoomTypeId"));
            }
        }
        // 价格策略ID
        if (map.containsKey("rpId")) {
            if (data != null) {
                data.setRpId((String) map.get("rpId"));
            }
        }
        // 预定时间，下单时间
        if (map.containsKey("scheduledTime")) {
            if (data != null) {
                data.setScheduledTime((String) map.get("scheduledTime"));
            }
        } else {
            String now = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN);
            if (data != null) {
                data.setScheduledTime(now);
            }
        }
        // 触发时间
        if (map.containsKey("actionTime")) {
            hotelOrderDto.setActionTime((String) map.get("actionTime"));
        } else {
            String now = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN);
            hotelOrderDto.setActionTime(now);
        }
        // 艺龙酒店ID
        if (map.containsKey("mHotelId")) {
            if (data != null) {
                data.setMHotelId((String) map.get("mHotelId"));
            }
        }
        // 艺龙酒店房型ID
        if (map.containsKey("mRoomTypeId")) {
            if (data != null) {
                data.setMRoomTypeId((String) map.get("mRoomTypeId"));
            }
        }
        // 入住天数，间夜数
        if (map.containsKey("day")) {
            if (data != null) {
                data.setDay((String) map.get("day"));
            }
        }
        // 预定数量
        if (map.containsKey("quantity")) {
            if (data != null) {
                data.setQuantity((String) map.get("quantity"));
            }
        }

        // travelersInfo
        if (map.containsKey("startTime")) {
            if (data != null && travelersItemList != null) {
                // 入住时间
                travelersItemList.get(0).setStartTime((String) map.get("startTime"));
            }
        }
        // 离店时间
        if (map.containsKey("arrivalTime")) {
            if (data != null && travelersItemList != null) {
                travelersItemList.get(0).setArrivalTime((String) map.get("arrivalTime"));
            }
        }
        // 入住人姓名
        if (map.containsKey("passengerName")) {
            if (data != null && travelersItemList != null) {
                travelersItemList.get(0).setPassengerName((String) map.get("passengerName"));
            }
        }
        // priceList,  stayday: 入住日期
        if (map.containsKey("stayday")) {
            if (data != null && priceListItemList != null) {
                priceListItemList.get(0).setStayday((String) map.get("stayday"));
            }
        }
        // 折前售价
        if (map.containsKey("salePriceOriginal")) {
            if (data != null && priceListItemList != null) {
                priceListItemList.get(0).setSalePriceOriginal(Double.parseDouble((String) map.get("salePriceOriginal")));
            }
        }
        // 折后售价
        if (map.containsKey("realSalePrice")) {
            if (data != null && priceListItemList != null) {
                priceListItemList.get(0).setRealSalePrice(Double.parseDouble((String) map.get("realSalePrice")));
            }
        }
        // 折前底价、单间夜价格
        if (map.containsKey("realCostOriginal")) {
            if (data != null && priceListItemList != null) {
                priceListItemList.get(0).setRealCostOriginal(Double.parseDouble((String) map.get("realCostOriginal")));
            }
        }

        if (map.containsKey("realCost")){
            if (data!=null){
                data.setRealCost((String) map.get("realCost"));
            }
        }

        if (map.containsKey("extraData")) {
            if (data != null) {
                data.setExtraData((String) map.get("extraData"));
            }
        }
        // 坐标类型: google-1，百度-2
        if (map.containsKey("createLocationType")) {
            if (data != null) {
                data.setCreateLocationType((String) map.get("createLocationType"));
            }
        }
        // 酒店GPS 纬度
        if (map.containsKey("hotelLat")) {
            if (data != null) {
                data.setHotelLat((String) map.get("hotelLat"));
            }
        }
        // 酒店GPS 经度
        if (map.containsKey("hotelLng")) {
            if (data != null) {
                data.setHotelLng((String) map.get("hotelLng"));
            }
        }
        // 订单 GPS 纬度
        if (map.containsKey("orderLat")) {
            if (data != null) {
                data.setOrderLat((String) map.get("orderLat"));
            }
        }
        // 订单 GPS 经度
        if (map.containsKey("orderLng")) {
            if (data != null) {
                data.setOrderLng((String) map.get("orderLng"));
            }
        }
        // 酒店所属城市
        if (map.containsKey("hotelCity")) {
            if (data != null) {
                data.setHotelCity((String) map.get("hotelCity"));
            }
        }

        if (map.containsKey("star")) {
            if (data != null) {
                data.setStar((String) map.get("star"));
            }
        }

        if (map.containsKey("isExperienceOrder")) {
            if (data != null) {
                data.getExperienceMemberInfo().setIsExperienceOrder((Boolean) map.get("isExperienceOrder"));
            }
        }
        // 促销ID/红包批次号
        if (map.containsKey("promotionid")) {
            data.getPromotioninfo().get(0).setPromotionid(Integer.parseInt((String) map.get("promotionid")));
        }

        if (map.containsKey("operationTime")) {
            if (data != null) {
                data.setOperationTime(map.get("operationTime").toString());
            }
        }



        return hotelOrderDto;
    }

    /**
     * @param: map: 需要修改的入参，  hotelOrderPayParams: unique_pay 入参
     * @return: 返回 现付的支付接口入参
     * @date: 2022/9/20
     */
    public static OrderPayDto ChangeHotelOrderPayParams(Map<String, String> map, String hotelOrderPayParams) {
        OrderPayDto orderPayDto = JSON.parseObject(hotelOrderPayParams, OrderPayDto.class);
        PayData payData = orderPayDto.getData();
        List<CardsItem> cardsItemList = payData != null ? payData.getCards() : null;

        if (map.containsKey("relationValue")) {
            orderPayDto.setRelationValue(map.get("relationValue"));
        }

        if (map.containsKey("product")) {
            orderPayDto.setProduct(map.get("product"));
        }

        if (map.containsKey("actionTime")) {
            orderPayDto.setActionTime(map.get("actionTime"));
        } else {
            String now = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN);
            orderPayDto.setActionTime(now);
        }

        if (map.containsKey("dcBuyerAccount")) {
            if (cardsItemList != null) {
                cardsItemList.get(0).setDcBuyerAccount(map.get("dcBuyerAccount"));
            }
        }

        return orderPayDto;
    }

    /**
    * 修改酒店订单状态： 新单 N、已审 V、已确认 A、已入住 F、已结账 C、取消 E、NoShow B、删除 D
    * @param:
    * @return:
    * @date: 2023/7/5
    */
    public static void changeHotelOrderState(String orderState, HotelOrderDto hotelOrderDto, String requestUrl){
        hotelOrderDto.getData().setOrderState(orderState);
        //data.setOrderState(orderState);
        //hotelOrderDto.setData(data);
        UnirestUtils.post(requestUrl, JSON.toJSONString(hotelOrderDto));
    }


    public static Map<String, Object> initializeMemberInfo() {
        // 5要素判断同一会员，任意要素相同，均判断为同一会员。 会员信息初始化赋值
        Map<String, Object> memberInfo = new HashMap<>();
        memberInfo.put("phone", FakerUtil.generatePhone());
        memberInfo.put("member", FakerUtil.getRandomNumber("240", 11));
        memberInfo.put("unionId", FakerUtil.getRandomString("ohmd", 18));
        memberInfo.put("deviceId", FakerUtil.generateUUID());
        memberInfo.put("dcBuyerAccount", FakerUtil.getRandomString("dcBuyer", 18));

        return memberInfo;
    }

    public static Map<String, String> initializeSku(Boolean skuNew) {
        // sku: sHotelId+sRoomTypeId+rpId  sku_new：sHotelId+sRoomTypeId
        Map<String, String> sku = new HashMap<>();
        sku.put("sHotelId", FakerUtil.getRandomNumber("1", 7));
        sku.put("sRoomTypeId", FakerUtil.getRandomNumber("2", 7));

        if (!skuNew) {
            sku.put("rpId", FakerUtil.getRandomNumber("91", 7));
        }
        return sku;
    }

    public static void main(String[] args) {
        // 从数据库获取测试数据
        //SqlSession sqlSession = MyBatisUtil.getSqlSession(envConfig.getEnv(), "ApiTesterData");
        //ApiTesterDataMapper apiTesterDataMapper = sqlSession.getMapper(ApiTesterDataMapper.class);
        //ApiTesterData unusualOrder = apiTesterDataMapper.queryTesterDataByRule("oceanxinjian1.rc.poseidon", "1401");

        //String paramJson = unusualOrder.getParamsJson();
        //Map<String, Object> mapParams = new HashMap<>();
        //mapParams.put("relationValue", "190");
        //System.out.println(changeHotelOrderParams(mapParams, paramJson));
    }
}
