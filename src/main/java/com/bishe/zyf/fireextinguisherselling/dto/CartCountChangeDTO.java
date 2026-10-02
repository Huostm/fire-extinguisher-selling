package com.bishe.zyf.fireextinguisherselling.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-26
 * @Description: 增减购物车商品数量请求DTO
 */

@Data
public class CartCountChangeDTO {
    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空")
    private Long productId;
}
