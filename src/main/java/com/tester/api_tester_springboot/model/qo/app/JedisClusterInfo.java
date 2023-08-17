package com.tester.api_tester_springboot.model.qo.app;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 王海虹
 * @create: 2022-09-23 16:25
 */
@Data
public class JedisClusterInfo {
    private List<JedisClusterHostAndPort> nameList;
    private List<JedisClusterHostAndPort> galleryFactor;
    private List<JedisClusterHostAndPort> galleryGather;
    private List<JedisClusterHostAndPort> galleryReceive;
    private List<JedisClusterHostAndPort> bjRedis;
}
