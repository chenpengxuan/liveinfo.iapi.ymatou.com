package com.ymatou.liveinfo.domain.cache;

import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.ymatou.liveinfo.facade.common.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangxudong on 2017/4/5.
 */
@Component
public class CacheFactory {

    private static Logger logger = LoggerFactory.getLogger(CacheFactory.class);

    private HashMap<String, LoadingCache> cacheMap = new HashMap<>();

    @Resource
    private TaskExecutor taskExecutor;

    /**
     * 获取到缓存
     *
     * @param cacheName
     * @param maximumSize
     * @param expireTime 过期时间，单位秒
     * @param cacheLoader
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> LoadingCache<K , V> getCache(String cacheName, int maximumSize, int expireTime, CacheLoader<K , V> cacheLoader){
        if(maximumSize < 1){
            maximumSize = 1;
            logger.error(String.format("invalid cache name:%s maximunSize:%d in biz.config.", cacheName, maximumSize));
        }

        if(cacheName.contains("_")){
            throw new BizException("invalid cacheName, can't contains: underline");
        }

        String cacheKey = String.format("%s_%d_%d", cacheName, maximumSize, expireTime);
        LoadingCache<K , V> cache = (LoadingCache<K , V>)cacheMap.get(cacheKey);
        if(cache == null) {
            synchronized (cacheMap){
                if(cacheMap.containsKey(cacheKey)){
                    cache = (LoadingCache<K , V>)cacheMap.get(cacheKey);
                }else{
                    cache = CacheBuilder.newBuilder()
                            .maximumSize(maximumSize)
                            .expireAfterWrite(expireTime, TimeUnit.SECONDS)
                            .recordStats()
                            .build(cacheLoader);
                    cacheMap.put(cacheKey, cache);

                    // 新建缓存之后，需要移除失效的缓存
                    removeInvalidCache(cacheName, cacheKey);
                }
            }
        }
        return cache;
    }

    /**
     * 获取到到缓存
     *
     * @param cacheLoader must implements CacheInfo
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> LoadingCache<K , V> getCache(CacheLoader<K , V> cacheLoader){
        CacheInfo cacheInfo = (CacheInfo) cacheLoader;
        if(cacheInfo == null){
            throw new BizException("cacheLoader must implements CacheInfo");
        }
        return getCache(cacheInfo.getCacheName(), cacheInfo.getMaximumSize(), cacheInfo.getExpireTime(), cacheLoader);
    }

    /**
     * 移除无效的缓存
     * @param cacheName
     * @param curCacheKey
     */
    private void removeInvalidCache(String cacheName, String curCacheKey){
        taskExecutor.execute(() ->{
            try {
                TimeUnit.MILLISECONDS.sleep(200); // 避免disconf切换缓存策略造成并发NPE问题
                List<String> removeKeys = new ArrayList<>();
                for (Map.Entry<String, LoadingCache> cacheEntry: cacheMap.entrySet()) {
                    String cacheKey = cacheEntry.getKey();
                    if(cacheKey.startsWith(cacheName) && !cacheKey.equals(curCacheKey)){
                        LoadingCache loadingCache = cacheMap.get(cacheKey);
                        loadingCache.cleanUp();
                        removeKeys.add(cacheKey);
                    }
                }
                synchronized (this) {
                    for (String key : removeKeys) {
                        if (cacheMap.containsKey(key)) {
                            cacheMap.remove(key);
                            logger.info("removeInvalidCache success remove cache: " + key);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("removeInvalidCache faild, with curCacheKey:" + curCacheKey, e);
            }
        });
    }

    /**
     * 获取缓存状态
     * @return
     */
    public String stats(){
        List<String> cacheStatsList = new ArrayList<>();
        for (Map.Entry<String, LoadingCache> cacheEntry: cacheMap.entrySet()) {
            CacheStats stats = cacheEntry.getValue().stats();
            cacheStatsList.add(cacheEntry.getKey() + ":" + stats.toString());
        }
        String json = JSONObject.toJSONString(cacheStatsList);

        return json;
    }
}
