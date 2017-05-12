package com.ymatou.liveinfo.domain.utils;

import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.facade.common.BizException;
import com.ymatou.liveinfo.facade.model.ActivityComplexInfo;
import com.ymatou.liveinfo.facade.model.ActivityInfo;
import com.ymatou.liveinfo.facade.model.ActivityInfoForBack;
import com.ymatou.liveinfo.infrastructure.db.model.ActivityPo;
import org.apache.commons.beanutils.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by gejianhua on 2017/4/11.
 * 类型映射
 */
public class MappingUtils {

    public static ActivityInfo toActivityInfo(Live live) {
        if (live == null) {
            return null;
        }
        ActivityInfo activityInfo = new ActivityInfo();
        try {

            EntityUtils.toActivity(activityInfo, live);
            //BeanUtils.copyProperties(activityInfo, live);
        } catch (Exception e) {
            throw new BizException("BeanUtils copyProperties Fail,with liveId:" + live.getActivityId(), e);
        }
        return activityInfo;
    }


    public static ActivityComplexInfo toActivityComplexInfo(Live live) {
        if (live == null) {
            return null;
        }
        ActivityComplexInfo activityComplexInfo = new ActivityComplexInfo();
        try {

            EntityUtils.toActivity(activityComplexInfo, live);
            //BeanUtils.copyProperties(activityComplexInfo, live);
        } catch (Exception e) {
            throw new BizException("BeanUtils copyProperties Fail,with liveId:" + live.getActivityId(), e);
        }
        return activityComplexInfo;
    }

    public static List<ActivityInfo> toActivityInfoes(List<Live> lives) {
        List<ActivityInfo> activityInfos = new ArrayList<>();
        if (lives == null) {
            return activityInfos;
        }

        for (Live live : lives) {
            activityInfos.add(toActivityInfo(live));
        }
        return activityInfos;
    }


    public static ActivityInfoForBack toActivityInfo(ActivityPo activityPo) {
        if (activityPo == null) {
            return null;
        }
        ActivityInfoForBack activityInfo = new ActivityInfoForBack();
        activityInfo.setSellerId(activityPo.getSellerId());
        activityInfo.setActivityId(activityPo.getActivityId());
        activityInfo.setAction(activityPo.getAction());
        activityInfo.setActivityContent(activityPo.getContent());
        activityInfo.setActivityName(activityPo.getName());
        activityInfo.setActivityPicture(activityPo.getPicUrl());
        activityInfo.setAddTime(activityPo.getAddTime());
        activityInfo.setAppConfirmed(activityPo.getAppConfirmed());
        activityInfo.setShopAddress(activityPo.getPosition());
        activityInfo.setStartTime(activityPo.getBeginTime());
        activityInfo.setTitle(activityPo.getTitle());
        activityInfo.setVideoCover(activityPo.getVideoCover());
        activityInfo.setVideoUrl(activityPo.getVideoUrl());
        activityInfo.setEndTime(activityPo.getEndTime());
        activityInfo.setCountryId(activityPo.getCountryId());
        activityInfo.setOffLineReasons(activityPo.getOffLineReasons());
        activityInfo.setOriginalActivityId(activityPo.getOriginalActivityId());
        activityInfo.setReplay(Optional.ofNullable(activityPo.getIsReplay()).orElse(false));
        activityInfo.setActivityLiveId(Optional.ofNullable(activityPo.getLiveActivityId()).orElse(0));
        activityInfo.setDeliverType(Optional.ofNullable(activityPo.getDeliveryType()).orElse(0));
        activityInfo.setSort(Optional.ofNullable(activityPo.getSort()).orElse(0));
        activityInfo.setLatlng(activityPo.getLatlng());
        activityInfo.setSeller(activityPo.getSellerName());

        return activityInfo;
    }

    public static List<ActivityInfoForBack> toActivityInfos(List<ActivityPo> activityPos){
        List<ActivityInfoForBack> activityInfos = new ArrayList<>();

        if(activityPos == null || activityPos.size() <= 0){
            return activityInfos;
        }

        activityPos.forEach((activityPo) -> {
            activityInfos.add(toActivityInfo(activityPo));
        });

        return activityInfos;
    }

}




















































