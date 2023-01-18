package com.study.cloud.alibaba.api.v1;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.study.cloud.alibaba.api.v1.blockhandlers.TestBlockHandlers;
import com.study.cloud.alibaba.commons.Result;
import com.study.cloud.alibaba.commons.entity.User;
import com.study.cloud.alibaba.commons.utils.SpringContextUtil;
import com.study.cloud.alibaba.sentinelconfig.RuleCenter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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

    @ApiOperation(
        value = "获取此用户所有的通知信息",
        notes = "获取此用户所有的通知信息；一般是登录后的第一个请求。返回此用户的未处理的事务的数量和一些基本信息",

        httpMethod = "GET"
    )
    @GetMapping("/sayHi")
    @SentinelResource(value = RuleCenter.SENTINEL_RESOURCE_TEST, blockHandlerClass = TestBlockHandlers.class ,blockHandler = "blockHandlerForSayHi"
        ,fallback = "fallbackHandlerForSayHi", fallbackClass = TestBlockHandlers.class, exceptionsToIgnore = {IOException.class} )
    //@RequiresPermissions("user:list")
    public Result sayHi(@RequestParam  String name) throws InterruptedException {
        Environment environment = SpringContextUtil.getBean(Environment.class);
        String serverInfo = environment.getProperty("server.servlet.context-path") + "-" + environment.getProperty("server.port");
        System.out.println("访问链接：http://localhost:" +environment.getProperty("server.port")+environment.getProperty("server.servlet.context-path"));
        String result = "hello , " + serverInfo;
//        Thread.sleep(5000l);
        result = result.concat(name);
        System.out.println(result);
        return Result.ok(result);
    }

    @ApiOperation(
        value = "测试流控时，报异常，并且捕获异常的情况",
        notes = "测试流控时，报异常，并且捕获异常的情况，exceptionsToIgnore = {IOException.class} 里面是忽略掉的异常。",

        httpMethod = "GET"
    )
    @GetMapping("/sayHiAndException")
    @SentinelResource(value = RuleCenter.SENTINEL_RESOURCE_TEST, blockHandlerClass = TestBlockHandlers.class ,blockHandler = "blockHandlerForSayHi"
        ,fallback = "fallbackHandlerForSayHi", fallbackClass = TestBlockHandlers.class, exceptionsToIgnore = {IOException.class} )
    //@RequiresPermissions("user:list")
    public Result sayHiAndThrowExceptions(@RequestParam  String name) throws InterruptedException {
        Environment environment = SpringContextUtil.getBean(Environment.class);
        String serverInfo = environment.getProperty("server.servlet.context-path") + "-" + environment.getProperty("server.port");
        System.out.println("访问链接：http://localhost:" +environment.getProperty("server.port")+environment.getProperty("server.servlet.context-path"));
        String result = "hello , " + serverInfo;
//        Thread.sleep(5000l);
        File fileh = null;
        fileh.canRead();
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
