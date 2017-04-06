package com.ymatou.liveinfo.test.facade.model;

import com.ymatou.liveinfo.facade.enums.ActivityStateEnum;
import com.ymatou.liveinfo.facade.enums.LiveActionEnum;
import com.ymatou.liveinfo.facade.model.ActivityInfo;
import com.ymatou.liveinfo.test.BaseTest;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangxudong on 2017/4/6.
 */
public class ActivityInfoTest extends BaseTest {

    @Test
    public void testGetActivityStatusText(){
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.setAction(LiveActionEnum.Deleted.getCode());
        assertEquals(ActivityStateEnum.End.getMessage(), activityInfo.getActivityStatusText());

        activityInfo.setAction(LiveActionEnum.Disable.getCode());
        assertEquals(ActivityStateEnum.End.getMessage(), activityInfo.getActivityStatusText());

        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.HOUR, -2);

        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.HOUR, 2);

        activityInfo.setAction(LiveActionEnum.Available.getCode());
        activityInfo.setAppConfirmed(true);
        activityInfo.setStartTime(startTime.getTime());
        activityInfo.setEndTime(endTime.getTime());

        assertEquals(ActivityStateEnum.InProcess.getMessage(), activityInfo.getActivityStatusText());

        startTime.add(Calendar.HOUR, 3);
        activityInfo.setStartTime(startTime.getTime());
        assertEquals(ActivityStateEnum.NotStart.getMessage(), activityInfo.getActivityStatusText());

        startTime.add(Calendar.HOUR, 1);
        activityInfo.setStartTime(startTime.getTime());
        assertEquals(ActivityStateEnum.End.getMessage(), activityInfo.getActivityStatusText());

        startTime.add(Calendar.HOUR, -4);
        activityInfo.setStartTime(startTime.getTime());
        activityInfo.setAppConfirmed(false);
        assertEquals(ActivityStateEnum.NotEffected.getMessage(), activityInfo.getActivityStatusText());

        endTime.add(Calendar.HOUR, -1);
        activityInfo.setEndTime(endTime.getTime());
        assertEquals(ActivityStateEnum.End.getMessage(), activityInfo.getActivityStatusText());
    }
}
