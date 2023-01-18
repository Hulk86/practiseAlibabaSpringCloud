package com.study.cloud.alibaba.config;

/**
 * 其中：RibbonClient中name 微服务名称，configuration配置类
 *
 * 注意：configuration等于的TestConfiguration必须是@Configuration，但要注意它不在主应用程序上下文的@ComponentScan中，
 * 否则它将被所有@RibbonClients共享。如果使用@ComponentScan（或@SpringBootApplication），
 * 则需要采取措施以避免包含它（例如，将其放在单独的，不重叠的包中，或者指定要在@ComponentScan中显式扫描的包）
 */
public @interface ExcludeFromComponentScan {
  //估计一般情况下，就算是全局也没有问题，这里只是学习使用一下而已

}
