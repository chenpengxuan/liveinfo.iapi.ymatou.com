package com.ymatou.liveinfo.facade;

import com.ymatou.liveinfo.facade.common.BaseResponse;
import com.ymatou.liveinfo.facade.model.GetActivityInfoReq;
import com.ymatou.liveinfo.facade.model.GetSellerActivityReq;
import com.ymatou.liveinfo.facade.model.GetSellerActivityResp;
import com.ymatou.liveinfo.facade.model.GetSellerLivesReq;

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

    /**
     * 获取直播详情
     * @param req
     * @return
     */
    BaseResponse getActivityInfo(GetActivityInfoReq req);

    /**
     * 获取商家直播信息
     * @param req
     * @return
     */
    BaseResponse getSellerLives(GetSellerLivesReq req);
}
