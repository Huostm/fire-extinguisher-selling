package com.bishe.zyf.fireextinguisherselling.config;

import com.bishe.zyf.fireextinguisherselling.interceptor.AdminInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/**")          // 拦截所有后台接口
                .excludePathPatterns(
                        "/user/admin/login",                // 登录放行
                        "/user/admin/register"              // 注册放行
                );
    }
}