package com.ymatou.liveinfo.facade;

/**
 * Created by wangxudong on 2017/4/1.
 */
public interface SystemFacade {
    /**
     * 版本号
     * @return
     */
    public String version();

    /**
     *  点火接口
     * @return
     */
    public String warmup();
}
