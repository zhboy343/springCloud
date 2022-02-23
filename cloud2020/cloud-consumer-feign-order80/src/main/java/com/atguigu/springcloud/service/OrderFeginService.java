package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

public interface OrderFeginService {
    public CommonResult<Payment> create(Payment payment);
    public CommonResult<Payment> getPaymentById(Long id);
    public void timeout() throws InterruptedException;
}
