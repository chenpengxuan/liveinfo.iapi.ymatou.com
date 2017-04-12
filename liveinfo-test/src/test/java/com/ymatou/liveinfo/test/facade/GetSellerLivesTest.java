package com.ymatou.liveinfo.test.facade;

import com.ymatou.liveinfo.domain.config.BizConfig;
import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.domain.model.LiveProduct;
import com.ymatou.liveinfo.domain.model.Product;
import com.ymatou.liveinfo.domain.repository.LiveProductRepository;
import com.ymatou.liveinfo.domain.repository.LiveRepository;
import com.ymatou.liveinfo.domain.repository.ProductRepository;
import com.ymatou.liveinfo.facade.LiveQueryFacade;
import com.ymatou.liveinfo.facade.common.BaseResponse;
import com.ymatou.liveinfo.facade.common.ResponseCode;
import com.ymatou.liveinfo.facade.model.GetSellerLivesReq;
import com.ymatou.liveinfo.facade.model.GetSellerLivesResp;
import com.ymatou.liveinfo.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by gejianhua on 2017/4/12.
 */
public class GetSellerLivesTest extends BaseTest {

    @Resource
    private LiveRepository liveRepository;
    @Resource
    private LiveProductRepository liveProductRepository;
    @Resource
    private ProductRepository productRepository;
    @Resource
    private LiveQueryFacade liveQueryFacade;
    @Resource
    private BizConfig bizConfig;

