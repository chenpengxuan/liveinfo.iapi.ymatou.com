package com.ymatou.liveinfo.facade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 * 自身内容能以可读方式输出
 * 
 * @author wangxudong
 *
 */
public abstract class PrintFriendliness implements Serializable {

    /**
     * 序列化版本
     */
    private static final long serialVersionUID = -8504829055186474478L;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":"
                + JSON.toJSONString(this, SerializerFeature.WriteDateUseDateFormat,
                        SerializerFeature.SkipTransientField);
    }

}
