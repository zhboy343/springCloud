package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    PaymentService paymentService;

    // 正常访问
    @GetMapping(value = "/payment/hystrix/ok")
    public String paymentInfoOk(@RequestParam("id") int id) {
        return paymentService.paymentInfoOk(id);
    }

    // 超时访问
    @GetMapping(value = "/payment/hystrix/timeOut")
    public String paymentInfoTimeOut(@RequestParam("id") int id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return paymentService.paymentInfoTimeOut(id);
    }

    // 异常访问
    @GetMapping(value = "/payment/hystrix/error")
    public String paymentInfoError(@RequestParam("id") int id) {
        return paymentService.paymentInfoError(id);
    }

}
