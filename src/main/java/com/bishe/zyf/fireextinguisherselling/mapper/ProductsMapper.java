package com.bishe.zyf.fireextinguisherselling.mapper;

import com.bishe.zyf.fireextinguisherselling.entity.Products;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Administrator
* @description 针对表【products(商品表)】的数据库操作Mapper
* @createDate 2026-08-24 20:36:43
* @Entity com.bishe.zyf.fireextinguisherselling.entity.Products
*/
public interface ProductsMapper extends BaseMapper<Products> {

    /**
     * 更新商品状态
     * @param id
     * @param changedStatus
     * @return
     */
    int updateStatus(Long id, Integer changedStatus);
}




