package com.tester.api_tester_springboot;

import cn.hutool.core.util.StrUtil;
import com.tester.api_tester_springboot.core.config.RiskSystemConfig;
import com.tester.api_tester_springboot.core.handler.TestResultStorageHandler;
import com.tester.api_tester_springboot.core.handler.TestResultStorageListener;
import com.tester.api_tester_springboot.model.db.mapper.ApiTesterDataMapper;
import com.tester.api_tester_springboot.util.FakerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.*;

/**
 * @description: springBootTest 引入 testNg环境
 * @author: 王海虹
 * @create: 2023-04-26 14:50
 */
@SpringBootTest
@Slf4j
@Listeners({TestResultStorageListener.class})
public class BaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private TestResultStorageHandler testResultStorageHandler;

    @Autowired
    protected RiskSystemConfig riskSystemConfig;

    @Autowired
    protected ApiTesterDataMapper apiTesterDataMapper;

    // 当前正在运行的类的 类名
    public String className = this.getClass().getSimpleName();

    @Parameters({"runId"})
    @BeforeClass
    public void initParameters(ITestContext iTestContext, @Optional String runId) {
        // 在test class执行前，将 testResultStorageHandler 挂载到 ITestContext
        iTestContext.setAttribute("testResultStorageHandler", testResultStorageHandler);
        if (StrUtil.isNotEmpty(runId) && !runId.equals("${runId}")) {
            iTestContext.setAttribute("runId", runId);
        } else {
            iTestContext.setAttribute("runId", FakerUtil.generateUUID());
        }
    }

    @BeforeSuite
    public void beforeSuite() {
        log.info("测试开始: " + className);

    }

    @AfterSuite
    public void afterSuite() {
        log.info("测试结束: " + className);
    }
}
