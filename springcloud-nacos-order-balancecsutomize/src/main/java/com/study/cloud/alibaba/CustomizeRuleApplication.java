package com.study.cloud.alibaba;
import com.study.cloud.alibaba.rule.CustomizeRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;

@ComponentScans(value = {
        @ComponentScan(value="com.study.cloud.alibaba.**")
})
@EnableDiscoveryClient
@SpringBootApplication
/*@RibbonClients(value = {
    @RibbonClient(name = "nacos-true-consumer", configuration = CustomizeRule.class)
})*/
public class CustomizeRuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomizeRuleApplication.class, args);
    }
}

