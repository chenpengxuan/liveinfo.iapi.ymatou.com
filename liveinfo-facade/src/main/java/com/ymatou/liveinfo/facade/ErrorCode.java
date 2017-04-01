package com.ymatou.liveinfo.facade;


/**
 * @author wangxudong 2016年12月22日 下午3:39:08
 *
 */
public enum ErrorCode {

    /*
     * 参数错误 4000
     */
    ILLEGAL_ARGUMENT(4000, "错误的请求参数"),

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

    private ErrorCode(int code, String message) {
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
    public static ErrorCode getByCode(int code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        return null;
    }
}
