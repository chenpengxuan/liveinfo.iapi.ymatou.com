package com.ymatou.liveinfo.domain.service;

import com.google.common.cache.LoadingCache;
import com.ymatou.liveinfo.domain.cache.ActivityCacheLoader;
import com.ymatou.liveinfo.domain.cache.CacheFactory;
import com.ymatou.liveinfo.domain.config.BizConfig;
import com.ymatou.liveinfo.domain.model.Live;

import com.ymatou.liveinfo.domain.model.LiveProduct;
import com.ymatou.liveinfo.domain.model.Product;
import com.ymatou.liveinfo.domain.repository.LiveProductRepository;

import com.ymatou.liveinfo.domain.repository.LiveRepository;
import com.ymatou.liveinfo.domain.repository.ProductRepository;
import com.ymatou.liveinfo.domain.repository.sqlserver.CountrySqlRepository;
import com.ymatou.liveinfo.domain.repository.sqlserver.LiveSqlRepository;
import com.ymatou.liveinfo.domain.utils.MappingUtils;
import com.ymatou.liveinfo.facade.common.BizException;

import com.ymatou.liveinfo.facade.enums.ActivityStateEnum;
import com.ymatou.liveinfo.facade.enums.LiveActionEnum;
import com.ymatou.liveinfo.facade.enums.ProductInLiveSearchTypeEnum;
import com.ymatou.liveinfo.facade.model.*;

import com.ymatou.liveinfo.facade.model.ActivityInfo;
import com.ymatou.liveinfo.facade.model.GetActivityIdsBySellerIdsRespData;
import com.ymatou.liveinfo.facade.model.GetProductListByLiveIdReq;
import com.ymatou.liveinfo.infrastructure.db.model.ActivityPo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Created by wangxudong on 2017/4/1.
 */
@Component
public class LiveService {

    private static Logger logger = LoggerFactory.getLogger(LiveService.class);

    @Resource
    private LiveRepository liveRepository;
    @Resource
    private LiveProductRepository liveProductRepository;
    @Resource
    private ProductRepository productRepository;
    @Resource
    private LiveSqlRepository liveSqlRepository;
    @Resource
    private CountrySqlRepository countrySqlRepository;

    @Resource
    private CacheFactory cacheFactory;

    @Resource
    private ActivityCacheLoader activityCacheLoader;

    @Resource
    private BizConfig bizConfig;


    /**
     * 获取买手当前的直播信息
     *
     * @param sellerId
     * @return
     */
    public ActivityInfo getSellerCurrentActivity(int sellerId) {
        Live live = liveRepository.getSellerCurrentLive(sellerId);
        if(live == null){
            return  null;
        }

        ActivityInfo activityInfo = new ActivityInfo();
        try {
            BeanUtils.copyProperties(activityInfo, live);
            return activityInfo;
        } catch (Exception e) {
            throw new BizException("BeanUtils copyProperties Fail,with liveId:" + live.getActivityId(), e);
        }
    }

    /**
     * 获取直播详情
     * @param req
     * @return
     */
    public GetActivityInfoResp getActivityInfo(GetActivityInfoReq req) {
        //获取直播信息
        Live live = this.liveRepository.getLiveById(req.getActivityId());
        if (live == null) {
            return null;
        }
        if (live.getAction() != LiveActionEnum.Available.getCode()) {
            logger.warn(String.format("getActivityInfo live:%d is not available", req.getActivityId()));
            return null;
        }

        GetActivityInfoResp resp = new GetActivityInfoResp();
        try {
            BeanUtils.copyProperties(resp, live);
        } catch (Exception e) {
            throw new BizException("getActivityInfo BeanUtils copyProperties Fail,with liveId:" + live.getActivityId(), e);
        }
        //返回需要包含商品
        if (req.isIncludeProducts()) {
            List<ProductInfo> productInfos = this.getProductInfoesOfLive(req.getActivityId(), 10);
            resp.setProductList(productInfos);
        }

        //获取品牌品类
        if(this.bizConfig.isEnableBrandList() == false){
            return resp;
        }

        List<GetActivityInfoResp.BrandInfo> brandInfos = new ArrayList<>();
        resp.setBrandList(brandInfos);
        //获取直播品牌
        List<LiveProduct> brandsOfLive = this.liveProductRepository.getBrandsOfLive(req.getActivityId());
        for (LiveProduct brandOfLive: brandsOfLive) {
            GetActivityInfoResp.BrandInfo brandInfo = new GetActivityInfoResp.BrandInfo();
            brandInfo.setBrandEnName(brandOfLive.getBrandEnName());
            brandInfo.setBrandId(brandOfLive.getBrandId());
            brandInfo.setBrandName(brandOfLive.getBrandName());
            brandInfo.setBrandType(1);
            brandInfo.setCategoryId(0);
            brandInfo.setParentId(0);
            brandInfo.setParentName("");
            brandInfos.add(brandInfo);
        }
        //获取直播品类
        List<LiveProduct> categoriesOfLive = this.liveProductRepository.getCategoriesOfLive(req.getActivityId());
        for (LiveProduct categoryOfLive: categoriesOfLive) {
            GetActivityInfoResp.BrandInfo brandInfo = new GetActivityInfoResp.BrandInfo();
            brandInfo.setBrandEnName("");
            brandInfo.setBrandId(0);
            brandInfo.setBrandName("");
            brandInfo.setBrandType(2);
            brandInfo.setCategoryId(categoryOfLive.getThirdCategoryId());
            brandInfo.setParentId(categoryOfLive.getSecondCategoryId());
            brandInfo.setParentName(categoryOfLive.getSecondCategoryName());
            brandInfos.add(brandInfo);
        }
        return resp;
    }

