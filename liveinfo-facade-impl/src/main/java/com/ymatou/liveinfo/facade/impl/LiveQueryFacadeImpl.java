package com.ymatou.liveinfo.facade.impl;

import com.ymatou.liveinfo.domain.service.LiveService;
import com.ymatou.liveinfo.facade.LiveQueryFacade;
import com.ymatou.liveinfo.facade.common.BaseResponse;
import com.ymatou.liveinfo.facade.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.*;
import java.util.List;


@Component("liveQueryFacade")
@Path("/{api:(?i:api)}")
@Produces({"application/json; charset=UTF-8"})
public class LiveQueryFacadeImpl implements LiveQueryFacade {

    @Resource
    private LiveService liveService;

    @Override
    @GET
    @Path("/{Activity:(?i:Activity)}/{GetSellerActivity:(?i:GetSellerActivity)}")
    public BaseResponse getSellerActivity(@BeanParam GetSellerActivityReq req) {
        BaseResponse resp = new BaseResponse();

        ActivityInfo activityInfo = liveService.getSellerCurrentActivity(req.getSellerId());
        resp.setData(activityInfo);
        return resp;
    }

    @Override
    @POST
    @Path("/{Activity:(?i:Activity)}/{ListInProgressActivitiesBySellerIds:(?i:ListInProgressActivitiesBySellerIds)}")
    public BaseResponse ListInProgressActivitiesBySellerIds(ListInProgressActivitiesBySellerIdsReq req) {
        BaseResponse response = new BaseResponse();
        List<ActivityInfo> activityInfos = liveService.getSellerCurrentActivityList(req.getSellerIds());

        ListInProgressActivitiesBySellerIdsRespData  respData = new ListInProgressActivitiesBySellerIdsRespData();
        respData.setActivities(activityInfos);
        respData.setActivityCount(activityInfos.size());

        response.setData(respData);

        return response;
    }

    @Override
    @POST
    @Path("/{Activity:(?i:Activity)}/{GetActivityIdsBySellerIds:(?i:GetActivityIdsBySellerIds)}")
    public BaseResponse GetActivityIdsBySellerIds(GetActivityIdsBySellerIdsReq req) {
        BaseResponse response = new BaseResponse();
        List<GetActivityIdsBySellerIdsRespData.SellerAcitvityId> sellerAcitvityIds
                = liveService.getSellerCurrentActivityIdList(req.getSellerIds());

        GetActivityIdsBySellerIdsRespData  respData = new GetActivityIdsBySellerIdsRespData();
        respData.setActivityIds(sellerAcitvityIds);

        response.setData(respData);

        return response;
    }

    @Override
    @POST
    @Path("/{Activity:(?i:Activity)}/{ListInProgressActivitiesByIds:(?i:ListInProgressActivitiesByIds)}")
    public BaseResponse ListInProgressActivitiesByIds(ListInProgressActivitiesByIdsReq req) {
        BaseResponse response = new BaseResponse();
        List<ActivityInfo> activityInfos = liveService.getInProgressActivitiesByIds(req.getActivityIds());

        ListInProgressActivitiesByIdsRespData  respData = new ListInProgressActivitiesByIdsRespData();
        respData.setActivities(activityInfos);
        respData.setActivityCount(activityInfos.size());

        response.setData(respData);

        return response;
    }
}
