package com.tester.api_tester_springboot.core.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 风控各个系统的url
 * @author: 王海虹
 * @create: 2023-05-15 17:38
 */
@Configuration
@Data
public class RiskSystemConfig {
    @Value("${risk-system.poseidon}")
    private String poseidonUrl;

    @Value("${risk-system.gallery}")
    private String galleryUrl;

    @Value("${risk-system.nameList}")
    private String nameListUrl;

    @Value("${risk-system.fraudDataPlatform}")
    private String fraudDataPlatformUrl;

    @Value("${risk-system.platformSZ}")
    private String platformSZUrl;

    @Value("${risk-system.plane}")
    private String planeUrl;

    @Value("${risk-system.augur}")
    private String augurUrl;

    @Value("${risk-system.member}")
    private String memberUrl;
}
