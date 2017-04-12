package com.ymatou.liveinfo.facade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ymatou.liveinfo.facade.common.PrintFriendliness;

import java.util.List;

/**
 * Created by wangxudong on 2017/4/11.
 */
public class GetProductListByLiveIdRespData extends PrintFriendliness {

    @JsonProperty("ProductIdList")
    private List<String> productIdList;

    public List<String> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<String> productIdList) {
        this.productIdList = productIdList;
    }
}
