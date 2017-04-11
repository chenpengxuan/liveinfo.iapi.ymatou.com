package com.ymatou.liveinfo.facade;

import com.ymatou.liveinfo.facade.common.BaseResponse;
import com.ymatou.liveinfo.facade.model.GetActivityIdsBySellerIdsReq;
import com.ymatou.liveinfo.facade.model.GetSellerActivityReq;
import com.ymatou.liveinfo.facade.model.ListInProgressActivitiesByIdsReq;
import com.ymatou.liveinfo.facade.model.ListInProgressActivitiesBySellerIdsReq;

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
     * 根据卖家id列表获取正在进行中直播列表
     * @param req
     * @return
     */
    BaseResponse ListInProgressActivitiesBySellerIds(ListInProgressActivitiesBySellerIdsReq req);

    /**
     * 根据卖家id列表获取正在进行中直播ID列表
     * @param req
     * @return
     */
    BaseResponse GetActivityIdsBySellerIds(GetActivityIdsBySellerIdsReq req);

    /**
     * 根据多个直播id获取直播列表
     * @param req
     * @return
     */
    BaseResponse ListInProgressActivitiesByIds(ListInProgressActivitiesByIdsReq req);
}
