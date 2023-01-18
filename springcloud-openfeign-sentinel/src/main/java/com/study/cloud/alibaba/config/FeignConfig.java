package com.study.cloud.alibaba.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.study.cloud.alibaba.interceptor.CustomFeignInterceptor;

/**
 * 全局配置：当时用 @Configuration 将会作用到所有的服务提供方，也就是多个转发模式
 * 局部配置， 不使用 @Configuration，只针对某个服务进行配置, 就不要叫 @Configuration
 * 3. 通过配置文件 配置在
 */
@Configuration
public class FeignConfig {

  /*
  //设置全局的 feign的日志模式 （推荐用配置文件）
  @Bean
  public Logger.Level feignLoggerLevel() {
    return  Level.FULL;
  }*/


  /*//设置全局的 feign的契约模式，Contract.Default 支持原生注解 （推荐用配置文件）
  @Bean
  public Contract feignContract() {
    return  new Contract.Default();
  }*/

  //设置超时时间 （推荐用配置文件）
  /*@Bean
  public Request.Options options() {
    return new Options(15, TimeUnit.SECONDS,30,TimeUnit.SECONDS,true);
  }*/

  //设置拦截器, 同样的也可以通过配置文件设置 拦截器（推荐用配置文件）
 /* @Bean
  public RequestInterceptor feignInterceptor() {
    return new CustomFeignInterceptor();

  }*/
}
