package com.tester.api_tester_springboot.core.aop;

import com.alibaba.fastjson.JSONObject;
import com.tester.api_tester_springboot.util.RcLoggerUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.tester.api_tester_springboot.model.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * @program fraud-data-report
 * @description:全局异常处理
 * @author: zhaoceng
 * @create: 2021/06/07 17:28
 */
@RestControllerAdvice
@Log4j2
public class ExceptionControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public Result exceptionHandler(HttpServletRequest request, RuntimeException e) {
        e.printStackTrace();
        RcLoggerUtils.systemErrorReq(log, "ExceptionControllerAdvice", System.currentTimeMillis(), request, 1, e);
        log.error(e.getMessage(),e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result ArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        try {
            e.printStackTrace();
            RcLoggerUtils.systemErrorReq(log, "ExceptionControllerAdvice", System.currentTimeMillis(), request, 1, e);
            return Result.fail(JSONObject.toJSONString(e.getBindingResult().getAllErrors().stream().map(a->a.getDefaultMessage()).collect(Collectors.toList())));
        } catch (Exception exception) {
            RcLoggerUtils.systemErrorReq(log, "ExceptionControllerAdvice", System.currentTimeMillis(), request, 1, exception);
            return Result.fail(e.getMessage());
        }
    }
}