package com.tester.api_tester_springboot.enums;

/**
 * @description:
 * @author: 王海虹
 * @create: 2022-07-26 19:32
 */

public enum EnvironmentEnum {
    TEST_ENV("test","env/test/mybatis-config.xml"),
    PRE_ENV("pre","env/test/mybatis-config.xml"),
    ONLINE_ENV("online","env/online/mybatis-config.xml");


    private final String envName;
    private final String path;

    private EnvironmentEnum(String envName, String path) {
        this.envName = envName;
        this.path = path;
    }

    public String getEnvName() {
        return envName;
    }

    public String getPath() {
        return path;
    }
}
