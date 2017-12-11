package com.shun.framework.aspect;

import com.alibaba.dubbo.rpc.RpcContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: mew <p />
 * Time: 17/11/6 17:57  <p />
 * Version: V1.0  <p />
 * Description: rpc提供者和消费者日志打印 <p />
 */
public class RpcLogAspect {

    private static Logger _log = LoggerFactory.getLogger(RpcLogAspect.class);

    /** 开始时间 */
    private long startTime = 0L;

    /** 结束时间 */
    private long endTime = 0L;

    @Before("execution(* *..api..*.*(..))")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        _log.debug("doBeforeInServiceLayer");
        startTime = System.currentTimeMillis();
    }

    @After("execution(* *..api..*.*(..))")
    public void doAfterInServiceLayer(JoinPoint joinPoint) {
        _log.debug("doAfterInServiceLayer");
    }

    @Around("execution(* *..api..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        // 是否是消费端
        boolean consumerSide = RpcContext.getContext().isConsumerSide();
        // 获取最后一次提供方或调用方IP
        String ip = RpcContext.getContext().getRemoteHost();
        // 服务url
        String rpcUrl = RpcContext.getContext().getUrl().getParameter("application");
        endTime = System.currentTimeMillis();
        _log.debug("doAround>>>result={},耗时：{}", result, endTime - startTime);

        _log.info("consumerSide={}, ip={}, url={}", consumerSide, ip, rpcUrl);
        return result;
    }

}
