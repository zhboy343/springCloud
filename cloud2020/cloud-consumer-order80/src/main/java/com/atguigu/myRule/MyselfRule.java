package com.atguigu.myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 官网信息 -- 自定义配置类不能放在 @ComponentScan 标签生扫描的包及其子包下
@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule(){
        /*
        RoundRobinRule 轮询
        RandomRule 随机
        RetryRule  先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内会进行重试
        WeightedResponseTimeRule 对RoundRobinRule的扩展，响应速度越快的实例选择权重越大，越容易被选择
        BestAvailableRule 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
        AvailabilityFilteringRule 先过滤掉故障实例，再选择并发较小的实例
         */
        return new RandomRule();// 定义为随机
    }
}