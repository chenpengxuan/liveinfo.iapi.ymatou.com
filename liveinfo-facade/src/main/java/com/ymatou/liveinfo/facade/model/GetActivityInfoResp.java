package com.ymatou.liveinfo.facade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ymatou.liveinfo.facade.common.PrintFriendliness;

import java.util.List;

/**
 * Created by gejianhua on 2017/4/7.
 * 获取直播信息
 */
public class GetActivityInfoResp extends ActivityInfo {

    @JsonProperty("BrandList")
    private List<BrandInfo> brandList;

    @JsonProperty("ProductList")
    private List<ProductInfo> productList;

    public List<BrandInfo> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<BrandInfo> brandList) {
        this.brandList = brandList;
    }

    public List<ProductInfo> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductInfo> productList) {
        this.productList = productList;
    }

    /**
     * 品牌品类信息
     */
    public static class BrandInfo extends PrintFriendliness{

        @JsonProperty("BrandType")
        private int brandType;

        @JsonProperty("BrandName")
        private String brandName;

        @JsonProperty("BrandEnName")
        private String brandEnName;

        @JsonProperty("BrandId")
        private int brandId;

        @JsonProperty("CategoryId")
        private int categoryId;

        @JsonProperty("ParentName")
        private String parentName;

        @JsonProperty("ParentId")
        private int parentId;


        public int getBrandType() {
            return brandType;
        }

        public void setBrandType(int brandType) {
            this.brandType = brandType;
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

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getParentName() {
            return parentName;
        }

        public void setParentName(String parentName) {
            this.parentName = parentName;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }
    }

}
















































