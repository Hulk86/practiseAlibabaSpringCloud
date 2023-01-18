package com.study.cloud.alibaba.rule;

import java.util.List;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.reactive.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.reactive.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.reactive.Request;
import org.springframework.cloud.client.loadbalancer.reactive.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceSupplier;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author ：Hulk
 * @date ：Created in 2022/12/29 23:36
 * @description ：TODO...
 * @modified By ：
 * @version:
 */
//public class RandomLoadBalancer2 implements ReactorLoadBalancer<ServiceInstance>
public class RandomLoadBalancer2 extends RoundRobinLoadBalancer {

  public RandomLoadBalancer2(String serviceId,
      ObjectProvider<ServiceInstanceSupplier> serviceInstanceSupplier) {
    super(serviceId, serviceInstanceSupplier);
  }

  public RandomLoadBalancer2(
      ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider,
      String serviceId) {
    super(serviceInstanceListSupplierProvider, serviceId);
  }

  public RandomLoadBalancer2(
      ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider,
      String serviceId, int seedPosition) {
    super(serviceInstanceListSupplierProvider, serviceId, seedPosition);
  }

  public RandomLoadBalancer2(String serviceId,
      ObjectProvider<ServiceInstanceSupplier> serviceInstanceSupplier, int seedPosition) {
    super(serviceId, serviceInstanceSupplier, seedPosition);
  }

  @Override
  public Mono<Response<ServiceInstance>> choose(Request request) {
    return super.choose(request);

  }


}
