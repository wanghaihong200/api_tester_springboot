package com.tester.api_tester_springboot.core.config;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.tester.api_tester_springboot.util.RcLoggerUtils;
import com.tester.api_tester_springboot.util.http.UnirestUtils;
import com.tester.api_tester_springboot.util.XmlUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Configuration
@Log4j2
@PropertySource("classpath:redis.properties")
public class RedissonConfig {

    @Value("${bjRedisAddressUrl}")
    private String redisAddressUrl;
    @Value("${bjRedisUserName}")
    private String redisUserName;
    @Value("${bjRedisPassword}")
    private String redisPassword;

    @Value("${namelistRedisUserName}")
    private String namelistRedisUserName;

    @Value("${namelistRedisPassword}")
    private String namelistRedisPassword;

    @Value("${namelistRedisAddressUrl}")
    private String namelistRedisAddressUrl;

    @Autowired
    private Environment environment;

    @Bean("redissonClient")
    public RedissonClient redissonClient() {
        String property = environment.getProperty("redission.active");
        if ("stage".equals(property)){
            return BuildNullClient();
        }
        //redisson版本是3.5，集群的ip前面要加上“redis://”，不然会报错，3.2版本可不加
        List<String> clusterNodes = new ArrayList<>();
        String[] address = getAddress(this.redisAddressUrl,this.redisUserName,this.redisPassword);
        try {
            RcLoggerUtils.record(log, "redissonClient", System.currentTimeMillis(), "start","",  JSONObject.toJSON(address),  JSONObject.toJSON(address));
            if (ObjectUtils.isEmpty(address)){
                throw new RuntimeException("redission,初始化加载异常");
            }
            for (int i = 0; i < address.length; i++) {
                clusterNodes.add("redis://" + address[i]);
            }
            Config config = new Config();
            //config.setCodec(new JsonJacksonCodec());
            config.useClusterServers() //这是用的集群server
                    .setScanInterval(2000) //设置集群状态扫描时间
                    .addNodeAddress(clusterNodes.toArray(new String[clusterNodes.size()]));
            return  Redisson.create(config);
        }catch (Exception e){
            RcLoggerUtils.systemError(log, "redissonClient", System.currentTimeMillis(), address,"",  500,  e);
        }
        return BuildNullClient();
    }

