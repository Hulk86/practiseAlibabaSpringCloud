package com.study.cloud.alibaba.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import java.time.LocalDateTime;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 配置分页插件
 *
 */
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig implements MetaObjectHandler {

  /**
   * 分页插件
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
  }

  /**
   * 乐观锁插件
   *
   * @return
   */
  @Bean
  public OptimisticLockerInterceptor optimisticLockerInterceptor() {
    return new OptimisticLockerInterceptor();
  }

  /**
   * 自动填充功能
   */
  @Override
  public void insertFill(MetaObject metaObject) {
    this.setInsertFieldValByName("createTime", LocalDateTime.now(),metaObject);
    this.setInsertFieldValByName("updateTime",LocalDateTime.now(),metaObject);
  }
  @Override
  public void updateFill(MetaObject metaObject) {
    this.setUpdateFieldValByName("updateTime",LocalDateTime.now(),metaObject);
  }
}