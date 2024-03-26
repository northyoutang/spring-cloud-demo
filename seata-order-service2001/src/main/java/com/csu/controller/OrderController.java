package com.csu.controller;

import com.csu.entities.Order;
import com.csu.resp.ResultData;
import com.csu.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@GlobalTransactional(name = "seata-order-create", rollbackFor = Exception.class)
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/order/create")
    public ResultData create(Order order) {
        orderService.createOrder(order);
        return ResultData.success(order);
    }
}
