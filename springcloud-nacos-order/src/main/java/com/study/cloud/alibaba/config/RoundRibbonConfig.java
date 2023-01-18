package com.study.cloud.alibaba.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义 客户端负载均衡 ， 用 ribbon实现
 */
@Configuration
public class RoundRibbonConfig {

  /**
   * 全局配置，指定负载均衡策略，不管此服务为多少个微服务提供代理转发，都会用到这个注入的bean的
   * @return
   */
  @Bean
  public IRule iRule() {
    //还可以是 RoundRobinRule, NacosRule, RandomRule 等；
    /*
     分为 Ribbon 提供的 和 spring-cloude-nacos-config-discoryt
     */
    return new NacosRule();
  }


}
