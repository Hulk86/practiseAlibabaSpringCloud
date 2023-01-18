package com.study.cloud.alibaba.api.v2;

import com.study.cloud.alibaba.commons.Result;
import com.study.cloud.alibaba.commons.entity.User;
import com.study.cloud.alibaba.db.first.entity.UserEntity;
import com.study.cloud.alibaba.db.first.repository.MasterUserRepository;
import com.study.cloud.alibaba.db.second.entity.SecondUserEntity;
import com.study.cloud.alibaba.db.second.repostory.SecondUserRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/test2")
@RestController(value = "TestController2")
@ApiModel(value="NotificationController",
    description = "各种通知的总接口"
)

public class TestController {

    @Autowired
    MasterUserRepository masterUserRepository;

    @Autowired
    SecondUserRepository secondUserRepository;

    @ApiOperation(
        value = "获取此用户所有的通知信息",
        notes = "获取此用户所有的通知信息；一般是登录后的第一个请求。返回此用户的未处理的事务的数量和一些基本信息",

        httpMethod = "GET"
    )
    @GetMapping("/sayHi2")
    //@RequiresPermissions("user:list")
    public ResponseEntity sayHi(@RequestParam  String name) {
        Result result = new Result();
//        Thread.sleep(5000l);
        result.put("queryName", name);
        Optional<UserEntity> userEntityOptional =  masterUserRepository.findByName(name);
        if(userEntityOptional.isPresent()) {
            result.put("userName", userEntityOptional.get().getName());
            result.put("mobilePhone", userEntityOptional.get().getMobile());
        }
        return new ResponseEntity(result, HttpStatus.OK);

    }

    @ApiOperation(
        value = "获取此用户所有的通知信息",
        notes = "获取此用户所有的通知信息；一般是登录后的第一个请求。返回此用户的未处理的事务的数量和一些基本信息",

        httpMethod = "GET"
    )
    @GetMapping("/sayHi3")
    //@RequiresPermissions("user:list")
    public ResponseEntity sayHi3(@RequestParam  String name) {
        Result result = new Result();
//        Thread.sleep(5000l);
        result.put("queryName", name);
        Optional<SecondUserEntity> userEntityOptional =  secondUserRepository.findByName(name);
        if(userEntityOptional.isPresent()) {
            result.put("userName", userEntityOptional.get().getName());
            result.put("mobilePhone", userEntityOptional.get().getMobile());
        }
        return new ResponseEntity(result, HttpStatus.OK);

    }

    @PostMapping("/setUser2")
    public Result setUser(@RequestBody User user){
        System.out.println(user.toString());
        return Result.ok(user);
    }
}
