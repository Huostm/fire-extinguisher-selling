package com.bishe.zyf.fireextinguisherselling.vo;

import lombok.Data;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-25
 * @Description: 小程序微信登录VO
 */

@Data
public class WechatLoginVO {

    /**
     * 登录令牌，后续请求放到请求头 Authorization 里
     */
    private String token;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 用户身份 0：普通用户 1：管理员
     */
    private Integer userType;
}
