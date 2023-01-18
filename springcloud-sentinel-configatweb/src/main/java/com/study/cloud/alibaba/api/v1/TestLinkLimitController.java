package com.study.cloud.alibaba.api.v1;

import com.study.cloud.alibaba.commons.Result;
import com.study.cloud.alibaba.commons.utils.SpringContextUtil;
import com.study.cloud.alibaba.service.IOrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Hulk
 * @date ：Created in 2023/1/13 21:28
 * @description ：TODO...
 * @modified By ：
 * @version:
 */
@RestController
@RequestMapping(value = "/linkLimit")
public class TestLinkLimitController {

  @Autowired
  IOrderService iOrderService;

  @ApiOperation(
      value = "测试",
      notes = "测试",

      httpMethod = "GET"
  )
  @RequestMapping(value = "/test1", method = RequestMethod.GET)
  public ResponseEntity<Result> test1(@RequestParam Integer id) throws InterruptedException {
    Environment environment = SpringContextUtil.getBean(Environment.class);
    String serverInfo = environment.getProperty("server.servlet.context-path") + "-" + environment.getProperty("server.port");
    System.out.println("访问链接：http://localhost:" +environment.getProperty("server.port")+environment.getProperty("server.servlet.context-path"));
    String result = "getInfo , " + serverInfo;
//        Thread.sleep(5000l);

    result = result + iOrderService.getUser();

    System.out.println(result);
    return new ResponseEntity(Result.ok(result), HttpStatus.OK);

  }

  @ApiOperation(
      value = "测试",
      notes = "测试",

      httpMethod = "GET"
  )
  @RequestMapping(value = "/test2", method = RequestMethod.GET)
  public ResponseEntity<Result> test2(@RequestParam Integer id) throws InterruptedException {
    Environment environment = SpringContextUtil.getBean(Environment.class);
    String serverInfo = environment.getProperty("server.servlet.context-path") + "-" + environment.getProperty("server.port");
    System.out.println("访问链接：http://localhost:" +environment.getProperty("server.port")+environment.getProperty("server.servlet.context-path"));
    String result = "getInfo , " + serverInfo;
//        Thread.sleep(5000l);

    result = result.concat(iOrderService.getUser());
    System.out.println(result);
    return new ResponseEntity(Result.ok(result), HttpStatus.OK);

  }
}
