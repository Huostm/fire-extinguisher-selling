package com.bishe.zyf.fireextinguisherselling.config;

import com.bishe.zyf.fireextinguisherselling.interceptor.AdminInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AdminInterceptor adminInterceptor;

    // ========== CORS 跨域配置 ==========
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")  // 允许所有来源（生产环境换成具体域名）
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)      // 允许携带 Cookie
                .maxAge(3600);               // 预检请求缓存时间
    }

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