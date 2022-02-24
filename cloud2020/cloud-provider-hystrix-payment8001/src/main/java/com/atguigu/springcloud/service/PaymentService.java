package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

public interface PaymentService {
    public String paymentInfoOk(int id);
    public String paymentInfoTimeOut(int id);
    public String paymentInfoError(int id);
    public String paymentInfoPlanB(int id);
    public String paymentInfoPlanDefault();
}
