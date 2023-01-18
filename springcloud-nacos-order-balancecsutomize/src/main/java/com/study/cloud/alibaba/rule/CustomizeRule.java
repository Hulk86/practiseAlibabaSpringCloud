package com.study.cloud.alibaba.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ：Hulk
 * @date ：Created in 2022/12/27 23:57
 * @description ：TODO...
 * @modified By ：
 * @version:
 */
public class CustomizeRule extends AbstractLoadBalancerRule {

  @Override
  public void initWithNiwsConfig(IClientConfig iClientConfig) {


  }

  @Override
  public Server choose(Object key) {
    ILoadBalancer loadBalancer = this.getLoadBalancer();
    int randomIndex = ThreadLocalRandom.current().nextInt(loadBalancer.getReachableServers().size());
    return loadBalancer.getAllServers().get(randomIndex);
  }
}
