package com.study.cloud.alibaba.sentinelconfig;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：Hulk
 * @date ：Created in 2023/1/6 19:50
 * @description ：TODO...
 * @modified By ：
 * @version:
 */
@Configuration
public class RuleCenter {

  public static final String SENTINEL_RESOURCE_PRODUCT = "product-sentinel";
  public static final String SENTINEL_RESOURCE_TEST = "test-sentinel";

  @Bean
  public SentinelResourceAspect sentinelResourceAspect(){
    return new SentinelResourceAspect();
  }

  @PostConstruct
  public void init() {

    List<FlowRule> ruleList = new ArrayList<>();


    FlowRule flowRule = new FlowRule();
    flowRule.setResource(SENTINEL_RESOURCE_PRODUCT);
    flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
    flowRule.setCount(2);
    ruleList.add(flowRule);

    FlowRule flowRule2 = new FlowRule();
    flowRule.setResource(SENTINEL_RESOURCE_TEST);
    flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
    flowRule.setCount(2);
    ruleList.add(flowRule2);

    FlowRuleManager.loadRules(ruleList);

    //flowRule.setResource();

  }
}
