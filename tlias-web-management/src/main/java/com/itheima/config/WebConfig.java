package com.itheima.config;

//import com.itheima.interceptor.DempInterceptor;
import com.itheima.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
    /*@Autowired
    DempInterceptor dempInterceptor;*/
    //@Autowired
    //TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("拦截器注册成功");
        //registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }
}
