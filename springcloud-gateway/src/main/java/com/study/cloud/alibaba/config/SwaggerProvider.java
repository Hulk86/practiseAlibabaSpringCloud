package com.study.cloud.alibaba.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * 需要自建 swagger ：
 * 参考 网页： SpringCloudAlibaba项目搭建nacos+gateway
 * 网页地址 ：https://blog.csdn.net/qq_39934154/article/details/121914132?spm=1001.2101.3001.6650.4&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-4-121914132-blog-124278091.pc_relevant_recovery_v2&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-4-121914132-blog-124278091.pc_relevant_recovery_v2&utm_relevant_index=5
 */
@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {

    /**
     * swagger的api json文档路径
     */
    public static final String API_URI = "/v2/api-docs";
    /**
     * Eureka发现功能的方法的名字，注册的服务会加入这个前缀
     */
    public static final String EUREKA_SUB_PFIX = "CompositeDiscoveryClient_";
    /**
     * 服务发现的路由处理器
     */
    private final DiscoveryClientRouteDefinitionLocator routeLocator;

    public SwaggerProvider(DiscoveryClientRouteDefinitionLocator routeLocator) {
      this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {

      List<SwaggerResource> resources = new ArrayList<>();

      List<String> routes = new ArrayList<>();
      //从DiscoveryClientRouteDefinitionLocator 中取出routes，构造成swaggerResource
      routeLocator.getRouteDefinitions().subscribe(routeDefinition -> {
        resources.add(swaggerResource(
            //获取id(服务注册的id)
            routeDefinition.getId()
                //去除CompositeDiscoveryClient_前缀
                .substring(EUREKA_SUB_PFIX.length()),
            //获取路由定义信息列表
            routeDefinition.getPredicates()
                //获取路径信息PredicateDefinition{name='Path', args={pattern=/byb-provider2/**}}
                .get(0)
                .getArgs()
                //将pattern中的/**替换为服务swagger文档路径
                .get("pattern")
                .replace("/**", API_URI)));
      });
      return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {

      SwaggerResource swaggerResource = new SwaggerResource();
      swaggerResource.setName(name);
      swaggerResource.setLocation(location);
      swaggerResource.setSwaggerVersion("2.0");
      return swaggerResource;
    }


}
