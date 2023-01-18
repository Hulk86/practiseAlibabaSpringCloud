package com.study.cloud.alibaba.api.v1.feign;

import com.study.cloud.alibaba.commons.Result;
import com.study.cloud.alibaba.commons.entity.User;
import com.study.cloud.alibaba.config.FeignConfig;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * value 指定调用rest接口对应的在 nacos中的服务的名
 * path 指定调用rest的requestMapping
 */
@FeignClient(value = "product-consumer", path = "/product", fallback = ProductFeignServiceFallback.class) //指定 openfeign 的熔断的实现类，当找不到服务的时候就会降级，调用到这个类指向的服务方法
//@FeignClient(value = "product-consumer", path = "/product" , configuration = FeignConfig.class)
public interface ProductControllerFegin {


  @GetMapping("/getById")
  public Result getProductById(@RequestParam(name="id") Integer id);


  /*@RequestLine("GET /getById")
  public Result getProductById(@QueryMap Map<String, Integer> mapinfo); //就算是只有一个参数，也要QueryMap,相当于 springMvc里面的  @RequestParam*/

/*  requestBody 的案例
@RequestLine("POST /app1/post")
  @Body("{user}")
  String post(@Param("user") User user);
  */
}