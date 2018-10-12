package com.gxk.shiro.aop;

import com.google.gson.Gson;
import com.gxk.shiro.util.GsonUtil;
import com.gxk.shiro.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//在一个通知方法中同时编写前置通知和后置通知。测试
@Aspect
@Component
@Slf4j
public class LogAspect {

    private Gson gson = GsonUtil.getGson();

    //定义切点
    @Pointcut("execution(public * com.gxk.shiro.controller.*.*(..))")
    public void pointCut(){

    }
    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        long startTime = System.currentTimeMillis();
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取请求
        assert ra != null;
        HttpServletRequest req = ra.getRequest();
        //获取相应
        HttpServletResponse res = ra.getResponse();
        //获取请求头信息中的token
        String token = req.getHeader("token");

        Map<String,Object> reqMap = new HashMap<>();
        if (!StringUtils.isEmpty(token)){
            reqMap.put("userId",JWTUtil.getUserId(token));
            reqMap.put("token",token);
        }
        reqMap.put("url",req.getRequestURI());
        reqMap.put("method",req.getMethod());
        reqMap.put("requestParams", Arrays.toString(pjp.getArgs()));

        log.info("controller请求：" + gson.toJson(reqMap));

        //result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        //-------------------------------------------------
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("status",res.getStatus());
        resMap.put("returnValue",result);
        long endTime = System.currentTimeMillis();
        resMap.put("totalTime",endTime - startTime);
        log.info("controller响应：" + gson.toJson(resMap));

        return result;
    }

}
