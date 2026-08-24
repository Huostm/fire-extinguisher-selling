package com.bishe.zyf.fireextinguisherselling.dto;

import lombok.Data;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-24
 * @Description: 后台请求登录DTO
 */

@Data
public class LoginRequestDTO {
    /**
     * 管理员账号
     */
    private String username;

    /**
     * 管理员密码
     */
    private String password;
}
