package com.ymatou.liveinfo.facade.model;

import com.ymatou.liveinfo.facade.common.BaseRequest;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * Created by wangxudong on 2017/4/11.
 */
public class ListInProgressActivitiesByIdsReq extends BaseRequest {

    /**
     * 直播Id列表
     */
    @NotEmpty(message = "直播Id不能为空")
    private List<Integer> activityIds;

    /**
     * 商品数量
     */
    private Integer productNum;

    public List<Integer> getActivityIds() {
        return activityIds;
    }

    public void setActivityIds(List<Integer> activityIds) {
        this.activityIds = activityIds;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }
}
