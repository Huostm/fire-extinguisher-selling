package com.bishe.zyf.fireextinguisherselling.service;

import com.bishe.zyf.fireextinguisherselling.dto.LoginRequestDTO;
import com.bishe.zyf.fireextinguisherselling.dto.RegisterRequestDTO;
import com.bishe.zyf.fireextinguisherselling.dto.WechatLoginDTO;
import com.bishe.zyf.fireextinguisherselling.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.zyf.fireextinguisherselling.vo.LoginVO;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import com.bishe.zyf.fireextinguisherselling.vo.WechatLoginVO;

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

    /**
     * 小程序微信登录
     * 用 code 换 openid，查库有则登录、无则自动注册普通用户，返回 token
     * @param wechatLoginDTO
     * @return
     */
    ResultVO<WechatLoginVO> wechatLogin(WechatLoginDTO wechatLoginDTO);

    /**
     * 小程序用户完善昵称
     * @param userId 当前登录用户id
     * @param nickname 用户填写的昵称
     * @return
     */
    ResultVO<WechatLoginVO> updateNickname(Long userId, String nickname);
}
