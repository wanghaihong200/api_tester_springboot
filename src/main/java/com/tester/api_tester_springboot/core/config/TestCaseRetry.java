package com.tester.api_tester_springboot.core.config;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @description: 重试分析器接口的实现类， 定义测试用例失败重试机制
 * @author: 王海虹
 * @create: 2022-08-08 11:00
 */
public class TestCaseRetry implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryTime = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount < maxRetryTime){
            retryCount++;
            return true;
        }else{
            retryCount=0;
        }

        return false;
    }
}
