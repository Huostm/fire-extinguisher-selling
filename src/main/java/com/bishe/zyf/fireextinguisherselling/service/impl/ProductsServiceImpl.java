package com.bishe.zyf.fireextinguisherselling.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.zyf.fireextinguisherselling.dto.CreateProductDTO;
import com.bishe.zyf.fireextinguisherselling.dto.QueryDTO;
import com.bishe.zyf.fireextinguisherselling.dto.UpdateProductDTO;
import com.bishe.zyf.fireextinguisherselling.entity.Categories;
import com.bishe.zyf.fireextinguisherselling.entity.Products;
import com.bishe.zyf.fireextinguisherselling.mapper.CategoriesMapper;
import com.bishe.zyf.fireextinguisherselling.service.ProductsService;
import com.bishe.zyf.fireextinguisherselling.mapper.ProductsMapper;
import com.bishe.zyf.fireextinguisherselling.vo.CategoryVO;
import com.bishe.zyf.fireextinguisherselling.vo.PageResultVO;
import com.bishe.zyf.fireextinguisherselling.vo.ProductVO;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* @author Administrator
* @description 针对表【products(商品表)】的数据库操作Service实现
* @createDate 2026-08-24 20:36:43
*/
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products>
    implements ProductsService{

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Override
    public ResultVO<String> createProduct(CreateProductDTO createProductDTO) {
        if (createProductDTO == null){
            return ResultVO.error("商品信息不能为空");
        }
        Products products = new Products();
        String description = createProductDTO.getDescription();
        String name = createProductDTO.getName();
        products.setName(name);
        if (!StringUtils.hasText(description)){
            products.setDescription(name+"暂无详细介绍");
        }else{
            products.setDescription(description);
        }
        products.setPrice(createProductDTO.getPrice());
        products.setStock(createProductDTO.getStock());
        String imageUrl = createProductDTO.getImageUrl();
        if (!StringUtils.hasText(imageUrl)){
            products.setImageUrl("https://tse4.mm.bing.net/th/id/OIP.6STOQ3XY-8gOHDKQPoViDwHaJQ?r=0&rs=1&pid=ImgDetMain&o=7&rm=3");
        }else {
            products.setImageUrl(imageUrl);
        }
        products.setIsActive(0);
        products.setCategoryId(createProductDTO.getCategoryId());
        boolean saved = this.save(products);
        if (saved){
            return ResultVO.success("新增成功");
        }else{
            return ResultVO.error("新增失败");
        }
    }

    @Override
    public ResultVO<String> deleteProduct(Long id) {
        if (id==null){
            return ResultVO.error("请选择要删除的灭火器");
        }
        Products byId = this.getById(id);
        if (byId==null){
            return ResultVO.error("数据不存在");
        }
        boolean hasRecord = this.removeById(id);
        return hasRecord? ResultVO.success(String.format("删除%s成功",byId.getName())) :
                ResultVO.error(String.format("删除%s失败",byId.getName()));
    }

    @Override
    public ResultVO<String> updateProduct(UpdateProductDTO updateProductDTO) {
        if (updateProductDTO == null){
            return ResultVO.error("修改内容不能为空");
        }
        Products byId = this.getById(updateProductDTO.getId());
        if (byId==null){
            return ResultVO.error("数据不存在");
        }
        BeanUtils.copyProperties(updateProductDTO,byId);
        boolean hasRecord = this.updateById(byId);
        if (!hasRecord){
            return ResultVO.error("修改失败");
        }
        return ResultVO.success("修改成功");
    }

    @Override
    public ResultVO<String> changeStatus(Long id) {
        if (id==null){
            return ResultVO.error("请选择要修改的灭火器");
        }
        Products byId = this.getById(id);
        if (byId==null){
            return ResultVO.error("数据不存在");
        }
        Integer isActive = byId.getIsActive();
        Integer changedStatus = isActive==1? 0:1;
        int result = productsMapper.updateStatus(id,changedStatus);
        if (result==0){
            return ResultVO.error("修改灭火器状态失败");
        }
        return ResultVO.error("更改灭火器状态成功");
    }

    @Override
    public ResultVO<PageResultVO<ProductVO>> pageList(QueryDTO queryDTO) {
        LambdaQueryWrapper<Products> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(queryDTO.getKeyword())){
            queryWrapper.like(Products::getName, queryDTO.getKeyword());
        }
        Page<Products> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        Page<Products> resultPage = this.page(page, queryWrapper);
        List<ProductVO> voList = resultPage.getRecords().stream().map(product -> {
            ProductVO vo = new ProductVO();
            BeanUtils.copyProperties(product, vo);
            Long categoryId = product.getCategoryId();
            Categories categories = categoriesMapper.selectById(categoryId);
            vo.setCategoryName(categories.getName());
            return vo;
        }).toList();

        PageResultVO<ProductVO> pageResult = new PageResultVO<>();
        pageResult.setTotal(resultPage.getTotal());
        pageResult.setPages(resultPage.getPages());
        pageResult.setCurrent(resultPage.getCurrent());
        pageResult.setSize(resultPage.getSize());
        pageResult.setList(voList);

        return ResultVO.success(pageResult);
    }
}




