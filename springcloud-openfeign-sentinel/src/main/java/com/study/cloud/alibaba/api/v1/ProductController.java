package com.study.cloud.alibaba.api.v1;

import com.alibaba.cloud.sentinel.SentinelProperties;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.study.cloud.alibaba.api.v1.feign.ProductControllerFegin;
import com.study.cloud.alibaba.commons.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/simpleProduct")
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
    @RequestMapping(path="/getById" ,method = RequestMethod.GET)
    @SentinelResource(value = "theProductGetById", blockHandler = "flowLimitBygetById" )
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



    //@Autowired
    //SentinelProperties sentinelProperties;

    public Result flowLimitBygetById(Integer id , BlockException be) {
        return Result.error("被限流");
    }

    @PostConstruct
    public void init() {
     //   sentinelProperties.getDatasource();
/*        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, "192.168.3.67:8819");
        properties.put(PropertyKeyConst.NAMESPACE, "a879cac6-3fac-4ddb-822a-36132829d72c");

        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(properties, "DemoAliCloud1", "openfeign-sentinel-sentinelConfig",
            source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());*/
    }

}
