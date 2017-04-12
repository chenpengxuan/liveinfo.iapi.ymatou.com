package com.ymatou.liveinfo.facade;

import com.ymatou.liveinfo.facade.common.BaseResponse;
import com.ymatou.liveinfo.facade.model.*;

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
    BaseResponse getSellerActivity(GetSellerActivityReq req);

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

    /**
     * 根据卖家id列表获取正在进行中直播列表
     * @param req
     * @return
     */
    BaseResponse listInProgressActivitiesBySellerIds(ListInProgressActivitiesBySellerIdsReq req);

    /**
     * 根据卖家id列表获取正在进行中直播ID列表
     * @param req
     * @return
     */
    BaseResponse getActivityIdsBySellerIds(GetActivityIdsBySellerIdsReq req);

    /**
     * 根据多个直播id获取直播列表
     * @param req
     * @return
     */
    BaseResponse listInProgressActivitiesByIds(ListInProgressActivitiesByIdsReq req);

    /**
     * 根据直播Id获取商品Id列表
     * @param req
     * @return
     */
    BaseResponse getProductListByLiveId(GetProductListByLiveIdReq req);
}
