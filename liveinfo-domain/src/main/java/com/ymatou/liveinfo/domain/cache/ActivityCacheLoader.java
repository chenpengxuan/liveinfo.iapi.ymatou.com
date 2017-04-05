package com.ymatou.liveinfo.domain.cache;

import com.google.common.cache.CacheLoader;
import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.facade.common.BizException;
import com.ymatou.liveinfo.facade.model.ActivityInfo;
import com.ymatou.liveinfo.domain.repository.LiveRepository;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * Created by wangxudong on 2017/4/5.
 */
@Component
public class ActivityCacheLoader extends CacheLoader<Integer, Optional<ActivityInfo>> {

    @Resource
    private LiveRepository liveRepository;

    /**
     * Computes or retrieves the value corresponding to {@code key}.
     *
     * @param key the non-null key whose value should be loaded
     * @return the value associated with {@code key}; <b>must not be null</b>
     * @throws Exception            if unable to load the result
     * @throws InterruptedException if this method is interrupted. {@code InterruptedException} is
     *                              treated like any other {@code Exception} in all respects except that, when it is caught,
     *                              the thread's interrupt status is set
     */
    @Override
    public Optional<ActivityInfo> load(Integer key) throws Exception {
        ActivityInfo activityInfo = getByRepository(key);

        return  Optional.ofNullable(activityInfo);
    }

    public ActivityInfo getByRepository(Integer sellerId){
        Live live = liveRepository.getSellerCurrentLive(sellerId);
        if(live == null){
            return  null;
        }

        ActivityInfo activityInfo = new ActivityInfo();
        try {
            BeanUtils.copyProperties(activityInfo, live);
            return activityInfo;
        } catch (Exception e) {
            throw new BizException("BeanUtils copyProperties Fail,with liveId:" + live.getActivityId(), e);
        }
    }
}
