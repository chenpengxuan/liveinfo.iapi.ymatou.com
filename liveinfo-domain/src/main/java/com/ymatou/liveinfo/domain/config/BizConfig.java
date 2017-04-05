package com.ymatou.liveinfo.domain.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.stereotype.Component;

/**
 * Created by wangxudong on 2017/4/5.
 */
@Component
@DisconfFile(fileName = "biz.properties")
public class BizConfig {

    /**
     * 直播信息缓存数量
     */
    private int cacheLiveMaxsize;

    /**
     * 直播信息缓存时间
     */
    private int cacheLiveExpireTime;

    @DisconfFileItem(name = "cache.live.maxsize")
    public int getCacheLiveMaxsize() {
        return cacheLiveMaxsize;
    }

    public void setCacheLiveMaxsize(int cacheLiveMaxsize) {
        this.cacheLiveMaxsize = cacheLiveMaxsize;
    }

    @DisconfFileItem(name = "cache.live.expiretime")
    public int getCacheLiveExpireTime() {
        return cacheLiveExpireTime;
    }

    public void setCacheLiveExpireTime(int cacheLiveExpireTime) {
        this.cacheLiveExpireTime = cacheLiveExpireTime;
    }
}
