package com.ymatou.liveinfo.test.facade;

import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.domain.model.LiveProduct;
import com.ymatou.liveinfo.domain.repository.LiveProductRepository;
import com.ymatou.liveinfo.domain.repository.LiveRepository;
import com.ymatou.liveinfo.domain.repository.ProductRepository;
import com.ymatou.liveinfo.facade.LiveQueryFacade;
import com.ymatou.liveinfo.facade.common.BaseResponse;
import com.ymatou.liveinfo.facade.enums.ProductInLiveSearchTypeEnum;
import com.ymatou.liveinfo.facade.model.GetProductListByLiveIdReq;
import com.ymatou.liveinfo.facade.model.GetProductListByLiveIdRespData;
import com.ymatou.liveinfo.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by wangxudong on 2017/4/17.
 */
public class GetProductListByLiveIdTest extends BaseTest {

    @Resource
    private LiveQueryFacade liveQueryFacade;

    @Resource
    private LiveRepository liveRepository;
    @Resource
    private LiveProductRepository liveProductRepository;

    @Test
    public void testGetProductListByLiveId(){
        Live live = buildLiveBaseInfo();
        liveRepository.insertLive(live);
        System.out.println("live:" + live);

        LiveProduct liveProduct1 = this.bulidLiveProduct(0);
        liveProduct1.setLiveId(live.getActivityId());
        liveProduct1.setProductId("test-product-100");
        liveProduct1.setSort(200);
        this.liveProductRepository.insert(liveProduct1);
        System.out.println("liveproduct1:" + liveProduct1);

        LiveProduct liveProduct2 = this.bulidLiveProduct(0);
        liveProduct2.setLiveId(live.getActivityId());
        liveProduct2.setProductId("test-product-200");
        liveProduct2.setSort(100);
        this.liveProductRepository.insert(liveProduct2);
        System.out.println("liveproduct2:" + liveProduct2);

        LiveProduct liveProduct3 = this.bulidLiveProduct(0);
        liveProduct3.setLiveId(live.getActivityId());
        liveProduct3.setProductId("test-product-300");
        liveProduct3.setSort(300);
        this.liveProductRepository.insert(liveProduct3);
        System.out.println("liveproduct3:" + liveProduct3);

        LiveProduct liveProduct4 = this.bulidLiveProduct(0);
        liveProduct4.setLiveId(live.getActivityId());
        liveProduct4.setProductId("test-product-400");
        liveProduct4.setSort(300);
        this.liveProductRepository.insert(liveProduct4);
        System.out.println("liveproduct4:" + liveProduct4);

        GetProductListByLiveIdReq req = new GetProductListByLiveIdReq();
        req.setLiveId(live.getActivityId());
        BaseResponse response = liveQueryFacade.getProductListByLiveId(req);

        assertNotNull(response);
        assertEquals(200, response.getCode());

        // 验证基本功能和排序
        GetProductListByLiveIdRespData respData = (GetProductListByLiveIdRespData) response.getData();
        assertNotNull(respData);
        assertEquals(4, respData.getProductIdList().size());
        assertEquals(liveProduct2.getProductId(), respData.getProductIdList().get(0));
        assertEquals(liveProduct1.getProductId(), respData.getProductIdList().get(1));
        assertEquals(liveProduct3.getProductId(), respData.getProductIdList().get(3));
        assertEquals(liveProduct4.getProductId(), respData.getProductIdList().get(2));

        // 验证按照品牌名称查询
        req = new GetProductListByLiveIdReq();
        req.setLiveId(live.getActivityId());
        req.setSearchType(String.valueOf(ProductInLiveSearchTypeEnum.BrandName.getCode()));
        req.setKeyword(liveProduct1.getBrandName());

        response = liveQueryFacade.getProductListByLiveId(req);

        assertNotNull(response);
        assertEquals(200, response.getCode());

        respData = (GetProductListByLiveIdRespData) response.getData();
        assertNotNull(respData);
        assertEquals(1, respData.getProductIdList().size());
        assertEquals(liveProduct1.getProductId(), respData.getProductIdList().get(0));

        // 验证按照品类名称查询
        req = new GetProductListByLiveIdReq();
        req.setLiveId(live.getActivityId());
        req.setSearchType(ProductInLiveSearchTypeEnum.CategoryName.getMessage());
        req.setKeyword(liveProduct3.getThirdCategoryName());

        response = liveQueryFacade.getProductListByLiveId(req);

        assertNotNull(response);
        assertEquals(200, response.getCode());

        respData = (GetProductListByLiveIdRespData) response.getData();
        assertNotNull(respData);
        assertEquals(1, respData.getProductIdList().size());
        assertEquals(liveProduct3.getProductId(), respData.getProductIdList().get(0));

        // 验证按照品牌Id进行过滤
        req = new GetProductListByLiveIdReq();
        req.setLiveId(live.getActivityId());
        req.setSearchType(ProductInLiveSearchTypeEnum.BrandIdAndCategoryId.getMessage());
        req.setBrandIdList(liveProduct1.getBrandId() + "," + liveProduct4.getBrandId());

        response = liveQueryFacade.getProductListByLiveId(req);

        assertNotNull(response);
        assertEquals(200, response.getCode());

        respData = (GetProductListByLiveIdRespData) response.getData();
        assertNotNull(respData);
        assertEquals(2, respData.getProductIdList().size());
        assertEquals(liveProduct1.getProductId(), respData.getProductIdList().get(0));
        assertEquals(liveProduct4.getProductId(), respData.getProductIdList().get(1));

        // 验证按照类别Id进行过滤
        req = new GetProductListByLiveIdReq();
        req.setLiveId(live.getActivityId());
        req.setSearchType(ProductInLiveSearchTypeEnum.BrandIdAndCategoryId.getMessage());
        req.setThirdCategoryIdList(liveProduct2.getThirdCategoryId() + "," + liveProduct3.getSecondCategoryId());

        response = liveQueryFacade.getProductListByLiveId(req);

        assertNotNull(response);
        assertEquals(200, response.getCode());

        respData = (GetProductListByLiveIdRespData) response.getData();
        assertNotNull(respData);
        assertEquals(2, respData.getProductIdList().size());
        assertEquals(liveProduct2.getProductId(), respData.getProductIdList().get(0));
        assertEquals(liveProduct3.getProductId(), respData.getProductIdList().get(1));


        // 验证按照品牌和类别Id进行过滤
        req = new GetProductListByLiveIdReq();
        req.setLiveId(live.getActivityId());
        req.setSearchType(ProductInLiveSearchTypeEnum.BrandIdAndCategoryId.getMessage());
        req.setBrandIdList(String.valueOf(liveProduct3.getBrandId()));
        req.setThirdCategoryIdList(liveProduct2.getThirdCategoryId() + "," + liveProduct3.getSecondCategoryId());

        response = liveQueryFacade.getProductListByLiveId(req);

        assertNotNull(response);
        assertEquals(200, response.getCode());

        respData = (GetProductListByLiveIdRespData) response.getData();
        assertNotNull(respData);
        assertEquals(1, respData.getProductIdList().size());
        assertEquals(liveProduct3.getProductId(), respData.getProductIdList().get(0));
    }

}
