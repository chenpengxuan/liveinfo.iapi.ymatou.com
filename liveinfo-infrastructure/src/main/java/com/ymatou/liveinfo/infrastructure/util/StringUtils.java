/**
 * (C) Copyright 2017 Ymatou (http://www.ymatou.com/).
 *
 * All rights reserved.
 */
package com.ymatou.liveinfo.infrastructure.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;


/**
 * 字符串工具类
 * 
 * @author wangxudong 2017年1月1日 下午7:27:34
 *
 */
public class StringUtils {

    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     * 获取到Utf8编码的字符串字节数
     * 
     * @param sourcce
     * @return
     */
    static public int getUtf8ByteLength(String source) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(source)) {
            return 0;
        }

        try {
            return source.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException e) {
            logger.error("getUtf8ByteLength failed, source:" + source, e);
            throw new RuntimeException("getUtf8ByteLength failed, source:" + source, e);
        }
    }

    /**
     * 获取到字符串的长度
     * 
     * 原理：字符串中所有的非标准字符（双字节字符）替换成两个标准字符（**，或其他的也可以）。这样就可以直接例用length方法获得字符串的字节长度了
     * 
     * @param source
     * @return
     */
    static public int getByteLength(String source) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(source)) {
            return 0;
        }

        String destString = source.replaceAll("[^\\x00-\\xff]", "**");
        return destString.length();
    }

    /**
     * 构建自定长度 的字符串
     * 
     * @param charStr
     * @param length
     * @return
     */
    static public String buildString(String charStr, int length) {
        StringBuffer stringBuffer = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            stringBuffer.append(charStr);
        }

        return stringBuffer.toString();
    }
}
