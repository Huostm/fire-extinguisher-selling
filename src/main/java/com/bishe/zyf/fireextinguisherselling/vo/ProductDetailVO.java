package com.bishe.zyf.fireextinguisherselling.vo;

import lombok.Data;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-25
 * @Description: 商品详情响应VO
 */

@Data
public class ProductDetailVO {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Integer stock;
    private String imageUrl;
    private String categoryName;
}
