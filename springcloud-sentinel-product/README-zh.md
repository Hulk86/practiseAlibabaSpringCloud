sentinel-product


## 笔记

* ** sentinel 不仅仅可以用在微服务中，还可以用在分布式系统中，当做服务的轻量级流量控制组件
* ** @SentinelResource 注解可以注入规则(本质上是一个ASPECT),所以必须加一个aspect的Bean。 此演示工程中，在RuleCenter中添加了一个 SentinelResourceAspect 的 Bean。

## 流控规则
  
* **  @SentinelResource 的其主要的属性有:
  ````
  value : 当前sentinel的资源名。
  blockHandler: 触发流控后的处理函数，此函数需要满足 :
                1. 必须是public
                2. 参数必须和@SentinelResource 的函数的参数一致，
                3. 返回值必须和@SentinelResource 的函数的返回值一致。
                4. 此函数的参数最后面可以加一个 BlockException类型 的参数，此exception 是一个
                   abstract，有几个实现类，比如 FlowException, DegradeException 等。这样就
                   可以区分是哪种类型的异常了
  blockHandlerClass: 如果此 blockHandler 方法不在同一个类中，可以用此属性写上实现类的位置, blockHandler 方法必须是static方法。
  fallback: 当接口出现了异常，交给fallback指定的方法进行处理
  fallbackClass : 如果此 fallback 方法不在同一个类中，可以用此属性写上实现类的位置, 此时fallback 方法必须是static方法。
  exceptionsToIgnore: 标明哪些异常不需要 fallback 处理
  ````
     1.添加依赖
  
## 降级规则
* ** 检查接口，如果发现接口超过了规则的设定，则把接口熔断(降级)
  ````
    //设置规则类型
    degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
    //degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
    //一分钟之内，执行了2次，出现了2次异常，就会触发熔断（降级）
    degradeRule.setCount(1);
    //最小需要请求2次
    degradeRule.setMinRequestAmount(2);
    //窗口期，一旦触发了熔断，则至少需要等待这么一个周期，再次正常进入判断的时间
    //10秒过后--再次启动是处于半开状态，恢复接口调用，但是再发生错误，立即就再次进入熔断（在本案例中，正常情况下是需要2次异常才会进入熔断，而半开状态下，1次异常就立马进入熔断）
    degradeRule.setTimeWindow(10);
    //设置一次服务降级的探测时间段，
    degradeRule.setStatIntervalMs(60 * 1000);
  
    ruleList.add(degradeRule);
    DegradeRuleManager.loadRules(ruleList);
  ````


## Sentinel 控制台
网址是：
#### https://github.com/alibaba/Sentinel/releases
文档：
#### https://github.com/alibaba/Sentinel/wiki
* ** 启动参数
 ````
  -Dsentinel.dashboard.auth.username=sentinel 用于指定控制台的登录用户名为 sentinel；
  -Dsentinel.dashboard.auth.password=123456 用于指定控制台的登录密码为 123456；如果省略这两个参数，默认用户和密码均为 sentinel；
  -Dserver.servlet.session.timeout=7200 用于指定 Spring Boot 服务端 session 的过期时间，如 7200 表示 7200 秒；60m 表示 60 分钟，默认为 30 分钟；
 ````

* ** spring-cloud 整合到 sentinel
 ````
     在pom中加入 sentinel-starter
       <dependency>
      <groupId>com.alibaba.cloud</groupId>
      <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
    </dependency>
    
    2. 在application.ymal 加入指定需要注册到的 sentinel的url地址
    spring:
      cloud:
        sentinel:
          transport:
            port: 58853 #和控制台交流的端口，只要是没有被使用的端口就行
            dashboard: 192.168.41.1:8380
          enabled: true
 ````


***
##流控规则
1. 直接，自己影响自己
2. 关联
3. 链路



