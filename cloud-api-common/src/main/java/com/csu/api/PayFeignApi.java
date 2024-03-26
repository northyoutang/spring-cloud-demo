package com.csu.api;


import com.csu.entities.PayDTO;
import com.csu.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


//@FeignClient(value = "cloud-payment-service")
@FeignClient(value = "cloud-gateway")
public interface PayFeignApi {

    /**
     * 新增支付流水记录
     */
    @PostMapping(value = "/pay/add")
    ResultData<String> addPay(@RequestBody PayDTO payDTO);

    /**
     * 删除支付流水记录n
     */
    @DeleteMapping(value = "/pay/delete/{id}")
    ResultData<Integer> deletePay(@PathVariable("id") Integer id);

    /**
     * 更新支付流水记录
     */
    @PutMapping("/pay/update")
    ResultData<String> updatePay(@RequestBody PayDTO payDTO);

    /**
     * 按ID查询支付流水记录
     */
    @GetMapping(value = "/pay/get/{id}")
    ResultData getById(@PathVariable("id") Integer id);

    /**
     * 查询全部支付流水记录
     */
    @GetMapping(value = "/pay/get/all")
    ResultData getAll();

    /**
     * OpenFeign支持负载均衡演示
     */
    @GetMapping(value = "/pay/get/info")
    String getInfoByConsul();


    /**
     * Resilience4j CircuitBreaker 的例子
     */
    @GetMapping(value = "/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);

    /**
     * Resilience4j Bulkhead 的例子
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id);


    /**
     * Resilience4j Ratelimit 的例子
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    String myRatelimit(@PathVariable("id") Integer id);

    /**
     * Micrometer(Sleuth)进行链路监控的例子
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    String myMicrometer(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例01
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    ResultData getGateWayById(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例02
     */
    @GetMapping(value = "/pay/gateway/info")
    ResultData<String> getGatewayInfo();
}
