package com.tester.api_tester_springboot;

import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {RedissonAutoConfiguration.class})
@EnableScheduling
@EnableAsync
public class ApiTesterSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiTesterSpringbootApplication.class, args);
    }
}
