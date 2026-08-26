package com.bishe.zyf.fireextinguisherselling.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-25
 * @Description: 小程序微信登录请求DTO
 */

@Data
public class WechatLoginDTO {

    /**
     * wx.login() 返回的临时登录凭证 code
     */
    @NotBlank(message = "code不能为空")
    private String code;
}
