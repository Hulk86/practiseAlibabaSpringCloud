package com.study.cloud.alibaba.api.v1;

import com.study.cloud.alibaba.api.v1.feign.TestControllerFegin;
import com.study.cloud.alibaba.commons.Result;
import com.study.cloud.alibaba.commons.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/testLocal")
@RestController(value = "TestLocalController")
@ApiModel(value="NotificationController",
    description = "各种通知的总接口"
)

public class TestLocalController {

    //使用openfeign, restTemplate 可以下岗了

    /*RestTemplate loadRestTemplate;

    @Autowired
    public void setLoadRestTemplate(RestTemplate loadRestTemplate) {
        this.loadRestTemplate = loadRestTemplate;
    }*/




    @ApiOperation(
        value = "本地测试",
        notes = "测试本地接口，不转发",

        httpMethod = "GET"
    )
    @RequestMapping(path="/sayHiLoacl" ,method = RequestMethod.GET)
    public Result sayHiLoacl(@RequestParam  String name) {
        //Thread.sleep(5000l);



        return Result.ok("localTestSayHi____"+name);
    }

}
