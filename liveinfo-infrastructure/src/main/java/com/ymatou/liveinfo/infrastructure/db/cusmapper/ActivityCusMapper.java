package com.ymatou.liveinfo.infrastructure.db.cusmapper;

import com.ymatou.liveinfo.facade.model.SearchActivityReq;

import java.util.List;

/**
 * Created by gejianhua on 2017/4/17.
 */
public interface ActivityCusMapper {

    /**
     * 运营后台查询直播
     * @param req
     * @return
     */
    List<List<?>> searchActivity(SearchActivityReq req);
}
