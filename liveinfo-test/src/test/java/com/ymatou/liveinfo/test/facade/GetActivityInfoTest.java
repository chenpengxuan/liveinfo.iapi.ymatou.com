package com.ymatou.liveinfo.test.facade;

import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.domain.model.LiveProduct;
import com.ymatou.liveinfo.domain.model.Product;
import com.ymatou.liveinfo.domain.repository.LiveProductRepository;
import com.ymatou.liveinfo.domain.repository.LiveRepository;
import com.ymatou.liveinfo.domain.repository.ProductRepository;
import com.ymatou.liveinfo.facade.LiveQueryFacade;
import com.ymatou.liveinfo.facade.common.BaseResponse;
import com.ymatou.liveinfo.facade.common.ResponseCode;
import com.ymatou.liveinfo.facade.enums.LiveActionEnum;
import com.ymatou.liveinfo.facade.model.GetActivityInfoReq;
import com.ymatou.liveinfo.facade.model.GetActivityInfoResp;
import com.ymatou.liveinfo.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * Created by gejianhua on 2017/4/12.
 */
public class GetActivityInfoTest extends BaseTest {

    @Resource
    private LiveRepository liveRepository;
    @Resource
    private LiveProductRepository liveProductRepository;
    @Resource
    private ProductRepository productRepository;
    @Resource
    private LiveQueryFacade liveQueryFacade;

    @Test
    public void testGetActivityInfo(){
        Live live = this.buildLiveBaseInfo();
        this.liveRepository.insertLive(live);
        System.out.println("live:" + live);

        Product product1 = this.bulidProduct();
        this.productRepository.insert(product1);
        System.out.println("product1:" + product1);

        Product product2 = this.bulidProduct();
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


        GetActivityInfoReq req = new GetActivityInfoReq();
        req.setActivityId(live.getActivityId());
        req.setIncludeProducts(true);
        BaseResponse baseResponse = this.liveQueryFacade.getActivityInfo(req);

        Assert.assertNotNull(baseResponse);
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), baseResponse.getCode());
        Assert.assertNotNull(baseResponse.getData());

        GetActivityInfoResp resp = (GetActivityInfoResp)baseResponse.getData();
        Assert.assertEquals(live.getActivityId(), resp.getActivityId());

        Assert.assertEquals(2, resp.getProductList().size());
        Assert.assertEquals(product1.getPictures()[0], resp.getProductList().get(0).getPicUrl());
        Assert.assertEquals(0, product1.calcMinPrice().compareTo(resp.getProductList().get(0).getPrice()));

        Assert.assertEquals(4, resp.getBrandList().size());
        Assert.assertEquals(2, resp.getBrandList().stream().filter(m -> m.getBrandType() == 1).count());
        Assert.assertEquals(2, resp.getBrandList().stream().filter(m -> m.getBrandType() == 2).count());
    }


    @Test
    public void testGetActivityInfoNotInTimeRange(){
        Live live = this.buildLiveBaseInfo();
        this.liveRepository.insertLive(live);
        System.out.println("live:" + live);

        Product product1 = this.bulidProduct();
        this.productRepository.insert(product1);
        System.out.println("product1:" + product1);

        Product product2 = this.bulidProduct();
        this.productRepository.insert(product2);
        System.out.println("product2:" + product2);

        LiveProduct liveProduct1 = this.bulidLiveProduct(0);
        liveProduct1.setLiveId(live.getActivityId());
        liveProduct1.setProductId(product1.getProductId());
        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.HOUR, -2);
        liveProduct1.setEndTime(endTime.getTime());
        this.liveProductRepository.insert(liveProduct1);
        System.out.println("liveproduct1:" + liveProduct1);

        LiveProduct liveProduct2 = this.bulidLiveProduct(0);
        liveProduct2.setLiveId(live.getActivityId());
        liveProduct2.setProductId(product2.getProductId());
        this.liveProductRepository.insert(liveProduct2);
        System.out.println("liveproduct2:" + liveProduct2);


        GetActivityInfoReq req = new GetActivityInfoReq();
        req.setActivityId(live.getActivityId());
        req.setIncludeProducts(true);
        BaseResponse baseResponse = this.liveQueryFacade.getActivityInfo(req);

        Assert.assertNotNull(baseResponse);
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), baseResponse.getCode());
        Assert.assertNotNull(baseResponse.getData());

        GetActivityInfoResp resp = (GetActivityInfoResp)baseResponse.getData();
        Assert.assertEquals(live.getActivityId(), resp.getActivityId());

        Assert.assertEquals(1, resp.getProductList().size());
        Assert.assertEquals(product2.getPictures()[0], resp.getProductList().get(0).getPicUrl());
        Assert.assertEquals(0, product2.calcMinPrice().compareTo(resp.getProductList().get(0).getPrice()));

        Assert.assertEquals(4, resp.getBrandList().size());
        Assert.assertEquals(2, resp.getBrandList().stream().filter(m -> m.getBrandType() == 1).count());
        Assert.assertEquals(2, resp.getBrandList().stream().filter(m -> m.getBrandType() == 2).count());
    }

    @Test
    public void testGetActivityInfoInavailable(){
        Live live = this.buildLiveBaseInfo();
        live.setAction(LiveActionEnum.Disable.getCode());
        this.liveRepository.insertLive(live);
        System.out.println("live:" + live);

        Product product1 = this.bulidProduct();
        this.productRepository.insert(product1);
        System.out.println("product1:" + product1);

        Product product2 = this.bulidProduct();
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


        GetActivityInfoReq req = new GetActivityInfoReq();
        req.setActivityId(live.getActivityId());
        req.setIncludeProducts(true);
        BaseResponse baseResponse = this.liveQueryFacade.getActivityInfo(req);

        Assert.assertNotNull(baseResponse);
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), baseResponse.getCode());
        Assert.assertNull(baseResponse.getData());
    }

    @Test
    public void testGetActivityInfoExcludeProduct(){
        Live live = this.buildLiveBaseInfo();
        this.liveRepository.insertLive(live);
        System.out.println("live:" + live);

        Product product1 = this.bulidProduct();
        this.productRepository.insert(product1);
        System.out.println("product1:" + product1);

        Product product2 = this.bulidProduct();
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


        GetActivityInfoReq req = new GetActivityInfoReq();
        req.setActivityId(live.getActivityId());
        req.setIncludeProducts(false);
        BaseResponse baseResponse = this.liveQueryFacade.getActivityInfo(req);

        Assert.assertNotNull(baseResponse);
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), baseResponse.getCode());
        Assert.assertNotNull(baseResponse.getData());

        GetActivityInfoResp resp = (GetActivityInfoResp)baseResponse.getData();
        Assert.assertEquals(live.getActivityId(), resp.getActivityId());

        Assert.assertEquals(null, resp.getProductList());

        Assert.assertEquals(4, resp.getBrandList().size());
        Assert.assertEquals(2, resp.getBrandList().stream().filter(m -> m.getBrandType() == 1).count());
        Assert.assertEquals(2, resp.getBrandList().stream().filter(m -> m.getBrandType() == 2).count());
    }

}











































