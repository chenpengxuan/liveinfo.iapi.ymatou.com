package com.ymatou.liveinfo.domain.model;

import com.ymatou.liveinfo.facade.common.PrintFriendliness;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by gejianhua on 2017/4/7.
 * 直播活动商品关系信息，冗余了一部分商品信息
 */
@Entity(value = "LiveProducts", noClassnameStored = true)
public class LiveProduct extends PrintFriendliness {

    /**
     * 主键
     */
    @Id
    private ObjectId id;

    /**
     * 直播Id
     */
    @Property("lid")
    private int liveId;

    /**
     * 商品Id
     */
    @Property("spid")
    private String productId;
    /**
     * 品牌Id
     */
    @Property("bid")
    private int brandId;

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
    private int firstCategoryId;

    /**
     * 一级分类名称
     */
    @Property("mcatname")
    private String firstCategoryName;

    /**
     * 二级分类Id
     */
    @Property("scatid")
    private int secondCategoryId;

    /**
     * 二级分类名称
     */
    @Property("scatname")
    private String secondCategoryName;

    /**
     * 三级分类Id
     */
    @Property("tcatid")
    private int thirdCategoryId;

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

    public int getLiveId() {
        return liveId;
    }

    public void setLiveId(int liveId) {
        this.liveId = liveId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
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

    public int getFirstCategoryId() {
        return firstCategoryId;
    }

    public void setFirstCategoryId(int firstCategoryId) {
        this.firstCategoryId = firstCategoryId;
    }

    public String getFirstCategoryName() {
        return firstCategoryName;
    }

    public void setFirstCategoryName(String firstCategoryName) {
        this.firstCategoryName = firstCategoryName;
    }

    public int getSecondCategoryId() {
        return secondCategoryId;
    }

    public void setSecondCategoryId(int secondCategoryId) {
        this.secondCategoryId = secondCategoryId;
    }

    public String getSecondCategoryName() {
        return secondCategoryName;
    }

    public void setSecondCategoryName(String secondCategoryName) {
        this.secondCategoryName = secondCategoryName;
    }

    public int getThirdCategoryId() {
        return thirdCategoryId;
    }

    public void setThirdCategoryId(int thirdCategoryId) {
        this.thirdCategoryId = thirdCategoryId;
    }

    public String getThirdCategoryName() {
        return thirdCategoryName;
    }

    public void setThirdCategoryName(String thirdCategoryName) {
        this.thirdCategoryName = thirdCategoryName;
    }
}


















































