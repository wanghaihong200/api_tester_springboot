package com.tester.api_tester_springboot.core.aop;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONObject;
import com.tester.api_tester_springboot.util.RcLoggerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author haihong.wang 2022/6/22
 */

@Aspect
@Component
public class ControllerLogAspect {
    private final static Logger logger = LogManager.getLogger(ControllerLogAspect.class);

    @Pointcut("execution(public * com.tester.api_tester_springboot.controller..*.*(..))")
    public void pointcut() {
    }

    /**
     * 切面执行
     */
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        // 健康检查的排除掉
        if(className.equals("SysController")) {
            return proceed;
        }
        Object[] args = joinPoint.getArgs();
        if(ArrayUtil.isNotEmpty(args)) {
            List<Object> logArgs = Arrays.stream(args).filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse) && !(arg instanceof MultipartFile)))
                    .collect(Collectors.toList());
            RcLoggerUtils.recordReq(logger, className , startTime,null,null, JSONObject.toJSONString(logArgs), JSONObject.toJSONString(proceed));
        }
        return proceed;
    }
}
