package com.tester.api_tester_springboot.personal;

import com.tester.api_tester_springboot.BaseTest;
import com.tester.api_tester_springboot.core.config.RiskSystemConfig;
import com.tester.api_tester_springboot.core.config.TestCaseRetry;
import com.tester.api_tester_springboot.efficiency.TesterTools;
import com.tester.api_tester_springboot.service.api.gallery.GalleryService;
import com.tester.api_tester_springboot.util.FakerUtil;
import com.tester.api_tester_springboot.util.http.OkHttp3Utils;
import io.qameta.allure.Epic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * @description:
 * @author: 王海虹
 * @create: 2023-06-27 10:11
 */
@Epic("个人日常测试")
@Slf4j
public class DailyTest extends BaseTest {
    @Autowired
    protected RiskSystemConfig riskSystemConfig;



    @Test(enabled = true)
    public void marketingRiskTest(){
        Map<String, Object> initializeParams = GalleryService.initializeMarketingRiskParams();
        initializeParams.put("unionId", "ohmdbETLURq7QO6vkhBLrxfRVW4d");
        initializeParams.put("phone", "13391387821");
        initializeParams.put("openId", "547657343265");
        initializeParams.put("accessId", "1529653281111654400");
        initializeParams.put("ip", "43.250.245.235");
        initializeParams.put("hotelId", "6485391");
        initializeParams.put("memberId", "24058734597838");
        initializeParams.put("deviceId", "6856b29f-b968-43e5-b171-1eb35824d7a3");
        initializeParams.put("payAccount", "788e84ef-9d5c-4d67-899d-84ee3f6b0b52");
        GalleryService.marketingRisk(initializeParams, riskSystemConfig.getGalleryUrl());
    }

    @Test(enabled = false)
    public void createRiskTagNamList(){
        String platformUrl = riskSystemConfig.getFraudDataPlatformUrl();
        //TesterTools.createRiskTagNamList("I301", "memberId", "3", platformUrl);
        TesterTools.createRiskTagNamList("S101", "unionId", "3", "ohmdTt---78aNtX8CdHPlozmSaVE", platformUrl);
    }

    @Test
    public void okHttp3Test() throws Exception {
        // get请求，方法顺序按照这种方式，切记选择post/get一定要放在倒数第二，同步或者异步倒数第一，才会正确执行
        System.out.println(OkHttp3Utils.stringOkHttpGet("https://www.baidu.com", null, null, null));
        assert 1==2;
    }

    @Test(retryAnalyzer = TestCaseRetry.class)
    public void faker(){
        System.out.println(FakerUtil.generatePhone());
    }
}
