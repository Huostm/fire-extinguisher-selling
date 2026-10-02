package com.bishe.zyf.fireextinguisherselling.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.zyf.fireextinguisherselling.dto.CreateOrderDTO;
import com.bishe.zyf.fireextinguisherselling.entity.Orders;
import com.bishe.zyf.fireextinguisherselling.entity.Products;
import com.bishe.zyf.fireextinguisherselling.mapper.ProductsMapper;
import com.bishe.zyf.fireextinguisherselling.service.OrdersService;
import com.bishe.zyf.fireextinguisherselling.mapper.OrdersMapper;
import com.bishe.zyf.fireextinguisherselling.service.ProductsService;
import com.bishe.zyf.fireextinguisherselling.utils.CreateOrderSN;
import com.bishe.zyf.fireextinguisherselling.utils.LoginUserContext;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author Administrator
* @description 针对表【orders(订单主表)】的数据库操作Service实现
* @createDate 2026-08-24 20:36:43
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private CreateOrderSN createOrderSN;

    @Override
    @Transactional
    public ResultVO<String> createOrder(CreateOrderDTO createOrderDTO) {
        if (createOrderDTO==null){
            return ResultVO.error("订单有误，请重新选择");
        }
        Long productId = createOrderDTO.getProductId();
        if (productId==null || productId<=0){
            return ResultVO.error("请选择正确的商品");
        }
        ResultVO<Products> productByIdResult = productsService.getProductById(productId);
        if (!productByIdResult.isSuccess()){
            return ResultVO.error("查询商品失败");
        }
        Products product = productByIdResult.getData();
        Integer stock = product.getStock();
        Integer num = createOrderDTO.getNum();
        if (num==null || num <= 0) {
            return ResultVO.error("购买数量必须大于0");
        }
        if (num > stock) {
            return ResultVO.error("库存不足，当前库存：" + stock);
        }

        //TODO
/*        Long userId = LoginUserContext.getUserId();
        if (userId==null || userId<=0){
            return ResultVO.error("请登录正确账号");
        }*/
        String orderNo = createOrderSN.generateOrderNo();

        Orders orders = new Orders();
        orders.setOrderSn(orderNo);
        //TODO
        orders.setUserId(2L);
        orders.setReceiptInfoId(createOrderDTO.getReceiptInfoId());
        orders.setTotalAmount(getTotalAmount(productId,num));
        orders.setStatus(0);
        // TODO:加 Redis 锁
        product.setStock(--stock);
        int i = productsMapper.updateById(product);
        boolean save = this.save(orders);
        if (!save || i<=0){
            throw new RuntimeException("下单失败，请重试");
        }
        return ResultVO.success("下单成功");
    }

    /**
     * 计算总金额
     * @param productId
     * @param num
     * @return
     */
    private Long getTotalAmount(Long productId,Integer num){
        Products products = productsMapper.selectById(productId);
        Long price = products.getPrice();
        return price*num;
    }
}




