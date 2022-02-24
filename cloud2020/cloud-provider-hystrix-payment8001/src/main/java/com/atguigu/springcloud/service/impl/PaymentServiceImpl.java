package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDao paymentDao;

    @Override
    public String paymentInfoOk(int id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfoOk,id: " + id;
    }

    @Override
    public String paymentInfoTimeOut(int id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfoTimeOut,id: " + id;
    }

    @Override
    public String paymentInfoError(int id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfoError,id: " + id;
    }
}
