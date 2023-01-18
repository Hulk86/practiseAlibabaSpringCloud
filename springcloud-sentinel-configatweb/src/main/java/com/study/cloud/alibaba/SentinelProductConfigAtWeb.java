package com.study.cloud.alibaba;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ComponentScans(value = {
        @ComponentScan(value="com.study.cloud.alibaba.**")
})
@EnableDiscoveryClient
@SpringBootApplication
public class SentinelProductConfigAtWeb {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication
            .run(SentinelProductConfigAtWeb.class, args);

    }
}
