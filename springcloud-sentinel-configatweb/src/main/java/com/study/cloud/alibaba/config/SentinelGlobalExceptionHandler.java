package com.study.cloud.alibaba.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import com.study.cloud.alibaba.commons.Result;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 * sentinel 的统一以上处理 ，包括 流控， 降级等(好像如果存在了 @ControllerAdvice ，则这个 sentinel的BlockExceptionHandler 会失效， 至少DegradeException的会失效 )
 */
@Component
public class SentinelGlobalExceptionHandler implements BlockExceptionHandler {

  @Override
  public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      BlockException e) throws Exception {


    Result rs=null;

    if(e instanceof FlowException)
      rs= Result.error("-1","接口限流了");
    else if(e instanceof DegradeException)
      rs=Result.error("-2","服务降级了");
    else if(e instanceof ParamFlowException)
      rs=Result.error("-3","参数限流了");
    else if(e instanceof AuthorityException)
      rs=Result.error("-4","权限规则不通过");
    else if(e instanceof SystemBlockException)
      rs=Result.error("-5","系统保护");

    httpServletResponse.setStatus(500);
    httpServletResponse.setContentType("application/json;charset=utf-8");
    httpServletResponse.getWriter().write(JSON.toJSONString(rs));

  }

}
