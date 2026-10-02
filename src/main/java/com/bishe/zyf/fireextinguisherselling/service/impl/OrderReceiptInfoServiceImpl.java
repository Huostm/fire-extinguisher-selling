package com.bishe.zyf.fireextinguisherselling.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.zyf.fireextinguisherselling.dto.CreateReceiptInfoDTO;
import com.bishe.zyf.fireextinguisherselling.dto.UpdateReceiptInfoDTO;
import com.bishe.zyf.fireextinguisherselling.entity.OrderReceiptInfo;
import com.bishe.zyf.fireextinguisherselling.service.OrderReceiptInfoService;
import com.bishe.zyf.fireextinguisherselling.mapper.OrderReceiptInfoMapper;
import com.bishe.zyf.fireextinguisherselling.utils.LoginUserContext;
import com.bishe.zyf.fireextinguisherselling.vo.ListReceiptInfoVO;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【order_receipt_info(订单收货信息表（快照）)】的数据库操作Service实现
* @createDate 2026-08-24 20:36:43
*/
@Service
public class OrderReceiptInfoServiceImpl extends ServiceImpl<OrderReceiptInfoMapper, OrderReceiptInfo>
    implements OrderReceiptInfoService{

    @Autowired
    private OrderReceiptInfoMapper orderReceiptInfoMapper;

    @Override
    public ResultVO<String> createOrderReceiptInfo(CreateReceiptInfoDTO createReceiptInfoDTO) {
        if (createReceiptInfoDTO ==null){
            return ResultVO.error("收货信息不能为空");
        }
        Long userId = LoginUserContext.getUserId();
        if (userId==null){
            return ResultVO.error("登录状态错误，请重试");
        }
        LambdaQueryWrapper<OrderReceiptInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OrderReceiptInfo::getUserId,userId);
        Long receiptInfoCount = orderReceiptInfoMapper.selectCount(lambdaQueryWrapper);
        if (receiptInfoCount == 5){
            return ResultVO.error("一个账户只能创建五个收货信息");
        }
        createReceiptInfoDTO.setUserId(userId);
        OrderReceiptInfo orderReceiptInfo = new OrderReceiptInfo();
        BeanUtils.copyProperties(createReceiptInfoDTO,orderReceiptInfo);
        boolean save = this.save(orderReceiptInfo);
        if (!save){
            return ResultVO.error("创建收货信息失败");
        }
        return ResultVO.success("创建收货信息成功");
    }

    @Override
    public ResultVO<String> deleteReceiptInfo(Long id) {
        if (id==null || id<=0){
            return ResultVO.error("请选择正确的收货信息");
        }
        OrderReceiptInfo byId = this.getById(id);
        Long userId = byId.getUserId();
        if (!userId.equals(LoginUserContext.getUserId())){
            return ResultVO.error("不能删除别人的收货信息");
        }
        boolean hasRecord = this.removeById(id);
        if (!hasRecord){
            return ResultVO.error("删除失败");
        }
        return ResultVO.success("删除成功");
    }

    @Override
    public ResultVO<String> updateReceiptInfo(UpdateReceiptInfoDTO updateReceiptInfoDTO) {
        if (updateReceiptInfoDTO==null){
            return ResultVO.error("请选择正确的收货信息");
        }
        Long receiptId = updateReceiptInfoDTO.getId();
        OrderReceiptInfo byId = this.getById(receiptId);
        if (byId==null){
            return ResultVO.error("不存在此收货信息，不能修改");
        }
        Long userId = byId.getUserId();
        if (!userId.equals(LoginUserContext.getUserId())){
            return ResultVO.error("不能编辑别人的收货信息");
        }
        OrderReceiptInfo orderReceiptInfo = new OrderReceiptInfo();
        BeanUtils.copyProperties(updateReceiptInfoDTO,orderReceiptInfo);
        orderReceiptInfo.setUserId(2L);
        boolean hasRecord = this.updateById(orderReceiptInfo);
        if (!hasRecord){
            return ResultVO.error("修改收货信息失败");
        }
        return ResultVO.success("修改成功");
    }

    @Override
    public ResultVO<List<ListReceiptInfoVO>> listReceiptInfo() {
        Long userId = LoginUserContext.getUserId();
        LambdaQueryWrapper<OrderReceiptInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OrderReceiptInfo::getUserId,userId);
        List<OrderReceiptInfo> orderReceiptInfos = orderReceiptInfoMapper.selectList(lambdaQueryWrapper);

        List<ListReceiptInfoVO> voList = orderReceiptInfos.stream()
                .map(entity -> {
                    ListReceiptInfoVO vo = new ListReceiptInfoVO();
                    BeanUtils.copyProperties(entity, vo);
                    return vo;
                })
                .toList();
        return ResultVO.success(voList);
    }
}




