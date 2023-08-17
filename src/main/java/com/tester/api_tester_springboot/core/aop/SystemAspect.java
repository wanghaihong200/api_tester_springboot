package com.tester.api_tester_springboot.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @program fraud-data-report
 * @description:aop切面
 * @author: zhaoceng
 * @create: 2021/06/07 17:33
 */
@Aspect
@Component
@Slf4j
public class SystemAspect {
    @Pointcut("execution(public * com.tester.api_tester_springboot.controller..*.*(..))")
    public void Pointcut() {
    }

    @Pointcut("execution(public * com.tester.api_tester_springboot.service.*.*(..))")
    public void PointcutService() {
    }
    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before("Pointcut()")
    public void beforeMethod(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
    }


    /**
     * 后置通知
     *
     * @param joinPoint
     */
    @After("Pointcut()")
    public void afterMethod(JoinPoint joinPoint) {
    }

    /**
     * 返回通知 result为返回内容
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "Pointcut()", returning = "result")
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "PointcutService()", throwing = "e")
    public void afterReturningMethod(JoinPoint joinPoint, Exception e) {
        e.printStackTrace();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.error("service:{} , method : {}  error : {}",joinPoint.getTarget().toString(), signature.getMethod().getName() , e.getMessage());
    }

    /**
     * 环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("Pointcut()")
    public Object Around(ProceedingJoinPoint pjp) throws Throwable {
        Object object = pjp.proceed();
        return object;
    }
}
