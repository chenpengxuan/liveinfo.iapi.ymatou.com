package com.ymatou.liveinfo.test.facade;

import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.facade.LiveQueryFacade;
import com.ymatou.liveinfo.facade.common.BaseResponse;
import com.ymatou.liveinfo.facade.enums.ActivityStateEnum;
import com.ymatou.liveinfo.facade.model.ActivityInfo;
import com.ymatou.liveinfo.facade.model.GetSellerActivityReq;
import com.ymatou.liveinfo.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertNull;
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

        Live sellerCurrentLive = liveRepository.getLiveById(live.getActivityId());
        assertNotNull(sellerCurrentLive);
        assertEquals(live.getActivityId(), sellerCurrentLive.getActivityId());

        GetSellerActivityReq req = new GetSellerActivityReq();
        req.setSellerId(live.getSellerId());
        BaseResponse resp = liveQueryFacade.getSellerActivity(req);
        assertEquals(200, resp.getCode());

        ActivityInfo activityInfo = (ActivityInfo)resp.getData();
        assertActivityInfo(live, activityInfo);
        assertEquals(ActivityStateEnum.InProcess.getCode(), activityInfo.getActivityState());
        assertEquals(ActivityStateEnum.InProcess.getMessage(), activityInfo.getActivityStatusText());
    }

    @Test
    public void testGetSellerActivityWhenAcitvityStateNotStart() throws InterruptedException {
        Live live = buildLiveBaseInfo();
        live.setStartTime(getDateFormNow(Calendar.HOUR, 2));
        live.setEndTime(getDateFormNow(Calendar.HOUR, 3));
        liveRepository.insertLive(live);

        System.out.println("LiveId:" + live.getActivityId());
        TimeUnit.MILLISECONDS.sleep(100);

        Live sellerCurrentLive = liveRepository.getLiveById(live.getActivityId());
        assertNotNull(sellerCurrentLive);
        assertEquals(live.getActivityId(), sellerCurrentLive.getActivityId());

        GetSellerActivityReq req = new GetSellerActivityReq();
        req.setSellerId(live.getSellerId());
        BaseResponse resp = liveQueryFacade.getSellerActivity(req);
        assertEquals(200, resp.getCode());

        ActivityInfo activityInfo = (ActivityInfo)resp.getData();
        assertNull(activityInfo); // 未进行的直播不会显示
    }

}
