package com.tester.api_tester_springboot.enums.platform;

import lombok.AllArgsConstructor;

/**
 * @description: 高危名单-check_field枚举
 * @author: 王海虹
 * @create: 2022-08-10 14:37
 */
@AllArgsConstructor
public enum RiskTagCheckFieldEnum {
    // openId
    OPEN_ID("openId", "1"),
    // openId
    UNION_ID("unionId", "2"),
    // 会员号
    MEMBER_ID("memberId", "3"),
    // 手机号
    PHONE("phone", "4"),
    // duhu设备指纹
    FPTOKEN("fptoken", "5"),
    // ip
    IP("ip", "6"),
    // 经纬度
    Lon_LAT("longitudeLatitude", "7"),
    //设备号
    Device("device", "8"),
    //酒店id
    Hotel_Id("hotelId", "9"),
    //支付账号
    PayAccount("payAccount", "10"),
    ;



    private final String checkField;
    private final String value;

    public String getCheckField() {
        return checkField;
    }

    public String getValue() {
        return value;
    }
}
