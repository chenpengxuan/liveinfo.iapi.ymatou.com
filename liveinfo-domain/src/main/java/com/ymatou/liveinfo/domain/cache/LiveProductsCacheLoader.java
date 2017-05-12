package com.ymatou.liveinfo.domain.cache;

import com.google.common.cache.CacheLoader;
import com.ymatou.liveinfo.domain.config.BizConfig;
import com.ymatou.liveinfo.domain.model.Product;
import com.ymatou.liveinfo.domain.repository.LiveProductRepository;
import com.ymatou.liveinfo.domain.repository.LiveRepository;
import com.ymatou.liveinfo.domain.repository.ProductRepository;
import com.ymatou.liveinfo.facade.model.ActivityInfo;
import com.ymatou.liveinfo.facade.model.ProductInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by wangxudong on 2017/5/12.
 */
@Component
public class LiveProductsCacheLoader extends CacheLoader<Integer, Optional<List<ProductInfo>>> implements  CacheInfo  {

    @Resource
    private BizConfig bizConfig;

    @Resource
    private LiveProductRepository liveProductRepository;

    @Resource
    private ProductRepository productRepository;

    /**
     * 获取到缓存名称
     *
     * @return
     */
    @Override
    public String getCacheName() {
        return "LiveProducts";
    }

    /**
     * 获取到缓存最大数量
     *
     * @return
     */
    @Override
    public int getMaximumSize() {
        return bizConfig.getCacheLiveMaxsize();
    }

    /**
     * 获取到缓存过期时间
     *
     * @return
     */
    @Override
    public int getExpireTime() {
        return bizConfig.getCacheLiveExpireTime();
    }

    /**
     * 判断是否禁用缓存
     * @return
     */
    public boolean disableCache(){
        return bizConfig.getCacheLiveExpireTime() < 1;
    }


    /**
     * Computes or retrieves the value corresponding to {@code key}.
     *
     * @param key the non-null key whose value should be loaded
     * @return the value associated with {@code key}; <b>must not be null</b>
     * @throws Exception            if unable to load the result
     * @throws InterruptedException if this method is interrupted. {@code InterruptedException} is
     *                              treated like any other {@code Exception} in all respects except that, when it is caught,
     *                              the thread's interrupt status is set
     */
    @Override
    public Optional<List<ProductInfo>> load(Integer key) throws Exception {

        return null;
    }

    /**
     * 获取直播的商品信息
     * @param liveId
     * @param limit
     * @return
     */
    private List<ProductInfo> getProductInfoesOfLive(int liveId, int limit){
        List<ProductInfo> productInfos = new ArrayList<>();

        List<String> productIds = this.liveProductRepository.getProductIdsByLive(liveId, limit);
        if (productIds.size() > 0) {
            List<Product> products = this.productRepository.getProducts(productIds);
            for (String productId : productIds) {
                Optional<Product> productOptional = products.stream().filter(x -> productId.equals(x.getProductId())).findFirst();
                if(productOptional == null || !productOptional.isPresent())
                {
                    continue;
                }
                Product product = productOptional.get();
                ProductInfo productInfo = new ProductInfo();
                productInfo.setProductId(product.getProductId());
                productInfo.setPsp(product.isPsp());
                productInfo.setSellStatus(1);
                productInfo.setNewGuestPrice(product.calcMinPrice());
                productInfo.setPrice(product.calcMinPrice());
                productInfo.setVipPrice(product.calcMinPrice());
                if (product.getPictures() != null && product.getPictures().length > 0) {
                    productInfo.setPicUrl(product.getPictures()[0]);
                } else {
                    productInfo.setPicUrl("");
                }
                productInfos.add(productInfo);
            }
        }
        return productInfos;
    }


}
