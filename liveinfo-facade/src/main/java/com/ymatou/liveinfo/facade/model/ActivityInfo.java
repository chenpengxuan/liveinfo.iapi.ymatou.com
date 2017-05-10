package com.ymatou.liveinfo.facade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ymatou.liveinfo.facade.common.PrintFriendliness;
import com.ymatou.liveinfo.facade.enums.ActivityStateEnum;
import com.ymatou.liveinfo.facade.enums.LiveActionEnum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 直播信息，对应的数据库实体为Live
 * Created by wangxudong on 2017/4/1.
 */
public class ActivityInfo extends PrintFriendliness {

    /**
     * 直播Id
     */
    @JsonProperty("ActivityId")
    private int activityId;

    /**
     * 标题
     */
    @JsonProperty("Title")
    private String title;

    /**
     * 视频封面图片
     */
    @JsonProperty("VideoCover")
    private String videoCover;

    /**
     * 视频URL
     */
    @JsonProperty("VideoUrl")
    private String videoUrl;

    /**
     * 直播可用状态：1 - 可用，0 - 不可用，-1 - 已删除
     */
    @JsonProperty("Action")
    private int action;

    /**
     * 直播内容
     */
    @JsonProperty("ActivityContent")
    private String activityContent;

    /**
     * 扫货地
     */
    @JsonProperty("ActivityName")
    private String activityName;

    /**
     * 直播图片
     */
    @JsonProperty("ActivityPicture")
    private String activityPicture;

    /**
     * 添加时间
     */
    @JsonProperty("AddTime")
    private Date addTime;

    /**
     * 国家
     */
    @JsonProperty("Country")
    private String country;

    /**
     * 结束时间
     */
    @JsonProperty("EndTime")
    private Date endTime;

    /**
     * 买手UserId
     */
    @JsonProperty("SellerId")
    private int sellerId;

    /**
     * 商家地址
     */
    @JsonProperty("ShopAddress")
    private String shopAddress;

    /**
     * 开始时间
     */
    @JsonProperty("StartTime")
    private Date startTime;

    /**
     * App确认状态
     */
    @JsonProperty("AppConfirmed")
    private Boolean appConfirmed;

    /**
     * 直播信息
     * @return
     */
    @JsonProperty("ActivityInfo")
    public String getActivityInfo(){
        return activityContent;
    }

    /**
     * 物流方式 已经废弃
     */
    @JsonProperty("DeliverType")
    private int deliverType;

    /**
     * 品牌列表 已经废弃
     */
    @JsonProperty("Brands")
    private List<String> brands = new ArrayList<>();

    /**
     * 直播封面
     */
    @JsonProperty("LiveCover")
    private String liveCover;

    /**
     * 计算直播状态枚举
     * @return
     */
    private ActivityStateEnum calcActivityState(){
        if(LiveActionEnum.Available.getCode() == action){
            Calendar now = Calendar.getInstance(); //date
            if(Boolean.TRUE.equals(appConfirmed)){
                Date nowDate = now.getTime();
                if(nowDate.before(startTime) && endTime.after(startTime)){
                    return ActivityStateEnum.NotStart;
                }else  if(nowDate.before(endTime) && nowDate.after(startTime)){
                    return ActivityStateEnum.InProcess;
                }else{
                    return ActivityStateEnum.End;
                }
            }else{
                now.add(Calendar.HOUR, 1);
                if(now.getTime().before(this.endTime)){
                    return  ActivityStateEnum.NotEffected;
                }
                else{
                    return ActivityStateEnum.End;
                }
            }
        }else{
            return ActivityStateEnum.End;
        }
    }


    /**
     *
     * @return
     */
    @JsonProperty("ActivityStatusText")
    public String getActivityStatusText(){
        return calcActivityState().getMessage();
    }

    /**
     *
     * @return
     */
    @JsonProperty("ActivityState")
    public int getActivityState(){
        return calcActivityState().getCode();
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoCover() {
        return videoCover;
    }

    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityPicture() {
        return activityPicture;
    }

    public void setActivityPicture(String activityPicture) {
        this.activityPicture = activityPicture;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Boolean getAppConfirmed() {
        return appConfirmed;
    }

    public void setAppConfirmed(Boolean appConfirmed) {
        this.appConfirmed = appConfirmed;
    }

    public int getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(int deliverType) {
        this.deliverType = deliverType;
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    public String getLiveCover() {
        return liveCover;
    }

    public void setLiveCover(String liveCover) {
        this.liveCover = liveCover;
    }
}
