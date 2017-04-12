package com.ymatou.liveinfo.domain.model;

import com.ymatou.liveinfo.facade.common.BizException;
import com.ymatou.liveinfo.facade.common.PrintFriendliness;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gejianhua on 2017/4/7.
 * 商品信息
 */
@Entity(value = "Products", noClassnameStored = true)
public class Product extends PrintFriendliness {
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
    private String price;

    private BigDecimal minPrice;

    /**
     * 计算价格
     *
     * @return
     */
    public BigDecimal calcMinPrice() {
        if(this.minPrice != null){
            return this.minPrice;
        }

        String[] strPrices = this.price.split(",");
        List<BigDecimal> prices = new ArrayList<>();

        for (String strPrice : strPrices) {
            BigDecimal price = new BigDecimal(strPrice);
            if (price.compareTo(new BigDecimal("0")) == 0) {
                continue;
            }
            prices.add(price);
        }

        if (prices.size() == 0) {
            throw new BizException("product:" + this.productId + " price is zero");
        }
        prices.sort(BigDecimal::compareTo);
        this.minPrice = prices.get(0);
        return this.minPrice;
    }


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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}












































