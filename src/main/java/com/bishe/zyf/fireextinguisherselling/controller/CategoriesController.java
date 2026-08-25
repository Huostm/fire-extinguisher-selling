package com.bishe.zyf.fireextinguisherselling.controller;

import com.bishe.zyf.fireextinguisherselling.dto.CreateCategoryDTO;
import com.bishe.zyf.fireextinguisherselling.service.CategoriesService;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-25
 * @Description: 灭火器分类接口
 */

@RestController
@RequestMapping("/category")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @PostMapping("/create")
    public ResultVO<String> createCategory(@Valid @RequestBody CreateCategoryDTO createCategoryDTO){
        return categoriesService.createCategory(createCategoryDTO);
    }

    @PutMapping("/status/{id}")
    public ResultVO<String> changeStatus(@PathVariable Long id){
        return categoriesService.changeStatus(id);
    }

}
