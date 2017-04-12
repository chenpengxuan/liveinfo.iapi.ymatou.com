package com.ymatou.liveinfo.domain.model;

import com.ymatou.liveinfo.facade.common.PrintFriendliness;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.math.BigDecimal;

/**
 * Created by gejianhua on 2017/4/7.
 * 商品信息
 */
@Entity(value = "Products", noClassnameStored = true)
public class Product extends PrintFriendliness{
    /**
     * 主键
     */
    @Id
    private ObjectId id;
    /**
     * 商品Id
     */
    @Property("spid")
    private String productId;
    /**
     * 是否psp商品
     */
    @Property("ispsp")
    private boolean psp;

    /**
     * 商品图片
     */
    @Property("pics")
    private String[] pictures;

    /**
     * 价格
     */
    @Property("minp")
    private BigDecimal price;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public boolean isPsp() {
        return psp;
    }

    public void setPsp(boolean psp) {
        this.psp = psp;
    }

    public String[] getPictures() {
        return pictures;
    }

    public void setPictures(String[] pictures) {
        this.pictures = pictures;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}












































