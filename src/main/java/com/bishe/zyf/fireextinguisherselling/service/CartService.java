package com.bishe.zyf.fireextinguisherselling.service;

import com.bishe.zyf.fireextinguisherselling.dto.CartCountChangeDTO;
import com.bishe.zyf.fireextinguisherselling.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;

/**
* @author Administrator
* @description 针对表【cart(购物车表)】的数据库操作Service
* @createDate 2026-08-24 20:36:26
*/
public interface CartService extends IService<Cart> {

    /**
     * 将商品添加至购物车
     * @param productId
     * @return
     */
    ResultVO<String> addCart(Long productId,Long userId);

    /**
     * 根据商品id删除购物车内容
     * @param productId
     * @return
     */
    ResultVO<String> delete(Long productId);

    /**
     * 增加购物车指定商品数量
     * @param cartCountChangeDTO
     * @return
     */
    ResultVO<String> increaseCount(CartCountChangeDTO cartCountChangeDTO);

    /**
     * 减少购物车指定商品数量
     * @param cartCountChangeDTO
     * @return
     */
    ResultVO<String> decreaseCount(CartCountChangeDTO cartCountChangeDTO);
}
