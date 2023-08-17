package com.tester.api_tester_springboot.model.qo.app;

import lombok.Data;

/**
 * @description: 环境配置文件 application.yml 的映射类
 * @author: 王海虹
 * @create: 2022-07-28 15:58
 */
@Data
public class ApplicationMapper {
    private String env;

    private UrlMapper test;

    private UrlMapper pre;

    private UrlMapper online;

    private MailInfoMapper mailInfo;

    private JedisClusterInfo jedisCluster;

    private String platformCookie;
}
