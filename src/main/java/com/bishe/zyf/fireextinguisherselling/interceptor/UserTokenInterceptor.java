package com.bishe.zyf.fireextinguisherselling.interceptor;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bishe.zyf.fireextinguisherselling.utils.LoginUserContext;
import com.bishe.zyf.fireextinguisherselling.entity.User;
import com.bishe.zyf.fireextinguisherselling.mapper.UserMapper;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-25
 * @Description: 小程序用户拦截器
 * 前端请求头 Authorization 里带的就是登录时返回的 openid，
 * 这里用 openid 查 user 表拿到 userId，放进 request attribute + ThreadLocal上下文，
 * Controller可用request.getAttribute("userId")，Service直接LoginUserContext.getUserId()获取当前用户。
 * 只拦截需要登录的用户端接口（如购物车），不拦商品浏览和登录接口。
 */
@Component
public class UserTokenInterceptor implements HandlerInterceptor {

    /** 当前登录用户 id 在 request 中的 key */
    public static final String USER_ID = "userId";

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        String authorization = request.getHeader("Authorization");
        if (authorization != null && !authorization.isEmpty()) {
            // 兼容 Bearer 格式 Authorization: Bearer openid
            String openid;
            if (authorization.startsWith("Bearer ")) {
                openid = authorization.substring(7);
            } else {
                openid = authorization;
            }

            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getWechatOpenid, openid);
            wrapper.eq(User::getIsDeleted, 0);
            User user = userMapper.selectOne(wrapper);

            if (user != null) {
                // 判断账号是否封禁
                if (user.getStatus() == 1) {
                    response.setStatus(403);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(JSON.toJSONString(ResultVO.error(403, "账号已被封禁")));
                    return false;
                }
                request.setAttribute(USER_ID, user.getId());
                // 存入ThreadLocal，Service中直接获取
                LoginUserContext.setUserId(user.getId());
                return true;
            }
        }

        response.setStatus(401);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(ResultVO.error(401, "请先登录")));
        return false;
    }

    /**
     * 请求完成后清理ThreadLocal，防止内存泄漏
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginUserContext.clear();
    }
}
