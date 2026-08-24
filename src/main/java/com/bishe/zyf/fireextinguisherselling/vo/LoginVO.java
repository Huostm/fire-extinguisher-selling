package com.bishe.zyf.fireextinguisherselling.vo;

import lombok.Data;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-24
 * @Description: 用户登录VO
 */

@Data
public class LoginVO {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户身份
     * 0：普通用户   1：管理员
     */
    private int userType;
}
