package com.atguigu.springcloud.feign;

import org.springframework.stereotype.Component;

@Component
public class ComsumerFallbackService implements FeignService{
    @Override
    public String paymentInfoOk(int id) {
        return "服务端Hystrix--正常";
    }

    @Override
    public String paymentInfoCircuitBreaker(int id) {
        return "服务端Hystrix--熔断";
    }

    @Override
    public String paymentInfoTimeOut(int id) {
        return "服务端Hystrix--降级";
    }

    @Override
    public String paymentInfoDefault(int id) {
        return "服务端Hystrix--默认降级方案";
    }

    @Override
    public String consumerInfoOk(int id) {
        return "客户端Hystrix--正常";
    }

    @Override
    public String consumerInfoCircuitBreaker(int id) {
        return "客户端Hystrix--熔断";
    }

    @Override
    public String consumerInfoFallBack(int id) {
        return "客户端Hystrix--降级";
    }
}
