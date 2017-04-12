package com.ymatou.liveinfo.domain.utils;

import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.facade.common.BizException;
import com.ymatou.liveinfo.facade.model.ActivityComplexInfo;
import com.ymatou.liveinfo.facade.model.ActivityInfo;
import org.apache.commons.beanutils.BeanUtils;

import java.util.ArrayList;
import java.util.List;

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
            BeanUtils.copyProperties(activityInfo, live);
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
            BeanUtils.copyProperties(activityComplexInfo, live);
        } catch (Exception e) {
            throw new BizException("BeanUtils copyProperties Fail,with liveId:" + live.getActivityId(), e);
        }
        return activityComplexInfo;
    }

    public static List<ActivityInfo> toActivityInfoes(List<Live> lives) {
        List<ActivityInfo> activityInfos = new ArrayList<>();
        if(lives == null){
            return activityInfos;
        }

        for (Live live : lives) {
            activityInfos.add(toActivityInfo(live));
        }
        return activityInfos;
    }

}




















































