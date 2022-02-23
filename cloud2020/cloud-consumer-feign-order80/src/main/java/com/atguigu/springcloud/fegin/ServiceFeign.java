package com.atguigu.springcloud.fegin;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// 添加要调用接口的服务集群名称
@FeignClient(value = "cloud-payment-service")
public interface ServiceFeign {
    /*
    添加要调用的方法
    方法要求
        方法名称：随意
        返回值：对应
        参数：对应 --参数上的注解也要一致
        注解：对应
     */

    // 创建订单
    @PostMapping(value = "/payment/create")
    CommonResult<Payment> createx(@RequestBody Payment payment);

    // 查询订单
    @GetMapping(value = "/payment/get/{id}")
    CommonResult getPaymentByIdx(@PathVariable("id") Long id);
}
