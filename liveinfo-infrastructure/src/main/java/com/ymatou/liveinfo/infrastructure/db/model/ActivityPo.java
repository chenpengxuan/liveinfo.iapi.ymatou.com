package com.ymatou.liveinfo.infrastructure.db.model;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityPo {
    /**
     * INTEGER(10) 必填<br>
     * 
     */
    private Integer activityId;

    /**
     * INTEGER(10) 必填<br>
     * 
     */
    private Integer activityTemplateId;

    /**
     * VARCHAR(20)<br>
     * 
     */
    private String code;

    /**
     * VARCHAR(100) 默认值[('')]<br>
     * 
     */
    private String name;

    /**
     * TIMESTAMP(23,3) 必填<br>
     * 
     */
    private Date beginTime;

    /**
     * TIMESTAMP(23,3) 必填<br>
     * 
     */
    private Date endTime;

    /**
     * VARCHAR(100) 必填<br>
     * 
     */
    private String titlePrefix;

    /**
     * INTEGER(10) 必填<br>
     * 
     */
    private Integer action;

    /**
     * INTEGER(10) 必填<br>
     * 
     */
    private Integer adminId;

    /**
     * TIMESTAMP(23,3) 必填<br>
     * 
     */
    private Date addTime;

    /**
     * INTEGER(10) 默认值[((-1))] 必填<br>
     * 
     */
    private Integer sellerId;

    /**
     * VARCHAR(200)<br>
     * 
     */
    private String position;

    /**
     * DECIMAL(10,2) 默认值[((0))] 必填<br>
     * 
     */
    private BigDecimal defaultFeeRate;

    /**
     * VARCHAR(100)<br>
     * 
     */
    private String seoTitle;

    /**
     * VARCHAR(200)<br>
     * 
     */
    private String seoKeyword;

    /**
     * VARCHAR(200)<br>
     * 
     */
    private String seoDescription;

    /**
     * VARCHAR(20)<br>
     * 
     */
    private String masterPage;

    /**
     * VARCHAR(200)<br>
     * 
     */
    private String pageUrl;

    /**
     * VARCHAR(2000)<br>
     * 
     */
    private String content;

    /**
     * VARCHAR(500)<br>
     * 
     */
    private String picUrl;

    /**
     * TIMESTAMP(23,3)<br>
     * 
     */
    private Date applyDeadline;

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     * 
     */
    private Integer appliedSpuLimit;

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     * 
     */
    private Integer appliedSellerType;

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     * 
     */
    private Integer stockLimit;

    /**
     * VARCHAR(50) 必填<br>
     * 
     */
    private String sellerName;

    /**
     * VARCHAR(2000)<br>
     * 
     */
    private String offLineReasons;

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     * 
     */
    private Integer sort;

    /**
     * INTEGER(10) 默认值[((-1))] 必填<br>
     * 
     */
    private Integer marketId;

    /**
     * VARCHAR(4000) 默认值[('')]<br>
     * 
     */
    private String brands;

    /**
     * INTEGER(10)<br>
     * 
     */
    private Integer countryId;

    /**
     * BIT(1) 默认值[((0))] 必填<br>
     * 
     */
    private Boolean isReplay;

    /**
     * INTEGER(10)<br>
     * 
     */
    private Integer originalActivityId;

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     * 
     */
    private Integer createReplayTimes;

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     * 
     */
    private Integer liveActivityId;

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     * 
     */
    private Integer deliveryType;

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     * 
     */
    private Integer vipPushStatus;

    /**
     * TIMESTAMP(23,3)<br>
     * 
     */
    private Date lastSortUpdateTime;

    /**
     * VARCHAR(400)<br>
     * 
     */
    private String detailAddress;

    /**
     * BIT(1)<br>
     * 
     */
    private Boolean isFromPC;

    /**
     * BIT(1)<br>
     * 
     */
    private Boolean appConfirmed;

    /**
     * VARCHAR(50)<br>
     * 
     */
    private String title;

    /**
     * VARCHAR(200)<br>
     * 
     */
    private String videoCover;

    /**
     * VARCHAR(200)<br>
     * 
     */
    private String videoUrl;

    /**
     * VARCHAR(36)<br>
     * 
     */
    private String latlng;

    /**
     * INTEGER(10) 必填<br>
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * INTEGER(10) 必填<br>
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * INTEGER(10) 必填<br>
     */
    public Integer getActivityTemplateId() {
        return activityTemplateId;
    }

    /**
     * INTEGER(10) 必填<br>
     */
    public void setActivityTemplateId(Integer activityTemplateId) {
        this.activityTemplateId = activityTemplateId;
    }

    /**
     * VARCHAR(20)<br>
     */
    public String getCode() {
        return code;
    }

    /**
     * VARCHAR(20)<br>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * VARCHAR(100) 默认值[('')]<br>
     */
    public String getName() {
        return name;
    }

    /**
     * VARCHAR(100) 默认值[('')]<br>
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * TIMESTAMP(23,3) 必填<br>
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * TIMESTAMP(23,3) 必填<br>
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * TIMESTAMP(23,3) 必填<br>
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * TIMESTAMP(23,3) 必填<br>
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * VARCHAR(100) 必填<br>
     */
    public String getTitlePrefix() {
        return titlePrefix;
    }

    /**
     * VARCHAR(100) 必填<br>
     */
    public void setTitlePrefix(String titlePrefix) {
        this.titlePrefix = titlePrefix == null ? null : titlePrefix.trim();
    }

    /**
     * INTEGER(10) 必填<br>
     */
    public Integer getAction() {
        return action;
    }

    /**
     * INTEGER(10) 必填<br>
     */
    public void setAction(Integer action) {
        this.action = action;
    }

    /**
     * INTEGER(10) 必填<br>
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * INTEGER(10) 必填<br>
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * TIMESTAMP(23,3) 必填<br>
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * TIMESTAMP(23,3) 必填<br>
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * INTEGER(10) 默认值[((-1))] 必填<br>
     */
    public Integer getSellerId() {
        return sellerId;
    }

    /**
     * INTEGER(10) 默认值[((-1))] 必填<br>
     */
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * VARCHAR(200)<br>
     */
    public String getPosition() {
        return position;
    }

    /**
     * VARCHAR(200)<br>
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * DECIMAL(10,2) 默认值[((0))] 必填<br>
     */
    public BigDecimal getDefaultFeeRate() {
        return defaultFeeRate;
    }

    /**
     * DECIMAL(10,2) 默认值[((0))] 必填<br>
     */
    public void setDefaultFeeRate(BigDecimal defaultFeeRate) {
        this.defaultFeeRate = defaultFeeRate;
    }

    /**
     * VARCHAR(100)<br>
     */
    public String getSeoTitle() {
        return seoTitle;
    }

    /**
     * VARCHAR(100)<br>
     */
    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle == null ? null : seoTitle.trim();
    }

    /**
     * VARCHAR(200)<br>
     */
    public String getSeoKeyword() {
        return seoKeyword;
    }

    /**
     * VARCHAR(200)<br>
     */
    public void setSeoKeyword(String seoKeyword) {
        this.seoKeyword = seoKeyword == null ? null : seoKeyword.trim();
    }

    /**
     * VARCHAR(200)<br>
     */
    public String getSeoDescription() {
        return seoDescription;
    }

    /**
     * VARCHAR(200)<br>
     */
    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription == null ? null : seoDescription.trim();
    }

    /**
     * VARCHAR(20)<br>
     */
    public String getMasterPage() {
        return masterPage;
    }

    /**
     * VARCHAR(20)<br>
     */
    public void setMasterPage(String masterPage) {
        this.masterPage = masterPage == null ? null : masterPage.trim();
    }

    /**
     * VARCHAR(200)<br>
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * VARCHAR(200)<br>
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    /**
     * VARCHAR(2000)<br>
     */
    public String getContent() {
        return content;
    }

    /**
     * VARCHAR(2000)<br>
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * VARCHAR(500)<br>
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * VARCHAR(500)<br>
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    /**
     * TIMESTAMP(23,3)<br>
     */
    public Date getApplyDeadline() {
        return applyDeadline;
    }

    /**
     * TIMESTAMP(23,3)<br>
     */
    public void setApplyDeadline(Date applyDeadline) {
        this.applyDeadline = applyDeadline;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public Integer getAppliedSpuLimit() {
        return appliedSpuLimit;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public void setAppliedSpuLimit(Integer appliedSpuLimit) {
        this.appliedSpuLimit = appliedSpuLimit;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public Integer getAppliedSellerType() {
        return appliedSellerType;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public void setAppliedSellerType(Integer appliedSellerType) {
        this.appliedSellerType = appliedSellerType;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public Integer getStockLimit() {
        return stockLimit;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public void setStockLimit(Integer stockLimit) {
        this.stockLimit = stockLimit;
    }

    /**
     * VARCHAR(50) 必填<br>
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * VARCHAR(50) 必填<br>
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    /**
     * VARCHAR(2000)<br>
     */
    public String getOffLineReasons() {
        return offLineReasons;
    }

    /**
     * VARCHAR(2000)<br>
     */
    public void setOffLineReasons(String offLineReasons) {
        this.offLineReasons = offLineReasons == null ? null : offLineReasons.trim();
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * INTEGER(10) 默认值[((-1))] 必填<br>
     */
    public Integer getMarketId() {
        return marketId;
    }

    /**
     * INTEGER(10) 默认值[((-1))] 必填<br>
     */
    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    /**
     * VARCHAR(4000) 默认值[('')]<br>
     */
    public String getBrands() {
        return brands;
    }

    /**
     * VARCHAR(4000) 默认值[('')]<br>
     */
    public void setBrands(String brands) {
        this.brands = brands == null ? null : brands.trim();
    }

    /**
     * INTEGER(10)<br>
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * INTEGER(10)<br>
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     * BIT(1) 默认值[((0))] 必填<br>
     */
    public Boolean getIsReplay() {
        return isReplay;
    }

    /**
     * BIT(1) 默认值[((0))] 必填<br>
     */
    public void setIsReplay(Boolean isReplay) {
        this.isReplay = isReplay;
    }

    /**
     * INTEGER(10)<br>
     */
    public Integer getOriginalActivityId() {
        return originalActivityId;
    }

    /**
     * INTEGER(10)<br>
     */
    public void setOriginalActivityId(Integer originalActivityId) {
        this.originalActivityId = originalActivityId;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public Integer getCreateReplayTimes() {
        return createReplayTimes;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public void setCreateReplayTimes(Integer createReplayTimes) {
        this.createReplayTimes = createReplayTimes;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public Integer getLiveActivityId() {
        return liveActivityId;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public void setLiveActivityId(Integer liveActivityId) {
        this.liveActivityId = liveActivityId;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public Integer getDeliveryType() {
        return deliveryType;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public Integer getVipPushStatus() {
        return vipPushStatus;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public void setVipPushStatus(Integer vipPushStatus) {
        this.vipPushStatus = vipPushStatus;
    }

    /**
     * TIMESTAMP(23,3)<br>
     */
    public Date getLastSortUpdateTime() {
        return lastSortUpdateTime;
    }

    /**
     * TIMESTAMP(23,3)<br>
     */
    public void setLastSortUpdateTime(Date lastSortUpdateTime) {
        this.lastSortUpdateTime = lastSortUpdateTime;
    }

    /**
     * VARCHAR(400)<br>
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * VARCHAR(400)<br>
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }

    /**
     * BIT(1)<br>
     */
    public Boolean getIsFromPC() {
        return isFromPC;
    }

    /**
     * BIT(1)<br>
     */
    public void setIsFromPC(Boolean isFromPC) {
        this.isFromPC = isFromPC;
    }

    /**
     * BIT(1)<br>
     */
    public Boolean getAppConfirmed() {
        return appConfirmed;
    }

    /**
     * BIT(1)<br>
     */
    public void setAppConfirmed(Boolean appConfirmed) {
        this.appConfirmed = appConfirmed;
    }

    /**
     * VARCHAR(50)<br>
     */
    public String getTitle() {
        return title;
    }

    /**
     * VARCHAR(50)<br>
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * VARCHAR(200)<br>
     */
    public String getVideoCover() {
        return videoCover;
    }

    /**
     * VARCHAR(200)<br>
     */
    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover == null ? null : videoCover.trim();
    }

    /**
     * VARCHAR(200)<br>
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * VARCHAR(200)<br>
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    /**
     * VARCHAR(36)<br>
     */
    public String getLatlng() {
        return latlng;
    }

    /**
     * VARCHAR(36)<br>
     */
    public void setLatlng(String latlng) {
        this.latlng = latlng == null ? null : latlng.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Ymt_Activity
     *
     * @mbggenerated Mon Apr 17 15:29:22 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", activityId=").append(activityId);
        sb.append(", activityTemplateId=").append(activityTemplateId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", titlePrefix=").append(titlePrefix);
        sb.append(", action=").append(action);
        sb.append(", adminId=").append(adminId);
        sb.append(", addTime=").append(addTime);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", position=").append(position);
        sb.append(", defaultFeeRate=").append(defaultFeeRate);
        sb.append(", seoTitle=").append(seoTitle);
        sb.append(", seoKeyword=").append(seoKeyword);
        sb.append(", seoDescription=").append(seoDescription);
        sb.append(", masterPage=").append(masterPage);
        sb.append(", pageUrl=").append(pageUrl);
        sb.append(", content=").append(content);
        sb.append(", picUrl=").append(picUrl);
        sb.append(", applyDeadline=").append(applyDeadline);
        sb.append(", appliedSpuLimit=").append(appliedSpuLimit);
        sb.append(", appliedSellerType=").append(appliedSellerType);
        sb.append(", stockLimit=").append(stockLimit);
        sb.append(", sellerName=").append(sellerName);
        sb.append(", offLineReasons=").append(offLineReasons);
        sb.append(", sort=").append(sort);
        sb.append(", marketId=").append(marketId);
        sb.append(", brands=").append(brands);
        sb.append(", countryId=").append(countryId);
        sb.append(", isReplay=").append(isReplay);
        sb.append(", originalActivityId=").append(originalActivityId);
        sb.append(", createReplayTimes=").append(createReplayTimes);
        sb.append(", liveActivityId=").append(liveActivityId);
        sb.append(", deliveryType=").append(deliveryType);
        sb.append(", vipPushStatus=").append(vipPushStatus);
        sb.append(", lastSortUpdateTime=").append(lastSortUpdateTime);
        sb.append(", detailAddress=").append(detailAddress);
        sb.append(", isFromPC=").append(isFromPC);
        sb.append(", appConfirmed=").append(appConfirmed);
        sb.append(", title=").append(title);
        sb.append(", videoCover=").append(videoCover);
        sb.append(", videoUrl=").append(videoUrl);
        sb.append(", latlng=").append(latlng);
        sb.append("]");
        return sb.toString();
    }
}