package com.study.cloud.alibaba.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author ：Hulk
 * @date ：Created in 2023/1/5 23:47
 * @description ：TODO...
 * @modified By ：
 * @version:
 */

@Component
@RefreshScope
@Data
public class RedisConfig {

  @Value("${spring.redis.jedis.pool.max-active:100}")
  private Integer maxActive;

}
