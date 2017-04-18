package com.ymatou.liveinfo.facade.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by gejianhua on 2017/4/18.
 * 运营后台直播信息
 */
public class ActivityInfoForBack extends ActivityInfo {

    /**
     * 国家Id
     */
    @JsonProperty("CountryId")
    private Integer countryId;

    /**
     * 下线原因
     */
    @JsonProperty("OffLineReasons")
    private String offLineReasons;

    /**
     * 回播原直播Id
     */
    @JsonProperty("OriginalActivityId")
    private Integer originalActivityId;

    /**
     * 是否回播
     */
    @JsonProperty("IsReplay")
    private boolean replay;

    /**
     * 直播活动Id
     */
    @JsonProperty("ActivityLiveId")
    private int activityLiveId;

    /**
     * 物流类型
     */
    @JsonProperty("DeliverType")
    private int deliverType;

    /**
     * 排序
     */
    @JsonProperty("Sort")
    private int sort;

    /**
     * 坐标
     */
    @JsonProperty("Latlng")
    private String latlng;


    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getOffLineReasons() {
        return offLineReasons;
    }

    public void setOffLineReasons(String offLineReasons) {
        this.offLineReasons = offLineReasons;
    }

    public Integer getOriginalActivityId() {
        return originalActivityId;
    }

    public void setOriginalActivityId(Integer originalActivityId) {
        this.originalActivityId = originalActivityId;
    }

    public boolean isReplay() {
        return replay;
    }

    public void setReplay(boolean replay) {
        this.replay = replay;
    }

    public int getActivityLiveId() {
        return activityLiveId;
    }

    public void setActivityLiveId(int activityLiveId) {
        this.activityLiveId = activityLiveId;
    }

    public int getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(int deliverType) {
        this.deliverType = deliverType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getLatlng() {
        return latlng;
    }

    public void setLatlng(String latlng) {
        this.latlng = latlng;
    }
}






























































































