package com.tester.api_tester_springboot.util;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.client.codec.StringCodec;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: 王海虹
 * @create: 2023-05-11 11:44
 */
@Slf4j
public class RedisUtils {
    
    private static final long DEFAULT_EXPIRED = 5*60L;

    /**
     * 读取缓存
     *
     * @param key 缓存key
     * @param <T>
     * @return 缓存返回值
     */
    public static <T> T get(String key, RedissonClient redisson) {
        RBucket<T> bucket = redisson.getBucket( key);
        return bucket.get();
    }

    /**
     * 以string的方式读取缓存
     *
     * @param key 缓存key
     * @return 缓存返回值
     */
    public static String getString(String key, RedissonClient redisson) {
        RBucket<String> bucket = redisson.getBucket(key, StringCodec.INSTANCE);
        return bucket.get();
    }

    /**
     * 设置缓存（注：redisson会自动选择序列化反序列化方式）,默认过期时间5分钟
     *
     * @param key   缓存key
     * @param value 缓存值
     * @param <T>
     */
    public static <T> void put(String key, T value, RedissonClient redisson) {
        RBucket<T> bucket = redisson.getBucket(key);
        bucket.set(value, DEFAULT_EXPIRED, TimeUnit.SECONDS);
    }

    /**
     * 以string的方式设置缓存
     *
     * @param key
     * @param value
     */
    public static void putString(String key, String value,RedissonClient redisson) {
        RBucket<String> bucket = redisson.getBucket( key, StringCodec.INSTANCE);
        bucket.set(value, DEFAULT_EXPIRED, TimeUnit.SECONDS);
    }

    /**
     * 以string的方式保存缓存（与其他应用共用redis时需要使用该函数）
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param expired 缓存过期时间
     */
    public void putString(String key, String value, long expired, RedissonClient redisson) {
        RBucket<String> bucket = redisson.getBucket(key, StringCodec.INSTANCE);
        bucket.set(value, expired <= 0 ? DEFAULT_EXPIRED : expired, TimeUnit.SECONDS);
    }

    /**
     * 如果不存在则写入缓存（string方式，不带有redisson的格式信息）
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param expired 缓存过期时间
     */
    public boolean putStringIfAbsent(String key, String value, long expired, RedissonClient redisson) {
        RBucket<String> bucket = redisson.getBucket(key, StringCodec.INSTANCE);
        return bucket.trySet(value, expired <= 0 ? DEFAULT_EXPIRED : expired, TimeUnit.SECONDS);
    }

    /**
     * 如果不存在则写入缓存（string方式，不带有redisson的格式信息）（不带过期时间，永久保存）
     *
     * @param key     缓存key
     * @param value   缓存值
     */
    public boolean putStringIfAbsent(String key, String value, RedissonClient redisson) {
        RBucket<String> bucket = redisson.getBucket( key, StringCodec.INSTANCE);
        return bucket.trySet(value);
    }

    /**
     * 设置缓存
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param expired 缓存过期时间
     * @param <T>     类型
     */
    public <T> void put(String key, T value, long expired, RedissonClient redisson) {
        RBucket<T> bucket = redisson.getBucket(key);
        bucket.set(value, expired <= 0 ? DEFAULT_EXPIRED : expired, TimeUnit.SECONDS);
    }

    /**
     * 移除缓存
     *
     * @param key
     */
    public void remove(String key, RedissonClient redisson) {
        redisson.getBucket( key).delete();
    }

    /**
     * 判断缓存是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key, RedissonClient redisson) {
        return redisson.getBucket( key).isExists();
    }


    /**
     * 暴露redisson的RList对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RList<T> getRedisList(String key, RedissonClient redisson) {
        return redisson.getList( key);
    }

    /**
     * 暴露redisson的RMapCache对象
     *
     * @param key
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> RMapCache<K, V> getRedisMap(String key, RedissonClient redisson) {
        return redisson.getMapCache(key);
    }

    /**
     * 暴露redisson的RSET对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RSet<T> getRedisSet(String key, RedissonClient redisson) {
        return redisson.getSet( key);
    }


    /**
     * 暴露redisson的RScoredSortedSet对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RScoredSortedSet<T> getRedisScoredSortedSet(String key, RedissonClient redisson) {
        return redisson.getScoredSortedSet(key);
    }

    /**
     * 暴露redisson的Lock对象
     *
     * @param key
     * @return
     */
    public RLock getRedisLock(String key, RedissonClient redisson) {
        return redisson.getLock( key);
    }


    @PreDestroy
    public void close(RedissonClient redisson) {
        try {
            if (redisson != null) {
                redisson.shutdown();
            }
        } catch (Exception ex) {
            log.error( ex.getMessage(), ex);
        }
    }
}
