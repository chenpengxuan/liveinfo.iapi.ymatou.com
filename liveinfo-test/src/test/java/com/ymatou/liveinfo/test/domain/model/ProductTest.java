package com.ymatou.liveinfo.test.domain.model;

import com.ymatou.liveinfo.domain.model.Product;
import com.ymatou.liveinfo.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by gejianhua on 2017/4/12.
 */
public class ProductTest extends BaseTest {

    @Test
    public void testCalcMinPrice(){
        Product product = this.bulidProduct();
        product.setPrice("10,20,6");
        Assert.assertEquals(0, product.calcMinPrice().compareTo(new BigDecimal("6")));
    }

    @Test
    public void testCalcMinPriceContainZero(){
        Product product = this.bulidProduct();
        product.setPrice("10,0,6");
        Assert.assertEquals(0, product.calcMinPrice().compareTo(new BigDecimal("6")));
    }
}