    @Test
    public void testGetSellerLivesSingle() throws InterruptedException {
        //添加历史直播
        int sellerId = new Random().nextInt(10000000) + 60000000;
        Live live = null;
        Product product1 = null;
        Product product2 = null;
        for (int i = 0; i < 12; i++) {
            live = this.buildLiveBaseInfo();
            if (i < 11) {
                Calendar endTime = Calendar.getInstance();
                endTime.add(Calendar.HOUR, -10);
                live.setEndTime(endTime.getTime());
            }
            live.setSellerId(sellerId);
            this.liveRepository.insertLive(live);
            TimeUnit.MILLISECONDS.sleep(100);

            System.out.println("live:" + live);

            product1 = this.bulidProduct();
            this.productRepository.insert(product1);
            System.out.println("product1:" + product1);

            product2 = this.bulidProduct();
            this.productRepository.insert(product2);
            System.out.println("product2:" + product2);

            LiveProduct liveProduct1 = this.bulidLiveProduct(0);
            liveProduct1.setLiveId(live.getActivityId());
            liveProduct1.setProductId(product1.getProductId());
            this.liveProductRepository.insert(liveProduct1);
            System.out.println("liveproduct1:" + liveProduct1);

            LiveProduct liveProduct2 = this.bulidLiveProduct(0);
            liveProduct2.setLiveId(live.getActivityId());
            liveProduct2.setProductId(product2.getProductId());
            this.liveProductRepository.insert(liveProduct2);
            System.out.println("liveproduct2:" + liveProduct2);
        }


        GetSellerLivesReq req = new GetSellerLivesReq();
        req.setSellerId(live.getSellerId());
        req.setSingle(true);
        BaseResponse baseResponse = this.liveQueryFacade.getSellerLives(req);

        Assert.assertNotNull(baseResponse);
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), baseResponse.getCode());
        Assert.assertNotNull(baseResponse.getData());

        GetSellerLivesResp resp = (GetSellerLivesResp) baseResponse.getData();
        Assert.assertNotNull(resp.getInProgressActivity());
        Assert.assertEquals(live.getActivityId(), resp.getInProgressActivity().getActivityId());
        Assert.assertEquals(2, resp.getInProgressActivity().getProductList().size());
        //Assert.assertEquals(product2.getProductId(), resp.getInProgressActivity().getProductList().get(0).getProductId());
        //Assert.assertEquals(product1.getProductId(), resp.getInProgressActivity().getProductList().get(1).getProductId());

        Assert.assertEquals(0, resp.getHistoryActivities().size());
    }


    @Test
    public void testGetSellerLivesContainHistory() throws InterruptedException {
        //添加历史直播
        int sellerId = new Random().nextInt(10000000) + 60000000;
        Live live = null;
        Product product1 = null;
        Product product2 = null;
        for (int i = 0; i < 12; i++) {
            live = this.buildLiveBaseInfo();
            if (i < 11) {
                Calendar endTime = Calendar.getInstance();
                endTime.add(Calendar.HOUR, -10);
                live.setEndTime(endTime.getTime());
            }
            live.setSellerId(sellerId);
            this.liveRepository.insertLive(live);
            TimeUnit.MILLISECONDS.sleep(100);

            System.out.println("live:" + live);

            product1 = this.bulidProduct();
            this.productRepository.insert(product1);
            System.out.println("product1:" + product1);

            product2 = this.bulidProduct();
            this.productRepository.insert(product2);
            System.out.println("product2:" + product2);

            LiveProduct liveProduct1 = this.bulidLiveProduct(0);
            liveProduct1.setLiveId(live.getActivityId());
            liveProduct1.setProductId(product1.getProductId());
            this.liveProductRepository.insert(liveProduct1);
            System.out.println("liveproduct1:" + liveProduct1);

            LiveProduct liveProduct2 = this.bulidLiveProduct(0);
            liveProduct2.setLiveId(live.getActivityId());
            liveProduct2.setProductId(product2.getProductId());
            this.liveProductRepository.insert(liveProduct2);
            System.out.println("liveproduct2:" + liveProduct2);
        }


        GetSellerLivesReq req = new GetSellerLivesReq();
        req.setSellerId(live.getSellerId());
        req.setSingle(false);
        BaseResponse baseResponse = this.liveQueryFacade.getSellerLives(req);

        Assert.assertNotNull(baseResponse);
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), baseResponse.getCode());
        Assert.assertNotNull(baseResponse.getData());

        GetSellerLivesResp resp = (GetSellerLivesResp) baseResponse.getData();
        Assert.assertNotNull(resp.getInProgressActivity());
        Assert.assertEquals(live.getActivityId(), resp.getInProgressActivity().getActivityId());
        Assert.assertEquals(2, resp.getInProgressActivity().getProductList().size());

        int historyQuantity = 11;
        if (historyQuantity > this.bizConfig.getHistoryLiveQuantity()) {
            historyQuantity = this.bizConfig.getHistoryLiveQuantity();
        }
        Assert.assertEquals(historyQuantity, resp.getHistoryActivities().size());
    }


    @Test
    public void testGetSellerLivesNotExistsInProgress() {
        //添加历史直播
        int sellerId = new Random().nextInt(10000000) + 60000000;
        Live live = null;
        Product product1 = null;
        Product product2 = null;
        for (int i = 0; i < 12; i++) {
            live = this.buildLiveBaseInfo();
            Calendar endTime = Calendar.getInstance();
            endTime.add(Calendar.HOUR, -10);
            live.setEndTime(endTime.getTime());
            live.setSellerId(sellerId);
            this.liveRepository.insertLive(live);
            System.out.println("live:" + live);

            product1 = this.bulidProduct();
            this.productRepository.insert(product1);
            System.out.println("product1:" + product1);

            product2 = this.bulidProduct();
            this.productRepository.insert(product2);
            System.out.println("product2:" + product2);

            LiveProduct liveProduct1 = this.bulidLiveProduct(0);
            liveProduct1.setLiveId(live.getActivityId());
            liveProduct1.setProductId(product1.getProductId());
            this.liveProductRepository.insert(liveProduct1);
            System.out.println("liveproduct1:" + liveProduct1);

            LiveProduct liveProduct2 = this.bulidLiveProduct(0);
            liveProduct2.setLiveId(live.getActivityId());
            liveProduct2.setProductId(product2.getProductId());
            this.liveProductRepository.insert(liveProduct2);
            System.out.println("liveproduct2:" + liveProduct2);
        }


        GetSellerLivesReq req = new GetSellerLivesReq();
        req.setSellerId(live.getSellerId());
        req.setSingle(false);
        BaseResponse baseResponse = this.liveQueryFacade.getSellerLives(req);

        Assert.assertNotNull(baseResponse);
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), baseResponse.getCode());
        Assert.assertNotNull(baseResponse.getData());

        GetSellerLivesResp resp = (GetSellerLivesResp) baseResponse.getData();
        Assert.assertNull(resp.getInProgressActivity());

        int historyQuantity = 12;
        if (historyQuantity > this.bizConfig.getHistoryLiveQuantity()) {
            historyQuantity = this.bizConfig.getHistoryLiveQuantity();
        }
        Assert.assertEquals(historyQuantity, resp.getHistoryActivities().size());
    }


}









































