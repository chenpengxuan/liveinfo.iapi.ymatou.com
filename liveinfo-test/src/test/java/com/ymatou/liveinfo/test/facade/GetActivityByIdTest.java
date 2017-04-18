package com.ymatou.liveinfo.test.facade;

import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.facade.LiveQueryFacade;
import com.ymatou.liveinfo.facade.common.BaseResponse;
import com.ymatou.liveinfo.facade.common.PrintFriendliness;
import com.ymatou.liveinfo.facade.enums.ActivityStateEnum;
import com.ymatou.liveinfo.facade.model.ActivityInfo;
import com.ymatou.liveinfo.facade.model.GetActivityByIdReq;
import com.ymatou.liveinfo.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by wangxudong on 2017/4/17.
 */
public class GetActivityByIdTest extends BaseTest {
    @Resource
    private LiveQueryFacade liveQueryFacade;

    @Test
    public void testGetActivityById(){
        Live live = buildLiveBaseInfo();
        liveRepository.insertLive(live);

        System.out.println("Live:" + live);

        GetActivityByIdReq req = new GetActivityByIdReq();
        req.setActivityId(live.getActivityId());

        BaseResponse response = liveQueryFacade.getActivityById(req);
        assertNotNull(response);
        assertEquals(200, response.getCode());

        ActivityInfo activityInfo = (ActivityInfo)response.getData();
        assertActivityInfo(live, activityInfo);
        assertEquals(ActivityStateEnum.InProcess.getCode(), activityInfo.getActivityState());
        assertEquals(ActivityStateEnum.InProcess.getMessage(), activityInfo.getActivityStatusText());
    }

}
