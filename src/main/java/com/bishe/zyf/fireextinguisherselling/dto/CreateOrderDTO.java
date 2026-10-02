package com.bishe.zyf.fireextinguisherselling.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-10-02
 * @Description: 创建订单请求DTO
 */

@Data
public class CreateOrderDTO {

    /**
     * 商品id
     */
    @NotNull(message = "请选择商品")
    private Long productId;

    /**
     * 商品数量
     */
    @NotNull(message = "请填写要购买的商品数量")
    private Integer num;

    /**
     * 收货信息id
     */
    @NotNull(message = "请选择收货信息")
    private Long receiptInfoId;
}
