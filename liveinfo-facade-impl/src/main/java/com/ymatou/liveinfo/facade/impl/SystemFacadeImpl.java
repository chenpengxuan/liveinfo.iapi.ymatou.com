package com.ymatou.liveinfo.facade.impl;

import com.ymatou.liveinfo.domain.cache.CacheFactory;
import com.ymatou.liveinfo.facade.SystemFacade;
import com.ymatou.liveinfo.infrastructure.util.NetUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by wangxudong on 2017/4/1.
 */
@Component("systemFacade")
@Path("/")
public class SystemFacadeImpl implements  SystemFacade{

    @Resource
    private CacheFactory cacheFactory;

    /**
     * 版本号
     *
     * @return
     */
    @GET
    @Path("/version")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String version() {
        return "{"
                + "\"ip\":\"" + NetUtil.getHostIp() + "\","
                + "\"1.0.1\":\"2017-04-13.01 first deploy.\","
                + "\"1.0.2\":\"2017-04-21.01 add other api.\","
                + "\"1.0.3\":\"2017-04-27.01 fix api 5 beanparam.\""
                + "}";
    }

    /**
     * 点火接口
     *
     * @return
     */
    @GET
    @Path("/warmup")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String warmup() {
        return "ok";
    }

    /**
     * 缓存统计
     * @return
     */
    @GET
    @Path("/cache/stats")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String cacheStats(){
        return cacheFactory.stats();
    }
}
