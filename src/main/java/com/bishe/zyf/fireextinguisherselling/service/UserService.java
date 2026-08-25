package com.bishe.zyf.fireextinguisherselling.service;

import com.bishe.zyf.fireextinguisherselling.dto.LoginRequestDTO;
import com.bishe.zyf.fireextinguisherselling.dto.RegisterRequestDTO;
import com.bishe.zyf.fireextinguisherselling.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.zyf.fireextinguisherselling.vo.LoginVO;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;

/**
* @author Administrator
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2026-08-24 20:36:43
*/
public interface UserService extends IService<User> {

    /**
     * 用户登录
     * @param loginRequestDTO
     * @return
     */
    ResultVO<LoginVO> login(LoginRequestDTO loginRequestDTO);

    /**
     * 管理员注册
     * @param registerRequestDTO
     * @return
     */
    ResultVO<String> register(RegisterRequestDTO registerRequestDTO);
}
