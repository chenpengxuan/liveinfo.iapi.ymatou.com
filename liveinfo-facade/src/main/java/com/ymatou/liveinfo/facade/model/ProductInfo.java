package com.ymatou.liveinfo.facade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ymatou.liveinfo.facade.common.PrintFriendliness;

import java.math.BigDecimal;

/**
 * Created by gejianhua on 2017/4/7.
 * 商品信息
 */
public class ProductInfo extends PrintFriendliness {

    @JsonProperty("IsPsp")
    private boolean psp;

    @JsonProperty("ProductId")
    private String productId;

    @JsonProperty("PicUrl")
    private String picUrl;

    @JsonProperty("Price")
    private BigDecimal price;

    @JsonProperty("VipPrice")
    private  BigDecimal vipPrice;

    @JsonProperty("NewGuestPrice")
    private BigDecimal newGuestPrice;

    @JsonProperty("SellStatus")
    private int sellStatus;



    public boolean isPsp() {
        return psp;
    }

    public void setPsp(boolean psp) {
        this.psp = psp;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public BigDecimal getNewGuestPrice() {
        return newGuestPrice;
    }

    public void setNewGuestPrice(BigDecimal newGuestPrice) {
        this.newGuestPrice = newGuestPrice;
    }

    public int getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(int sellStatus) {
        this.sellStatus = sellStatus;
    }
}
