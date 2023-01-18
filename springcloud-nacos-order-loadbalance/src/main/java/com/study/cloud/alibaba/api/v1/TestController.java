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


@RequestMapping("/test1")
@RestController(value = "TestController1")
@ApiModel(value="NotificationController",
    description = "各种通知的总接口"
)

public class TestController {

    RestTemplate loadRestTemplate;

    @Autowired
    public void setLoadRestTemplate(RestTemplate loadRestTemplate) {
        this.loadRestTemplate = loadRestTemplate;
    }


    @ApiOperation(
        value = "获取此用户所有的通知信息",
        notes = "获取此用户所有的通知信息；一般是登录后的第一个请求。返回此用户的未处理的事务的数量和一些基本信息",

        httpMethod = "GET"
    )
    @GetMapping("/sayHi")
    //@RequiresPermissions("user:list")
    public Result sayHi(@RequestParam  String name) throws InterruptedException {

//        Thread.sleep(5000l);
        Result result = loadRestTemplate.getForObject("http://nacos-true-consumer/test1/sayHi?name="+name, Result.class);
        System.out.println(result);

        return Result.ok(result);
    }

    @PostMapping("/setUser")
    public Result setUser(@RequestBody User user){
        System.out.println(user.toString());
        return Result.ok(user);
    }
}
