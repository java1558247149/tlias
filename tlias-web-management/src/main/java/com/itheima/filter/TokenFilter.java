package com.itheima.filter;


import com.itheima.pojo.LoginInfo;

import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtsUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取请求路径
        String uri = request.getRequestURI();
        log.info("请求路径：{}", uri);
        //2.判断请求路径是否是登录路径
        if (uri.contains("/login")) {
            log.info("登录操作");
            filterChain.doFilter(request, response);
            return;
        }

        //3.获取请求头中的token
        String token = request.getHeader("token");
        log.info("请求令牌：{}", token);
        //4.判断token是否为空，如果为空，则返回错误结果
        if (token == null) {
            log.info("请求令牌为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //5.解析token，如果解析失败，则返回错误结果
        try {
            log.info("开始解析令牌");
            Claims claims = JwtsUtil.parseToken(token);
            Object o = claims.get("id");
            CurrentHolder.setCurrentId((Integer) o);
        } catch (Exception e) {
            log.info("解析令牌失败");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //6.解析成功，则放行
        log.info("令牌解析成功");
        filterChain.doFilter(request, response);

        //删除共享数据
        CurrentHolder.remove();
    }
}
