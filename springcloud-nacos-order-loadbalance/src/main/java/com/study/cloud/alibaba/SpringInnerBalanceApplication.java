package com.study.cloud.alibaba;

import com.study.cloud.alibaba.rule.CustomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ComponentScans(value = {
        @ComponentScan(value="com.study.cloud.alibaba.**")

})
@EnableDiscoveryClient
@SpringBootApplication
/*@RibbonClients(value = {
    @RibbonClient(name = "nacos-true-consumer", configuration = RoundRibbonConfig.class)
})*/

@LoadBalancerClients({
    @LoadBalancerClient(name = "nacos-true-consumer", configuration = CustomRule.class),
})
public class SpringInnerBalanceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringInnerBalanceApplication.class, args);
    }
}

