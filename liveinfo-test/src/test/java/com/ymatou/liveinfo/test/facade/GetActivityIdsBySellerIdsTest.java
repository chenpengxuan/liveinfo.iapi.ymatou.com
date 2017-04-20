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
public class GetActivityIdsBySellerIdsTest extends BaseTest {

    @Resource
    private LiveQueryFacade liveQueryFacade;

    @Test
    public void testGetActivityIdsBySellerIds() throws InterruptedException {
        Live live1 = buildLiveBaseInfo();
        liveRepository.insertLive(live1);

        TimeUnit.MILLISECONDS.sleep(200);

        Live live2 = buildLiveBaseInfo();
        liveRepository.insertLive(live2);

        System.out.println("LiveId1:" + live1.getActivityId());
        System.out.println("LiveId2:" + live2.getActivityId());
        System.out.println("SellerId1:" + live1.getSellerId());
        System.out.println("SellerId2:" + live2.getSellerId());

        GetActivityIdsBySellerIdsReq req = new GetActivityIdsBySellerIdsReq();
        List<Integer> sellerIds = new ArrayList<>();
        sellerIds.add(live1.getSellerId());
        sellerIds.add(live2.getSellerId());
        req.setSellerIds(sellerIds);

        BaseResponse resp = liveQueryFacade.getActivityIdsBySellerIds(req);
        assertEquals(200, resp.getCode());

        GetActivityIdsBySellerIdsRespData respData = (GetActivityIdsBySellerIdsRespData)resp.getData();
        assertEquals(2, respData.getActivityIds().size());

        for(GetActivityIdsBySellerIdsRespData.SellerAcitvityId sellerAcitvityId : respData.getActivityIds()){
            Live live = liveRepository.getLiveById(sellerAcitvityId.getActivityId());
            assertEquals(live.getSellerId(), sellerAcitvityId.getSellerId());
        }
    }
}
