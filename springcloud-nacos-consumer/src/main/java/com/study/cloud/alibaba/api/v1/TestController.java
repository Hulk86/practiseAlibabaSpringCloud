package com.study.cloud.alibaba.api.v1;

import com.google.gson.Gson;
import com.study.cloud.alibaba.commons.Result;
import com.study.cloud.alibaba.commons.entity.User;
import com.study.cloud.alibaba.commons.utils.SpringContextUtil;
import com.study.cloud.alibaba.db.UserEntity;
import com.study.cloud.alibaba.db.UserRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/test1")
@RestController(value = "TestController1")
@ApiModel(value="NotificationController",
    description = "各种通知的总接口"
)

public class TestController {

    @Autowired
    UserRepository userRepository;

    @ApiOperation(
        value = "获取此用户所有的通知信息",
        notes = "获取此用户所有的通知信息；一般是登录后的第一个请求。返回此用户的未处理的事务的数量和一些基本信息",

        httpMethod = "GET"
    )
    @GetMapping("/sayHi")
    //@RequiresPermissions("user:list")
    public Result sayHi(@RequestParam  String name) throws InterruptedException {
        Environment environment = SpringContextUtil.getBean(Environment.class);
        String serverInfo = environment.getProperty("server.servlet.context-path") + "-" + environment.getProperty("server.port");
        System.out.println("访问链接：http://localhost:" +environment.getProperty("server.port")+environment.getProperty("server.servlet.context-path"));

        Optional<UserEntity> userEntityOptional =  userRepository.findByName(name);

        String result = "hello " + serverInfo + new Gson().toJson(userEntityOptional);
//        Thread.sleep(5000l);
        result = result.concat(name);
        System.out.println(result);
        return Result.ok(result);
    }

    @PostMapping("/setUser")
    public Result setUser(@RequestBody User user){
        System.out.println(user.toString());
        return Result.ok(user);
    }
}
