package com.tester.api_tester_springboot.core.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @description:
 * @author: 王海虹
 * @create: 2023-05-24 14:43
 */
@PropertySource("classpath:kafka.properties")
@Configuration
@Data
public class KafkaConfig {
    @Value("${bj.kafka.bootstrap-servers}")
    private String bjBootstrapServers;
    @Value("${bj.kafka.producer.batch-size}")
    private int bjProducerBatchSize;
    @Value("${bj.kafka.producer.retries}")
    private int bjProduceRetries;
    @Value("${bj.kafka.producer.buffer-memory}")
    private long bjProducerBufferMemory;
    @Value("${bj.kafka.producer.compression-type}")
    private String bjProducerCompressionType;
    @Value("${bj.kafka.producer.acks}")
    private String bjProducerAcks;

    @Value("${bj.kafka.consumer.enable-auto-commit}")
    private boolean bjConsumerEnableAutoCommit;
    @Value("${bj.kafka.consumer.auto-offset-reset}")
    private String bjConsumerAutoOffsetReset;
    @Value("${bj.kafka.consumer.auto-commit-interval}")
    private int bjConsumerAutoCommitInterval;
    @Value("${bj.kafka.consumer.max-poll-records}")
    private int bjConsumerMaxPollRecords;

    @Value("${sz.kafka.bootstrap-servers}")
    private String szBootstrapServers;
    @Value("${sz.kafka.producer.batch-size}")
    private int szProducerBatchSize;
    @Value("${sz.kafka.producer.retries}")
    private int szProduceRetries;
    @Value("${sz.kafka.producer.buffer-memory}")
    private long szProducerBufferMemory;
    @Value("${sz.kafka.producer.compression-type}")
    private String szProducerCompressionType;
    @Value("${sz.kafka.producer.acks}")
    private String szProducerAcks;

    @Value("${sz.kafka.consumer.enable-auto-commit}")
    private boolean szConsumerEnableAutoCommit;
    @Value("${sz.kafka.consumer.auto-offset-reset}")
    private String szConsumerAutoOffsetReset;
    @Value("${sz.kafka.consumer.auto-commit-interval}")
    private int szConsumerAutoCommitInterval;
    @Value("${sz.kafka.consumer.max-poll-records}")
    private int szConsumerMaxPollRecords;

}
