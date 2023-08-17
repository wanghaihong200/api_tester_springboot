package com.tester.api_tester_springboot.core.aop.annotation;

import java.lang.annotation.*;

/**
 * 统一异常处理日志注解，
 * 注意如果加上此注解，那么方法的的异常将默认被捕获！！！！
 */
@Target({  ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptionLog {
    /**
     * 日志上的参数名称，spl表达式
     * @return
     */
    String reqParamName() default "";
    /**
     * 异常是否需要抛出
     * @return
     */
    boolean needThrows() default false;

}
