package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "/payment/test")
    public void test(@RequestParam(value = "test") String test){
        System.out.println(test);
    }

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*****插入结果："+result);
        if (result>0){  //成功
            return new CommonResult(200,"插入数据库成功,serverPort："+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败,serverPort："+serverPort,null);
        }
    }
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果："+payment.toString());
        if (payment!=null){  //说明有数据，能查询成功
            return new CommonResult(200,"查询成功,serverPort："+serverPort,payment);
        }else {
            return new CommonResult(444,"没有对应记录，查询ID："+id + ",serverPort：" + serverPort,null);
        }
    }
}
