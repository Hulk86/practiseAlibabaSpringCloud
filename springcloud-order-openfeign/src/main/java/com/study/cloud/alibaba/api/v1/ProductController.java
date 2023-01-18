package com.study.cloud.alibaba.api.v1;

import com.study.cloud.alibaba.api.v1.feign.ProductControllerFegin;
import com.study.cloud.alibaba.api.v1.feign.TestControllerFegin;
import com.study.cloud.alibaba.commons.Result;
import com.study.cloud.alibaba.commons.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/the_product")
@RestController(value = "ProductController")
@ApiModel(value="ProductController",
    description = "微服务对外的总出入口，Product的总接口"
)
public class ProductController {

    //使用openfeign, restTemplate 可以下岗了

    /*RestTemplate loadRestTemplate;

    @Autowired
    public void setLoadRestTemplate(RestTemplate loadRestTemplate) {
        this.loadRestTemplate = loadRestTemplate;
    }*/

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductControllerFegin productControllerFegin;


    @ApiOperation(
        value = "获取此用户所有的通知信息",
        notes = "获取此用户所有的通知信息；一般是登录后的第一个请求。返回此用户的未处理的事务的数量和一些基本信息",

        httpMethod = "GET"
    )
    @RequestMapping(path="/sayHi" ,method = RequestMethod.GET)
    //@RequiresPermissions("user:list")
    public Result getById(@RequestParam  Integer id){

//        Thread.sleep(5000l);
        //HashMap<String,Integer> requestMap = new HashMap<>();
       // requestMap.put("id",id);
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);

        LocalDateTime now2 = ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime();
        System.out.println(now2);


        Result result = productControllerFegin.getProductById(id);
        System.out.println(result);

        return Result.ok(result);
    }

}
