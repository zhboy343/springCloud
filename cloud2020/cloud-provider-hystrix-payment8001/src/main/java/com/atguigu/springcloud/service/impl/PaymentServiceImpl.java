package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "__paymentInfoTimeOut,id: " + id;
    }

    @Override
    public String paymentInfoError(int id) {
        return "线程池：" + Thread.currentThread().getName() + "__paymentInfoError,id: " + id;
    }

    @Override
    public String paymentInfoPlanB(int id) {
        return "线程池：" + Thread.currentThread().getName() + "__paymentInfoPlanB,id: " + id;
    }

    @Override
    public String paymentInfoPlanDefault() {
        return "线程池：" + Thread.currentThread().getName() + "__paymentInfoPlanDefault";
    }
}
