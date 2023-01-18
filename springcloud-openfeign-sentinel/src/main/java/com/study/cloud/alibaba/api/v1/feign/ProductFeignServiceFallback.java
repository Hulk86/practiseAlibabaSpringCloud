package com.study.cloud.alibaba.api.v1.feign;

import com.study.cloud.alibaba.commons.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * openfeign 熔断降级的类
 */
@Component
public class ProductFeignServiceFallback implements ProductControllerFegin {

  public Result getProductById(@RequestParam(name="id") Integer id){
    return Result.error("jiangjila-11111111111111111111111111111111111111111111");
  }

}
