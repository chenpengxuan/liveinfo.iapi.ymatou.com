package com.ymatou.liveinfo.facade.model;

import com.ymatou.library.datetimeparse.DateTimeParse;
import com.ymatou.liveinfo.facade.common.BaseRequest;
import com.ymatou.liveinfo.facade.enums.ActivityStateEnum;

import javax.ws.rs.QueryParam;
import java.util.Date;
import java.util.List;

/**
 * Created by gejianhua on 2017/4/17.
 * 运营后台查询直播
 */
public class SearchActivityReq  extends BaseRequest{

    public SearchActivityReq(){
        this.countryId = -1;
        this.isInActivity = -1;
        this.isLive = -1;
    }

    /**
     * 直播Id
     */
    @QueryParam("ActivityId")
    private int activityId;

    /**
     * 直播名称
     */
    @QueryParam("ActivityName")
    private String activityName;

    /**
     * 直播介绍
     */
    @QueryParam("ActivityContent")
    private String activityContent;

    /**
     * 是否渠道
     */
    @QueryParam("IsChannel")
    private boolean channel;

    /**
     * 创建直播开始时间
     */
    @QueryParam("AddTimeBegin")
    private String addTimeBegin;

    /**
     * 创建直播结束时间
     */
    @QueryParam("AddTimeEnd")
    private String addTimeEnd;

    /**
     * 大州Id
     */
    @QueryParam("AreaId")
    private int areaId;

    /**
     * 国家Id
     */
    @QueryParam("CountryId")
    private int countryId;
    /**
     * 直播品类Id
     */
    @QueryParam("ActivityCategory")
    private int activityCategory;

    /**
     * 直播开始时间
     */
    @QueryParam("StartTime")
    private String startTime;

    /**
     * 直播结束时间
     */
    @QueryParam("EndTime")
    private String endTime;

    /**
     * 买手Id
     */
    @QueryParam("BuyerId")
    private Integer sellerId;

    /**
     * 排除的卖家编号
     */
    @QueryParam("ExceptSellerIds")
    private List<Integer> exceptSellerIds;

    /**
     * 买手名称
     */
    @QueryParam("BuyerName")
    private String sellerName;

    /**
     * 每页记录数
     */
    @QueryParam("PageSize")
    private int pageSize;

    /**
     * 页索引,从1开始
     */
    @QueryParam("PageIndex")
    private int pageIndex;

    /**
     * 是否在活动中
     */
    @QueryParam("IsInActivity")
    private int isInActivity;

    /**
     * 是否回播
     */
    @QueryParam("IsLive")
    private int isLive;

    /**
     * 是否推荐直播
     */
    @QueryParam("IsRecommand")
    private boolean recommand;

    /**
     * 排序字段，默认dAddTime，可选iSort
     */
    @QueryParam("SortField")
    private String sortField;

    /**
     * 直播状态
     */
    @QueryParam("ActivityState")
    private int activityState;

    /**
     * 可选Desc，Asc
     */
    @QueryParam("SortType")
    private String sortType;

    /**
     * 即将结束时间
     */
    private Date willEndTime;

    /**
     * 国家Id列表
     */
    private List<Integer> countryIds;


    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public boolean isChannel() {
        return channel;
    }

    public void setChannel(boolean channel) {
        this.channel = channel;
    }

    public Date getAddTimeBegin() {
        return DateTimeParse.tryParse(addTimeBegin);
    }

    public void setAddTimeBegin(String addTimeBegin) {
        this.addTimeBegin = addTimeBegin;
    }

    public Date getAddTimeEnd() {
        return DateTimeParse.tryParse(addTimeEnd);
    }

    public void setAddTimeEnd(String addTimeEnd) {
        this.addTimeEnd = addTimeEnd;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getActivityCategory() {
        return activityCategory;
    }

    public void setActivityCategory(int activityCategory) {
        this.activityCategory = activityCategory;
    }

    public Date getStartTime() {
        return DateTimeParse.tryParse(startTime);
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return DateTimeParse.tryParse(endTime);
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public List<Integer> getExceptSellerIds() {
        return exceptSellerIds;
    }

    public void setExceptSellerIds(List<Integer> exceptSellerIds) {
        this.exceptSellerIds = exceptSellerIds;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getIsInActivity() {
        return isInActivity;
    }

    public void setIsInActivity(int isInActivity) {
        this.isInActivity = isInActivity;
    }

    public int getIsLive() {
        return isLive;
    }

    public void setIsLive(int isLive) {
        this.isLive = isLive;
    }

    public boolean isRecommand() {
        return recommand;
    }

    public void setRecommand(boolean recommand) {
        this.recommand = recommand;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public int getActivityState() {
        return this.activityState;
    }

    public void setActivityState(int activityState) {
        this.activityState = activityState;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public Date getWillEndTime() {
        return willEndTime;
    }

    public void setWillEndTime(Date willEndTime) {
        this.willEndTime = willEndTime;
    }

    public List<Integer> getCountryIds() {
        return countryIds;
    }

    public void setCountryIds(List<Integer> countryIds) {
        this.countryIds = countryIds;
    }
}
















































