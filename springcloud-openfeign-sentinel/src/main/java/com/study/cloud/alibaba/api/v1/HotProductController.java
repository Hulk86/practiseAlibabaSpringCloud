package com.study.cloud.alibaba.api.v1;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.cloud.alibaba.commons.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Hulk
 * @date ：Created in 2023/1/15 16:12
 * @description ：TODO...
 * @modified By ：
 * @version:
 */
@RestController
@RequestMapping(value = "/hotProduct")
public class HotProductController {

  /*
  1. 假设 参数大部分值都是热点参数， 那单机阈值就主要针对热点参数进行流控
   */
  @RequestMapping(value = "/getProductById" , method = RequestMethod.GET)
  @SentinelResource(value = "getProductById-hotBlock", blockHandler = "HotProductBolckHandler")
  public ResponseEntity<Result> getProductById(@RequestParam Integer productId) {

    return ResponseEntity.ok(Result.ok("当前商品为——————"+ String.valueOf(productId)));

  }

  public ResponseEntity<Result> HotProductBolckHandler(Integer productId, BlockException be) {
    return new ResponseEntity(Result.ok("热点商品:"+String.valueOf(productId)), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
