package com.ymatou.liveinfo.test.facade.model;

import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.facade.LiveQueryFacade;
import com.ymatou.liveinfo.facade.enums.ActivityStateEnum;
import com.ymatou.liveinfo.facade.model.ActivityInfo;
import com.ymatou.liveinfo.facade.model.GetSellerActivityReq;
import com.ymatou.liveinfo.facade.model.GetSellerActivityResp;
import com.ymatou.liveinfo.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by wangxudong on 2017/4/6.
 */
public class GetSellerActivityTest extends BaseTest {

    @Resource
    private LiveQueryFacade liveQueryFacade;

    @Test
    public void testGetSellerActivity(){
        Live live = buildLiveBaseInfo();
        liveRepository.insertLive(live);

        System.out.println("LiveId:" + live.getActivityId());

        Live sellerCurrentLive = liveRepository.getSellerCurrentLive(live.getSellerId());
        assertNotNull(sellerCurrentLive);
        assertEquals(live.getActivityId(), sellerCurrentLive.getActivityId());

        GetSellerActivityReq req = new GetSellerActivityReq();
        req.setSellerId(live.getSellerId());
        GetSellerActivityResp resp = liveQueryFacade.getSellerActivity(req);
        assertEquals(200, resp.getCode());

        ActivityInfo activityInfo = (ActivityInfo)resp.getData();
        assertActivityInfo(live, activityInfo);
        assertEquals(ActivityStateEnum.InProcess.getCode(), activityInfo.getActivityState());
        assertEquals(ActivityStateEnum.InProcess.getMessage(), activityInfo.getActivityStatusText());
    }

}
