package com.bishe.zyf.fireextinguisherselling.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-24
 * @Description: 注册接口请求DTO
 */

@Data
public class RegisterRequestDTO {

    /**
     * 管理员密钥
     */
    @NotBlank(message = "管理员密钥不能为空")
    private String checkKey;

    /**
     * 管理员账号
     */
    @NotBlank(message = "账号填写不能为空")
    private String username;

    /**
     * 管理员密码
     */
    @NotBlank(message = "密码填写不能为空")
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
