package com.ymatou.liveinfo.facade.model;

import com.ymatou.liveinfo.facade.common.BaseRequest;

import javax.validation.constraints.Min;
import javax.ws.rs.QueryParam;

/**
 * Created by gejianhua on 2017/4/7.
 * 获取直播信息
 */
public class GetActivityInfoReq extends BaseRequest {

    /**
     * 直播活动Id
     */
    @QueryParam("activityId")
    @Min(value = 1, message = "无效的活动Id")
    private int activityId;

    /**
     * 是否包含活动商品
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
