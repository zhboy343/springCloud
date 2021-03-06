package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.OrderFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderFeignController {

    @Autowired
    private OrderFeginService orderFeginService;

    // 创建订单
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        return orderFeginService.create(payment);
    }

    // 查询订单
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return orderFeginService.getPaymentById(id);
    }

    // feign超时设置测试
    @GetMapping(value = "/consumer/payment/timeout")
    public void timeOut() throws InterruptedException {
        System.out.println("xxxxxxxxxx1");
        orderFeginService.timeout();
    }
}
