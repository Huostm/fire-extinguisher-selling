package com.bishe.zyf.fireextinguisherselling.controller;
import com.bishe.zyf.fireextinguisherselling.dto.CreateOrderDTO;
import com.bishe.zyf.fireextinguisherselling.service.OrdersService;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResultVO<String> createOrder(@Valid @RequestBody CreateOrderDTO createOrderDTO){
        return ordersService.createOrder(createOrderDTO);
    }
}