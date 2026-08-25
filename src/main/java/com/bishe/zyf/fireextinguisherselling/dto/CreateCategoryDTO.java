package com.bishe.zyf.fireextinguisherselling.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-25
 * @Description: 新建灭火器类型请求DTO
 */

@Data
public class CreateCategoryDTO {

    /**
     * 灭火器类型名称
     */
    @NotBlank(message = "灭火器类型名称不能为空")
    private String name;

    /**
     * 检测周期，单位：年'
     */
    @NotNull(message = "检测周期不能为空")
    private Integer testCycle;

    /**
     * 首次维修后的检测周期，单位：年
     */
    @NotNull(message = "首次维修后的检测周期")
    private Integer afterFixTestCycle;

    /**
     * 报废年限，单位：年
     */
    @NotNull(message = "报废年限不能为空")
    private Integer serviceLifeYears;

}
