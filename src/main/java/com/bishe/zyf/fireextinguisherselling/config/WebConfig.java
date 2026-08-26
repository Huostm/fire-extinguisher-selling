package com.bishe.zyf.fireextinguisherselling.config;

import com.bishe.zyf.fireextinguisherselling.interceptor.AdminInterceptor;
import com.bishe.zyf.fireextinguisherselling.interceptor.UserTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AdminInterceptor adminInterceptor;

    @Autowired
    private UserTokenInterceptor userTokenInterceptor;

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
        // 管理端拦截器：校验管理员 session
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/**")          // 默认拦截所有接口
                .excludePathPatterns(
                        "/user/admin/login",                // 管理员登录放行
                        "/user/admin/register",             // 管理员注册放行
                        "/user/wechat/**",                  // 小程序登录/资料接口放行
                        "/product/user/**",                 // 用户端商品浏览放行
                        "/cart/**"                          // 购物车走下面的 token 拦截器
                );

        // 小程序用户拦截器：校验 token，放入当前用户id
        registry.addInterceptor(userTokenInterceptor)
                .addPathPatterns("/cart/**", "/user/wechat/profile")  // 需要登录的用户端接口
                .excludePathPatterns("/user/wechat/login");           // 登录本身不校验
    }
}