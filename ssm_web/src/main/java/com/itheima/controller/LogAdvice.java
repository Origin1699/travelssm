package com.itheima.controller;


import com.itheima.domain.SysLog;
import com.itheima.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
public class LogAdvice {
    @Autowired
    private LogService service;
    @Autowired
    private HttpServletRequest request;

    @Around("execution(* com.itheima.controller.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) {
        Object obj = null;
        try {
            //得到访问时间
            Date visitTime = new Date();
            //获取到目标对象的类型
            Class clazz = pjp.getTarget().getClass();
            //得到当前目标对象中方法的参数
            Object[] args = pjp.getArgs();
            //放行方法
            obj = pjp.proceed(args);
            if (!pjp.getSignature().getName().equals("ininBinder")) {
                //得到一个日志对象
                SysLog log = new SysLog();
                log.setMethod("类名为：" + clazz.getName() + "方法名为：" + pjp.getSignature().getName());//"类名为："+XXX+"方法名为："+XXX
                log.setExecutionTime(new Date().getTime() - visitTime.getTime());
                log.setIp(request.getRemoteAddr());
                log.setUrl(request.getRequestURI());
                log.setUsername(request.getRemoteUser());
                log.setVisitTime(visitTime);
                //日志入库
                service.logSave(log);
            }
        } catch (Throwable t) {
            //还原异常
            if (t.getMessage().equals("Access is denied")) {
                throw new AccessDeniedException("权限不足！");
            } else {
                try {
                    throw new Exception("权限不足！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            t.printStackTrace();
        }
        return obj;

    }
}
