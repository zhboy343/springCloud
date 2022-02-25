package com.atguigu.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 配置feign注解
@FeignClient(
        // 设置要访问的集群服务
        value = "CLOUD-PAYMENT-HYSTRIX-SERVICE"
        // 设置客户端降级方案
)
public interface FeignService {
    /*
    添加要调用的方法--对应服务端Controller类中方法
    方法要求
        方法名称：随意
        返回值：对应
        参数：对应 --参数上的注解也要一致
        注解：对应
     */

    // 正常访问-服务端
    @GetMapping(value = "/payment/hystrix/ok")
    public String paymentInfoOk(@RequestParam("id") int id);

    // 熔断-服务端
    @GetMapping(value = "/payment/hystrix/CircuitBreaker")
    public String paymentInfoCircuitBreaker(@RequestParam("id") int id);

    // 超时-服务端
    @GetMapping(value = "/payment/hystrix/timeOut")
    public String paymentInfoTimeOut(@RequestParam("id") int id);

    // 测试默认降级方案
    @GetMapping(value = "/payment/hystrix/default")
    public String paymentInfoDefault(@RequestParam("id") int id);

    // 正常访问 - 客户端hystrix
    @GetMapping(value = "/payment/ok")
    public String consumerInfoOk(@RequestParam("id") int id);

    // 熔断- 客户端hystrix
    @GetMapping(value = "/payment/circuitBreaker")
    public String consumerInfoCircuitBreaker(@RequestParam("id") int id);

    // 降级 - 客户端hystrix
    @GetMapping(value = "/payment/fallBack")
    public String consumerInfoFallBack(@RequestParam("id") int id);
}
