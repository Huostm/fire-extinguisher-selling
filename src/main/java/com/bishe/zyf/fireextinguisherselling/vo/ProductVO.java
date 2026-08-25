package com.bishe.zyf.fireextinguisherselling.vo;

import lombok.Data;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-25
 * @Description: 商品分页查询响应VO
 */

@Data
public class ProductVO {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Integer stock;
    private String imageUrl;
    private Integer isActive;
    private String categoryName;
}
