package com.ymatou.liveinfo.domain.model;

import com.ymatou.liveinfo.facade.SystemFacade;
import com.ymatou.liveinfo.infrastructure.util.NetUtil;
import org.springframework.stereotype.Component;

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
                + "\"1.0.1\":\"2017-04-01.01 first deploy.\""
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
}
