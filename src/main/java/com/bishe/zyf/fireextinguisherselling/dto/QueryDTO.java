package com.bishe.zyf.fireextinguisherselling.dto;

import lombok.Data;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-25
 * @Description: 分页查询DTO
 */

@Data
public class QueryDTO {
    private String keyword;
    private Integer pageNum=1;
    private Integer pageSize=10;
}
