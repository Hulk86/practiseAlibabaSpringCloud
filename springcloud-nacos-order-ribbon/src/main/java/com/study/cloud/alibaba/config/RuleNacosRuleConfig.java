package com.study.cloud.alibaba.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义 客户端负载均衡 ， 用 ribbon实现
 */
//@Configuration
//@ExcludeFromComponentScan
public class RuleNacosRuleConfig {

  //@Bean(value = "nacosRule")
  @Bean
  public IRule iRule() {
    //还可以是 RoundRobinRule, NacosRule, RandomRule 等；
    //NacosRule 可以贴合 nacos 服务中设置的服务权重，根据权重来进行分发
    return new NacosRule();
  }

}
