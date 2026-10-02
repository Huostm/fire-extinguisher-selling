package com.bishe.zyf.fireextinguisherselling.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-10-02
 * @Description: 编辑收货信息请求DTO
 */

@Data
public class UpdateReceiptInfoDTO {

    @NotNull(message = "请选择收货信息")
    private Long id;

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
