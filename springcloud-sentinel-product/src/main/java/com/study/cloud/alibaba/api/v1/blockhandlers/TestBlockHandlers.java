package com.study.cloud.alibaba.api.v1.blockhandlers;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.cloud.alibaba.commons.Result;

/**
 * @author ：Hulk
 * @date ：Created in 2023/1/6 19:41
 * @description ：TODO...
 * @modified By ：
 * @version:
 */

public class TestBlockHandlers {

  //像这样如果是在外部，则需要加上static
  public static Result blockHandlerForSayHi(String name, BlockException ex) {

    return  Result.error("say hi,被流控！！！");
  }


  public static Result fallbackHandlerForSayHi(String name, Throwable e) {
    System.out.println(e.toString());
    return Result.error("say hi, 异常了！！！");
  }
}
