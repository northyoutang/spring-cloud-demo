package com.csu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient //使用Consul为注册中心时注册服务
@EnableFeignClients //启用Feign客户端，定义服务+绑定接口，以声明式的方法实现服务调用
public class Main8004 {
    public static void main(String[] args) {
        SpringApplication.run(Main8004.class, args);
    }
}