    private RedissonClient BuildNullClient() {
        return new RedissonClient() {
            @Override
            public <K, V> RStream<K, V> getStream(String s) {
                return null;
            }

            @Override
            public <K, V> RStream<K, V> getStream(String s, Codec codec) {
                return null;
            }

            @Override
            public RRateLimiter getRateLimiter(String s) {
                return null;
            }

            @Override
            public RBinaryStream getBinaryStream(String s) {
                return null;
            }

            @Override
            public <V> RGeo<V> getGeo(String s) {
                return null;
            }

            @Override
            public <V> RGeo<V> getGeo(String s, Codec codec) {
                return null;
            }

            @Override
            public <V> RSetCache<V> getSetCache(String s) {
                return null;
            }

            @Override
            public <V> RSetCache<V> getSetCache(String s, Codec codec) {
                return null;
            }

            @Override
            public <K, V> RMapCache<K, V> getMapCache(String s, Codec codec) {
                return null;
            }

            @Override
            public <K, V> RMapCache<K, V> getMapCache(String s, Codec codec, MapOptions<K, V> mapOptions) {
                return null;
            }

            @Override
            public <K, V> RMapCache<K, V> getMapCache(String s) {
                return null;
            }

            @Override
            public <K, V> RMapCache<K, V> getMapCache(String s, MapOptions<K, V> mapOptions) {
                return null;
            }

            @Override
            public <V> RBucket<V> getBucket(String s) {
                return null;
            }

            @Override
            public <V> RBucket<V> getBucket(String s, Codec codec) {
                return null;
            }

            @Override
            public RBuckets getBuckets() {
                return null;
            }

            @Override
            public RBuckets getBuckets(Codec codec) {
                return null;
            }

            @Override
            public <V> RHyperLogLog<V> getHyperLogLog(String s) {
                return null;
            }

            @Override
            public <V> RHyperLogLog<V> getHyperLogLog(String s, Codec codec) {
                return null;
            }

            @Override
            public <V> RList<V> getList(String s) {
                return null;
            }

            @Override
            public <V> RList<V> getList(String s, Codec codec) {
                return null;
            }

            @Override
            public <K, V> RListMultimap<K, V> getListMultimap(String s) {
                return null;
            }

            @Override
            public <K, V> RListMultimap<K, V> getListMultimap(String s, Codec codec) {
                return null;
            }

            @Override
            public <K, V> RListMultimapCache<K, V> getListMultimapCache(String s) {
                return null;
            }

            @Override
            public <K, V> RListMultimapCache<K, V> getListMultimapCache(String s, Codec codec) {
                return null;
            }

            @Override
            public <K, V> RLocalCachedMap<K, V> getLocalCachedMap(String s, LocalCachedMapOptions<K, V> localCachedMapOptions) {
                return null;
            }

            @Override
            public <K, V> RLocalCachedMap<K, V> getLocalCachedMap(String s, Codec codec, LocalCachedMapOptions<K, V> localCachedMapOptions) {
                return null;
            }

            @Override
            public <K, V> RMap<K, V> getMap(String s) {
                return null;
            }

            @Override
            public <K, V> RMap<K, V> getMap(String s, MapOptions<K, V> mapOptions) {
                return null;
            }

            @Override
            public <K, V> RMap<K, V> getMap(String s, Codec codec) {
                return null;
            }

            @Override
            public <K, V> RMap<K, V> getMap(String s, Codec codec, MapOptions<K, V> mapOptions) {
                return null;
            }

            @Override
            public <K, V> RSetMultimap<K, V> getSetMultimap(String s) {
                return null;
            }

            @Override
            public <K, V> RSetMultimap<K, V> getSetMultimap(String s, Codec codec) {
                return null;
            }

            @Override
            public <K, V> RSetMultimapCache<K, V> getSetMultimapCache(String s) {
                return null;
            }

            @Override
            public <K, V> RSetMultimapCache<K, V> getSetMultimapCache(String s, Codec codec) {
                return null;
            }

            @Override
            public RSemaphore getSemaphore(String s) {
                return null;
            }

            @Override
            public RPermitExpirableSemaphore getPermitExpirableSemaphore(String s) {
                return null;
            }

            @Override
            public RLock getLock(String s) {
                return null;
            }

            @Override
            public RLock getFairLock(String s) {
                return null;
            }

            @Override
            public RReadWriteLock getReadWriteLock(String s) {
                return null;
            }

            @Override
            public <V> RSet<V> getSet(String s) {
                return null;
            }

            @Override
            public <V> RSet<V> getSet(String s, Codec codec) {
                return null;
            }

            @Override
            public <V> RSortedSet<V> getSortedSet(String s) {
                return null;
            }

            @Override
            public <V> RSortedSet<V> getSortedSet(String s, Codec codec) {
                return null;
            }

            @Override
            public <V> RScoredSortedSet<V> getScoredSortedSet(String s) {
                return null;
            }

            @Override
            public <V> RScoredSortedSet<V> getScoredSortedSet(String s, Codec codec) {
                return null;
            }

            @Override
            public RLexSortedSet getLexSortedSet(String s) {
                return null;
            }

            @Override
            public RTopic getTopic(String s) {
                return null;
            }

            @Override
            public RTopic getTopic(String s, Codec codec) {
                return null;
            }

            @Override
            public RPatternTopic getPatternTopic(String s) {
                return null;
            }

            @Override
            public RPatternTopic getPatternTopic(String s, Codec codec) {
                return null;
            }

            @Override
            public <V> RQueue<V> getQueue(String s) {
                return null;
            }

            @Override
            public <V> RDelayedQueue<V> getDelayedQueue(RQueue<V> rQueue) {
                return null;
            }

            @Override
            public <V> RQueue<V> getQueue(String s, Codec codec) {
                return null;
            }

            @Override
            public <V> RPriorityQueue<V> getPriorityQueue(String s) {
                return null;
            }

            @Override
            public <V> RPriorityQueue<V> getPriorityQueue(String s, Codec codec) {
                return null;
            }

            @Override
            public <V> RPriorityBlockingQueue<V> getPriorityBlockingQueue(String s) {
                return null;
            }

            @Override
            public <V> RPriorityBlockingQueue<V> getPriorityBlockingQueue(String s, Codec codec) {
                return null;
            }

            @Override
            public <V> RPriorityBlockingDeque<V> getPriorityBlockingDeque(String s) {
                return null;
            }

            @Override
            public <V> RPriorityBlockingDeque<V> getPriorityBlockingDeque(String s, Codec codec) {
                return null;
            }

            @Override
            public <V> RPriorityDeque<V> getPriorityDeque(String s) {
                return null;
            }

            @Override
            public <V> RPriorityDeque<V> getPriorityDeque(String s, Codec codec) {
                return null;
            }

            @Override
            public <V> RBlockingQueue<V> getBlockingQueue(String s) {
                return null;
            }

            @Override
            public <V> RBlockingQueue<V> getBlockingQueue(String s, Codec codec) {
                return null;
            }

            @Override
            public <V> RBoundedBlockingQueue<V> getBoundedBlockingQueue(String s) {
                return null;
            }

            @Override
            public <V> RBoundedBlockingQueue<V> getBoundedBlockingQueue(String s, Codec codec) {
                return null;
            }

            @Override
            public <V> RDeque<V> getDeque(String s) {
                return null;
            }

            @Override
            public <V> RDeque<V> getDeque(String s, Codec codec) {
                return null;
            }

            @Override
            public <V> RBlockingDeque<V> getBlockingDeque(String s) {
                return null;
            }

            @Override
            public <V> RBlockingDeque<V> getBlockingDeque(String s, Codec codec) {
                return null;
            }

            @Override
            public RAtomicLong getAtomicLong(String s) {
                return null;
            }

            @Override
            public RAtomicDouble getAtomicDouble(String s) {
                return null;
            }

            @Override
            public RLongAdder getLongAdder(String s) {
                return null;
            }

            @Override
            public RDoubleAdder getDoubleAdder(String s) {
                return null;
            }

            @Override
            public RCountDownLatch getCountDownLatch(String s) {
                return null;
            }

            @Override
            public RBitSet getBitSet(String s) {
                return null;
            }

            @Override
            public <V> RBloomFilter<V> getBloomFilter(String s) {
                return new RBloomFilter<V>() {
                    @Override
                    public boolean add(V v) {
                        return false;
                    }

                    @Override
                    public boolean contains(V v) {
                        return false;
                    }

                    @Override
                    public boolean tryInit(long l, double v) {
                        return false;
                    }

                    @Override
                    public long getExpectedInsertions() {
                        return 0;
                    }

                    @Override
                    public double getFalseProbability() {
                        return 0;
                    }

                    @Override
                    public long getSize() {
                        return 0;
                    }

                    @Override
                    public int getHashIterations() {
                        return 0;
                    }

                    @Override
                    public long count() {
                        return 0;
                    }

                    @Override
                    public boolean expire(long l, TimeUnit timeUnit) {
                        return false;
                    }

                    @Override
                    public boolean expireAt(long l) {
                        return false;
                    }

                    @Override
                    public boolean expireAt(Date date) {
                        return false;
                    }

                    @Override
                    public boolean clearExpire() {
                        return false;
                    }

                    @Override
                    public long remainTimeToLive() {
                        return 0;
                    }

                    @Override
                    public RFuture<Boolean> expireAsync(long l, TimeUnit timeUnit) {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> expireAtAsync(Date date) {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> expireAtAsync(long l) {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> clearExpireAsync() {
                        return null;
                    }

                    @Override
                    public RFuture<Long> remainTimeToLiveAsync() {
                        return null;
                    }

                    @Override
                    public long sizeInMemory() {
                        return 0;
                    }

                    @Override
                    public void restore(byte[] bytes) {

                    }

                    @Override
                    public void restore(byte[] bytes, long l, TimeUnit timeUnit) {

                    }

                    @Override
                    public void restoreAndReplace(byte[] bytes) {

                    }

                    @Override
                    public void restoreAndReplace(byte[] bytes, long l, TimeUnit timeUnit) {

                    }

                    @Override
                    public byte[] dump() {
                        return new byte[0];
                    }

                    @Override
                    public boolean touch() {
                        return false;
                    }

                    @Override
                    public void migrate(String s, int i, int i1, long l) {

                    }

                    @Override
                    public void copy(String s, int i, int i1, long l) {

                    }

                    @Override
                    public boolean move(int i) {
                        return false;
                    }

                    @Override
                    public String getName() {
                        return null;
                    }

                    @Override
                    public boolean delete() {
                        return false;
                    }

                    @Override
                    public boolean unlink() {
                        return false;
                    }

                    @Override
                    public void rename(String s) {

                    }

                    @Override
                    public boolean renamenx(String s) {
                        return false;
                    }

                    @Override
                    public boolean isExists() {
                        return false;
                    }

                    @Override
                    public Codec getCodec() {
                        return null;
                    }

                    @Override
                    public RFuture<Long> sizeInMemoryAsync() {
                        return null;
                    }

                    @Override
                    public RFuture<Void> restoreAsync(byte[] bytes) {
                        return null;
                    }

                    @Override
                    public RFuture<Void> restoreAsync(byte[] bytes, long l, TimeUnit timeUnit) {
                        return null;
                    }

                    @Override
                    public RFuture<Void> restoreAndReplaceAsync(byte[] bytes) {
                        return null;
                    }

                    @Override
                    public RFuture<Void> restoreAndReplaceAsync(byte[] bytes, long l, TimeUnit timeUnit) {
                        return null;
                    }

                    @Override
                    public RFuture<byte[]> dumpAsync() {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> touchAsync() {
                        return null;
                    }

                    @Override
                    public RFuture<Void> migrateAsync(String s, int i, int i1, long l) {
                        return null;
                    }

                    @Override
                    public RFuture<Void> copyAsync(String s, int i, int i1, long l) {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> moveAsync(int i) {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> deleteAsync() {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> unlinkAsync() {
                        return null;
                    }

                    @Override
                    public RFuture<Void> renameAsync(String s) {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> renamenxAsync(String s) {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> isExistsAsync() {
                        return null;
                    }
                };
            }

            @Override
            public <V> RBloomFilter<V> getBloomFilter(String s, Codec codec) {
                return new RBloomFilter<V>() {
                    @Override
                    public boolean add(V v) {
                        return false;
                    }

                    @Override
                    public boolean contains(V v) {
                        return false;
                    }

                    @Override
                    public boolean tryInit(long l, double v) {
                        return false;
                    }

                    @Override
                    public long getExpectedInsertions() {
                        return 0;
                    }

                    @Override
                    public double getFalseProbability() {
                        return 0;
                    }

                    @Override
                    public long getSize() {
                        return 0;
                    }

                    @Override
                    public int getHashIterations() {
                        return 0;
                    }

                    @Override
                    public long count() {
                        return 0;
                    }

                    @Override
                    public boolean expire(long l, TimeUnit timeUnit) {
                        return false;
                    }

                    @Override
                    public boolean expireAt(long l) {
                        return false;
                    }

                    @Override
                    public boolean expireAt(Date date) {
                        return false;
                    }

                    @Override
                    public boolean clearExpire() {
                        return false;
                    }

                    @Override
                    public long remainTimeToLive() {
                        return 0;
                    }

                    @Override
                    public RFuture<Boolean> expireAsync(long l, TimeUnit timeUnit) {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> expireAtAsync(Date date) {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> expireAtAsync(long l) {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> clearExpireAsync() {
                        return null;
                    }

                    @Override
                    public RFuture<Long> remainTimeToLiveAsync() {
                        return null;
                    }

                    @Override
                    public long sizeInMemory() {
                        return 0;
                    }

                    @Override
                    public void restore(byte[] bytes) {

                    }

                    @Override
                    public void restore(byte[] bytes, long l, TimeUnit timeUnit) {

                    }

                    @Override
                    public void restoreAndReplace(byte[] bytes) {

                    }

                    @Override
                    public void restoreAndReplace(byte[] bytes, long l, TimeUnit timeUnit) {

                    }

                    @Override
                    public byte[] dump() {
                        return new byte[0];
                    }

                    @Override
                    public boolean touch() {
                        return false;
                    }

                    @Override
                    public void migrate(String s, int i, int i1, long l) {

                    }

                    @Override
                    public void copy(String s, int i, int i1, long l) {

                    }

                    @Override
                    public boolean move(int i) {
                        return false;
                    }

                    @Override
                    public String getName() {
                        return null;
                    }

                    @Override
                    public boolean delete() {
                        return false;
                    }

                    @Override
                    public boolean unlink() {
                        return false;
                    }

                    @Override
                    public void rename(String s) {

                    }

                    @Override
                    public boolean renamenx(String s) {
                        return false;
                    }

                    @Override
                    public boolean isExists() {
                        return false;
                    }

                    @Override
                    public Codec getCodec() {
                        return null;
                    }

                    @Override
                    public RFuture<Long> sizeInMemoryAsync() {
                        return null;
                    }

                    @Override
                    public RFuture<Void> restoreAsync(byte[] bytes) {
                        return null;
                    }

                    @Override
                    public RFuture<Void> restoreAsync(byte[] bytes, long l, TimeUnit timeUnit) {
                        return null;
                    }

                    @Override
                    public RFuture<Void> restoreAndReplaceAsync(byte[] bytes) {
                        return null;
                    }

                    @Override
                    public RFuture<Void> restoreAndReplaceAsync(byte[] bytes, long l, TimeUnit timeUnit) {
                        return null;
                    }

                    @Override
                    public RFuture<byte[]> dumpAsync() {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> touchAsync() {
                        return null;
                    }

                    @Override
                    public RFuture<Void> migrateAsync(String s, int i, int i1, long l) {
                        return null;
                    }

                    @Override
                    public RFuture<Void> copyAsync(String s, int i, int i1, long l) {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> moveAsync(int i) {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> deleteAsync() {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> unlinkAsync() {
                        return null;
                    }

                    @Override
                    public RFuture<Void> renameAsync(String s) {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> renamenxAsync(String s) {
                        return null;
                    }

                    @Override
                    public RFuture<Boolean> isExistsAsync() {
                        return null;
                    }
                };
            }

            @Override
            public RScript getScript() {
                return null;
            }

            @Override
            public RScript getScript(Codec codec) {
                return null;
            }

            @Override
            public RScheduledExecutorService getExecutorService(String s) {
                return null;
            }

            @Override
            public RScheduledExecutorService getExecutorService(String s, ExecutorOptions executorOptions) {
                return null;
            }

            @Override
            public RScheduledExecutorService getExecutorService(Codec codec, String s) {
                return null;
            }

            @Override
            public RScheduledExecutorService getExecutorService(String s, Codec codec) {
                return null;
            }

            @Override
            public RScheduledExecutorService getExecutorService(String s, Codec codec, ExecutorOptions executorOptions) {
                return null;
            }

            @Override
            public RRemoteService getRemoteService() {
                return null;
            }

            @Override
            public RRemoteService getRemoteService(Codec codec) {
                return null;
            }

            @Override
            public RRemoteService getRemoteService(String s) {
                return null;
            }

            @Override
            public RRemoteService getRemoteService(String s, Codec codec) {
                return null;
            }

            @Override
            public RTransaction createTransaction(TransactionOptions transactionOptions) {
                return null;
            }

            @Override
            public RBatch createBatch(BatchOptions batchOptions) {
                return null;
            }

            @Override
            public RBatch createBatch() {
                return null;
            }

            @Override
            public RKeys getKeys() {
                return null;
            }

            @Override
            public RLiveObjectService getLiveObjectService() {
                return null;
            }

            @Override
            public void shutdown() {

            }

            @Override
            public void shutdown(long l, long l1, TimeUnit timeUnit) {

            }

            @Override
            public Config getConfig() {
                return null;
            }

            @Override
            public NodesGroup<Node> getNodesGroup() {
                return null;
            }

            @Override
            public ClusterNodesGroup getClusterNodesGroup() {
                return null;
            }

            @Override
            public boolean isShutdown() {
                return false;
            }

            @Override
            public boolean isShuttingDown() {
                return false;
            }
        };
    }

    @Bean("redissonNameList")
    public RedissonClient redissonNameList() {
        String property = environment.getProperty("redission.active");
        if ("stage".equals(property)){
            return BuildNullClient();
        }
        //redisson版本是3.5，集群的ip前面要加上“redis://”，不然会报错，3.2版本可不加
        List<String> clusterNodes = new ArrayList<>();
        String[] address = getAddress(this.namelistRedisAddressUrl,this.namelistRedisUserName,this.namelistRedisPassword);
        try {
            if (ObjectUtils.isEmpty(address)){
                throw new RuntimeException("redission,初始化加载异常");
            }
            RcLoggerUtils.record(log, "redissonNameList", System.currentTimeMillis(), "start","",  JSONObject.toJSON(address),  JSONObject.toJSON(address));

            for (int i = 0; i < address.length; i++) {
                clusterNodes.add("redis://" + address[i]);
            }
            Config config = new Config();
            //config.setCodec(new JsonJacksonCodec());
            config.useClusterServers() //这是用的集群server
                    .setScanInterval(2000) //设置集群状态扫描时间
                    .addNodeAddress(clusterNodes.toArray(new String[clusterNodes.size()]));
            return  Redisson.create(config);
        }catch (Exception e){
            RcLoggerUtils.systemError(log, "redissonNameList", System.currentTimeMillis(), address,"",  500,  e);
        }
        return BuildNullClient();
    }


    protected String[] getAddress(String redisAddressUrl,String redisUserName,String redisPassword) {
        String response = "";
        List<String> ipList = new ArrayList<>();
        String[] ips = null;

        Map<String, String> basicAuth = new HashMap<>();
        basicAuth.put("username", redisUserName);
        basicAuth.put("password", redisPassword);

        try {
            response = UnirestUtils.getBasicAuth(redisAddressUrl, basicAuth);
            if (StringUtils.isNotBlank(response)) {
                JSONObject domJson = JSON.parseObject(XmlUtils.xmlToJson(response));
                JSONArray redisArray = domJson.getJSONObject("tcbase.cache")
                        .getJSONObject("cache").getJSONArray("redis");
                if (!redisArray.isEmpty()) {
                    for (int i = 0; i < redisArray.size(); i++) {
                        Map<String, String> ipMap = new HashMap<>();
                        String ip = redisArray.getJSONObject(i).getString("ip");
                        if (StrUtil.isNotBlank(ip)) {
                            ipList.add(ip);
                        }
                    }
                }
            }

            ips = (String[]) ipList.toArray(new String[ipList.size()]);
            if (ArrayUtil.isNotEmpty(ips)) {
                Arrays.sort(ips);
            }
        } catch (Exception var12) {
            log.error("getAddress 获取redis连接失败,redisAddressUrl=" + this.redisAddressUrl, var12);
        }

        return ips;
    }



}
