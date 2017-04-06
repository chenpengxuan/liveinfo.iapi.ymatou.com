package com.ymatou.liveinfo.domain.model;

import com.ymatou.liveinfo.facade.common.PrintFriendliness;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.Date;

/**
 * 直播信息
 *
 */
@Entity(value = "Lives", noClassnameStored = true)
public class Live extends PrintFriendliness
{
    /**
     * 主键
     */
    @Id
    private ObjectId id;

    /**
     * 直播Id
     */
    @Property("lid")
    private int activityId;

    /**
     * 标题
     */
    @Property("title")
    private String title;

    /**
     * 视频封面图片
     */
    @Property("vcover")
    private String videoCover;

    /**
     * 视频URL
     */
    @Property("vurl")
    private String videoUrl;

    /**
     * 直播可用状态：1 - 可用，0 - 不可用，-1 - 已删除
     */
    @Property("action")
    private int action;

    /**
     * 直播内容
     */
    @Property("content")
    private String activityContent;

    /**
     * 扫货地
     */
    @Property("name")
    private String activityName;

    /**
     * 直播图片
     */
    @Property("pic")
    private String activityPicture;

    /**
     * 添加时间
     */
    @Property("add")
    private Date addTime;

    /**
     * 国家
     */
    @Property("country")
    private String country;

    /**
     * 结束时间
     */
    @Property("end")
    private Date endTime;

    /**
     * 买手UserId
     */
    @Property("sid")
    private int sellerId;

    /**
     * App确认
     */
    @Property("confirm")
    private Boolean appConfirmed;

    /**
     *
     */
    @Property("flag")
    private String flag;

    /**
     * 商家地址
     */
    @Property("addr")
    private String shopAddress;

    /**
     * 开始时间
     */
    @Property("start")
    private Date startTime;

    /**
     * 经纬度
     */
    @Property("latlng")
    private String latLng;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public Boolean getAppConfirmed() {
        return appConfirmed;
    }

    public void setAppConfirmed(Boolean appConfirmed) {
        this.appConfirmed = appConfirmed;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getLatLng() {
        return latLng;
    }

    public void setLatLng(String latLng) {
        this.latLng = latLng;
    }
}
