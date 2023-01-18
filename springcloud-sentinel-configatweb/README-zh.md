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
#其他的一些问题
***

## RestControllerAdvice的理解
 ````
   * RestControllerAdvice 的注解里面组合了 @ControllerAdvice 和 @ResponseBody
   * 所以默认是返回 response body的，如果返回的是一个对象，他也会帮你把对象转换成 json, 同时默认的 ResponseStatus 是 HttpStatus.ok
   * 如果需要修改返回的类型，可以用以下的两种方式:
   * 1. 返回的类型是一个 ResponseEntity<T>的类型，这个可以帮忙设置 responseStatus
   * 2. 利用 @ResponseStatus的注解
   
   个人理解还是 ResponseEntity 更灵活更有优势
 ````

##50讲
1. 关联流控： 关联的资源超过了阈值，则本资源被限制，在界面上就是高级选项里面配置的资源如果超过了阈值，则本资源被限制

## 51讲

1.链路流控: TestLinkLimitController 中的 test1 和 test2 共同访问了UserServiceImp的 getUser()，getUser() 在代码层面注入了 SentinelResource，这也代表了 SentinelResource 不仅可以注入controller 还可以注入一般的方法上。 同时，为了防止 sentinel 对资源的收敛管理，需要在yaml中配置 spring.cloud.sentinel.web-context-unify = false。 

1.1.在资源上用了 SentinelResource 则不能被统一流控规则handler了， 要自己加上流控规则的 blockHandler

## 52，53, 54讲
1. 快速失败:
2. warm up: （用于处理激增流量）在预热的时间内，慢慢的放开阈值内的通过率，比如设置的阈值是50，时间是30秒，则从一个通过率开始没， 再30秒内慢慢的让通过率达到50，之后保持50个稳定
3. 排队等待: (一般用于处理脉冲流量)设置一个超时时间，如果访问量达到了阈值，则多余的不是直接失败，而是等待一段时间（自己设置的超时时间），如果过了超时时间还是不能打成，则真的会被流控

## 55讲
1. 慢调用降级: 慢调用激活后，在熔断期过后，是进入的半开状态，当再次发生一次慢调用后，则下次立即熔断降级
2. 异常比例或者异常数降级:
  ````
  测试中发现 此时sentinel的全局异常捕获 SentinelGlobalExceptionHandler 会和 SpringMvc的全局异常捕获冲突，也就是异常会首先被 springMvc的GlobalRestExceptionHandler 捕获，不会跳转到 SentinelGlobalExceptionHandler， 好像此时必须修改 springMvc的GlobalRestExceptionHandler 让其继续抛出异常，继续抛出，
  ````