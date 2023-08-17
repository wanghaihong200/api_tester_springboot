package com.tester.api_tester_springboot.model;

import lombok.Builder;
import lombok.Data;

/**
 * @program fraud-data-report
 * @description:统一封装返回
 * @author: haihong.wang
 * @create: 2021/06/07 17:29
 */
@Data
@Builder
public class Result<T> {
    private Integer code;

    private String msg;

    private T data;

    public Result(T data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success() {
        return Result.builder()
                .code(StatusCode.SUCCESS.getCode())
                .msg(StatusCode.SUCCESS.getDes()).build();
    }

    public static Result success(Object data) {
        return Result.builder()
                .code(StatusCode.SUCCESS.getCode())
                .msg(StatusCode.SUCCESS.getDes())
                .data(data).build();
    }

    public static Result success(String msg) {
        return Result.builder()
                .code(StatusCode.SUCCESS.getCode())
                .msg(msg)
                .build();
    }

    public static Result fail(String msg) {
        return Result.builder()
                .code(StatusCode.FAIL.getCode())
                .msg(msg)
                .build();
    }

    public static Result fail(StatusCode statusCode, Object data) {
        return Result.builder()
                .code(statusCode.getCode())
                .msg(statusCode.getDes())
                .data(data)
                .build();
    }
    
    public static Result fail(Integer code, String msg) {
        return Result.builder()
                .code(code)
                .msg(msg)
                .build();
    }

    public static Result error(){
        return Result.builder()
                .code(StatusCode.SERVER_ERROR.getCode())
                .msg(StatusCode.SERVER_ERROR.getDes())
                .build();
    }

    public boolean success200() {
        return code != null && code == 200 && data != null;
    }
    public boolean success0() {
        return code != null && code == 0 && data != null;
    }
    public boolean successCode0() {
        return code != null && code == 0;
    }
    public boolean checkSuccessCode0() {
        if (!successCode0()){
            throw new RuntimeException(msg);
        }
        return true;
    }
    public boolean checkSuccess0() {
        if (!success0()){
            throw new RuntimeException(msg);
        }
        return true;
    }
}
