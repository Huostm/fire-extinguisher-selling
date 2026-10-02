package com.bishe.zyf.fireextinguisherselling.service;

import com.bishe.zyf.fireextinguisherselling.dto.CreateReceiptInfoDTO;
import com.bishe.zyf.fireextinguisherselling.dto.UpdateReceiptInfoDTO;
import com.bishe.zyf.fireextinguisherselling.entity.OrderReceiptInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.zyf.fireextinguisherselling.vo.ListReceiptInfoVO;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import jakarta.validation.Valid;

import java.util.List;

/**
* @author Administrator
* @description 针对表【order_receipt_info(订单收货信息表（快照）)】的数据库操作Service
* @createDate 2026-08-24 20:36:43
*/
public interface OrderReceiptInfoService extends IService<OrderReceiptInfo> {

    /**
     * 创建收货信息
     * @param createReceiptInfoDTO
     * @return
     */
    ResultVO<String> createOrderReceiptInfo(CreateReceiptInfoDTO createReceiptInfoDTO);

    /**
     * 删除收货信息
     * @param id
     * @return
     */
    ResultVO<String> deleteReceiptInfo(Long id);

    /**
     * 编辑收货信息
     * @param updateReceiptInfoDTO
     * @return
     */
    ResultVO<String> updateReceiptInfo(@Valid UpdateReceiptInfoDTO updateReceiptInfoDTO);

    /**
     * 收货信息列表
     * @return
     */
    ResultVO<List<ListReceiptInfoVO>> listReceiptInfo();
}
