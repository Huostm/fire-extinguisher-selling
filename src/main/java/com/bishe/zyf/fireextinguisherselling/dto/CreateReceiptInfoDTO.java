package com.bishe.zyf.fireextinguisherselling.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-09-30
 * @Description: 创建订单收获信息表请求DTO
 */

@Data
public class CreateReceiptInfoDTO {

    /**
     * 收货人id
     */
    private Long userId;

    @NotNull(message = "收货人姓名不能为空")
    private String receiverName;

    @NotNull(message = "收货人手机号不能为空")
    private String phone;

    @NotNull(message = "收货省份不能为空")
    private String province;

    @NotNull(message = "收货城市不能为空")
    private String city;

    @NotNull(message = "收货区/县不能为空")
    private String district;

    @NotNull(message = "详细地址不能为空")
    private String detailAddress;
}
