package com.ymatou.liveinfo.facade.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.ymatou.liveinfo.domain.config.BizConfig;
import com.ymatou.liveinfo.facade.common.BaseRequest;
import com.ymatou.liveinfo.facade.common.BaseResponse;
import com.ymatou.liveinfo.facade.common.BizException;
import com.ymatou.liveinfo.facade.common.ResponseCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Facade实现方法的AOP. 实现与业务无关的通用操作。 1，日志 2，异常处理等
 * 
 * @author tuwenjie
 *
 */
@Aspect
@Component
public class FacadeAspect {

    private static final Logger DEFAULT_LOGGER = LoggerFactory.getLogger(FacadeAspect.class);

    @Resource
    private BizConfig bizConfig;

    @Pointcut("execution(* com.ymatou.liveinfo.facade.*Facade.*(*)) && args(req)")
    public void executAccountFacade(BaseRequest req) {};

    @Around("executAccountFacade(req)")
    public Object aroundFacadeExecution(ProceedingJoinPoint joinPoint, BaseRequest req)
            throws InstantiationException, IllegalAccessException {
        long startTime = System.currentTimeMillis();
        Logger logger = DEFAULT_LOGGER;

        if (req == null) {
            logger.error("Recv: null");
            return builErrorResponse(joinPoint, ResponseCode.ILLEGAL_ARGUMENT, "request is null");
        }

        if (StringUtils.isEmpty(req.getRequestId())) {
            req.setRequestId(UUID.randomUUID().toString().replaceAll("-", ""));
        }

        MDC.put("logPrefix", getRequestFlag(req));
        logger.info("Recv:" + req);

        Object resp = null;
        try {
            req.validate();
            resp = joinPoint.proceed(new Object[] {req});

        } catch (IllegalArgumentException e) {
            resp = builErrorResponse(joinPoint, ResponseCode.ILLEGAL_ARGUMENT, e.getLocalizedMessage());
            logger.error(String.format("Invalid request:%s.", req.toString()), e);

        } catch (BizException e) {
            resp = builErrorResponse(joinPoint, e.getErrorCode(), e.getLocalizedMessage());
            logger.error(String.format("Failed to execute request: %s.", req.toString()), e);

        } catch (Throwable e) {
            resp = builErrorResponse(joinPoint, ResponseCode.UNKNOWN, e.getLocalizedMessage());
            logger.error(String.format("Unknown error in executing request:%s", req.toString()), e);
        } finally {
            if(bizConfig.isEnableRespLog()) {
                logger.info("Resp:" + resp);
            }

            long consumedTime = System.currentTimeMillis() - startTime;
            logger.info("Finished {}, Consumed:{}ms", getRequestFlag(req), consumedTime);
            MDC.clear();
        }

        return resp;
    }


    private BaseResponse builErrorResponse(ProceedingJoinPoint joinPoint, ResponseCode errorCode, String errorMsg)
            throws InstantiationException, IllegalAccessException {

        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        BaseResponse resp = (BaseResponse) ms.getReturnType().newInstance();
        resp.setCode(errorCode.getCode());

        String errorMessage = errorCode.getMessage();
        if (!StringUtils.isEmpty(errorMsg)) {
            errorMessage += "|" + errorMsg;
        }

        resp.setMessage(errorMessage);
        return resp;

    }

    private String getRequestFlag(BaseRequest req) {
        return req.getClass().getSimpleName() + "|" + req.getRequestId();
    }
}
