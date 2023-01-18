package com.study.cloud.alibaba.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：Hulk
 * @date ：Created in 2023/1/2 22:24
 * @description ：TODO...
 * @modified By ：
 * @version:
 */
public class CustomFeignInterceptor implements RequestInterceptor {

  Logger logger = LoggerFactory.getLogger(CustomFeignInterceptor.class);

  @Override
  public void apply(RequestTemplate requestTemplate) {
    requestTemplate.header("hhhh","hhhh"); //追加 head ，比如 token什么的，或者language什么的
    requestTemplate.query("qqqq","qqqq"); //追加 requestParam
   // requestTemplate.uri("/admin"); //追加httprequest  http://nacos-true-consumer/test1/admin?name=%E7%AE%A1%E7%90%86%E5%91%98--008&qqqq=qqqq
    logger.info("feign拦截器: [{}]",requestTemplate.request().toString());
  }
}
