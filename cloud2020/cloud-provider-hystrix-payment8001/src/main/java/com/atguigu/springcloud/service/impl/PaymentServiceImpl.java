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

    // 正常方法
    @Override
    public String paymentInfoOk(int id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfoOk,id: " + id;
    }

    // 设置超时时间、降级方案

    @Override
    public String paymentInfoTimeOut(int id) {
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("xxxxx");
        return "线程池：" + Thread.currentThread().getName() + "__paymentInfoTimeOut,id: " + id;
    }

    // 超时降级方法
    @Override
    public String paymentInfoPlanB(int id) {
        return "线程池：" + Thread.currentThread().getName() + "__超时降级方法,id: " + id;
    }

    /*
    熔断方法
    方法逻辑：
    判断传入参数是否小于0 若小于0则抛出异常 大于0则正常返回

    hystrix熔断逻辑：
    1.当出现错误时，开启一个时间窗（默认10s）
    2.在这个时间窗内，统计请求次数是否达到最小请求次数？
        如果没达到，则重置统计次数，回到第1步
        如果达到了，统计失败的请求次数占所有请求次数的百分比，是否达到阈值？
            如果达到了，则跳闸(不再请求对应服务-有降级方案则之间访问降级方案)
            如果没达到，则重置统计次数，回到第1步
    3.如果跳闸了，则会开启一个活动窗口（默认5s），每隔5s，Hystrix会让一个请求通过，看是否调用成功
        如果成功，重置断路器，回到第1步
        如果不成功，回到第3步
     */
    // hystrix配置
    @HystrixCommand(fallbackMethod = "paymentInfoPlanCircuitBreaker",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    @Override
    public String paymentInfoCircuitBreaker(int id) {
        if (id < 0) {
            throw new RuntimeException("*****id 不能负数");
        }
        return "线程池：" + Thread.currentThread().getName() + "__paymentInfoError,id: " + id;
    }

    // 熔断降级方案
    @Override
    public String paymentInfoPlanCircuitBreaker(int id) {
        return "线程池：" + Thread.currentThread().getName() + "__熔断降级方案";
    }

    // 默认降级方法
    @Override
    public String paymentInfoPlanDefault() {
        return "线程池：" + Thread.currentThread().getName() + "__默认降级方案";
    }
}
