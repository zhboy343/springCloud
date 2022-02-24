package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.feign.FeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private FeignService feignService;

    // 正常访问
    @GetMapping(value = "/consumer/hystrix/ok")
    public String paymentInfoOk(@RequestParam("id") int id){
        return feignService.paymentInfoOk(id);
    }

    // 超时访问
    @GetMapping(value = "/consumer/hystrix/timeOut")
    public String paymentInfoTimeOut(@RequestParam("id") int id) {
        return feignService.paymentInfoTimeOut(id);
    }

    // 异常访问
    @GetMapping(value = "/consumer/hystrix/error")
    public String paymentInfoError(@RequestParam("id") int id) {
        return feignService.paymentInfoError(id);
    }
}
