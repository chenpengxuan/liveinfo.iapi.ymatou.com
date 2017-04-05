package com.ymatou.liveinfo.facade;

import com.ymatou.liveinfo.facade.model.GetSellerActivityReq;
import com.ymatou.liveinfo.facade.model.GetSellerActivityResp;

/**
 * 直播查询API
 * Created by wangxudong on 2017/3/31.
 */
public interface LiveQueryFacade {
    /**
     * 获取卖家当前正在进行的直播
     * @param req 请求参数
     * @return
     */
    GetSellerActivityResp getSellerActivity(GetSellerActivityReq req);
}
