package com.bishe.zyf.fireextinguisherselling.controller;

import com.bishe.zyf.fireextinguisherselling.dto.CartCountChangeDTO;
import com.bishe.zyf.fireextinguisherselling.interceptor.UserTokenInterceptor;
import com.bishe.zyf.fireextinguisherselling.service.CartService;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-26
 * @Description: 购物车相关接口
 */

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addCart/{productId}")
    public ResultVO<String> addCart(@PathVariable Long productId, HttpServletRequest request){
        Long userId = (Long)request.getAttribute(UserTokenInterceptor.USER_ID);
        return cartService.addCart(productId,userId);
    }

    @DeleteMapping("/delete/{id}")
    public ResultVO<String> delete(@PathVariable Long productId){
        return cartService.delete(productId);
    }

    @PostMapping("/increaseCount")
    public ResultVO<String> increaseCount(@RequestBody CartCountChangeDTO cartCountChangeDTO){
        return cartService.increaseCount(cartCountChangeDTO);
    }

    @PostMapping("/decreaseCount")
    public ResultVO<String> decreaseCount(@RequestBody CartCountChangeDTO cartCountChangeDTO){
        return cartService.decreaseCount(cartCountChangeDTO);
    }

    // 购物车结算
}
