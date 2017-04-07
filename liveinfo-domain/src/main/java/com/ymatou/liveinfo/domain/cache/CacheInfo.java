package com.ymatou.liveinfo.domain.cache;

/**
 * Created by wangxudong on 2017/4/7.
 */
public interface CacheInfo {

    /**
     * 获取到缓存名称
     * @return
     */
    String getCacheName();

    /**
     * 获取到缓存最大数量
     * @return
     */
    int getMaximumSize();

    /**
     * 获取到缓存过期时间
     * @return
     */
    int getExpireTime();
}
