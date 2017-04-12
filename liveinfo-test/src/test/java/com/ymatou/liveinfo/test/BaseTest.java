package com.ymatou.liveinfo.test;

import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.domain.model.LiveProduct;
import com.ymatou.liveinfo.domain.model.Product;
import com.ymatou.liveinfo.domain.repository.LiveRepository;
import com.ymatou.liveinfo.facade.model.ActivityInfo;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author wangxudong 2016年7月22日 下午3:40:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-extra-beans.xml"})
public class BaseTest {

    @Resource
    protected LiveRepository liveRepository;

    /**
     * 构建基础的直播信息
     *
     * @return
     */
    protected Live buildLiveBaseInfo() {
        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.HOUR, -1);

        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.HOUR, 1);

        String random = UUID.randomUUID().toString();



        int liveId = liveRepository.getMaxLiveId() + 1;
        if (liveId < 9000000) {
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
        live.setSellerId(new Random().nextInt(10000000) + 60000000);
        live.setShopAddress("Live-ShopAddress-" + random);
        live.setTitle("Live-Title-" + random);
        live.setVideoCover("Live-VideoCover-" + random);
        live.setVideoUrl("Live-VideoUrl-" + random);

        return live;
    }

    /**
     * 构建直播商品关系
     *
     * @return
     */
    protected LiveProduct bulidLiveProduct(int randomSeed) {
        String uuid = UUID.randomUUID().toString();
        long seed = new Date().getTime() + randomSeed;
        int rand = new Random(seed).nextInt(1000000) + 7000000;

        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.HOUR, -1);

        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.HOUR, 1);

        int liveId = rand;
        int brandId = rand - 1;
        int firstCategoryId = rand + 1;
        int secondCategoryId = rand + 2;
        int thirdCategoryId = rand + 3;
        String productId = uuid;
        LiveProduct liveProduct = new LiveProduct();
        liveProduct.setBrandEnName("test-ebrand-" + brandId);
        liveProduct.setBrandId(brandId);
        liveProduct.setBrandName("test-brand-" + brandId);
        liveProduct.setEndTime(endTime.getTime());
        liveProduct.setFirstCategoryId(firstCategoryId);
        liveProduct.setFirstCategoryName("test-firstcatname-" + firstCategoryId);
        liveProduct.setLiveId(liveId);
        liveProduct.setProductId(productId);
        liveProduct.setSecondCategoryId(secondCategoryId);
        liveProduct.setSecondCategoryName("test-secondcatname-" + secondCategoryId);
        liveProduct.setSellStatus(1);
        liveProduct.setSort(new Double(String.valueOf(rand)));
        liveProduct.setStartTime(startTime.getTime());
        liveProduct.setThirdCategoryId(thirdCategoryId);
        liveProduct.setThirdCategoryName("test-thirdcatname-" + thirdCategoryId);

        return liveProduct;
    }


    /**
     * 构建商品信息
     * @return
     */
    protected Product bulidProduct() {
        String uuid = UUID.randomUUID().toString();

        String pic1 = "http://pic.ymatou.com/pic1";
        String pic2 = "http://pic.ymatou.com/pic2";
        String pic3 = "http://pic.ymatou.com/pic3";

        Product product = new Product();
        product.setPictures(new String[]{pic1, pic2, pic3});
        product.setPrice("100.5,90.00,10.00");
        product.setProductId(uuid);
        product.setPsp(true);
        return product;
    }

    /**
     * 验证基本的信息
     *
     * @param live
     * @param activityInfo
     */
    protected void assertActivityInfo(Live live, ActivityInfo activityInfo) {
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

    /**
     * 获取基于当前时间偏差的日期
     *
     * @param diffField
     * @param diffValue
     * @return
     */
    protected Date getDateFormNow(int diffField, int diffValue) {
        Calendar now = Calendar.getInstance();
        now.add(diffField, diffValue);
        return now.getTime();
    }
}
