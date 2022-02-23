package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        /*
        NONE:默认的，不显示任何日志
        BASIC:仅记录请求方法、url、响应状态及执行时间
        HEADERS:包含BASIC信息外，还有请求和响应的头信息
        FULL:包含HEADERS信息外，还有请求和响应的正文及元数据

        打印日志在
        <--- HTTP/1.1 200 (232ms) 开始
        <--- END HTTP (90-byte body) 结束
        之间的内容
         */
        return Logger.Level.FULL;
    }
}
