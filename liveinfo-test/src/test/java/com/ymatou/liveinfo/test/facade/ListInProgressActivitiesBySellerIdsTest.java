package com.ymatou.liveinfo.test.facade;

import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.facade.LiveQueryFacade;
import com.ymatou.liveinfo.facade.common.BaseResponse;
import com.ymatou.liveinfo.facade.enums.ActivityStateEnum;
import com.ymatou.liveinfo.facade.model.ActivityInfo;
import com.ymatou.liveinfo.facade.model.GetSellerActivityReq;
import com.ymatou.liveinfo.facade.model.ListInProgressActivitiesBySellerIdsReq;
import com.ymatou.liveinfo.facade.model.ListInProgressActivitiesBySellerIdsRespData;
import com.ymatou.liveinfo.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by wangxudong on 2017/4/10.
 */
public class ListInProgressActivitiesBySellerIdsTest extends BaseTest {

    @Resource
    private LiveQueryFacade liveQueryFacade;

    @Test
    public void testGetSellerActivity() throws InterruptedException {
        Live live1 = buildLiveBaseInfo();
        liveRepository.insertLive(live1);

        TimeUnit.MILLISECONDS.sleep(100);

        Live live2 = buildLiveBaseInfo();
        liveRepository.insertLive(live2);

        System.out.println("LiveId1:" + live1.getActivityId());
        System.out.println("LiveId2:" + live2.getActivityId());
        System.out.println("SellerId1:" + live1.getSellerId());
        System.out.println("SellerId2:" + live2.getSellerId());

        ListInProgressActivitiesBySellerIdsReq req = new ListInProgressActivitiesBySellerIdsReq();
        List<Integer> sellerIds = new ArrayList<>();
        sellerIds.add(live1.getSellerId());
        sellerIds.add(live2.getSellerId());
        req.setSellerIds(sellerIds);

        BaseResponse resp = liveQueryFacade.ListInProgressActivitiesBySellerIds(req);
        assertEquals(200, resp.getCode());

        ListInProgressActivitiesBySellerIdsRespData respData = (ListInProgressActivitiesBySellerIdsRespData)resp.getData();
        assertEquals(2, respData.getActivityCount());

        for(ActivityInfo activityInfo : respData.getActivities()){
            Live live = liveRepository.getSellerCurrentLive(activityInfo.getSellerId());
            assertActivityInfo(live, activityInfo);
            assertEquals(ActivityStateEnum.InProcess.getCode(), activityInfo.getActivityState());
            assertEquals(ActivityStateEnum.InProcess.getMessage(), activityInfo.getActivityStatusText());
        }
    }

    @Test
    public void testGetSellerActivityFailWhenArgInvalid(){
        ListInProgressActivitiesBySellerIdsReq req = new ListInProgressActivitiesBySellerIdsReq();
        List<Integer> sellerIds = new ArrayList<>();
        req.setSellerIds(sellerIds);

        BaseResponse resp = liveQueryFacade.ListInProgressActivitiesBySellerIds(req);
        assertEquals(400, resp.getCode());
        assertEquals("错误的请求参数|卖家Id不能为空", resp.getMessage());

    }
}
