package com.csu.controller;

import cn.hutool.core.bean.BeanUtil;
import com.csu.entities.Pay;
import com.csu.entities.PayDTO;
import com.csu.resp.ResultData;
import com.csu.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;
    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增", description = "新增支付流水方法，JSON串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        int result = payService.add(pay);
        return ResultData.success("成功插入记录，返回值: " + result);
    }

    @DeleteMapping(value = "/pay/delete/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        return ResultData.success(payService.delete(id));
    }

    @PutMapping("/pay/update")
    @Operation(summary = "更新", description = "更新支付流水方法，JSON串做参数")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtil.copyProperties(payDTO, pay);
        int result = payService.update(pay);
        return ResultData.success("成功更新记录，返回值: " + result);
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "按照ID查流水", description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        try {
            TimeUnit.SECONDS.sleep(62);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResultData.success(payService.getById(id));
    }

    @GetMapping(value = "/pay/get/all")
    @Operation(summary = "查流水", description = "查询全部支付流水方法")
    public ResultData<List<Pay>> getAll() {
        return ResultData.success(payService.getAll());
    }

    @GetMapping(value = "/pay/get/info")
    public String getInfoByConsul(@Value("${csu.info}") String csuInfo) {
        return "csuInfo:" + csuInfo + " port:" + port;
    }

}
