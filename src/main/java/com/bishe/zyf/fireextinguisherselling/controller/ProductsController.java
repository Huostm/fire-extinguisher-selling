package com.bishe.zyf.fireextinguisherselling.controller;

import com.bishe.zyf.fireextinguisherselling.dto.CreateProductDTO;
import com.bishe.zyf.fireextinguisherselling.dto.UpdateProductDTO;
import com.bishe.zyf.fireextinguisherselling.service.ProductsService;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-25
 * @Description: 商品相关接口
 */

@RestController
@RequestMapping("/product")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @PostMapping("/create")
    public ResultVO<String> createProduct(@Valid @RequestBody CreateProductDTO createProductDTO){
        return productsService.createProduct(createProductDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResultVO<String> deleteProduct(@PathVariable Long id){
        return productsService.deleteProduct(id);
    }

    @PutMapping("/update")
    public ResultVO<String> update(@Valid @RequestBody UpdateProductDTO updateProductDTO){
        return productsService.updateProduct(updateProductDTO);
    }

    @PutMapping("/status/{id}")
    public ResultVO<String> changeStatus(@PathVariable Long id){
        return productsService.changeStatus(id);
    }
}
