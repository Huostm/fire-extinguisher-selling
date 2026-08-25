package com.bishe.zyf.fireextinguisherselling.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.zyf.fireextinguisherselling.dto.CreateCategoryDTO;
import com.bishe.zyf.fireextinguisherselling.entity.Categories;
import com.bishe.zyf.fireextinguisherselling.entity.Products;
import com.bishe.zyf.fireextinguisherselling.service.CategoriesService;
import com.bishe.zyf.fireextinguisherselling.mapper.CategoriesMapper;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【categories(分类表)】的数据库操作Service实现
* @createDate 2026-08-24 20:36:43
*/
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories>
    implements CategoriesService{

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Override
    public ResultVO<String> createCategory(CreateCategoryDTO createCategoryDTO) {
        if (createCategoryDTO == null){
            return ResultVO.error("类型信息不能为空");
        }
        Categories categories = new Categories();
        categories.setName(createCategoryDTO.getName());
        categories.setTestCycle(createCategoryDTO.getTestCycle());
        categories.setServiceLifeYears(createCategoryDTO.getServiceLifeYears());
        categories.setIsActive(0);
        categories.setAfterFixTestCycle(createCategoryDTO.getAfterFixTestCycle());
        boolean saved = this.save(categories);
        if (saved){
            return ResultVO.error("新增成功");
        }else{
            return ResultVO.success("新增失败");
        }
    }

    @Override
    public ResultVO<String> changeStatus(Long id) {
        if (id==null){
            return ResultVO.error("请选择要修改状态的灭火器类型");
        }
        Categories byId = this.getById(id);
        if (byId==null){
            return ResultVO.error("数据不存在");
        }
        Integer isActive = byId.getIsActive();
        Integer changedStatus = isActive==1? 0:1;
        int result = categoriesMapper.updateStatus(id,changedStatus);
        if (result==0){
            return ResultVO.error("修改灭火器类型激活状态失败");
        }
        return ResultVO.error("更改灭火器类型激活状态成功");
    }
}




