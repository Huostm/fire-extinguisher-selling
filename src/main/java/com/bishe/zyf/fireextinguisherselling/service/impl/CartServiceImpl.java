package com.bishe.zyf.fireextinguisherselling.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.zyf.fireextinguisherselling.dto.CartCountChangeDTO;
import com.bishe.zyf.fireextinguisherselling.entity.Cart;
import com.bishe.zyf.fireextinguisherselling.entity.Products;
import com.bishe.zyf.fireextinguisherselling.mapper.ProductsMapper;
import com.bishe.zyf.fireextinguisherselling.service.CartService;
import com.bishe.zyf.fireextinguisherselling.mapper.CartMapper;
import com.bishe.zyf.fireextinguisherselling.utils.LoginUserContext;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【cart(购物车表)】的数据库操作Service实现
* @createDate 2026-08-24 20:36:26
*/
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart>
    implements CartService{

    @Autowired
    private ProductsMapper productsMapper;

    @Override
    public ResultVO<String> addCart(Long productId,Long userId) {
        if (productId==null){
            return ResultVO.error("请填写正确的商品id");
        }
        LambdaQueryWrapper<Products> productsQueryWrapper = new LambdaQueryWrapper<>();
        productsQueryWrapper.eq(Products::getIsDeleted,0);
        productsQueryWrapper.eq(Products::getIsActive,1);
        productsQueryWrapper.eq(Products::getId,productId);
        Products products = productsMapper.selectOne(productsQueryWrapper);
        Integer stock = products.getStock();
        if (stock<=0){
            return ResultVO.error("此灭火器剩余数量不足");
        }
        Cart productInCart = getProdcutInCart(productId, userId);
        Cart cart = new Cart();
        boolean result;
        if (productInCart == null){
            // 不存在商品
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setQuantity(1);
            cart.setSelected(0);
            result = this.save(cart);
        }else{
            cart.setQuantity(cart.getQuantity()+1);
            result = this.updateById(cart);
        }
        if (!result){
            return ResultVO.error("添加购物车失败");
        }
        return ResultVO.success("已成功添加至购物车");
    }

    @Override
    public ResultVO<String> delete(Long productId) {
        if (productId == null || productId<=0){
            return ResultVO.error("商品id有误");
        }
        Long userId = LoginUserContext.getUserId();
        Cart cart = getProdcutInCart(productId, userId);
        if (cart==null){
            return ResultVO.error("购物车不存在此商品");
        }
        boolean hasRecord = this.removeById(cart);
        if (!hasRecord){
            return ResultVO.error("删除失败");
        }
        return ResultVO.success("删除成功");
    }

    @Override
    public ResultVO<String> increaseCount(CartCountChangeDTO cartCountChangeDTO) {
        if(cartCountChangeDTO == null){
            return ResultVO.error("购物车商品选择有误");
        }
        Long userId = LoginUserContext.getUserId();
        Cart cart = getProdcutInCart(cartCountChangeDTO.getProductId(), userId);
        if (cart == null){
            return ResultVO.error("此商品购物车信息为空");
        }
        Integer quantity = cart.getQuantity();
        cart.setQuantity(++quantity);
        this.updateById(cart);
        return ResultVO.success("商品购物车数量增加成功");
    }

    @Override
    public ResultVO<String> decreaseCount(CartCountChangeDTO cartCountChangeDTO) {
        if(cartCountChangeDTO == null){
            return ResultVO.error("购物车商品选择有误");
        }
        Long userId = LoginUserContext.getUserId();
        Cart cart = getProdcutInCart(cartCountChangeDTO.getProductId(), userId);
        if (cart == null){
            return ResultVO.error("此商品购物车信息为空");
        }
        Integer quantity = cart.getQuantity();
        if (quantity>0){
            Integer result = quantity-1;
            if (result==0){
                this.removeById(cart);
            }else{
                cart.setQuantity(result);
                this.updateById(cart);
            }
        }else{
            return ResultVO.error("商品库存余量错误");
        }
        return ResultVO.success("商品购物车数量减少成功");
    }

    /**
     * 根据商品id查看用户购物车是否存在该商品
     * @param productId
     * @param userId
     * @return
     */
    private Cart getProdcutInCart(Long productId,Long userId){
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUserId,userId);
        wrapper.eq(Cart::getProductId,productId);
        return this.getOne(wrapper);
    }
}




