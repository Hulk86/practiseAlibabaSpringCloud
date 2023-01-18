package com.study.cloud.alibaba;
import com.study.cloud.alibaba.config.ExcludeFromComponentScan;
import com.study.cloud.alibaba.config.RuleNacosRuleConfig;
import com.study.cloud.alibaba.config.RuleRoundRibbonRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;

@ComponentScans(value = {
        @ComponentScan(
            value="com.study.cloud.alibaba.**",
            excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) }
            )

})
@EnableDiscoveryClient
@SpringBootApplication
//配置多个 的RibbonConfig 的时候 补鞥呢被@SpringbootApplication的@ComponentScan扫描到，厚泽就是全局配置的效果
/*@RibbonClients(value = {
    //RuleNacosRuleConfig.class 和 RuleRoundRibbonRuleConfig.class 都不能加上 @Configuration， 不然会说bean注入太多，冲突了
    @RibbonClient(name = "nacos-true-consumer", configuration = RuleNacosRuleConfig.class),
    @RibbonClient(name = "product-consumer", configuration = RuleRoundRibbonRuleConfig.class)
})*/
public class OrderRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderRibbonApplication.class, args);
    }
}

