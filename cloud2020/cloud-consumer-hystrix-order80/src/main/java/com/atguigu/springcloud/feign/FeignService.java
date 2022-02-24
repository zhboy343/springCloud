package com.atguigu.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE")
public interface FeignService {

    // 正常访问
    @GetMapping(value = "/payment/hystrix/ok")
    public String paymentInfoOk(@RequestParam("id") int id);

    // 超时访问
    @GetMapping(value = "/payment/hystrix/timeOut")
    public String paymentInfoTimeOut(@RequestParam("id") int id);

    // 异常访问
    @GetMapping(value = "/payment/hystrix/error")
    public String paymentInfoError(@RequestParam("id") int id);
}