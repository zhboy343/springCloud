package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


@RestController
// 配置默认降级方案 没有配置的使用默认的方案  配置了降级方案的使用配置的方案 使用默认降级的方法上也要添加@HystrixCommand注解
@DefaultProperties(defaultFallback = "paymentInfoPlanDefault")
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

    // 熔断 -- Hystrix加在service层
    @GetMapping(value = "/payment/hystrix/CircuitBreaker")
    public String paymentInfoCircuitBreaker(@RequestParam("id") int id) {
        return paymentService.paymentInfoCircuitBreaker(id);
    }

    // 设置超时时间、降级方案
    /*
       设置超时时间，让用户服务不在等待订单服务的响应 这样可以及时释放资源
       如下 设置等待时间为2s 超过时间不再等待
     */
    @HystrixCommand(
            commandProperties = {
                    // 设置响应时间，超过时间则抛出异常停止调用的服务
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
            },
            // 一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
            // 指定方法参数要与原方法参数相同
            fallbackMethod = "paymentInfoPlanB"
    )
    @GetMapping(value = "/payment/hystrix/timeOut")
    public String paymentInfoTimeOut(@RequestParam("id") int id) {
        return paymentService.paymentInfoTimeOut(id);
    }

    // 测试默认降级方案
    @HystrixCommand
    @GetMapping(value = "/payment/hystrix/default")
    public String paymentInfoDefault(@RequestParam("id") int id) {
        int x = 1/0;
        return "测试默认降级方案";
    }

    // 默认降级方案
    private String paymentInfoPlanDefault() {
        return paymentService.paymentInfoPlanDefault();
    }

    // 超时降级方案
    private String paymentInfoPlanB(int id) {
        return paymentService.paymentInfoPlanB(id);
    }


    // 正常访问 - 客户端hystrix
    @GetMapping(value = "/payment/ok")
    public String consumerInfoOk(@RequestParam("id") int id) {
        return "正常访问__客户端Hystrix,id:" + id;
    }

    // 熔断- 客户端hystrix
    @GetMapping(value = "/payment/circuitBreaker")
    public String consumerInfoCircuitBreaker(@RequestParam("id") int id) {
        return "熔断__客户端Hystrix,id:" + id;
    }

    // 降级 - 客户端hystrix
    @GetMapping(value = "/payment/fallBack")
    public String consumerInfoFallBack(@RequestParam("id") int id) {
        return "降级__客户端Hystrix,id:" + id;
    }
}
