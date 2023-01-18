package com.study.cloud.alibaba.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ExcludeFromComponentScan
public class RestTemplateConfig {
    @Bean
    //注解LoadBalanced 使用客户端模式的负载均衡 nacos-discovery里面自带了 ribone ,load-balance ,
    @LoadBalanced //
    public RestTemplate loadRestTemplate(RestTemplateBuilder builder){
        return  new RestTemplate();
    }
}
