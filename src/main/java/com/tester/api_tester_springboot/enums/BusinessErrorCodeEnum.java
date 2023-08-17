package com.tester.api_tester_springboot.enums;

/**
 * @description:
 * @author: 王海虹
 * @create: 2022-07-15 14:57
 */
public enum BusinessErrorCodeEnum {
    HTTPCLIENTREQ_ERROR(-99, "http请求异常"),
    CLASSTYPE_ILLIGAL(106, "clazz 类型错误,不能为基础类型"),
    METHODTYPE_ERROR(107, "请求参数类型错误");



    private final Integer code;
    private final String msg;

    private BusinessErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
