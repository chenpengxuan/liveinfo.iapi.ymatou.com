package com.ymatou.liveinfo.facade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ymatou.liveinfo.facade.common.PrintFriendliness;

import java.util.List;

/**
 * Created by gejianhua on 2017/4/11.
 * 获取买手直播列表
 */
public class GetSellerLivesResp extends PrintFriendliness {

    /**
     * 直播数
     */
    @JsonProperty("ActivityCount")
    private int activityCount;
    /**
     * 当前正在进行的直播
     */
    @JsonProperty("InProgressActivity")
    private ActivityComplexInfo inProgressActivity;

    /**
     * 即将进行的直播
     */
    @JsonProperty("UpcomingActivities")
    private List<ActivityInfo> upcomingActivities;

    /**
     * 已经结束的直播信息
     */
    @JsonProperty("HistoryActivities")
    private List<ActivityInfo> historyActivities;



    public int getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(int activityCount) {
        this.activityCount = activityCount;
    }

    public ActivityComplexInfo getInProgressActivity() {
        return inProgressActivity;
    }

    public void setInProgressActivity(ActivityComplexInfo inProgressActivity) {
        this.inProgressActivity = inProgressActivity;
    }

    public List<ActivityInfo> getUpcomingActivities() {
        return upcomingActivities;
    }

    public void setUpcomingActivities(List<ActivityInfo> upcomingActivities) {
        this.upcomingActivities = upcomingActivities;
    }

    public List<ActivityInfo> getHistoryActivities() {
        return historyActivities;
    }

    public void setHistoryActivities(List<ActivityInfo> historyActivities) {
        this.historyActivities = historyActivities;
    }
}














































