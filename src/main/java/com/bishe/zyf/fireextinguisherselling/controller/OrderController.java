package com.bishe.zyf.fireextinguisherselling.controller;
import com.bishe.zyf.fireextinguisherselling.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-09-28
 * @Description: 订单接口
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrdersService ordersService;

}