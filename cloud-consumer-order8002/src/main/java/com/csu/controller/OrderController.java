package com.csu.controller;

import com.csu.entities.PayDTO;
import com.csu.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {


    // public static final String PaymentService_URL = "http://localhost:8001";
    // 注册中心上微服务名称
    public static final String PaymentService_URL = "http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PaymentService_URL + "/pay/add", payDTO, ResultData.class);
    }

    @GetMapping(value = "/consumer/pay/delete/{id}")
    public ResultData deleteOrder(@PathVariable("id") Integer id) {
        restTemplate.delete(PaymentService_URL + "/pay/delete/" + id, ResultData.class, id);
        return ResultData.success("id:" + id + "删除成功");
    }


    @GetMapping(value = "/consumer/pay/get/{id}")
    public ResultData getOrderById(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PaymentService_URL + "/pay/get/" + id, ResultData.class, id);
    }

    @GetMapping(value = "/consumer/pay/get/info")
    private String getInfoByConsul() {
        return restTemplate.getForObject(PaymentService_URL + "/pay/get/info", String.class);
    }


}
