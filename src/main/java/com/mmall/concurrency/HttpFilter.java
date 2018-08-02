package com.mmall.concurrency;

import com.mmall.concurrency.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 需实现默认的Filter接口
 */
@Slf4j
public class HttpFilter implements Filter {

    //初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //核心方法
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //因为是通过http请求，ServletRequest需转换为HttpServletRequest类型。
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("do filter, {}, {}", Thread.currentThread().getId(), request.getServletPath());
        //放入URL相关信息
        RequestHolder.add(Thread.currentThread().getId());
        //若该filter不是想拦截住该请求，只是做相关的数据处理，还想让其他过滤器接收到，则需最后调用filterChain.doFilter(servletRequest, servletResponse)
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //销毁
    @Override
    public void destroy() {

    }
}
