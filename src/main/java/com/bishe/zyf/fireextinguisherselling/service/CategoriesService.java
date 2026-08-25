package com.bishe.zyf.fireextinguisherselling.service;

import com.bishe.zyf.fireextinguisherselling.dto.CreateCategoryDTO;
import com.bishe.zyf.fireextinguisherselling.entity.Categories;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import jakarta.validation.Valid;

/**
* @author Administrator
* @description 针对表【categories(分类表)】的数据库操作Service
* @createDate 2026-08-24 20:36:43
*/
public interface CategoriesService extends IService<Categories> {

    /**
     * 创建灭火器类型
     * @param createCategoryDTO
     * @return
     */
    ResultVO<String> createCategory(@Valid CreateCategoryDTO createCategoryDTO);

    /**
     * 修改灭火器类型激活状态
     * @param id
     * @return
     */
    ResultVO<String> changeStatus(Long id);
}
