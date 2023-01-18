# springcloud-nacos-order-balancecsutomize


## 笔记

* ** 模拟自定义的负载均衡，自己手写一个负载均衡
* ** 要注意线程安全，因为这个接口会同时处理多个访问.
* **  @ExcludeFromComponentScan 结合 ComponentScan 中的 excludeFilters 可以做到排除，避免bean冲突
* ** swagger ui http://127.0.0.1:28815/swagger-ui.html  
* ** 可以不用 @Configuration和@Bean, 用配置文件实现加载, 本实例就是以配置来实现的
  
* ** **通常默认情况下配置文件加载是懒加载，但是可以通过（*设置参数*）实现启动加载**
  ``` 
      nacos-true-consumer:
        ribbon:
          #NIWSServerListClassName: com.netflix.loadbalancer.ConfigurationBasedServerList
          # NFLoadBalancerClassName: com.alibaba.cloud.nacos.ribbon.NacosRule
          NFLoadBalancerRuleClassName: com.study.cloud.alibaba.rule.CustomizeRule
      ribbon:
        #开启饥饿加载(启动加载)
        eager-load:
          nabled: true
          #指定哪些客户端负载均衡器使用饥饿加载，如果有多个用 ',' 隔开
          clients: nacos-true-consume
  
* ** ~~使用端口~~.















