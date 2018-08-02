package com.mmall.concurrency;

import com.mmall.concurrency.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    //在处理之前输出
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
        return true;
    }

    //在完成之后删除信息、输出日志
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestHolder.remove();
        log.info("afterCompletion");
        return;
    }
}