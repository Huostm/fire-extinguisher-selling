package com.bishe.zyf.fireextinguisherselling.dto;

import lombok.Data;
import lombok.NonNull;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-24
 * @Description: 注册接口请求DTO
 */

@Data
public class RegisterRequestDTO {

    @NonNull
    /**
     * 管理员密钥
     */
    private String checkKey;

    /**
     * 管理员账号
     */
    @NonNull
    private String username;

    /**
     * 管理员密码
     */
    @NonNull
    private String password;

    /**
     * 管理员昵称
     */
    private String nickname;

    /**
     * 管理员头像地址
     */
    private String avatarUrl;
}
