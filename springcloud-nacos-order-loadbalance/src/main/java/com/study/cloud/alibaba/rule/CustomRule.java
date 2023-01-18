package com.study.cloud.alibaba.rule;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * @author ：Hulk
 * @date ：Created in 2022/12/29 23:28
 * @description ：自己实现一个基于 loadbalance的 负载均衡器
 * @modified By ：
 * @version:
 */
public class CustomRule {
  @Bean
  ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
      LoadBalancerClientFactory loadBalancerClientFactory) {
    String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
    return new RandomLoadBalancer2(loadBalancerClientFactory
        .getLazyProvider(name, ServiceInstanceListSupplier.class),
        name);
  }
}
