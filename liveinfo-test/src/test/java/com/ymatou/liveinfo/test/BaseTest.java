package com.ymatou.liveinfo.test;

import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.domain.repository.LiveRepository;
import com.ymatou.liveinfo.facade.model.ActivityInfo;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author wangxudong 2016年7月22日 下午3:40:09
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-extra-beans.xml"})
public class BaseTest {

    @Resource
    protected LiveRepository liveRepository;

    /**
     * 构建基础的直播信息
     * @return
     */
    protected Live buildLiveBaseInfo(){
        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.HOUR, -1);

        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.HOUR, 1);

        String random = UUID.randomUUID().toString();

        int liveId = liveRepository.getMaxLiveId() + 1;
        if(liveId < 9000000)
        {
            liveId += 9000000;
        }

        Live live = new Live();
        live.setAction(1);
        live.setStartTime(startTime.getTime());
        live.setEndTime(endTime.getTime());
        live.setAppConfirmed(true);
        live.setActivityContent("Live-ActivityContent-" + random);
        live.setActivityId(liveId);
        live.setActivityName("Live-ActivityName-" + random);
        live.setActivityPicture("Live-ActivityPicture-" + random);
        live.setCountry("美国");
        live.setFlag("Live-Flag-" + random);
        live.setAddTime(Calendar.getInstance().getTime());
        live.setLatLng("30;30");
        live.setSellerId(new Random().nextInt(1000));
        live.setShopAddress("Live-ShopAddress-" + random);
        live.setTitle("Live-Title-" + random);
        live.setVideoCover("Live-VideoCover-" + random);
        live.setVideoUrl("Live-VideoUrl-" + random);

        return live;
    }

    /**
     * 验证基本的信息
     * @param live
     * @param activityInfo
     */
    protected void assertActivityInfo(Live live, ActivityInfo activityInfo){
        assertNotNull(activityInfo);
        assertEquals(live.getActivityId(), activityInfo.getActivityId());
        assertEquals(live.getTitle(), activityInfo.getTitle());
        assertEquals(live.getVideoCover(), activityInfo.getVideoCover());
        assertEquals(live.getVideoUrl(), activityInfo.getVideoUrl());
        assertEquals(live.getActivityName(), activityInfo.getActivityName());
        assertEquals(live.getActivityPicture(), activityInfo.getActivityPicture());
        assertEquals(live.getAddTime(), activityInfo.getAddTime());
        assertEquals(live.getCountry(), activityInfo.getCountry());
        assertEquals(live.getEndTime(), activityInfo.getEndTime());
        assertEquals(live.getSellerId(), activityInfo.getSellerId());
        assertEquals(live.getShopAddress(), activityInfo.getShopAddress());
        assertEquals(live.getStartTime(), activityInfo.getStartTime());
        assertEquals(live.getActivityContent(), activityInfo.getActivityContent());
        assertEquals(live.getActivityContent(), activityInfo.getActivityInfo());
    }
}
