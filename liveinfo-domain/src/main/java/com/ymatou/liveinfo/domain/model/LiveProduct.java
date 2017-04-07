package com.ymatou.liveinfo.domain.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by gejianhua on 2017/4/7.
 * 直播活动商品关系信息，冗余了一部分商品信息
 */
@Entity(value = "LiveProducts", noClassnameStored = true)
public class LiveProduct {

    /**
     * 主键
     */
    @Id
    private ObjectId id;

    /**
     * 直播Id
     */
    @Property("lid")
    private Integer liveId;

    /**
     * 商品Id
     */
    @Property("spid")
    private String productId;
    /**
     * 品牌Id
     */
    @Property("bid")
    private Integer brandId;

    /**
     * 品牌名称
     */
    @Property("brand")
    private String brandName;

    /**
     * 品牌英文名称
     */
    @Property("ebrand")
    private String brandEnName;

    /**
     * 一级分类
     */
    @Property("mcatid")
    private Integer firstCategoryId;

    /**
     * 一级分类名称
     */
    @Property("mcatname")
    private String firstCategoryName;

    /**
     * 二级分类Id
     */
    @Property("scatid")
    private Integer secondCategoryId;

    /**
     * 二级分类名称
     */
    @Property("scatname")
    private String secondCategoryName;

    /**
     * 三级分类Id
     */
    @Property("tcatid")
    private Integer thirdCategoryId;

    /**
     * 三级分类名称
     */
    @Property("tcatname")
    private String thirdCategoryName;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getLiveId() {
        return liveId;
    }

    public void setLiveId(Integer liveId) {
        this.liveId = liveId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandEnName() {
        return brandEnName;
    }

    public void setBrandEnName(String brandEnName) {
        this.brandEnName = brandEnName;
    }

    public Integer getFirstCategoryId() {
        return firstCategoryId;
    }

    public void setFirstCategoryId(Integer firstCategoryId) {
        this.firstCategoryId = firstCategoryId;
    }

    public String getFirstCategoryName() {
        return firstCategoryName;
    }

    public void setFirstCategoryName(String firstCategoryName) {
        this.firstCategoryName = firstCategoryName;
    }

    public Integer getSecondCategoryId() {
        return secondCategoryId;
    }

    public void setSecondCategoryId(Integer secondCategoryId) {
        this.secondCategoryId = secondCategoryId;
    }

    public String getSecondCategoryName() {
        return secondCategoryName;
    }

    public void setSecondCategoryName(String secondCategoryName) {
        this.secondCategoryName = secondCategoryName;
    }

    public Integer getThirdCategoryId() {
        return thirdCategoryId;
    }

    public void setThirdCategoryId(Integer thirdCategoryId) {
        this.thirdCategoryId = thirdCategoryId;
    }

    public String getThirdCategoryName() {
        return thirdCategoryName;
    }

    public void setThirdCategoryName(String thirdCategoryName) {
        this.thirdCategoryName = thirdCategoryName;
    }
}


















































