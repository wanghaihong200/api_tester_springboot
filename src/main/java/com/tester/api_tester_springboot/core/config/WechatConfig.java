package com.tester.api_tester_springboot.core.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: 王海虹
 * @create: 2023-04-26 16:25
 */
@Configuration
@Data
public class WechatConfig {
    @Value("${wechat.notice.url}")
    private String wechatNoticeUrl;

    @Value("${wechat.notice.key}")
    private String wechatNoticeKey;

    @Value("${wechat.notice.active}")
    private Boolean wechatNoticeActive;
}
