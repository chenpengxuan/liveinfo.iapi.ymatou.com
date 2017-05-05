package com.ymatou.liveinfo.test.domain.model;

import com.ymatou.liveinfo.domain.model.Product;
import com.ymatou.liveinfo.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by gejianhua on 2017/4/12.
 */
public class ProductTest extends BaseTest {

    @Test
    public void testCalcMinPrice(){
        Product product = this.bulidProduct();
        product.setPrice("10,20,6");
        assertEquals(0, product.calcMinPrice().compareTo(new BigDecimal("6")));
    }

    @Test
    public void testCalcMinPriceContainZero(){
        Product product = this.bulidProduct();
        product.setPrice("10,0,6");
        assertEquals(0, product.calcMinPrice().compareTo(new BigDecimal("6")));
    }

    @Test
    public void testDouble(){
        String in = "1000";
        String out = String.valueOf(Double.valueOf(in) / 1000);

        assertEquals("1.0", out);

    }
}
