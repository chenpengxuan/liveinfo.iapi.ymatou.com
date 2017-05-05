package com.ymatou.liveinfo.test.facade;

import com.google.common.collect.Lists;
import com.ymatou.liveinfo.facade.LiveQueryFacade;
import com.ymatou.liveinfo.facade.common.BaseResponse;
import com.ymatou.liveinfo.facade.common.ResponseCode;
import com.ymatou.liveinfo.facade.enums.ActivityStateEnum;
import com.ymatou.liveinfo.facade.model.SearchActivityReq;
import com.ymatou.liveinfo.test.BaseTest;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;

/**
 * Created by gejianhua on 2017/4/17.
 */
public class SearchActivityTest extends BaseTest {

    @Autowired
    private LiveQueryFacade liveQueryFacade;

    private SearchActivityReq buildReqOfAllCondition(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SearchActivityReq req = new SearchActivityReq();
        req.setCountryId(1);
        req.setPageSize(20);
        req.setPageIndex(1);
        req.setActivityId(1);
        req.setActivityCategory(1);
        req.setActivityContent("1");
        req.setActivityName("test-activityname");
        req.setActivityState(ActivityStateEnum.InProcess.getCode());
        req.setAddTimeBegin(simpleDateFormat.format(DateTime.now().plusDays(-1).toDate()));
        req.setAddTimeEnd(simpleDateFormat.format(DateTime.now().plusDays(10).toDate()));
        req.setAreaId(1);
        req.setChannel(true);
        req.setEndTime(simpleDateFormat.format(DateTime.now().plusDays(10).toDate()));
        req.setExceptSellerIds(Lists.newArrayList(1, 2));
        req.setIsInActivity(1);
        req.setIsLive(1);
        req.setRecommand(true);
        req.setSellerId(1);
        req.setSellerName("test-sellername");
        req.setSortField("dendtime");
        req.setSortType("desc");
        req.setStartTime(simpleDateFormat.format(DateTime.now().plusDays(-1).toDate()));

        return req;
    }

    private SearchActivityReq buildReqOfNoneCondition(){
        SearchActivityReq req = new SearchActivityReq();
        req.setCountryId(-10);
        req.setAreaId(-1);
        req.setActivityId(-1);
        req.setIsLive(-1);
        req.setActivityState(-10);
        req.setIsInActivity(-2);
        return  req;
    }

    @Test
    public void testSearchActivity(){
        SearchActivityReq req = this.buildReqOfAllCondition();
        BaseResponse baseResponse = this.liveQueryFacade.searchActivity(req);

        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), baseResponse.getCode());
        Assert.assertNotNull(baseResponse.getData());
    }

    @Test
    public void testSearchActivityNoCountry(){
        SearchActivityReq req = this.buildReqOfAllCondition();
        req.setCountryId(-10);
        BaseResponse baseResponse = this.liveQueryFacade.searchActivity(req);

        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), baseResponse.getCode());
        Assert.assertNotNull(baseResponse.getData());
    }

    @Test
    public void testSearchActivityByActivityId(){
        SearchActivityReq req = this.buildReqOfNoneCondition();
        req.setActivityId(21);
        BaseResponse baseResponse = this.liveQueryFacade.searchActivity(req);

        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), baseResponse.getCode());
        Assert.assertNotNull(baseResponse.getData());
        System.out.println(baseResponse);
    }

    @Test
    public void testSearchActivityByTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SearchActivityReq req = this.buildReqOfNoneCondition();
        req.setAddTimeBegin(simpleDateFormat.format(DateTime.now().plusDays(-100).toDate()));
        req.setAddTimeEnd(simpleDateFormat.format(DateTime.now().plusDays(100).toDate()));

        BaseResponse baseResponse = this.liveQueryFacade.searchActivity(req);

        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), baseResponse.getCode());
        Assert.assertNotNull(baseResponse.getData());
        System.out.println(baseResponse);
    }
}












































