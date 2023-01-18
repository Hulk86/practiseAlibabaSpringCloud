# springcloud-config-product


## 笔记

* ** nacos-config 的配置信息必须放在 *bootstrap.yml*, 不然会报错。 因为整个项目要先启动 nacos-config，
     所以要单独放在 云服务特有的yaml文件， bootstrap.yml 中。
* ** nacos-config 有一个bug， 当项目的 namespace 为 public 时，
     此时如果客户在 bootstrap中显示的配置 的话，那么就会疯狂打印日志，此时可以注释掉这个，日志就不打印了；
     如果namespace是其他的名字的时候，则不会有这个问题
  
* ** 如果 nacos-service 开启了权限校验， 则 username和password 是必须的， 而且登录的用户必须对相关的namespage 有正确的权限
    ````
    #*************** Access Control Related Configurations ***************#
    ### If enable spring security, this option is deprecated in 1.2.0:
    spring.security.enabled=true
    ````

* ** shared-configs 和 extension-configs 都可以用作扩展配置文件加载的功能

* ** @RefreshScope 让nacos-config 的文件参数对应到一个类中
    ````
    @Component
    @RefreshScope
    @Data
    public class RedisConfig {

      @Value("${spring.redis.jedis.pool.max-active:100}")
      private Integer maxActive;

    }
    ````










