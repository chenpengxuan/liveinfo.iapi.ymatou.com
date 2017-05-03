package com.ymatou.liveinfo.facade.enums;

/**
 * 直播商品查询方式
 *
 * Created by wangxudong on 2017/4/12.
 */
public enum ProductInLiveSearchTypeEnum {

    // 全部
    All(0, "All"),

    // 按品牌名称查询
    BrandName(1, "BrandName"),

    // 按分类名称查询
    CategoryName(2, "CategoryName"),

    // 按品牌和分类编号查询
    BrandIdAndCategoryId(3, "BrandIdAndCategoryId");

    private int code;

    private String message;


    private ProductInLiveSearchTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 通过代码获取枚举项
     *
     * @param code
     * @return
     */
    public static ProductInLiveSearchTypeEnum getByCode(int code) {
        for (ProductInLiveSearchTypeEnum verifyCodeEnum : ProductInLiveSearchTypeEnum.values()) {
            if (verifyCodeEnum.getCode() == code) {
                return verifyCodeEnum;
            }
        }
        return null;
    }

    /**
     * 通过Message获取枚举
     * @param msg
     * @return
     */
    public static ProductInLiveSearchTypeEnum getByMessage(String msg) {
        for (ProductInLiveSearchTypeEnum enumItem : ProductInLiveSearchTypeEnum.values()) {
            if (enumItem.getMessage().equalsIgnoreCase(msg)) {
                return enumItem;
            }
        }
        return null;
    }
}
