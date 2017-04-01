package com.ymatou.liveinfo.facade.common;


/**
 * @author wangxudong 2016年12月22日 下午3:39:08
 *
 */
public enum ResponseCode {

    SUCCESS(200, "操作成功"),

    /*
     * 参数错误 4000
     */
    ILLEGAL_ARGUMENT(400, "错误的请求参数"),

    /*
     * 通用错误 5000
     */
    FAIL(5000, "请求处理失败"),

    /*
     * 系统错误 9999
     */
    UNKNOWN(9999, "未知错误，系统异常");

    private int code;

    private String message;

    private ResponseCode(int code, String message) {
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
    public static ResponseCode getByCode(int code) {
        for (ResponseCode errorCode : ResponseCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        return null;
    }
}
