package com.ymatou.liveinfo.facade.impl;

import com.ymatou.liveinfo.facade.LiveQueryFacade;
import com.ymatou.liveinfo.facade.common.BaseResponse;
import com.ymatou.liveinfo.facade.model.*;
import com.ymatou.liveinfo.domain.service.LiveService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Component("liveQueryFacade")
@Path("/{api:(?i:api)}")
@Produces({"application/json; charset=UTF-8"})
public class LiveQueryFacadeImpl implements LiveQueryFacade {

    @Resource
    private LiveService liveService;

    @Override
    @GET
    @Path("/{Activity:(?i:Activity)}/{GetSellerActivity:(?i:GetSellerActivity)}")
    public GetSellerActivityResp getSellerActivity(@BeanParam GetSellerActivityReq req) {
        GetSellerActivityResp resp = new GetSellerActivityResp();

        ActivityInfo activityInfo = liveService.getSellerCurrentActivity(req.getSellerId());
        resp.setData(activityInfo);
        return resp;
    }

    @Override
    @GET
    @Path("/{Activity:(?i:Activity)}/{GetActivityInfo:(?i:GetActivityInfo)}")
    public BaseResponse getActivityInfo(@BeanParam GetActivityInfoReq req){
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


}
