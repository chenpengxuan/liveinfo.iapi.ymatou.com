package com.ymatou.liveinfo.facade.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gejianhua on 2017/4/11.
 * 复合的直播信息
 */
public class ActivityComplexInfo extends ActivityInfo {

    @JsonProperty("ProductList")
    private List<ProductInfo> productList = new ArrayList<>();

    public List<ProductInfo> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductInfo> productList) {
        this.productList = productList;
    }
}
