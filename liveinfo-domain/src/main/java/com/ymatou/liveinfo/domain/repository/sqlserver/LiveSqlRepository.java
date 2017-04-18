package com.ymatou.liveinfo.domain.repository.sqlserver;

import com.ymatou.liveinfo.facade.model.SearchActivityReq;
import com.ymatou.liveinfo.infrastructure.db.cusmapper.ActivityCusMapper;
import com.ymatou.liveinfo.infrastructure.db.model.ActivityPo;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by gejianhua on 2017/4/17.
 */
@Component
public class LiveSqlRepository {

    @Autowired
    private ActivityCusMapper cusMapper;

    /**
     * 运营后台查询直播
     * @param req
     * @return
     */
    public ImmutablePair<Integer, List<ActivityPo>> searchActivity(SearchActivityReq req){
        List<List<?>> lists = this.cusMapper.searchActivity(req);
        int recordCount = ((List<Integer>) lists.get(0)).get(0);
        List<ActivityPo> activityPos = (List<ActivityPo>)lists.get(1);

        ImmutablePair<Integer, List<ActivityPo>> result = new ImmutablePair<>(recordCount, activityPos);
        return result;
    }
}

















































