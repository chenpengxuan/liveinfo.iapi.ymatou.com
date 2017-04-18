package com.ymatou.liveinfo.test.facade;

import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.facade.LiveQueryFacade;
import com.ymatou.liveinfo.facade.common.BaseResponse;
import com.ymatou.liveinfo.facade.enums.ActivityStateEnum;
import com.ymatou.liveinfo.facade.model.ActivityInfo;
import com.ymatou.liveinfo.facade.model.GetSellerActivityReq;
import com.ymatou.liveinfo.facade.model.GetSellerLatestLiveReq;
import com.ymatou.liveinfo.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by wangxudong on 2017/4/17.
 */
public class GetSellerLatestLiveTest extends BaseTest {
    @Resource
    private LiveQueryFacade liveQueryFacade;

    @Test
    public void testGetSellerLatestLive() throws InterruptedException {
        Live live = buildLiveBaseInfo();
        liveRepository.insertLive(live);

        System.out.println("LiveId:" + live.getActivityId());
        System.out.println("SellerId:" + live.getSellerId());

        TimeUnit.MILLISECONDS.sleep(100);

        Live sellerCurrentLive = liveRepository.getLiveById(live.getActivityId());
        assertNotNull(sellerCurrentLive);
        assertEquals(live.getActivityId(), sellerCurrentLive.getActivityId());

        GetSellerLatestLiveReq req = new GetSellerLatestLiveReq();
        req.setSellerId(live.getSellerId());
        BaseResponse resp = liveQueryFacade.getSellerLatestLive(req);
        assertEquals(200, resp.getCode());

        ActivityInfo activityInfo = (ActivityInfo)resp.getData();
        assertActivityInfo(live, activityInfo);
        assertEquals(ActivityStateEnum.InProcess.getCode(), activityInfo.getActivityState());
        assertEquals(ActivityStateEnum.InProcess.getMessage(), activityInfo.getActivityStatusText());
    }

    @Test
    public void testGetSellerLatestLiveHistory() throws InterruptedException {
        Live live = buildLiveBaseInfo();
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, -1);
        live.setEndTime(now.getTime());
        liveRepository.insertLive(live);

        System.out.println("LiveId:" + live.getActivityId());
        System.out.println("SellerId:" + live.getSellerId());

        TimeUnit.MILLISECONDS.sleep(100);

        Live sellerCurrentLive = liveRepository.getLiveById(live.getActivityId());
        assertNotNull(sellerCurrentLive);
        assertEquals(live.getActivityId(), sellerCurrentLive.getActivityId());

        GetSellerLatestLiveReq req = new GetSellerLatestLiveReq();
        req.setSellerId(live.getSellerId());
        BaseResponse resp = liveQueryFacade.getSellerLatestLive(req);
        assertEquals(200, resp.getCode());

        ActivityInfo activityInfo = (ActivityInfo)resp.getData();
        assertActivityInfo(live, activityInfo);
        assertEquals(ActivityStateEnum.End.getCode(), activityInfo.getActivityState());
        assertEquals(ActivityStateEnum.End.getMessage(), activityInfo.getActivityStatusText());
    }
}
