# nacos-order-ribbon 22讲，23讲


## 笔记

* ** 在这里 nacos-order-ribbon 为中间组件，a.对外接收指令，b.对内分发，这里为分发 nacos-consumer(利用 nacos-consumer 的启动参数，可以同时启动多个nacos)
* ** 学习使用 ribbon 设置客户端负载均衡
* ** nacos-discovery-config 里面已经有了ribbon
* ** ribbon 有很多种负载均衡 RandomRule, RoundRule等

* ** 在RestTemplate的bean方法上加上 @LoadBalance 就可以让这个RestTemplate启用loadBalance
  ````
   @Configuration
   //@ExcludeFromComponentScan
   public class RestTemplateConfig {
     @Bean
     //注解LoadBalanced 使用客户端模式的负载均衡 nacos-discovery里面自带了 ribone ,load-balance ,
     @LoadBalanced //
     public RestTemplate loadRestTemplate(RestTemplateBuilder builder){
       builder.setConnectTimeout(Duration.ofMinutes(2));
      return builder.build();
   
     }
   }
  ````
***
#23讲

* ** 可以给全局设置负载策略
  ````
  //类似这样注册bean就可以设置全局的负载均衡策略
  @Bean(value = "nacosRule")
  public IRule iRule() {
    //还可以是 RoundRobinRule, RetryRule NacosRule, RandomRule 等；
    return new NacosRule();
  } 
  ````
* ** RetryRule： 此规则的负载均衡器规则策略会尝试在失败的时候重试
* ** WeightedResponseTimeRule : 根据返回的响应快慢决定后面的权重，返回的越快，决定以后的权重越大
* ** NacosRule 也是一种 WeightedResponseTimeRule
* ** ZoneAvoidanceRule : 这个是默认的规则，当没有显性的设置规则的话，则就是这个规则。大概原理是先按照区域来选定，如果没有配置区域的概念，则按照RoundRobinRule 轮询规则

* ** 通过代码配置指定的负载均衡策略
  ````
   比如在按照上面，添加了很多的 负载均衡策略 bean（而且都通过@Configuration注入到了），
   但是这种被ComponentScan扫描到的 bean 是会被用作全局的设定中，不能被用作分别指定的场景中，
   也就是说 配置多个 的RibbonConfig 的时候 不能被@SpringbootApplication的@ComponentScan扫描到，厚泽就是全局配置的效果
  ````
  
* ** 1. 在服务启动类上面通过 RibbonClients 可以分别指定服务们各自的负载均衡策略:
  ````
    @RibbonClients(value = {
      @RibbonClient(name = "nacos-true-consumer", configuration = RoundRibbonConfig.class)
      @RibbonClient(name = "nacos-true-consumer1", configuration = RibbonConfig.class)
    })
    public class OrderRibbonApplication {
      public static void main(String[] args) {
        SpringApplication.run(OrderRibbonApplication.class, args);
      }
    }
  ````
  
* ** 通过在配置文件设置，也可以指定负载均衡策略
  ````
  #通过配置文件的配置项来定制负载均衡的策略，ps: 通过写配置类的 在 nacos-order-ribbon
  nacos-true-consumer: #其他微服务在 nacos中注册的服务名
    ribbon:
      NIWSServerListClassName: com.netflix.loadbalancer.ConfigurationBasedServerList
      NFLoadBalancerRuleClassName: com.alibaba.cloud.nacos.ribbon.NacosRule
  ````


***
# 24讲

* ** #可以通过在yaml配置文件中分配 微服务提供方自定义使用的 负载均衡策略，此时需要把 application启动类（@SpringBootApplication ）的
  @RibbonClients 的注解取消掉，不然就冲突了
  ````
  product-consumer:
    ribbon:
      #NIWSServerListClassName: com.netflix.loadbalancer.ConfigurationBasedServerList
      # NFLoadBalancerClassName: com.alibaba.cloud.nacos.ribbon.NacosRule
      NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RoundRobinRule

  nacos-true-consumer:
    ribbon:
      #NIWSServerListClassName: com.netflix.loadbalancer.ConfigurationBasedServerList
      # NFLoadBalancerClassName: com.alibaba.cloud.nacos.ribbon.NacosRule
      NFLoadBalancerRuleClassName: com.alibaba.cloud.nacos.ribbon.NacosRule
  ````
































## 组件

**[Sentinel](https://github.com/alibaba/Sentinel)**：把流量作为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。

**[Nacos](https://github.com/alibaba/Nacos)**：一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。

**[RocketMQ](https://rocketmq.apache.org/)**：一款开源的分布式消息系统，基于高可用分布式集群技术，提供低延时的、高可靠的消息发布与订阅服务。

**[Seata](https://github.com/seata/seata)**：阿里巴巴开源产品，一个易于使用的高性能微服务分布式事务解决方案。

**[Alibaba Cloud OSS](https://www.aliyun.com/product/oss)**: 阿里云对象存储服务（Object Storage Service，简称 OSS），是阿里云提供的海量、安全、低成本、高可靠的云存储服务。您可以在任何应用、任何时间、任何地点存储和访问任意类型的数据。

**[Alibaba Cloud SchedulerX](https://cn.aliyun.com/aliware/schedulerx)**: 阿里中间件团队开发的一款分布式任务调度产品，提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。

**[Alibaba Cloud SMS](https://www.aliyun.com/product/sms)**: 覆盖全球的短信服务，友好、高效、智能的互联化通讯能力，帮助企业迅速搭建客户触达通道。

更多组件请参考 [Roadmap](https://github.com/alibaba/spring-cloud-alibaba/blob/2022.0/Roadmap-zh.md)。