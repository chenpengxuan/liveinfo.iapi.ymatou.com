package com.ymatou.liveinfo.facade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ymatou.liveinfo.facade.common.PrintFriendliness;

import java.util.List;

/**
 * Created by wangxudong on 2017/4/11.
 */
public class GetActivityIdsBySellerIdsRespData extends PrintFriendliness {

    /**
     * 直播Id列表
     */
    @JsonProperty("ActivityIds")
    private List<SellerAcitvityId> activityIds;

    public List<SellerAcitvityId> getActivityIds() {
        return activityIds;
    }

    public void setActivityIds(List<SellerAcitvityId> activityIds) {
        this.activityIds = activityIds;
    }

    public static class SellerAcitvityId{

        @JsonProperty("SellerId")
        private int sellerId;

        @JsonProperty("ActivityId")
        private int activityId;

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }
    }
}
