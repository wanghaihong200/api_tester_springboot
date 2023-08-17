package com.tester.api_tester_springboot.consumer;

import com.alibaba.fastjson.JSONObject;
import com.tester.api_tester_springboot.util.RcLoggerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: 王海虹
 * @create: 2023-05-29 16:53
 */
//@Component
@Slf4j
public class TesterKafkaConsumer {
    private final static Logger logger = LogManager.getLogger(TesterKafkaConsumer.class);


    // 灰度环境，无法连接consumer
    @KafkaListener(topics = "${spring.kafka.marketing-risk-name-topic}", groupId = "${spring.kafka.group-ids.marketing-risk-name-white}", concurrency = "2", containerFactory = "bjKafkaContainerBatchFactory")
    public void consumerTesterKafkaMessge(List<ConsumerRecord<?, ?>> messages) {
        try {
            messages.stream().map(ConsumerRecord::value).collect(Collectors.toList()).forEach(System.out::println);
        } catch (Exception e) {
            String requestBody = JSONObject.toJSONString(messages.stream().map(ConsumerRecord::value).collect(Collectors.toList()));
            RcLoggerUtils.systemError(logger, "consumerMarketingRisk", System.currentTimeMillis(), requestBody, "", 501, e);
        }
    }
}
