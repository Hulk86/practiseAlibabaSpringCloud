package com.study.cloud.alibaba.db.second.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

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
    basePackages = {"com.study.cloud.alibaba.db.second.**"},// second 数据源的 repository 的包路径
    entityManagerFactoryRef = "secondaryEntityManagerFactory",// 改变 second 数据源的 EntityManagerFactory 的默认值，改为 secondEntityManagerFactory
    transactionManagerRef = "secondaryTransactionManager" // 改变 second 数据源的 TransactionManagement 的默认值，secondTransactionManager
)
public class SecondaryDataSourceConfig {

  /**
   * 指定 second 数据源的 dataSource 配置
   * secondary
   * @return second 数据源配置
   */
  @Bean(name = "secondaryDataSourceProperties")
  @ConfigurationProperties("spring.datasource.second") // second 数据源的配置前缀采⽤ spring.datasource.second
  public DataSourceProperties dataSourceProperties() {
    return new DataSourceProperties();
  }

  /**
   * 可以选择不同的数据源，这⾥使⽤ HikariDataSource，创建数据源
   *
   * @param secondaryDataSourceProperties 数据源配置
   * @return second 数据源
   */
  @Bean(name = "secondaryDataSource")
  //@ConfigurationProperties(prefix = "spring.datasource.druid") //配置 second 数据源所⽤的 hikari 配置 key 的前缀
  @ConfigurationProperties(prefix = "spring.datasource.hikari.slave") //配置 second 数据源所⽤的 hikari 配置 key 的前缀

  public DataSource dataSource(@Qualifier("secondaryDataSourceProperties")
      DataSourceProperties secondaryDataSourceProperties) {

  /*  DruidDataSource dataSource =
        secondaryDataSourceProperties.initializeDataSourceBuilder().type(DruidDataSource.class).build();*/
    /*if (StringUtils.hasText(secondDataSourceProperties.getName())) {
      dataSource.getp(secondDataSourceProperties.getName());
    }*/

    HikariDataSource dataSource =
        secondaryDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    if (StringUtils.hasText(secondaryDataSourceProperties.getName())) {
      dataSource.setPoolName(secondaryDataSourceProperties.getName());
    }

    return dataSource;
  }

  /**
   * 配置 second 数据源的 entityManagerFactory 命名为 secondEntityManagerFactory，⽤来对实体进⾏⼀些操作
   *
   * @param builder          构建器
   * @param secondaryDataSource second 数据源
   * @return second 实体管理工厂
   */

  @Bean(name = "secondaryEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder builder,
      @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
    return builder.dataSource(secondaryDataSource)
        // second 数据的实体所在的路径
        .packages("com.study.cloud.alibaba.db.second.**")
        // persistenceUnit 的名字采⽤ second
        .persistenceUnit("second")
        .build();
  }

  /**
   * 配置 second 数据源的事务管理者，命名为 secondaryTransactionManager 依赖 secondaryEntityManagerFactory
   *
   * @param secondaryEntityManagerFactory second 实体管理工厂
   * @return secondary 事务管理者
   */

  @Bean(name = "secondaryTransactionManager")
  public PlatformTransactionManager transactionManager(@Qualifier("secondaryEntityManagerFactory") EntityManagerFactory secondaryEntityManagerFactory) {
    return new JpaTransactionManager(secondaryEntityManagerFactory);
  }
}
