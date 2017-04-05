package com.ymatou.liveinfo.domain.service;

import com.google.common.cache.LoadingCache;
import com.ymatou.liveinfo.domain.cache.ActivityCacheLoader;
import com.ymatou.liveinfo.domain.cache.CacheFactory;
import com.ymatou.liveinfo.domain.config.BizConfig;
import com.ymatou.liveinfo.domain.repository.LiveRepository;
import com.ymatou.liveinfo.facade.common.BizException;
import com.ymatou.liveinfo.facade.model.ActivityInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * Created by wangxudong on 2017/4/1.
 */
@Component
public class LiveService {

    private static Logger logger = LoggerFactory.getLogger(LiveService.class);

    @Resource
    private LiveRepository liveRepository;

    @Resource
    private CacheFactory cacheFactory;

    @Resource
    private ActivityCacheLoader activityCacheLoader;

    @Resource
    private BizConfig bizConfig;

    /**
     * 获取买手当前的直播信息
     * @param sellerId
     * @return
     */
    public ActivityInfo getSellerCurrentActivity(int sellerId){
        LoadingCache<Integer, ActivityInfo> activityCache = cacheFactory.getCache(
                 "ActivityCacheBySellerId"
                , bizConfig.getCacheLiveMaxsize()
                , bizConfig.getCacheLiveExpireTime()
                , activityCacheLoader );
        try {
            return  activityCache.get(sellerId);
        } catch (ExecutionException e) {
            throw new BizException("get active info from cache faild,with sellerId:" + sellerId, e);
        }
    }
}
