package com.ymatou.liveinfo.facade.common;


/**
 * 业务异常
 * 
 * @author tuwenjie
 *
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1857440708804128584L;
    private ResponseCode errorCode;

    public BizException(ResponseCode errorCode, String msg) {

        this(errorCode, msg, null);
    }

    public BizException(ResponseCode errorCode) {

        this(errorCode, "", null);
    }

    public BizException(String msg) {
        this(ResponseCode.FAIL, msg);
    }

    public BizException(String msg, Throwable cause) {
        this(ResponseCode.FAIL, msg, cause);
    }

    public BizException(ResponseCode errorCode, String msg, Throwable cause) {

        super(msg, cause);
        if (errorCode == null) {
            throw new IllegalArgumentException("errorCode is null");
        }
        this.errorCode = errorCode;
    }

    public ResponseCode getErrorCode() {
        return errorCode;
    }
}
