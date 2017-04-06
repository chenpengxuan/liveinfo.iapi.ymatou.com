package com.ymatou.liveinfo.facade.enums;

/**
 * Created by wangxudong on 2017/4/6.
 */
public enum LiveActionEnum {

    Available(1, "可用的"),

    Disable(0, "不可用"),

    Deleted(-1, "删除"),

    WhiteList(-2, "不在金牌买手内，也是下线状态");

    private int code;

    private String message;


    private LiveActionEnum(int code, String message) {
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
    public static LiveActionEnum getByCode(int code) {
        for (LiveActionEnum verifyCodeEnum : LiveActionEnum.values()) {
            if (verifyCodeEnum.getCode() == code) {
                return verifyCodeEnum;
            }
        }
        return null;
    }
}
