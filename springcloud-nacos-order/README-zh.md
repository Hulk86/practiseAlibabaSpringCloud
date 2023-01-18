# demoAliCloud 21讲


## 21讲的笔记
* ** 通过在 RestTemplate 的bean类中加上 @LoadBalance 启动客户端负载均衡(当前这个服务本质上是一个客户端，或者说是客户端代理)
* springcloud-nacos-order = 28810
  







***
##网上提前看到的
* 通过配置文件 application.yaml 配置客户端负载均衡（ribbon client）的类型。
  #### 通过配置文件的配置项来定制负载均衡的策略，ps: 通过写配置类的 在 nacos-order-ribbon
  #### nacos-true-consumer:
  ####   ribbon:
  ####     NIWSServerListClassName: com.netflix.loadbalancer.ConfigurationBasedServerList
  ####     # NFLoadBalancerClassName: com.alibaba.cloud.nacos.ribbon.NacosRule
  ####  NFLoadBalancerRuleClassName: com.alibaba.cloud.nacos.ribbon.NacosRule







