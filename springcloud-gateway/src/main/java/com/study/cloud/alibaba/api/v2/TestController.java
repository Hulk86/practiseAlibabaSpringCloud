package com.study.cloud.alibaba.api.v2;

import com.study.cloud.alibaba.commons.Result;
import com.study.cloud.alibaba.commons.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/test2")
@RestController(value = "TestController2")
/*@ApiModel(value="NotificationController",
    description = "各种通知的总接口"
)*/

public class TestController {

   /* @ApiOperation(
        value = "获取此用户所有的通知信息",
        notes = "获取此用户所有的通知信息；一般是登录后的第一个请求。返回此用户的未处理的事务的数量和一些基本信息",

        httpMethod = "GET"
    )*/
    @GetMapping("/sayHi2")
    //@RequiresPermissions("user:list")
    public Result sayHi(@RequestParam  String name) throws InterruptedException {
        String result = "hello ";
//        Thread.sleep(5000l);
        result = result.concat(name);
        System.out.println(result);
        return Result.ok(result);
    }

    @PostMapping("/setUser2")
    public Result setUser(@RequestBody User user){
        System.out.println(user.toString());
        return Result.ok(user);
    }
}