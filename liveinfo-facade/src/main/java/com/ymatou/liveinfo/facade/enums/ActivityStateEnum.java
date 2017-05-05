package com.ymatou.liveinfo.facade.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by wangxudong on 2017/4/6.
 */
public enum ActivityStateEnum {

    NotStart(-1, "即将开始"),

    InProcess(0, "直播中"),

    End(1, "已结束"),

    NotEffected(-1, "未生效");

    private int code;

    private String message;


    private ActivityStateEnum(int code, String message) {
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
    public static ActivityStateEnum getByCode(int code) {
        for (ActivityStateEnum verifyCodeEnum : ActivityStateEnum.values()) {
            if (verifyCodeEnum.getCode() == code) {
                return verifyCodeEnum;
            }
        }
        return null;
    }

    public static ActivityStateEnum parseFrom(String activityState){
        if(StringUtils.isBlank(activityState)){
            return null;
        }
        if(StringUtils.isNumeric(activityState)){
            return getByCode(Integer.parseInt(activityState));
        }

        switch (activityState){
            case "NotEffected":
                return ActivityStateEnum.NotEffected;
            case "InProcess":
                return ActivityStateEnum.InProcess;
            case "NotStart":
                return ActivityStateEnum.NotStart;
            case "End":
                return ActivityStateEnum.End;
        }
        return null;
    }
}
