package com.ymatou.liveinfo.test.facade;

import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.facade.LiveQueryFacade;
import com.ymatou.liveinfo.facade.common.BaseResponse;
import com.ymatou.liveinfo.facade.enums.ActivityStateEnum;
import com.ymatou.liveinfo.facade.model.*;
import com.ymatou.liveinfo.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangxudong on 2017/4/11.
 */
public class ListInProgressActivitiesByIdsTest extends BaseTest {

    @Resource
    private LiveQueryFacade liveQueryFacade;

    @Test
    public void testListInProgressActivitiesByIds() throws InterruptedException {
        Live live1 = buildLiveBaseInfo();
        liveRepository.insertLive(live1);

        TimeUnit.MILLISECONDS.sleep(100);

        Live live2 = buildLiveBaseInfo();
        liveRepository.insertLive(live2);

        System.out.println("LiveId1:" + live1.getActivityId());
        System.out.println("LiveId2:" + live2.getActivityId());
        System.out.println("SellerId1:" + live1.getSellerId());
        System.out.println("SellerId2:" + live2.getSellerId());

        ListInProgressActivitiesByIdsReq req = new ListInProgressActivitiesByIdsReq();
        List<Integer> liveIds = new ArrayList<>();
        liveIds.add(live1.getActivityId());
        liveIds.add(live2.getActivityId());
        req.setActivityIds(liveIds);

        BaseResponse resp = liveQueryFacade.ListInProgressActivitiesByIds(req);
        assertEquals(200, resp.getCode());

        ListInProgressActivitiesByIdsRespData respData = (ListInProgressActivitiesByIdsRespData)resp.getData();
        assertEquals(2, respData.getActivityCount());

        for(ActivityInfo activityInfo : respData.getActivities()){
            Live live = liveRepository.getLiveById(activityInfo.getActivityId());
            assertActivityInfo(live, activityInfo);
            assertEquals(ActivityStateEnum.InProcess.getCode(), activityInfo.getActivityState());
            assertEquals(ActivityStateEnum.InProcess.getMessage(), activityInfo.getActivityStatusText());
        }
    }
}
