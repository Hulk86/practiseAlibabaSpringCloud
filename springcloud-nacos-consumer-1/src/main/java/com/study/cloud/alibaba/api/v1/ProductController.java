package com.study.cloud.alibaba.api.v1;

import com.google.gson.Gson;
import com.study.cloud.alibaba.commons.Result;
import com.study.cloud.alibaba.commons.utils.SpringContextUtil;
import com.study.cloud.alibaba.db.ProductEntity;
import com.study.cloud.alibaba.db.ProductRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Hulk
 * @date ：Created in 2023/1/2 10:58
 * @description ：TODO...
 * @modified By ：
 * @version:
 */
@RequestMapping("/product")
@RestController(value = "ProductController")
@ApiModel(value="ProductController",
    description = "产品相关的微服务"
)
public class ProductController {

  @Autowired
  ProductRepository productRepository;

  private final Gson gsonTransfer = new Gson();

  @ApiOperation(
      value = "获取此用户所有的通知信息",
      notes = "获取此用户所有的通知信息；一般是登录后的第一个请求。返回此用户的未处理的事务的数量和一些基本信息",

      httpMethod = "GET"
  )
  @GetMapping("/getById")
  //@RequiresPermissions("user:list")
  public Result getProductById(@RequestParam Integer id) throws InterruptedException {
    Environment environment = SpringContextUtil.getBean(Environment.class);
    String serverInfo = environment.getProperty("server.servlet.context-path") + "-" + environment.getProperty("server.port");
    System.out.println("访问链接：http://localhost:" +environment.getProperty("server.port")+environment.getProperty("server.servlet.context-path"));
    String result = "product, " + serverInfo;
      // Thread.sleep(5000l);

    Optional<ProductEntity> productEntityOptional = productRepository.findById(Long.valueOf(id));
    if (productEntityOptional.isPresent()) {
      result = result + gsonTransfer.toJson(productEntityOptional.get());
    } else {
      result = result + "product is null";
    }
    System.out.println(result);
    return Result.ok(result);
  }
}
