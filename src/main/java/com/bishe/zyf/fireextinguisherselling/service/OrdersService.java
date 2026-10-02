package com.bishe.zyf.fireextinguisherselling.service;

import com.bishe.zyf.fireextinguisherselling.dto.CreateOrderDTO;
import com.bishe.zyf.fireextinguisherselling.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;

/**
* @author Administrator
* @description 针对表【orders(订单主表)】的数据库操作Service
* @createDate 2026-08-24 20:36:43
*/
public interface OrdersService extends IService<Orders> {

    /**
     * 创建订单
     * @param createOrderDTO
     * @return
     */
    ResultVO<String> createOrder(CreateOrderDTO createOrderDTO);
}
