package com.ymatou.liveinfo.domain.service;

import com.ymatou.liveinfo.domain.model.ActivityInfo;
import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.domain.repository.LiveRepository;
import com.ymatou.liveinfo.facade.common.BizException;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by wangxudong on 2017/4/1.
 */
@Component
public class LiveService {

    @Resource
    private LiveRepository liveRepository;

    /**
     * 获取买手当前的直播信息
     * @param sellerId
     * @return
     */
    public ActivityInfo getSellerCurrentActivity(int sellerId){
        Live live = liveRepository.getSellerCurrentLive(sellerId);
        if(live == null){
            return  null;
        }
        ActivityInfo activityInfo = new ActivityInfo();
        try {
            BeanUtils.copyProperties(activityInfo, live);
            return activityInfo;
        } catch (Exception e) {
            throw new BizException("BeanUtils copyProperties Fail,with liveId:" + live.getActivityId());
        }
    }
}
