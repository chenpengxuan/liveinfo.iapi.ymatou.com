package com.ymatou.liveinfo.facade.model;

import com.ymatou.liveinfo.facade.common.BaseRequest;

import javax.validation.constraints.Min;
import javax.ws.rs.QueryParam;

/**
 * Created by wangxudong on 2017/4/17.
 */
public class GetSellerLatestLiveReq extends BaseRequest {
    @QueryParam("sellerId")
    @Min(value = 1, message = "无效的买手编号")
    private int sellerId;

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
}
