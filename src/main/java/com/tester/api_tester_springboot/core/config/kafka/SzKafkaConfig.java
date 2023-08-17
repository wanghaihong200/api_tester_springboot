package com.tester.api_tester_springboot.core.config.kafka;

import com.tester.api_tester_springboot.core.config.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * 苏州kafka配置
 * @author llg
 *
 */
@EnableKafka
@Configuration
public class SzKafkaConfig {

	@Autowired
	private KafkaConfig kafkaConfig;
		
    @Bean
    public KafkaTemplate<String, String> szKafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> szKafkaContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> szKafkaContainerBatchFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(true);
        return factory;
    }

    private ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    private Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getSzBootstrapServers());
        props.put(ProducerConfig.RETRIES_CONFIG, kafkaConfig.getSzProduceRetries());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, kafkaConfig.getSzProducerBatchSize());
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, kafkaConfig.getSzProducerBufferMemory());
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, kafkaConfig.getSzProducerCompressionType());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 60000);
        //props.put(ProducerConfig.ACKS_CONFIG, kafkaConfig.getSzProducerAcks());
        return props;
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getSzBootstrapServers());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaConfig.isSzConsumerEnableAutoCommit());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConfig.getSzConsumerAutoOffsetReset());
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, kafkaConfig.getSzConsumerAutoCommitInterval());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaConfig.getSzConsumerMaxPollRecords());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }
}
