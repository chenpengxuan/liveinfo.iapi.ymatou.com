package com.ymatou.liveinfo.domain.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangxudong on 2017/4/5.
 */
@Component
public class CacheFactory {

    private HashMap<String, LoadingCache> cacheMap = new HashMap<>();

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
                            .build(cacheLoader);
                    cacheMap.put(cacheKey, cache);
                }
            }
        }
        return cache;
    }
}
