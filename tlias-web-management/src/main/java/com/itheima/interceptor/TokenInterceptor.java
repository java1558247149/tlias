package com.itheima.interceptor;

import com.itheima.utils.JwtsUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Slf4j
//@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("1111");
        //1.获取请求路径
        String uri = request.getRequestURI();
        log.info("请求路径：{}", uri);
        //2.判断请求路径是否是登录路径
        if (uri.contains("/login")) {
            log.info("登录操作");

            return true;
        }

        //3.获取请求头中的token
        String token = request.getHeader("token");
        log.info("请求令牌：{}", token);
        //4.判断token是否为空，如果为空，则返回错误结果
        if (token == null) {
            log.info("请求令牌为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //5.解析token，如果解析失败，则返回错误结果
        try {
            log.info("开始解析令牌");
            JwtsUtil.parseToken(token);
        } catch (Exception e) {
            log.info("解析令牌失败");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return  false;
        }
        //6.解析成功，则放行
        log.info("令牌解析成功");
        return true;
    }
}
