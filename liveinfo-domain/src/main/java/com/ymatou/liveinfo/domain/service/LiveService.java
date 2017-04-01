package com.ymatou.liveinfo.domain.service;

import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.domain.repository.LiveRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by wangxudong on 2017/4/1.
 */
@Component
public class LiveService {

    @Resource
    private LiveRepository liveRepository;

    /**
     * 获取买手当前的直播信息
     * @param sellerId
     * @return
     */
    public Live getSellerCurrentLive(int sellerId){
        return liveRepository.getSellerCurrentLive(sellerId);
    }
}
