package com.ymatou.liveinfo.facade.model;

import com.ymatou.liveinfo.facade.common.BaseRequest;

import javax.validation.constraints.Min;
import javax.ws.rs.QueryParam;

/**
 * Created by gejianhua on 2017/4/11.
 * 获取买手直播列表
 */
public class GetSellerLivesReq extends BaseRequest{

    /**
     * 买手Id
     */
    @QueryParam("SellerId")
    @Min(value = 1, message = "sellerId is invalid")
    private int sellerId;
    /**
     * 是否单条, true:仅返回当前正进行的直播，false:包含历史直播
     */
    @QueryParam("IsSingle")
    private boolean isSingle;

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }
}
