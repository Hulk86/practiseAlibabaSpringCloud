package com.study.cloud.alibaba.api.v1;

import com.study.cloud.alibaba.api.v1.feign.TestControllerFegin;
import com.study.cloud.alibaba.commons.Result;
import com.study.cloud.alibaba.commons.entity.User;
import com.study.cloud.alibaba.db.first.entity.UserEntity;
import com.study.cloud.alibaba.db.first.repository.MasterUserRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RequestMapping("/test1")
@RestController(value = "TestController1")
@ApiModel(value="NotificationController",
    description = "各种通知的总接口"
)

public class TestController {

    //使用openfeign, restTemplate 可以下岗了

    /*RestTemplate loadRestTemplate;

    @Autowired
    public void setLoadRestTemplate(RestTemplate loadRestTemplate) {
        this.loadRestTemplate = loadRestTemplate;
    }*/


    @Autowired
    TestControllerFegin testControllerFegin;

    @Autowired
    MasterUserRepository masterUserRepository;

    @ApiOperation(
        value = "获取此用户所有的通知信息",
        notes = "获取此用户所有的通知信息；一般是登录后的第一个请求。返回此用户的未处理的事务的数量和一些基本信息",

        httpMethod = "GET"
    )
    @RequestMapping(path="/sayHi" ,method = RequestMethod.GET)
    public Result sayHi(@RequestParam  String name) {
        //Thread.sleep(5000l);
        Result result = testControllerFegin.sayHi(name);
        System.out.println(result);

        Optional<UserEntity> userEntityOptional =  masterUserRepository.findByName("管理员--007");
        if(userEntityOptional.isPresent()) {
            result.put("userName", userEntityOptional.get().getName());
        }
        return Result.ok(result);
    }

    @RequestMapping(path="/setUser" ,method = RequestMethod.PUT)
    public Result setUser(@RequestBody User user){
        System.out.println(user.toString());
        return Result.ok(user);
    }


}
