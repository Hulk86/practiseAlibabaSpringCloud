package com.study.cloud.alibaba;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;
//import org/springframework/util/ConcurrentLruCache

@ComponentScans(value = {
        @ComponentScan(value="com.study.cloud.alibaba.**")

})
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.study.cloud.alibaba.api.**")
public class OrderOpenFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeignApplication.class, args);
    }
}

