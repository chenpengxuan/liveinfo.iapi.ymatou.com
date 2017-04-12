package com.ymatou.liveinfo.facade.model;

import com.ymatou.liveinfo.facade.common.BaseRequest;

import javax.validation.constraints.Min;
import javax.ws.rs.QueryParam;

/**
 * Created by wangxudong on 2017/4/11.
 */
public class GetProductListByLiveIdReq extends BaseRequest {
    @QueryParam("LiveId")
    @Min(value = 1, message = "无效的直播Id")
    private int liveId;

    @QueryParam("SearchType")
    private int searchType;

    @QueryParam("Keyword")
    private String keyword;

    @QueryParam("BrandIdList")
    private String brandIdList;

    @QueryParam("ThirdCategoryIdList")
    private String thirdCategoryIdList;

    public int getLiveId() {
        return liveId;
    }

    public void setLiveId(int liveId) {
        this.liveId = liveId;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getBrandIdList() {
        return brandIdList;
    }

    public void setBrandIdList(String brandIdList) {
        this.brandIdList = brandIdList;
    }

    public String getThirdCategoryIdList() {
        return thirdCategoryIdList;
    }

    public void setThirdCategoryIdList(String thirdCategoryIdList) {
        this.thirdCategoryIdList = thirdCategoryIdList;
    }
}
