package com.ymatou.liveinfo.test.domain.repository;

import com.alibaba.fastjson.JSON;
import com.ymatou.liveinfo.domain.repository.LiveProductRepository;
import com.ymatou.liveinfo.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gejianhua on 2017/4/10.
 */
public class LiveProductRepositoryTest extends BaseTest {

    @Resource
    private LiveProductRepository liveProductRepository;

    @Test
    public void testGetProductIdByLive(){
        List<String> list = this.liveProductRepository.getProductIdsByLive(157818, 10);

        System.out.println(JSON.toJSONString(list));
        Assert.assertNotNull(list);
    }

    @Test
    public void testGetCategoriesOfLive(){
        System.out.println(this.liveProductRepository.getCategoriesOfLive(157818).toString());
    }

    @Test
    public void testGetBrandsOfLive(){
        System.out.println(this.liveProductRepository.getBrandsOfLive(157818).toString());
    }

}











































