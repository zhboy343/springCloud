package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.fegin.ServiceFeign;
import com.atguigu.springcloud.service.OrderFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderFeginServiceImpl implements OrderFeginService {

    @Autowired
    private ServiceFeign serviceFeign;

    @Override
    public CommonResult<Payment> create(Payment payment) {
        return serviceFeign.createx(payment);
    }

    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        return serviceFeign.getPaymentByIdx(id);
    }

    @Override
    public void timeout() throws InterruptedException {
        serviceFeign.timeout();
    }
}
