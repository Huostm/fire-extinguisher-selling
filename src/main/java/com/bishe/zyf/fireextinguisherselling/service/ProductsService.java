package com.bishe.zyf.fireextinguisherselling.service;

import com.bishe.zyf.fireextinguisherselling.dto.CreateProductDTO;
import com.bishe.zyf.fireextinguisherselling.dto.UpdateProductDTO;
import com.bishe.zyf.fireextinguisherselling.entity.Products;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import jakarta.validation.Valid;

/**
* @author Administrator
* @description 针对表【products(商品表)】的数据库操作Service
* @createDate 2026-08-24 20:36:43
*/
public interface ProductsService extends IService<Products> {

    /**
     * 新增商品
     * @param createProductDTO
     * @return
     */
    ResultVO<String> createProduct(CreateProductDTO createProductDTO);

    /**
     * 删除商品
     * @param id
     * @return
     */
    ResultVO<String> deleteProduct(Long id);

    /**
     * 修改商品信息
     * @param updateProductDTO
     * @return
     */
    ResultVO<String> updateProduct(@Valid UpdateProductDTO updateProductDTO);

    /**
     * 修改商品状态
     * @param id
     * @return
     */
    ResultVO<String> changeStatus(Long id);
}
