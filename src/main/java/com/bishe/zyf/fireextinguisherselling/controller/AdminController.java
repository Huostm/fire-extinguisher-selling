package com.bishe.zyf.fireextinguisherselling.controller;

import com.bishe.zyf.fireextinguisherselling.dto.LoginRequestDTO;
import com.bishe.zyf.fireextinguisherselling.dto.RegisterRequestDTO;
import com.bishe.zyf.fireextinguisherselling.service.UserService;
import com.bishe.zyf.fireextinguisherselling.vo.LoginVO;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
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
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResultVO<String> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        return userService.register(registerRequestDTO);
    }

    @PostMapping("/login")
    public ResultVO<LoginVO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return userService.login(loginRequestDTO);
    }
}
