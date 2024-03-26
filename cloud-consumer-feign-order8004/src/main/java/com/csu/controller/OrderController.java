package com.csu.controller;

import cn.hutool.core.date.DateUtil;
import com.csu.api.PayFeignApi;
import com.csu.entities.PayDTO;
import com.csu.resp.ResultData;
import com.csu.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping(value = "/feign/pay/delete/{id}")
    public ResultData deleteOrder(@PathVariable("id") Integer id) {
        return payFeignApi.deletePay(id);
    }


    @GetMapping(value = "/feign/pay/get/{id}")
    public ResultData getOrderById(@PathVariable("id") Integer id) {
        ResultData resultData = null;
        try {
            System.out.println("调用开始：" + DateUtil.now());
            resultData = payFeignApi.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常调用结束：" + DateUtil.now());
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        System.out.println("无异常调用结束：" + DateUtil.now());
        System.out.println(resultData.getData());
        return resultData;
    }

    @GetMapping(value = "/feign/pay/get/info")
    private String getInfoByConsul() {
        return payFeignApi.getInfoByConsul();
    }

}
