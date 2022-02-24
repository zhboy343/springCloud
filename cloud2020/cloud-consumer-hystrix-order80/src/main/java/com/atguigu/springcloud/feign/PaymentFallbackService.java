package com.atguigu.springcloud.feign;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements FeignService{
    @Override
    public String paymentInfoOk(int id) {
        return "paymentInfoOk--降级案";
    }

    @Override
    public String paymentInfoTimeOut(int id) {
        return "paymentInfoTimeOut--降级案";
    }

    @Override
    public String paymentInfoDefault(int id) {
        return "paymentInfoDefault--降级案";
    }

    @Override
    public String paymentInfoError(int id) {
        return "paymentInfoError--降级案";
    }
}