    /**
     * 获取买手直播列表
     * @param req
     * @return
     */
    public GetSellerLivesResp getSellerLives(GetSellerLivesReq req){
        GetSellerLivesResp resp = new GetSellerLivesResp();

        //获取当前进行的直播
        ActivityComplexInfo currentActivityInfo = null;
        Live currentLive = this.liveRepository.getSellerCurrentLive(req.getSellerId());
        if(currentLive != null){
            currentActivityInfo = MappingUtils.toActivityComplexInfo(currentLive);

            //获取当前直播商品
            currentActivityInfo.setProductList(this.getProductInfoesOfLive(currentActivityInfo.getActivityId(), 3));
        }
        resp.setInProgressActivity(currentActivityInfo);

        //获取商家历史直播
        List<ActivityInfo> historyActivityInfoes = new ArrayList<>();
        if(req.isSingle() == false){
            List<Live> lives = this.liveRepository.getSellerHistoryLives(req.getSellerId(), this.bizConfig.getHistoryLiveQuantity());
            historyActivityInfoes = MappingUtils.toActivityInfoes(lives);
        }
        resp.setHistoryActivities(historyActivityInfoes);
        return resp;
    }

    /**
     * 获取直播的商品信息
     * @param liveId
     * @param limit
     * @return
     */
    private List<ProductInfo> getProductInfoesOfLive(int liveId, int limit){
        List<ProductInfo> productInfos = new ArrayList<>();

        List<String> productIds = this.liveProductRepository.getProductIdsByLive(liveId, 10);
        if (productIds.size() > 0) {
            List<Product> products = this.productRepository.getProducts(productIds);
            for (Product product : products) {
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

    /**
     * 根据卖家id列表获取正在进行中直播列表
     *
     * @param sellerIdList
     * @return
     */
    public List<ActivityInfo> getSellerCurrentActivityList(List<Integer> sellerIdList){
        List<ActivityInfo> activityInfos = new ArrayList<>();
        List<Live> sellerCurrentLiveList = liveRepository.getSellerCurrentLiveList(sellerIdList);
        if(sellerCurrentLiveList != null || sellerCurrentLiveList.size() > 0){
            for (Live live: sellerCurrentLiveList) {
                ActivityInfo activityInfo = new ActivityInfo();
                try {
                    BeanUtils.copyProperties(activityInfo, live);
                    activityInfos.add(activityInfo);
                } catch (Exception e) {
                    throw new BizException("BeanUtils copyProperties Fail,with liveId:" + live.getActivityId(), e);
                }
            }
        }
        return activityInfos;
    }

    /**
     * 根据卖家id列表获取正在进行中直播Id列表
     *
     * @param sellerIdList
     * @return
     */
    public List<GetActivityIdsBySellerIdsRespData.SellerAcitvityId> getSellerCurrentActivityIdList(List<Integer> sellerIdList){
        List<GetActivityIdsBySellerIdsRespData.SellerAcitvityId> sellerAcitvityIds = new ArrayList<>();
        List<Live> sellerCurrentLiveList = liveRepository.getSellerCurrentLiveIdList(sellerIdList);
        if(sellerCurrentLiveList != null || sellerCurrentLiveList.size() > 0){
            for (Live live: sellerCurrentLiveList) {
                GetActivityIdsBySellerIdsRespData.SellerAcitvityId sellerAcitvityId = new GetActivityIdsBySellerIdsRespData.SellerAcitvityId();
                try {
                    BeanUtils.copyProperties(sellerAcitvityId, live);
                    sellerAcitvityIds.add(sellerAcitvityId);
                } catch (Exception e) {
                    throw new BizException("BeanUtils copyProperties Fail,with liveId:" + live.getActivityId(), e);
                }
            }
        }
        return sellerAcitvityIds;
    }

    /**
     * 根据卖家id列表获取正在进行中直播列表
     *
     * @param liveIds
     * @param prodNum 商品数量
     * @return
     */
    public List<ActivityComplexInfo> getInProgressActivitiesByIds(List<Integer> liveIds, int prodNum){
        List<ActivityComplexInfo> activityInfos = new ArrayList<>();
        List<Live> liveList = liveRepository.getInProgressLivesByIds(liveIds);
        if(liveList != null || liveList.size() > 0){
            for (Live live: liveList) {
                ActivityComplexInfo activityInfo = new ActivityComplexInfo();
                try {
                    BeanUtils.copyProperties(activityInfo, live);
                    activityInfos.add(activityInfo);
                } catch (Exception e) {
                    throw new BizException("BeanUtils copyProperties Fail,with liveId:" + live.getActivityId(), e);
                }
            }
        }

        if(activityInfos.size() == 0 || prodNum < 1)
        {
            return activityInfos;
        }

        for (ActivityComplexInfo activityComplexInfo : activityInfos){
            List<ProductInfo> productInfos = getProductInfoesOfLive(activityComplexInfo.getActivityId(), prodNum);
            activityComplexInfo.setProductList(productInfos);
        }
        return activityInfos;
    }

    /**
     * 查找直播中的商品
     * @param req
     * @return
     */
    public List<String> searchProductListByLiveId(GetProductListByLiveIdReq req){
        List<String> prodIds = new ArrayList<>();
        List<LiveProduct> liveProducts = liveProductRepository.getLiveProductsByLive(req.getLiveId());

        if(liveProducts == null || liveProducts.size() == 0){
            return prodIds;
        }

        ProductInLiveSearchTypeEnum searchTypeEnum = ProductInLiveSearchTypeEnum.getByCode(req.getSearchType());
        if(searchTypeEnum == null || ProductInLiveSearchTypeEnum.All.equals(searchTypeEnum)){
            return liveProducts.stream().map(liveProduct -> liveProduct.getProductId()).collect(Collectors.toList());
        }

        if(StringUtils.isBlank(req.getKeyword()) && !ProductInLiveSearchTypeEnum.BrandIdAndCategoryId.equals(searchTypeEnum)){
            return liveProducts.stream().map(liveProduct -> liveProduct.getProductId()).collect(Collectors.toList());
        }

        switch (searchTypeEnum) {
            case All:
                prodIds = liveProducts.stream().map(liveProduct -> liveProduct.getProductId()).collect(Collectors.toList());
                break;
            case BrandName:
                prodIds = liveProducts.stream().filter(liveProduct -> {
                    return !StringUtils.isBlank(liveProduct.getBrandName())
                            && liveProduct.getBrandName().equalsIgnoreCase(req.getKeyword());
                }).map(liveProduct -> liveProduct.getProductId()).collect(Collectors.toList());
                break;
            case CategoryName:
                prodIds = liveProducts.stream().filter(liveProduct -> {
                    return !StringUtils.isBlank(liveProduct.getThirdCategoryName())
                            && liveProduct.getThirdCategoryName().equalsIgnoreCase(req.getKeyword());
                }).map(liveProduct -> liveProduct.getProductId()).collect(Collectors.toList());
                break;
            case BrandIdAndCategoryId:
                prodIds = filterLiveProductByBrandIdOrCategoryId(req.getBrandIdList(), req.getThirdCategoryIdList(), liveProducts);
                break;
        }
        return prodIds;
    }

    /**
     * 根据品牌Id和品类Id列表进行过滤
     * @param brandIdList
     * @param categoryIdList
     * @param liveProducts
     * @return
     */
    private List<String> filterLiveProductByBrandIdOrCategoryId(String brandIdList, String categoryIdList, List<LiveProduct> liveProducts){
        String[] brandIds = StringUtils.isBlank(brandIdList) ? new String[0]: brandIdList.split(",");
        String[] categoryIds = StringUtils.isBlank(categoryIdList) ? new String[0]: categoryIdList.split(",");

        if(brandIds.length > 0 && categoryIds.length > 0) {
            return liveProducts.stream().filter(liveProduct -> {
                return Arrays.stream(brandIds).anyMatch(x -> x.equals(String.valueOf(liveProduct.getBrandId()))) &&
                        Arrays.stream(categoryIds).anyMatch(x -> x.equals(String.valueOf(liveProduct.getThirdCategoryId()))
                                || x.equals(String.valueOf(liveProduct.getSecondCategoryId())));

            }).map(liveProduct -> liveProduct.getProductId()).collect(Collectors.toList());

        }else if(brandIds.length > 0){
            return liveProducts.stream().filter(liveProduct -> {
                return Arrays.stream(brandIds).anyMatch(x -> x.equals(String.valueOf(liveProduct.getBrandId())));

            }).map(liveProduct -> liveProduct.getProductId()).collect(Collectors.toList());

        }else if(categoryIds.length > 0){
            return liveProducts.stream().filter(liveProduct -> {
                return Arrays.stream(categoryIds).anyMatch(x -> x.equals(String.valueOf(liveProduct.getThirdCategoryId()))
                        || x.equals(String.valueOf(liveProduct.getSecondCategoryId())));

            }).map(liveProduct -> liveProduct.getProductId()).collect(Collectors.toList());

        }else{
            return liveProducts.stream().map(liveProduct -> liveProduct.getProductId()).collect(Collectors.toList());
        }
    }

    /**
     * 根据直播Id获取到直播信息
     * @param activityId
     * @return
     */
    public ActivityInfo getActivityById(int activityId) {
        Live live = liveRepository.getAvaiableLiveById(activityId);
        if(live == null){
            return  null;
        }

        ActivityInfo activityInfo = new ActivityInfo();
        try {
            BeanUtils.copyProperties(activityInfo, live);
            return activityInfo;
        } catch (Exception e) {
            throw new BizException("BeanUtils copyProperties Fail,with liveId:" + live.getActivityId(), e);
        }
    }

    /**
     * 获取买手最近的直播（没有当前就取历史）
     * @param sellerId
     * @return
     */
    public ActivityInfo getSellerLatestLive(int sellerId) {
        Live live = liveRepository.getSellerCurrentLive(sellerId);
        if(live == null){
            live = liveRepository.getSellerLatestHistoryLive(sellerId);
        }

        if(live == null){
            return null;
        }

        ActivityInfo activityInfo = new ActivityInfo();
        try {
            BeanUtils.copyProperties(activityInfo, live);
            return activityInfo;
        } catch (Exception e) {
            throw new BizException("BeanUtils copyProperties Fail,with liveId:" + live.getActivityId(), e);
        }
    }

    /**
     * 获取买手当前的直播信息_缓存优化版本_暂时为使用
     *
     * @param sellerId
     * @return
     */
    public ActivityInfo getSellerCurrentActivity_bak(int sellerId) {
        if (activityCacheLoader.disableCache()) { // 禁用缓存
            return activityCacheLoader.getByRepository(sellerId);
        }

        LoadingCache<Integer, Optional<ActivityInfo>> activityCache = cacheFactory.getCache(activityCacheLoader);
        try {
            Optional<ActivityInfo> cacheResult = activityCache.get(sellerId);
            if (cacheResult.isPresent()) {
                return cacheResult.get();
            } else {
                return null;
            }
        } catch (ExecutionException e) {
            throw new BizException("get activeinfo from cache faild,with sellerId:" + sellerId, e);
        }
    }

    /**
     * 运营后台查询直播
     * @param req
     * @return
     */
    public SearchActivityResp searchActivity(SearchActivityReq req){
        if(req.getPageIndex() <= 0){
            req.setPageIndex(1);
        }
        if(req.getPageSize() <= 0){
            req.setPageSize(20);
        }
        if(req.getActivityState() == ActivityStateEnum.End.getCode()){
            req.setSortField("dEndTime");
            req.setSortType("desc");
        }

        DateTime dateTime = DateTime.now();
        dateTime = dateTime.plusHours(1);
        req.setWillEndTime(dateTime.toDate());

        if(req.getAreaId() > 0 && req.getCountryId() <= -1){
            List<Integer> countryIds = this.countrySqlRepository.getCountryIds(req.getAreaId());
            req.setCountryIds(countryIds);
        }

        ImmutablePair<Integer, List<ActivityPo>> result = this.liveSqlRepository.searchActivity(req);
        SearchActivityResp resp = new SearchActivityResp();
        resp.setTotalRecords(result.getLeft());
        resp.setActivityList(MappingUtils.toActivityInfos(result.getRight()));

        return resp;
    }
}















































