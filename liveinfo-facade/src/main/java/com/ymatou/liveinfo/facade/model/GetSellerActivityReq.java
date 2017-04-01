package com.ymatou.liveinfo.facade.model;

import com.ymatou.liveinfo.facade.BaseRequest;

/**
 * Created by wangxudong on 2017/3/31.
 */
public class GetSellerActivityReq extends BaseRequest {

    /**
     * 直播Id
     */
    private int activityId;

    /**
     * 是否需要带上productlist, 不填默认 false
     */
    private boolean includeProducts;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public boolean isIncludeProducts() {
        return includeProducts;
    }

    public void setIncludeProducts(boolean includeProducts) {
        this.includeProducts = includeProducts;
    }
}
