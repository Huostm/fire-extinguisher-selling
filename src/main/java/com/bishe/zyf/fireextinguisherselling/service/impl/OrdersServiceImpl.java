package com.bishe.zyf.fireextinguisherselling.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.zyf.fireextinguisherselling.entity.Orders;
import com.bishe.zyf.fireextinguisherselling.service.OrdersService;
import com.bishe.zyf.fireextinguisherselling.mapper.OrdersMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【orders(订单主表)】的数据库操作Service实现
* @createDate 2026-08-24 20:36:43
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

}




