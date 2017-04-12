package com.ymatou.liveinfo.domain.service;

import com.google.common.cache.LoadingCache;
import com.ymatou.liveinfo.domain.cache.ActivityCacheLoader;
import com.ymatou.liveinfo.domain.cache.CacheFactory;
import com.ymatou.liveinfo.domain.config.BizConfig;
import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.domain.repository.LiveRepository;
import com.ymatou.liveinfo.facade.common.BizException;
import com.ymatou.liveinfo.facade.model.ActivityInfo;
import com.ymatou.liveinfo.facade.model.GetActivityIdsBySellerIdsRespData;
import com.ymatou.liveinfo.facade.model.GetProductListByLiveIdReq;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * Created by wangxudong on 2017/4/1.
 */
@Component
public class LiveService {

    private static Logger logger = LoggerFactory.getLogger(LiveService.class);

    @Resource
    private LiveRepository liveRepository;

    @Resource
    private CacheFactory cacheFactory;

    @Resource
    private ActivityCacheLoader activityCacheLoader;

    @Resource
    private BizConfig bizConfig;

    /**
     * 获取买手当前的直播信息
     * @param sellerId
     * @return
     */
    public ActivityInfo getSellerCurrentActivity(int sellerId){
        if(activityCacheLoader.disableCache()){ // 禁用缓存
            return activityCacheLoader.getByRepository(sellerId);
        }

        LoadingCache<Integer, Optional<ActivityInfo>> activityCache = cacheFactory.getCache( activityCacheLoader );
        try {
            Optional<ActivityInfo> cacheResult = activityCache.get(sellerId);
            if(cacheResult.isPresent()){
                return cacheResult.get();
            }else {
                return null;
            }
        } catch (ExecutionException e) {
            throw new BizException("get activeinfo from cache faild,with sellerId:" + sellerId, e);
        }
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
     * @return
     */
    public List<ActivityInfo> getInProgressActivitiesByIds(List<Integer> liveIds){
        List<ActivityInfo> activityInfos = new ArrayList<>();
        List<Live> liveList = liveRepository.getInProgressLivesByIds(liveIds);
        if(liveList != null || liveList.size() > 0){
            for (Live live: liveList) {
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
     * 查找直播中的商品
     * @param req
     * @return
     */
    public List<String> searchProductListByLiveId(GetProductListByLiveIdReq req){
        List<String> prodIds = new ArrayList<>();

        return prodIds;
    }
}
