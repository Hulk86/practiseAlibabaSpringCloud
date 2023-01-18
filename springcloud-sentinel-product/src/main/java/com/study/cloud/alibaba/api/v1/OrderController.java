package com.study.cloud.alibaba.api.v1;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.study.cloud.alibaba.api.v1.blockhandlers.OrderBlockHandlers;
import com.study.cloud.alibaba.commons.Result;
import io.swagger.annotations.ApiModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Hulk
 * @date ：Created in 2023/1/6 20:41
 * @description ：TODO...
 * @modified By ：
 * @version:
 */
@RestController
@RequestMapping("/productOder")
@ApiModel(value = "商品订单")
public class OrderController {

  @RequestMapping(value ="/detail", method = RequestMethod.GET)
  @SentinelResource(value = "degrade-productOrder", entryType = EntryType.IN,
      blockHandlerClass = OrderBlockHandlers.class, blockHandler = "orderDetailDegradeHandler",
      fallbackClass = OrderBlockHandlers.class ,fallback = "fallbackHandlerForOrderDetail")
  public Result getProductOrderDetail(@RequestParam Integer orderId) {

    System.out.println("test1");
    if (orderId == 0) {
      System.out.println("正常接口调用");
    } else {
      if (orderId == 1) {
        System.out.println("慢调用");
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
    return Result.ok("拿到order详细信息！！！");

  }

  @PostConstruct
  public void init() {
    List<DegradeRule> ruleList = new ArrayList<>();

    DegradeRule degradeRule = new DegradeRule();
    degradeRule.setResource("degrade-productOrder");
    //设置规则类型
    degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
    //degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
    //一分钟之内，执行了2次，出现了2次异常，就会触发熔断（降级）
    degradeRule.setCount(1);
    //最小需要请求2次
    degradeRule.setMinRequestAmount(2);


    //窗口期，一旦触发了熔断，则至少需要等待这么一个周期，再次正常进入判断的时间
    degradeRule.setTimeWindow(10);
    //设置一次服务降级的探测时间段，
    degradeRule.setStatIntervalMs(60 * 1000);

    ruleList.add(degradeRule);

    DegradeRuleManager.loadRules(ruleList);
  }

}
