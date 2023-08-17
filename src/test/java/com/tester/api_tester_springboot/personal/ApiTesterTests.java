package com.tester.api_tester_springboot.personal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tester.api_tester_springboot.BaseTest;
import com.tester.api_tester_springboot.efficiency.TesterTools;
import com.tester.api_tester_springboot.model.qo.augur.RuleEngineQO;
import com.tester.api_tester_springboot.service.api.platform.PlatformService;
import com.tester.api_tester_springboot.util.FakerUtil;
import com.tester.api_tester_springboot.util.RedisUtils;
import com.tester.api_tester_springboot.util.http.UnirestUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
*
* @desc: 自测、小工具使用
* @date: 2023/5/22
*/
@SpringBootTest
@Slf4j
public class ApiTesterTests extends BaseTest {

    @Autowired
    private RedissonClient redissonNameList;

    @Autowired
    private KafkaTemplate szKafkaTemplate;
    @Autowired
    private KafkaTemplate bjKafkaTemplate;

    @Value("${spring.kafka.marketing-risk-name-topic}")
    private String marketingRiskNameTopic;

    @Test
     void testUnirest() {
        String requestUrl = "http://10.160.93.5:8080/marketing/tag/id";
        JSONObject res = UnirestUtils.get(requestUrl);
    }

    @Test
    void testRedission(){
        System.out.println(RedisUtils.getString("BL_2_creditCard_HRMUwBesJ34KxSULvRmNxvtTTszmFr2aeWSzNSpoeywJsBnQujKu5A==", redissonNameList));
        System.out.println(RedisUtils.getString("BL_204_phone_13895134148", redissonNameList));
        JSONArray res = JSON.parseArray(RedisUtils.getString("BL_204_phone_13895134148", redissonNameList));
        log.info(res.toString());
    }

    @Test
    void testKafkaProducer(){
        JSONObject message = new JSONObject();
        String key = FakerUtil.generateUUID();
        System.out.println(key);
        message.put("name","test");
        message.put("name2","hello");
        RuleEngineQO ruleEngineQO = new RuleEngineQO();
        ruleEngineQO.setFlowName("123");
        ruleEngineQO.setDatasourceId("333333");
        bjKafkaTemplate.send(marketingRiskNameTopic,key,JSON.toJSONString(ruleEngineQO));
        bjKafkaTemplate.send(marketingRiskNameTopic,key,JSON.toJSONString(message));
    }



    @Test
    void testStream(){
        List<String> words = Arrays.asList("Hello","World");
        List<String[]> newWords = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
        //newWords.stream().map(letter ->{
        //    System.out.println(letter);
        //    return letter;
        //});

        for (int i = 0; i < newWords.size(); i++) {
            System.out.println(newWords.get(i));
        }
    }

    @Test
    void test01(){
        String requestUrl = riskSystemConfig.getFraudDataPlatformUrl();
        PlatformService.marketingWhiteEditState(445,"1", requestUrl);
    }

    // 将认证解限拉白的 名单状态置为 无效
    @Test
    void test02(){
        List<String> whiteList = TesterTools.searchAndDeleteDeconstructionWhiteList("13391387808", apiTesterDataMapper,
                riskSystemConfig.getFraudDataPlatformUrl(),
                riskSystemConfig.getMemberUrl()
        );

        // 删除 deconstruction_result、rc_marketing_config_white 表中关联数据
        
    }
}
