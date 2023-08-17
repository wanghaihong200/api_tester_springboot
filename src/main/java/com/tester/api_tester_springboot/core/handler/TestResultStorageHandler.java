package com.tester.api_tester_springboot.core.handler;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tester.api_tester_springboot.core.config.WechatConfig;
import com.tester.api_tester_springboot.model.db.domain.TestResult;
import com.tester.api_tester_springboot.model.db.domain.TestResultSummary;
import com.tester.api_tester_springboot.model.db.mapper.TestResultSummaryMapper;
import com.tester.api_tester_springboot.service.TestResultService;
import com.tester.api_tester_springboot.util.http.UnirestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.ITestContext;
import org.testng.ITestResult;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: 王海虹
 * @create: 2023-04-26 18:02
 */
@Component
@Slf4j
public class TestResultStorageHandler {
    @Resource
    private WechatConfig wechatConfig;

    @Autowired
    private TestResultSummaryMapper testResultSummaryMapper;

    @Autowired
    private TestResultService testResultService;

    /**
     * 获取 ITestContext中的测试结果，写入到mysql
     *
     * @param:
     * @return:
     * @date: 2023/4/26
     */
    public void handle(ITestContext testContext) {
        List<TestResult> testResults = new ArrayList<>();
        String runId = (String) testContext.getAttribute("runId");
        if (StrUtil.isNotEmpty(runId) || runId.equals("null")) {
            String testName = testContext.getName();
            List<TestResult> passedTests = testContext.getPassedTests().getAllResults().stream()
                    .map((iTestResult) -> assembleTestResult(iTestResult, runId, testName))
                    .collect(Collectors.toList());
            List<TestResult> skippedTests = testContext.getSkippedTests().getAllResults().stream()
                    .map((iTestResult) -> assembleTestResult(iTestResult, runId, testName))
                    .collect(Collectors.toList());
            List<TestResult> failedTests = testContext.getFailedTests().getAllResults().stream()
                    .map((iTestResult) -> assembleTestResult(iTestResult, runId, testName))
                    .collect(Collectors.toList());
            // 将用例执行 的结果明细 存入 test_result表
            testResultService.saveBatch(passedTests);
            testResultService.saveBatch(skippedTests);
            testResultService.saveBatch(failedTests);

            // 将用例执行 的汇总结果数据 存入 test_result_summary
            TestResultSummary testResultSummary = TestResultSummary.builder()
                    .runId(runId)
                    .succeededAmount(String.valueOf(testContext.getPassedTests().size()))
                    .failedAmount(String.valueOf(testContext.getFailedTests().size()))
                    .skippedAmount(String.valueOf(testContext.getSkippedTests().size()))
                    .startTime(testContext.getStartDate())
                    .endTime(testContext.getEndDate())
                    .status(testContext.getFailedTests().size()>0 ? "2" : "1")
                    .operator("tester")
                    .build();
            testResultSummaryMapper.insert(testResultSummary);

            int totalNum = testContext.getPassedTests().size() + testContext.getFailedTests().size() + testContext.getSkippedTests().size();
            // 将测试结果 发送企业微信通知
            if (wechatConfig.getWechatNoticeActive()){
                wechatNotice(wechatConfig.getWechatNoticeKey(),runId,testName,totalNum,passedTests.size(),failedTests.size(),skippedTests.size());
            }
        }
    }

    /**
     * 组装用例结果数据
     *
     * @param: iTestResult: 测试用例 结果对象， runId：用例执行ID, testName: 用例名称
     * @return:
     * @date: 2023/5/5
     */
    private TestResult assembleTestResult(ITestResult iTestResult, String runId, String testName) {
        TestResult testResult = new TestResult();
        testResult.setRunId(runId);
        testResult.setTestClass(iTestResult.getTestClass().getName());
        testResult.setMethod(iTestResult.getMethod().getMethodName());
        //testResult.setCaseNo(iTestResult.getParameters()[0].toString());   // 获取 测试用例方法 中的参数
        //testResult.setCaseNo("1");
        testResult.setStatus(Integer.toString(iTestResult.getStatus()));
        testResult.setStartTime(new Date(iTestResult.getStartMillis()));
        testResult.setEndTime(new Date(iTestResult.getEndMillis()));
        testResult.setTestName(testName);
        return testResult;
    }


    /**
     * 微信机器人消息提醒
     */
    private void wechatNotice(String key,String taskID,String testName,int total,long PassNum,long FailNum,long SkipNum){

        String content = DateUtil.now() +" 自动化用例执行结果：" + "\n" +
                "# 执行批次:" + taskID + "\n" +
                "# 执行套件:" + testName + "\n" +
                "# 用例总数:" + total + "\n" +
                "# 成功数:" + PassNum+ "\n"+
                "# 失败数:" + FailNum + "\n" +
                "# 跳过数:" + SkipNum + "\n" +
                "# 详细结果链接:" + "待定";

        JSONObject MapContent = new JSONObject();
        MapContent.put("content",content);

        JSONObject sendMap = new JSONObject();
        sendMap.put("msgtype","text");
        sendMap.put("text",MapContent);


        String robot_url = wechatConfig.getWechatNoticeUrl()+"?key="+ key;

        JSONObject res = UnirestUtils.post(robot_url, JSON.toJSONString(sendMap));
        System.out.println("微信机器人消息提醒，执行结果："+JSON.toJSONString(res));

    }
}
