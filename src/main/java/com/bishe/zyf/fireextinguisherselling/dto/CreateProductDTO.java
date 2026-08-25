package com.bishe.zyf.fireextinguisherselling.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-25
 * @Description: 新增灭火器请求DTO
 */

@Data
public class CreateProductDTO {

    /**
     * 灭火器名称
     */
    @NotBlank(message = "灭火器名称不能为空")
    private String name;

    /**
     * 灭火器描述
     */
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
    private String imageUrl;

    /**
     * 灭火器分类id
     */
    @NotNull(message = "请选择商品分类")
    private Long categoryId;
}
