package com.ymatou.liveinfo.facade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ymatou.liveinfo.facade.common.PrintFriendliness;

import java.util.List;

/**
 * Created by wangxudong on 2017/4/10.
 */
public class ListInProgressActivitiesBySellerIdsRespData extends PrintFriendliness {

    /**
     * 直播数量
     */
    @JsonProperty("ActivityCount")
    private int activityCount;

    /**
     * 直播详情列表
     */
    @JsonProperty("Activities")
    private List<ActivityInfo> activities;

    public int getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(int activityCount) {
        this.activityCount = activityCount;
    }

    public List<ActivityInfo> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityInfo> activities) {
        this.activities = activities;
    }
}
