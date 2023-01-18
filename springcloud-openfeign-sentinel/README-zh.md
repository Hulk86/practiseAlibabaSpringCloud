# springcloud-order-openfeign


## 笔记

## 56讲 openfeign 整合降级规则
1. 添加依赖 ：
   ````
   1.1. spring-cloud-starter-openfeign 
   1.2. spring-cloud-starter-sentinel 
   1.3. spring-cloud-starter-alibaba-nacos-discovery
   ````
2. 配置文件添加依赖 :
   ````
   spring:
     sentinel:
       #openFeign 整合 sentinel
       enabled: true
   ````
3. openfeign 流控: 必须结合 SentinelResource注解，设计一个 blockHandler

4. openfeign 熔断降级，
  ````
  需要实现 @FeignClient接口的地方指定 blockHandler, 
  具体参考 ProductControllerFegin 和 ProductFeignServiceFallback
  ProductFeignServiceFallback 必须注册为 Component
  ````

***

## 57讲 热点参数流控
本质上就是针对访问调用时不同的参数进行有区别的流控，分为一般情况和热点参数的情况，热点参数需要在控制台指定

1. 需要结合 SentinelResource 来使用，
   ````
   在此demo 中 是在 HotProductController 模拟了此过程
   
   ````
2. 界面操作： 需要添加了热点流控之后，才能在后续的界面上点击编辑进行高级设置，设置那些热点参数

## 58讲 系统保护规则
1. 好像 在windows 上面设置会报错



***

## 59讲 sentinel 规则持久化
1. 原始模式: 保存在内存中，重启就没了
2. 拉(pull)模式 : 从数据源（本地文件，RDBMS关系型数据库）拉取。使用时需要在客户端注册数据源：将对应的数据源注册到对应的 RuleManager, 将写数据源注册至 transport的 WritableDataSourceRegister 中。
3. 推(push)模式： 生产环境下一般更常用的是 push 模式的数据源. 对于 push 模式的数据源，比如远程配置中心 （Zookeeper, Nacos, Apollo 等等）， 推送的操作不应由 Sentinel 客户端进行，而应该经控制台统一进行管理，直接进行推送， 数据源仅负责获取配置中心推送的配置并更新到本地。 因此推送规则正确做法应该是 ** 配置中心控制台/Sentinel 控制台 --》 配置中心 --》 Sentinel 数据源 --》 Sentinel **， 而不是经 Sentinel 数据源推送至配置中心。 这样的流程就非常清晰了。


4. 步骤： 
   1. 添加pom 依赖
      ````
               <!-- 这里面组件太多了，不需要这么多
       <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-sentinel-datasource</artifactId>
        </dependency>
        -->

        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-datasource-nacos</artifactId>
        </dependency>
      ````
   2. 配置文件配置 资源和数据源
      
   3.





写一个收到注入jdbc的类




















