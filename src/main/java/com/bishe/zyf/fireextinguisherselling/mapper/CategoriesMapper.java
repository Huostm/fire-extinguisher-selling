package com.bishe.zyf.fireextinguisherselling.mapper;

import com.bishe.zyf.fireextinguisherselling.entity.Categories;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Administrator
* @description 针对表【categories(分类表)】的数据库操作Mapper
* @createDate 2026-08-24 20:36:43
* @Entity com.bishe.zyf.fireextinguisherselling.entity.Categories
*/
public interface CategoriesMapper extends BaseMapper<Categories> {

    int updateStatus(Long id, Integer changedStatus);
}




