package com.study.cloud.alibaba.api.v1.blockhandlers;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.cloud.alibaba.commons.Result;

/**
 * @author ：Hulk
 * @date ：Created in 2023/1/7 20:33
 * @description ：TODO...
 * @modified By ：
 * @version:
 */
public class OrderBlockHandlers {

  //像这样如果是在外部，则需要加上static
  public static Result orderDetailDegradeHandler(Integer orderId, BlockException ex) {

    return  Result.error("get order detail, 被降级！！！");
  }

  public static Result fallbackHandlerForOrderDetail(Integer orderId, Throwable e) {
    System.out.println(e.toString());
    return Result.error("get order detail, 异常了！！！");
  }

}
