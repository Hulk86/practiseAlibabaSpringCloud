# springcloud-order-openfeign


## 笔记
* 使用 openfeign的步骤
  
  1. 加入依赖:
    ````
    <!-- 在parent pom 里面加入 openfeign的 dependency-->
       <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-openfeign-dependencies</artifactId>
            <version>${spring-cloud-openfeign.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    <!-- 在 本当前项目 pom 里面加入 openfeign的 dependency-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
    ````
  2. 添加 feign客户端接口 
    ````
    @FeignClient(value = "nacos-true-consumer", path = "/test1")
    public interface TestControllerFegin {
    }
    value : 用来指定 调用的nacos中的 服务提供者的 服务名
    path : 是调用的服务应用的 resetMapping 中的值，比如这里调用的服务是
            springcloud-nacos-consumer 中的 v1/TestController，他的restMapping 是test1
    至于里面的body的内容 完全可以参考 v1/TestController 的内容来写，不过看的出来，一些requestBody
    和 responseBody是相同的，所以有可能需要写2遍，或者抽出来做成一个package
  
    ````
  3. 加入 @EnableFeignClients
    ````
     在原有的基础上在启动类上加上 ，如下
     @ComponentScans(value = {
        @ComponentScan(value="com.study.cloud.alibaba.**")

     })
     @EnableDiscoveryClient
     @SpringBootApplication
     @EnableFeignClients(basePackages = "com.study.cloud.alibaba.api.**")
     public class OrderOpenFeignApplication {
         public static void main(String[] args) {
             SpringApplication.run(OrderOpenFeignApplication.class, args);
         }
     }

    ````
  
  4. 使用 openfeign 代替 restTemplate
    ````
     在当前的代理转发服务的controller中， 用 @Autowired 启用 上面步骤生成的 openfeign组件
  
      @Autowired
      TestControllerFegin testControllerFegin;
    ````

* openfeign的 配置
  
  1. 日志配置：分为全局日志配置和局部日志配置
    1.1 通过@Configuration 实现全局配置, 同时需要打开所有 带着@FeignClient 类的日志等级，
     ````
       @Configuration
       public class FeignConfig {
       
           @Bean
           public Logger.Level feignLoggerLevel() {
           return  Level.FULL;
           }
       
       }
       打开日志等级，可以是logback.xml 里面， 也可以是 application.yaml 里面
       比如在  application.yaml
        logging:
          level:
            com.study.cloud.alibaba.api.v1.feign: debug
     ````
     1.2 配置局部的日志：
     
          1.2.1 关闭@Configuration, 在@FeignClient的配置项中 使用 configuration 的属性：
                @FeignClient(value = "product-consumer", path = "/product" , configuration = FeignConfig.class)
     
     1.3 通过配置文件的方式（推荐！！！）,以上这些想看到feign的日志，前提都是需要把有@FrignClient的类的日志等级调整为debug
     ````
     feign:
       client:
         config:
         #服务名
         product-consumer:
           loggerLevel: BASIC
           #contract: feign.Contract.Default #设置为默认的契约（还原成了原生注解）
         nacos-true-consumer:
           loggerLevel: FULL
     ````

* openfeign的契约配置
  契约的意思是将 springMvc的注解还原为feign的原生注解， feign原生注解有自己的一套注解，老的springcloud用的是原生的 feign，后面才有的openfeign, openfeign 完成实现了 springMVC的注解， 老的feign则没有
  ````
   配置文件需要打开 contract 配置为使用原生的feign(feign1.0)配置，
   @RequestLine("GET /getById")
   public Result getProductById(@QueryMap Map<String, Integer> mapinfo); //就算是只有一个参数，也要QueryMap,相当于 springMvc里面的  @RequestParam
  ````



* openfeign的超时设置
























