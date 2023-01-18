package com.study.cloud.alibaba.db.first.config;

import com.alibaba.druid.pool.DruidDataSource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ：Hulk
 * @date ：Created in 2023/1/16 18:04
 * @description ：TODO...
 * @modified By ：
 * @version:
 */
@Configuration
@EnableTransactionManagement // 开启事务
@EnableJpaRepositories(  // 利⽤ EnableJpaRepositories 配置哪些包下⾯的 Repositories，采⽤哪个 EntityManagerFactory 和哪个 TransactionManagement
    basePackages = {"com.study.cloud.alibaba.db.first.**"},// master 数据源的 repository 的包路径
    entityManagerFactoryRef = "masterEntityManagerFactory",// 改变 master 数据源的 EntityManagerFactory 的默认值，改为 masterEntityManagerFactory
    transactionManagerRef = "masterTransactionManager" // 改变 master 数据源的 TransactionManagement 的默认值，masterTransactionManager
)
public class MasterDataSourceConfig {

  /**
   * 指定 master 数据源的 dataSource 配置
   *
   * @return master 数据源配置
   */
  @Primary
  @Bean(name = "masterDataSourceProperties")
  @ConfigurationProperties("spring.datasource.master") // master 数据源的配置前缀采⽤ spring.datasource.master
  public DataSourceProperties dataSourceProperties() {
    return new DataSourceProperties();
  }

  /**
   * 可以选择不同的数据源，这⾥使⽤ HikariDataSource，创建数据源
   *
   * @param masterDataSourceProperties 数据源配置
   * @return master 数据源
   */
  @Primary
  @Bean(name = "masterDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.hikari.master") //配置 master 数据源所⽤的 hikari 配置 key 的前缀
  public DataSource dataSource(@Qualifier("masterDataSourceProperties")
      DataSourceProperties masterDataSourceProperties) {

    DruidDataSource dataSource =
        masterDataSourceProperties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
    /*if (StringUtils.hasText(masterDataSourceProperties.getName())) {
      dataSource.getp(masterDataSourceProperties.getName());
    }*/

  /*  HikariDataSource dataSource2 =
        masterDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    if (StringUtils.hasText(masterDataSourceProperties.getName())) {
      dataSource2.setPoolName(masterDataSourceProperties.getName());
    }*/

    return dataSource;
  }

  /**
   * 配置 master 数据源的 entityManagerFactory 命名为 masterEntityManagerFactory，⽤来对实体进⾏⼀些操作
   *
   * @param builder          构建器
   * @param masterDataSource master 数据源
   * @return master 实体管理工厂
   */
  @Primary
  @Bean(name = "masterEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder builder,
      @Qualifier("masterDataSource") DataSource masterDataSource) {
    return builder.dataSource(masterDataSource)
        // master 数据的实体所在的路径
        .packages("com.study.cloud.alibaba.db.first.**")
        // persistenceUnit 的名字采⽤ master
        .persistenceUnit("master")
        .build();
  }

  /**
   * 配置 master 数据源的事务管理者，命名为 masterTransactionManager 依赖 masterEntityManagerFactory
   *
   * @param masterEntityManagerFactory master 实体管理工厂
   * @return master 事务管理者
   */
  @Primary
  @Bean(name = "masterTransactionManager")
  public PlatformTransactionManager transactionManager(@Qualifier("masterEntityManagerFactory") EntityManagerFactory masterEntityManagerFactory) {
    return new JpaTransactionManager(masterEntityManagerFactory);
  }
}
