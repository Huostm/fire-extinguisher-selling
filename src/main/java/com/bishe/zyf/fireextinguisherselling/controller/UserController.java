package com.bishe.zyf.fireextinguisherselling.controller;

import com.bishe.zyf.fireextinguisherselling.dto.LoginRequestDTO;
import com.bishe.zyf.fireextinguisherselling.dto.RegisterRequestDTO;
import com.bishe.zyf.fireextinguisherselling.dto.UpdateProfileDTO;
import com.bishe.zyf.fireextinguisherselling.dto.WechatLoginDTO;
import com.bishe.zyf.fireextinguisherselling.interceptor.UserTokenInterceptor;
import com.bishe.zyf.fireextinguisherselling.service.UserService;
import com.bishe.zyf.fireextinguisherselling.vo.LoginVO;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import com.bishe.zyf.fireextinguisherselling.vo.WechatLoginVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-24
 * @Description: 用户相关controller
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/admin/register")
    public ResultVO<String> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){
        return userService.register(registerRequestDTO);
    }

    @PostMapping("/admin/login")
    public ResultVO<LoginVO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        return userService.login(loginRequestDTO);
    }

    @PostMapping("/wechat/login")
    public ResultVO<WechatLoginVO> wechatLogin(@Valid @RequestBody WechatLoginDTO wechatLoginDTO){
        return userService.wechatLogin(wechatLoginDTO);
    }

    /**
     * 小程序用户完善昵称（头像统一用默认头像，不需上传）
     * 当前用户由 UserTokenInterceptor 从 token(openid) 解析后放入 request
     */
    @PostMapping("/wechat/profile")
    public ResultVO<WechatLoginVO> updateProfile(@RequestBody UpdateProfileDTO updateProfileDTO,
                                                 HttpServletRequest request){
        Long userId = (Long) request.getAttribute(UserTokenInterceptor.USER_ID);
        return userService.updateNickname(userId, updateProfileDTO.getNickname());
    }
}
