package com.ymatou.liveinfo.domain.utils;

import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.facade.model.ActivityComplexInfo;
import com.ymatou.liveinfo.facade.model.ActivityInfo;

/**
 * Created by wangxudong on 2017/5/12.
 */
public class EntityUtils {

    /**
     * 直播实体转换
     * @param to
     * @param from
     */
    public static void toActivity(ActivityInfo to, Live from){
        to.setActivityId(from.getActivityId());
        to.setTitle(from.getTitle());
        to.setVideoCover(from.getVideoCover());
        to.setVideoUrl(from.getVideoUrl());
        to.setAction(from.getAction());
        to.setActivityContent(from.getActivityContent());
        to.setActivityName(from.getActivityName());
        to.setActivityPicture(from.getActivityPicture());
        to.setAddTime(from.getAddTime());
        to.setCountry(from.getCountry());
        to.setEndTime(from.getEndTime());
        to.setSellerId(from.getSellerId());
        to.setShopAddress(from.getShopAddress());
        to.setStartTime(from.getStartTime());
        to.setAppConfirmed(from.getAppConfirmed());
        to.setLiveCover(from.getLiveCover());
    }

}
