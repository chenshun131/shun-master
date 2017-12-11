package com.shun.server.interceptor;

import com.alibaba.fastjson.JSON;
import com.shun.framework.util.RequestUtil;
import com.shun.upms.api.UpmsApiService;
import com.shun.upms.dao.po.UpmsLogPo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * User: mew <p />
 * Time: 17/11/8 17:53  <p />
 * Version: V1.0  <p />
 * Description: 日志记录AOP实现 <p />
 */
@Aspect
public class LogAspect {

    private static Logger _log = LoggerFactory.getLogger(LogAspect.class);

    // 开始时间
    private long startTime = 0L;

    // 结束时间
    private long endTime = 0L;

    @Autowired
    private UpmsApiService upmsApiService;

    @Before("execution(* *..controller..*.*(..))")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        _log.debug("doBeforeInServiceLayer");
        startTime = System.currentTimeMillis();
    }

    @After("execution(* *..controller..*.*(..))")
    public void doAfterInServiceLayer(JoinPoint joinPoint) {
        _log.debug("doAfterInServiceLayer");
    }

    @Around("execution(* *..controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        // 获取request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        UpmsLogPo upmsLogPo = new UpmsLogPo();
        // 从注解中获取操作名称、获取响应结果
        Object result = pjp.proceed();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation log = method.getAnnotation(ApiOperation.class);
            upmsLogPo.setDescription(log.value());
        }
        if (method.isAnnotationPresent(RequiresPermissions.class)) {
            RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
            String[] permissions = requiresPermissions.value();
            if (permissions.length > 0) {
                upmsLogPo.setPermissions(permissions[0]);
            }
        }
        endTime = System.currentTimeMillis();
        _log.debug("doAround>>>result={},耗时：{}", result, endTime - startTime);

        upmsLogPo.setBasePath(RequestUtil.getBasePath(request));
        upmsLogPo.setIp(RequestUtil.getIpAddr(request));
        upmsLogPo.setMethod(request.getMethod());
        if (request.getMethod().equalsIgnoreCase("GET")) {
            upmsLogPo.setParameter(request.getQueryString());
        } else {
            upmsLogPo.setParameter(ObjectUtils.toString(request.getParameterMap()));
        }
        upmsLogPo.setResult(JSON.toJSONString(result));
        upmsLogPo.setSpendTime((int) (endTime - startTime));
        upmsLogPo.setStartTime(startTime);
        upmsLogPo.setUri(request.getRequestURI());
        upmsLogPo.setUrl(ObjectUtils.toString(request.getRequestURL()));
        upmsLogPo.setUserAgent(request.getHeader("User-Agent"));
        upmsLogPo.setUsername(ObjectUtils.toString(request.getUserPrincipal()));

        // 将访问请求插入到数据库，可以直接使用 Nginx 将所有访问请求存储起来而不必浪费数据库资源
        upmsApiService.insertUpmsLogSelective(upmsLogPo);
        return result;
    }

}
