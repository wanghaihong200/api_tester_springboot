package com.tester.api_tester_springboot.core.aop;

import com.alibaba.fastjson.JSONObject;
import com.tester.api_tester_springboot.core.aop.annotation.ExceptionLog;
import com.tester.api_tester_springboot.core.excepiton.LogAspectException;
import com.tester.api_tester_springboot.util.RcLoggerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;

@Aspect
@Component
public class ExceptionLogAspect {
    private final static Logger logger = LogManager.getLogger(ExceptionLogAspect.class);
    private SpelExpressionParser parserSpel = new SpelExpressionParser();
    private LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    @Pointcut("@annotation(exceptionLog)")
    public void log(ExceptionLog exceptionLog) {
    }

    @Around("log(exceptionLog)")
    public Object around(ProceedingJoinPoint point, ExceptionLog exceptionLog) {
        Long startTime = System.currentTimeMillis();
        Object[] args = point.getArgs();
        String className = point.getTarget().getClass().getSimpleName();
        String methodName = point.getSignature().getName();
        Object resp = null;
        try {
            resp = point.proceed(args);
        } catch (Throwable e) {
            try {
                Object valueBykey = getValueBykey(exceptionLog.reqParamName(), point);
                if (ObjectUtils.isEmpty(valueBykey) && args != null && args.length > 0) {
                    valueBykey = args[0];
                }
                RcLoggerUtils.systemErrorReq(logger, className, startTime, methodName, "",
                        valueBykey==null?"": JSONObject.toJSON(valueBykey), -1, new Exception(e));
            } catch (Exception e1) {
                //日志记录错误忽略
            }
            if (exceptionLog.needThrows()) {
                throw new LogAspectException(e);
            }
        }
        return resp;

    }


    public Object getValueBykey(String key, ProceedingJoinPoint pjp) {
        EvaluationContext context = null;
        try {
            context = bindParam(getMethod(pjp), pjp.getArgs());
        } catch (NoSuchMethodException e) {
            return key;
        }
        try {
            if (ObjectUtils.isEmpty(key)) {
                return key;
            }
            Expression expression = parserSpel.parseExpression(key);
            return expression.getValue(context);
        } catch (Exception e) {
            return context.lookupVariable(key);
        }
    }

    /**
     * 获取当前执行的方法
     *
     * @param pjp
     * @return
     * @throws NoSuchMethodException
     */
    private Method getMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        Method targetMethod = pjp.getTarget().getClass().getMethod(method.getName(), method.getParameterTypes());
        return targetMethod;
    }

    /**
     * 将方法的参数名和参数值绑定
     *
     * @param method 方法，根据方法获取参数名
     * @param args   方法的参数值
     * @return
     */
    private EvaluationContext bindParam(Method method, Object[] args) {
        //获取方法的参数名
        String[] params = discoverer.getParameterNames(method);
        //将参数名与参数值对应起来
        EvaluationContext context = new StandardEvaluationContext();
        if (ObjectUtils.isEmpty(params)) {
            return context;
        }
        for (int len = 0; len < params.length; len++) {
            context.setVariable(params[len], args[len]);
        }
        return context;
    }
}
