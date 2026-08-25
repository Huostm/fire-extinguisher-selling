package com.bishe.zyf.fireextinguisherselling.vo;

import lombok.Data;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-25
 * @Description: 灭火器类别响应VO
 */

@Data
public class CategoryVO {
    private Long id;
    private String name;
    private Integer testCycle;
    private Integer serviceLifeYears;
    private Integer isActive;
    private Integer afterFixTestCycle;
    private String inspectionCycle;
}
