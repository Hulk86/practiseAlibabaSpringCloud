package com.study.cloud.alibaba.api.v1;

import com.study.cloud.alibaba.commons.Result;
import com.study.cloud.alibaba.commons.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RequestMapping("/product")
@RestController(value = "ProductController")
@ApiModel(value="ProductController",
    description = "ProductController 各种通知的总接口"
)

public class ProductController {

    RestTemplate loadRestTemplate;

    @Autowired
    public void setLoadRestTemplate(RestTemplate loadRestTemplate) {
        this.loadRestTemplate = loadRestTemplate;
    }


    @ApiOperation(
        value = "为 product-consumer 服务提供代理转发",
        notes = "product-consumer 服务由springcloud-nacos-consumer-1 提供",

        httpMethod = "GET"
    )
    @GetMapping("/getById")
    //@RequiresPermissions("user:list")
    public Result sayHi(@RequestParam  Integer theID) {
        //Thread.sleep(5000l);
        Result result = loadRestTemplate.getForObject("http://product-consumer/product/getById/?id="+ theID, Result.class);
        System.out.println(result);

        return Result.ok(result);
    }

    @PostMapping("/setUser")
    public Result setUser(@RequestBody User user){
        System.out.println(user.toString());
        return Result.ok(user);
    }
}
