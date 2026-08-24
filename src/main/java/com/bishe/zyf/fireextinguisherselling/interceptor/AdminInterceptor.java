package com.bishe.zyf.fireextinguisherselling.interceptor;

import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.alibaba.fastjson2.JSON;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-24
 * @Description: 管理员验证拦截器
 */

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        HttpSession session = request.getSession();
        // 检查是否登录
        Long userId = (Long)session.getAttribute("userId");
        if (userId == null){
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(ResultVO.error(401, "请先登录")));
            return false;
        }
        // 检查是否为管理员
        Integer userType = (Integer) session.getAttribute("userType");
        if (userType == null || userType != 1) {
            response.setStatus(403);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(ResultVO.error(403, "无权限访问，需要管理员身份")));
            return false;
        }

        return true;
    }

}
