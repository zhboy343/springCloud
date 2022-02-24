package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    // 正常访问
    @GetMapping(value = "/payment/hystrix/ok")
    public String paymentInfoOk(@RequestParam("id") int id) {
        return paymentService.paymentInfoOk(id);
    }

    // 超时访问
    /*
       设置超时时间，让用户服务不在等待订单服务的响应 这样可以及时释放资源
       如下 设置等待时间为2s 超过时间不再等待
     */
    @HystrixCommand(
            commandProperties = {
                    // 设置响应时间，超过时间则抛出异常停止调用的服务
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
            },
            // 一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
            fallbackMethod = "paymentInfoPlanB"
    )
    @GetMapping(value = "/payment/hystrix/timeOut")
    public String paymentInfoTimeOut(@RequestParam("id") int id) {
        return paymentService.paymentInfoTimeOut(id);
    }

    // 异常访问
    @GetMapping(value = "/payment/hystrix/error")
    public String paymentInfoError(@RequestParam("id") int id) {
        return paymentService.paymentInfoError(id);
    }

    private String paymentInfoPlanB(int id) {
        return paymentService.paymentInfoPlanB(id);
    }

}
