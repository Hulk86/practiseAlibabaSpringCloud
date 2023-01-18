package com.study.cloud.alibaba.api.v1;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.cloud.alibaba.commons.Result;
import com.study.cloud.alibaba.commons.utils.SpringContextUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import java.util.concurrent.TimeUnit;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/testDegrade")
@RestController(value = "TestDegrade")
@ApiModel(value="testDegrade",
    description = "测试熔断降级"
)
public class TestDegradeController {

    @ApiOperation(
        value = "测试",
        notes = "测试",

        httpMethod = "GET"
    )
    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public ResponseEntity<Result> getInfo(@RequestParam  String name) throws RuntimeException {
        Environment environment = SpringContextUtil.getBean(Environment.class);
        String serverInfo = environment.getProperty("server.servlet.context-path") + "-" + environment.getProperty("server.port");
        System.out.println("访问链接：http://localhost:" +environment.getProperty("server.port")+environment.getProperty("server.servlet.context-path"));
        String result = "getInfo , " + serverInfo;

        if (name.equals("0")) {
            System.out.println("正常接口调用");
        } else {
            if (name.equals("1")) {
                System.out.println("慢调用"); //慢调用的意思是，收到
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (name.equals("2")) {
                System.out.println("异常调用");
                throw new RuntimeException("接口发生异常");
            }
        }

//        Thread.sleep(5000l);
        result = result.concat(name);
        System.out.println(result);
        return new ResponseEntity(Result.ok(result), HttpStatus.OK);

    }

    @ApiOperation(
        value = "测试",
        notes = "测试",

        httpMethod = "POST"
    )
    @RequestMapping(value = "/addInfo", method = RequestMethod.POST)
    public ResponseEntity<Result> addInfo(@RequestParam  String name) throws InterruptedException {
        Environment environment = SpringContextUtil.getBean(Environment.class);
        String serverInfo = environment.getProperty("server.servlet.context-path") + "-" + environment.getProperty("server.port");
        System.out.println("访问链接：http://localhost:" +environment.getProperty("server.port")+environment.getProperty("server.servlet.context-path"));
        String result = "addInfo , " + serverInfo;
//        Thread.sleep(5000l);
        result = result.concat(name);
        System.out.println(result);
        return new ResponseEntity(Result.ok(result), HttpStatus.OK);
    }

    @ApiOperation(
        value = "测试",
        notes = "测试",

        httpMethod = "PUT"
    )
    //@SentinelResource(blockHandler = "orderDetailDegradeHandler")
    @RequestMapping(value = "/updateInfo", method = RequestMethod.PUT)
    public ResponseEntity<Result> updateInfo(@RequestParam  Integer orderId){
        System.out.println("test1");
        if (orderId == 0) {
            System.out.println("正常接口调用");
        } else {
            if (orderId == 1) {
                System.out.println("慢调用"); //慢调用的意思是，收到
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (orderId == 2) {
                System.out.println("异常调用");
                throw new RuntimeException("接口发生异常");
            }
        }
        return new ResponseEntity(Result.ok("拿到order详细信息！！！"), HttpStatus.OK);
    }

    public ResponseEntity<Result> orderDetailDegradeHandler(Integer orderId, BlockException ex) {

        return  new ResponseEntity(Result.error("get order detail, 被降级666666！！！"),HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ApiOperation(
        value = "测试",
        notes = "测试",

        httpMethod = "DELETE"
    )
    @RequestMapping(value = "/deleteInfo", method = RequestMethod.DELETE)
    public ResponseEntity<Result> deleteInfo(@RequestParam  String name) throws InterruptedException {
        Environment environment = SpringContextUtil.getBean(Environment.class);
        String serverInfo = environment.getProperty("server.servlet.context-path") + "-" + environment.getProperty("server.port");
        System.out.println("访问链接：http://localhost:" +environment.getProperty("server.port")+environment.getProperty("server.servlet.context-path"));
        String result = "deleteInfo , " + serverInfo;
//        Thread.sleep(5000l);
        result = result.concat(name);
        System.out.println(result);
        return new ResponseEntity(Result.ok(result), HttpStatus.OK);
    }


}
