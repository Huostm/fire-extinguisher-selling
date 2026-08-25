package com.bishe.zyf.fireextinguisherselling.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-25
 * @Description: 修改商品请求DTO
 */

@Data
public class UpdateProductDTO {
    /**
     * 灭火器名称
     */
    @NotBlank(message = "灭火器名称不能为空")
    private String name;

    /**
     * 灭火器描述
     */
    @NotBlank(message = "灭火器灭火器描述不能为空")
    private String description;

    /**
     * 灭火器价格，单位：分
     */
    @NotNull(message = "灭火器价格不能为空")
    private Long price;

    /**
     * 库存剩余数量
     */
    @NotNull(message = "库存剩余量不能为空")
    private Integer stock;

    /**
     * 商品图片
     */
    @NotBlank(message = "商品展示图不能为空")
    private String imageUrl;

    /**
     * 灭火器分类id
     */
    @NotNull(message = "请选择商品分类")
    private Long categoryId;
}
