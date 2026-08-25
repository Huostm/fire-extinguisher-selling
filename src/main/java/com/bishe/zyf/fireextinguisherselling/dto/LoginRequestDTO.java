package com.bishe.zyf.fireextinguisherselling.dto;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "账号不能为空")
    private String username;

    /**
     * 管理员密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
