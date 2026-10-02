package com.bishe.zyf.fireextinguisherselling.controller;

import com.bishe.zyf.fireextinguisherselling.dto.CreateReceiptInfoDTO;
import com.bishe.zyf.fireextinguisherselling.dto.UpdateReceiptInfoDTO;
import com.bishe.zyf.fireextinguisherselling.service.OrderReceiptInfoService;
import com.bishe.zyf.fireextinguisherselling.vo.ListReceiptInfoVO;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-10-02
 * @Description: 收货信息接口
 */

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    @Autowired
    private OrderReceiptInfoService orderReceiptInfoService;

    @PostMapping("/create")
    public ResultVO<String> createReceiptInfo(@Valid @RequestBody CreateReceiptInfoDTO createReceiptInfoDTO) {
        return orderReceiptInfoService.createOrderReceiptInfo(createReceiptInfoDTO);
    }

    @DeleteMapping("/{id}")
    public ResultVO<String> deleteReceiptInfo(@PathVariable Long id){
        return orderReceiptInfoService.deleteReceiptInfo(id);
    }

    @PostMapping("/update")
    public ResultVO<String> updateReceiptInfo(@Valid @RequestBody UpdateReceiptInfoDTO updateReceiptInfoDTO){
        return orderReceiptInfoService.updateReceiptInfo(updateReceiptInfoDTO);
    }

    @GetMapping("/list")
    public ResultVO<List<ListReceiptInfoVO>> listReceiptInfo(){
        return orderReceiptInfoService.listReceiptInfo();
    }

}
