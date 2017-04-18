package com.ymatou.liveinfo.facade.impl;


import com.ymatou.liveinfo.domain.service.LiveService;
import com.ymatou.liveinfo.facade.LiveQueryFacade;
import com.ymatou.liveinfo.facade.common.BaseRequest;
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
    @GET
    @Path("/{Activity:(?i:Activity)}/{GetActivityInfo:(?i:GetActivityInfo)}")
    public BaseResponse getActivityInfo(@BeanParam GetActivityInfoReq req) {
        GetActivityInfoResp resp = this.liveService.getActivityInfo(req);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(resp);
        return baseResponse;
    }

    @Override
    @GET
    @Path("/{Activity:(?i:Activity)}/{SellerActivity:(?i:SellerActivity)}")
    public BaseResponse getSellerLives(GetSellerLivesReq req) {
        GetSellerLivesResp resp = this.liveService.getSellerLives(req);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(resp);
        return baseResponse;
    }


    @POST
    @Path("/{Activity:(?i:Activity)}/{ListInProgressActivitiesBySellerIds:(?i:ListInProgressActivitiesBySellerIds)}")
    public BaseResponse listInProgressActivitiesBySellerIds(ListInProgressActivitiesBySellerIdsReq req) {
        BaseResponse response = new BaseResponse();
        List<ActivityInfo> activityInfos = liveService.getSellerCurrentActivityList(req.getSellerIds());

        ListInProgressActivitiesBySellerIdsRespData respData = new ListInProgressActivitiesBySellerIdsRespData();
        respData.setActivities(activityInfos);
        respData.setActivityCount(activityInfos.size());

        response.setData(respData);

        return response;
    }

    @Override
    @POST
    @Path("/{Activity:(?i:Activity)}/{GetActivityIdsBySellerIds:(?i:GetActivityIdsBySellerIds)}")
    public BaseResponse getActivityIdsBySellerIds(GetActivityIdsBySellerIdsReq req) {
        BaseResponse response = new BaseResponse();
        List<GetActivityIdsBySellerIdsRespData.SellerAcitvityId> sellerAcitvityIds
                = liveService.getSellerCurrentActivityIdList(req.getSellerIds());

        GetActivityIdsBySellerIdsRespData respData = new GetActivityIdsBySellerIdsRespData();
        respData.setActivityIds(sellerAcitvityIds);

        response.setData(respData);

        return response;
    }

    @Override
    @POST
    @Path("/{Activity:(?i:Activity)}/{ListInProgressActivitiesByIds:(?i:ListInProgressActivitiesByIds)}")
    public BaseResponse listInProgressActivitiesByIds(ListInProgressActivitiesByIdsReq req) {
        BaseResponse response = new BaseResponse();
        List<ActivityComplexInfo> activityInfos = liveService.getInProgressActivitiesByIds(req.getActivityIds(), req.getProductNum());

        ListInProgressActivitiesByIdsRespData respData = new ListInProgressActivitiesByIdsRespData();
        respData.setActivities(activityInfos);
        respData.setActivityCount(activityInfos.size());

        response.setData(respData);

        return response;
    }

    @Override
    @GET
    @Path("/{Product:(?i:Product)}/{GetProductListByLiveId:(?i:GetProductListByLiveId)}")
    public BaseResponse getProductListByLiveId(@BeanParam GetProductListByLiveIdReq req) {
        BaseResponse response = new BaseResponse();

        List<String> prodIds = liveService.searchProductListByLiveId(req);

        GetProductListByLiveIdRespData respData = new GetProductListByLiveIdRespData();
        respData.setProductIdList(prodIds);
        response.setData(respData);

        return response;
    }

    @Override
    @GET
    @Path("/{Activity:(?i:Activity)}/{SearchActivity:(?i:SearchActivity)}")
    public BaseResponse searchActivity(@BeanParam SearchActivityReq req) {
        SearchActivityResp resp = this.liveService.searchActivity(req);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(resp);
        return baseResponse;
    }

    @Override
    @GET
    @Path("/{Activity:(?i:Activity)}/{GetActivityById:(?i:GetActivityById)}")
    public BaseResponse getActivityById(@BeanParam GetActivityByIdReq req) {
        BaseResponse response = new BaseResponse();

        ActivityInfo activityInfo = liveService.getActivityById(req.getActivityId());
        response.setData(activityInfo);
        return response;
    }

    @Override
    @GET
    @Path("/{Activity:(?i:Activity)}/{GetSellerLatestLive:(?i:GetSellerLatestLive)}")
    public BaseResponse getSellerLatestLive(@BeanParam GetSellerLatestLiveReq req) {
        BaseResponse response = new BaseResponse();

        ActivityInfo activityInfo = liveService.getSellerLatestLive(req.getSellerId());
        response.setData(activityInfo);
        return response;
    }
}
