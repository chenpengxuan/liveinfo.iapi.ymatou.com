package com.ymatou.liveinfo.domain.model;

import com.ymatou.liveinfo.facade.common.BaseRequest;

import javax.validation.constraints.Min;
import javax.ws.rs.QueryParam;

/**
 * Created by wangxudong on 2017/3/31.
 */
public class GetActivityInfoReq extends BaseRequest {

    /**
     * 直播Id
     */
    @QueryParam("activityId")
    @Min(value = 1, message = "无效的直播编号")
    private int activityId;

    /**
     * 是否需要带上productlist, 不填默认 false
     */
    @QueryParam("includeProducts")
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
