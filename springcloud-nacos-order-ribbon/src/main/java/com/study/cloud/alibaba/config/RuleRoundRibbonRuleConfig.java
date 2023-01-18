package com.study.cloud.alibaba.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义 客户端负载均衡 ， 用 ribbon实现
 */
//@Configuration
//@ExcludeFromComponentScan //加上 ExcludeFromComponentScan 能够避免被扫描到，避免变成设置成全局的（设置全局的本意是替换原本默认的负载均衡策略）负载均衡策略
public class RuleRoundRibbonRuleConfig {

  //@Bean(value = "roundRule")
  @Bean
  public IRule iRule() {
    //还可以是 RoundRobinRule, NacosRule, RandomRule 等；
    return new RoundRobinRule();
  }

}
