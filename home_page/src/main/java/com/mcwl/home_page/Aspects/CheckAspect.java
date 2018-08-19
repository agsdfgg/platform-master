package com.mcwl.home_page.Aspects;

import com.mcwl.home_page.send.HomepageSend;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author: Jerry
 * @Date: 2018/8/13
 * @Description: 接口token控制切面
 * */
@Aspect
@Component
public class CheckAspect {

    @Autowired
    HomepageSend send;

    protected static org.slf4j.Logger logger = LoggerFactory.getLogger(CheckAspect.class);

    @Pointcut("execution(public * com.mcwl.home_page.controller*..*(..)) && @annotation(com.mcwl.home_page.anntation.Check)" )
    public void check() {
    }

    @Before("check()")
    public void doBefore(JoinPoint joinPoint){
        logger.info( "进入doBefore切面");
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //发送消息，内容token到鉴权中心
        send.send(request.getHeader("token"));
        //消息内容
        logger.info("消息内容是"+request.getHeader("token"));
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(returning = "ret", pointcut = "check()")
    public void doAfterReturning(Object ret){
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }
}
