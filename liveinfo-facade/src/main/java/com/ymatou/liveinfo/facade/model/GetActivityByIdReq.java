package com.ymatou.liveinfo.facade.model;

import com.ymatou.liveinfo.facade.common.BaseRequest;

import javax.ws.rs.QueryParam;

/**
 * Created by wangxudong on 2017/4/17.
 */
public class GetActivityByIdReq extends BaseRequest {

    @QueryParam("activityId")
    private int activityId;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}
