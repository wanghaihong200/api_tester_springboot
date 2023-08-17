package com.tester.api_tester_springboot.core.handler;

import org.testng.ITestContext;
import org.testng.TestListenerAdapter;

/**
 * @description: 测试用例结果保存 监听器
 * @author: 王海虹
 * @create: 2023-04-26 16:12
 */

public class TestResultStorageListener extends TestListenerAdapter {

    @Override
    public void onFinish(ITestContext testContext) {
        TestResultStorageHandler testResultStorageHandler = (TestResultStorageHandler) testContext.getAttribute("testResultStorageHandler");
        if (testResultStorageHandler != null) {
            testResultStorageHandler.handle(testContext);
        }
    }
}
