package com.healthan.liugong.apiintranet.aop;

import com.healthan.liugong.apiintranet.common.GsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @autor yb
 * @date 2019/9/26 9:41
 **/
@Aspect
@Component
public class WebLogAspect {

    public static final Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    @Around("execution(public * com.healthan.liugong.apiintranet.controller.*.*(..))")
    public Object webLog(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        StringBuilder startSb = new StringBuilder();
        startSb.append(point.getSignature().getDeclaringType().getSimpleName()).append(" ").append(point.getSignature().getName()).append(" 开始");
        log.info(startSb.toString());

        StringBuilder requestSb = new StringBuilder();
        requestSb.append("API请求参数: ").append(GsonUtil.toJson(point.getArgs()));
        log.info(requestSb.toString());

        Object o = point.proceed();

        StringBuilder responseSb = new StringBuilder();
        responseSb.append("API返回参数: ").append(GsonUtil.toJson(o));
        log.info(responseSb.toString());

        StringBuilder endSb = new StringBuilder();
        endSb.append(point.getSignature().getDeclaringType().getSimpleName()).append(" ").append(point.getSignature().getName()).append(" 结束 耗时 ").append(System.currentTimeMillis() - startTime).append(" ms");
        log.info( endSb.toString());
        return o;
    }
}