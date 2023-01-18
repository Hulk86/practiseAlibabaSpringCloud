package com.study.cloud.alibaba.api.v1;

import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.ThreadUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController(value = "testUsingResponseController")
@RequestMapping(path = "/testUsingResponse")
public class TestUsingResponseController {

  @ResponseStatus(HttpStatus.ACCEPTED)
  @ResponseBody
  @RequestMapping(path = "/getInfo", method = RequestMethod.GET)
  public int getInfo(@RequestParam Integer id) {
    return ThreadLocalRandom.current().nextInt(1000);
  }

}
