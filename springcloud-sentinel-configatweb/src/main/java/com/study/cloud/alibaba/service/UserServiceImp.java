package com.study.cloud.alibaba.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.cloud.alibaba.commons.Result;
import org.springframework.stereotype.Service;

/**
 * @author ：Hulk
 * @date ：Created in 2023/1/13 21:24
 * @description ：TODO...
 * @modified By ：
 * @version:
 */

@Service
public class UserServiceImp implements IOrderService {

  @Override
  @SentinelResource(value = "getUser", blockHandler = "orderDetailDegradeHandler")
  public String getUser() {
    return "normal user666";
  }

  public  String orderDetailDegradeHandler(BlockException ex) {

    return "  被流控！！！777777777777";
    //return  Result.error("get order detail, 被降级！！！");
  }
}
