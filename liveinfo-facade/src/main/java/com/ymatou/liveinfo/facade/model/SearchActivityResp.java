package com.ymatou.liveinfo.facade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ymatou.liveinfo.facade.common.PrintFriendliness;

import java.util.List;

/**
 * Created by gejianhua on 2017/4/17.
 */
public class SearchActivityResp extends PrintFriendliness{

    /**
     * 总记录数
     */
    @JsonProperty("TotalRecords")
    private int totalRecords;

    /**
     * 直播列表
     */
    @JsonProperty("ActivityList")
    private List<ActivityInfoForBack> activityList;



    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<ActivityInfoForBack> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityInfoForBack> activityList) {
        this.activityList = activityList;
    }

}















































