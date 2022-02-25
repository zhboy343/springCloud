package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

public interface PaymentService {
    // 正常方法
    public String paymentInfoOk(int id);
    // 超时方法
    public String paymentInfoTimeOut(int id);
    // 超时降级方法
    public String paymentInfoPlanB(int id);
    // 熔断方法
    public String paymentInfoCircuitBreaker(int id);
    // 熔断降级方法
    public String paymentInfoPlanCircuitBreaker(int id);
    // 默认降级方法
    public String paymentInfoPlanDefault();

}
