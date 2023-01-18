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
public class NacosConfigProduct {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosConfigProduct.class, args);


        String productName = applicationContext.getEnvironment().getProperty("product.name");
        Integer productAmount = applicationContext.getEnvironment()
            .getProperty("product.amount", Integer.class);
        System.err.println("productName :" + productName + "; productAmount: " + productAmount);

        String clusterNodes = applicationContext.getEnvironment().getProperty("spring.redis.cluster.nodes");
        System.out.println("clusterNodes:" + String.valueOf(clusterNodes));

        String username = applicationContext.getEnvironment().getProperty("spring.rabbitmq.username");
        System.out.println("rabbitmqusername:" + String.valueOf(username));
    }
}
/*
product.name= kele
product.amount = 19860617
 */