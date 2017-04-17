package com.ymatou.liveinfo.facade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ymatou.liveinfo.facade.common.PrintFriendliness;

import java.util.List;

/**
 * Created by wangxudong on 2017/4/11.
 */
public class ListInProgressActivitiesByIdsRespData extends PrintFriendliness {

    /**
     * 直播数量
     */
    @JsonProperty("ActivityCount")
    private int activityCount;

    /**
     * 直播信息列表
     */
    @JsonProperty("Activities")
    private List<ActivityComplexInfo> activities;

    public int getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(int activityCount) {
        this.activityCount = activityCount;
    }

    public List<ActivityComplexInfo> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityComplexInfo> activities) {
        this.activities = activities;
    }
}
