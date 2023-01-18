package com.study.cloud.alibaba;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScans(value = {
        @ComponentScan(value="com.study.cloud.alibaba.**"),
    @ComponentScan(value="com.study.cloud.alibaba.**")
})
@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(value = "com.study.cloud.alibaba.**")
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
