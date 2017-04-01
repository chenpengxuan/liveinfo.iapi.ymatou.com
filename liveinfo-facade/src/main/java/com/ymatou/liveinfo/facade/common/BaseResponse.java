package com.ymatou.liveinfo.facade.common;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * 响应基类. <em>其所有子类必须有默认的构造函数</em>
 * 
 * @author tuwenjie
 *
 */
public class BaseResponse extends PrintFriendliness {

    private static final long serialVersionUID = -5719901720924490738L;

    @JsonProperty("Code")
    private int code;

    @JsonProperty("Msg")
    private String message;

    @JsonProperty("BCode")
    private int bCode;

    @JsonProperty("ServerTime")
    private String serverTime;

    @JsonProperty("Data")
    private PrintFriendliness data;

    public int getbCode() {
        return bCode;
    }

    public void setbCode(int bCode) {
        this.bCode = bCode;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public PrintFriendliness getData() {
        return data;
    }

    public void setData(PrintFriendliness data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 默认构造成功的响应
     */
    public BaseResponse() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.serverTime = formatter.format(currentTime);
        this.setCode(ResponseCode.SUCCESS.getCode());
        this.setMessage(ResponseCode.SUCCESS.getMessage());
    }

    public static BaseResponse newFailInstance(ResponseCode errorCode) {
        BaseResponse result = new BaseResponse();
        result.setCode(errorCode.getCode());
        result.setMessage(errorCode.getMessage());
        return result;
    }
}
