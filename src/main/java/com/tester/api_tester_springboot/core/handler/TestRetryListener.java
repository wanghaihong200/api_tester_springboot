package com.tester.api_tester_springboot.core.handler;

import com.tester.api_tester_springboot.core.config.TestCaseRetry;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: 王海虹
 * @create: 2023-08-01 17:08
 */
public class TestRetryListener implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        IRetryAnalyzer retryAnalyzer = iTestAnnotation.getRetryAnalyzer();
        if (null == retryAnalyzer) {
            iTestAnnotation.setRetryAnalyzer(TestCaseRetry.class);
        }
    }
}
