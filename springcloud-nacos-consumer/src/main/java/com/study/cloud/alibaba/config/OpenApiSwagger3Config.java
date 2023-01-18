package com.study.cloud.alibaba.config;

import com.alibaba.nacos.common.utils.CollectionUtils;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author ：Hulk
 * @date ：Created in 2023/1/16 10:39
 * @description ：TODO...
 * @modified By ：
 * @version:
 */
@EnableOpenApi
@Configuration
public class OpenApiSwagger3Config {


  @Value("${swagger3.enable}")

  private Boolean enableSwagger;

  @Value("${swagger3.title}")

  private String title;

  @Value("${swagger3.description}")

  private String description;

  @Value("${swagger3.authHeaderKey}")

  private String authHeaderKey;

  @Value("${swagger3.version}")

  private String version;

  /*@Bean
  public Docket docketV1() {

    return new Docket(DocumentationType.OAS_30).groupName("v1")

        .pathMapping("/")

        // 是否启用

        .enable(enableSwagger)

        // Swagger UI 头部信息

        .apiInfo( apiInfo())

        // 指定生成文档的接口

        .select()

        //类上有RestController的就进行扫描

        //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
          .apis(RequestHandlerSelectors.basePackage("com.study.cloud.alibaba.api.v1"))


        .paths(PathSelectors.any())

        .build().securityContexts(null)
        .securitySchemes(null);

        // 设置安全认证信息

        //.securitySchemes(securitySchemes())

        // 设置安全认证应用范围

        //.securityContexts(securityContexts());

  }*/

  @Bean
  public Docket docketV2() {

    return new Docket(DocumentationType.OAS_30).pathMapping("/")
        // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
        /*.enable(enable)*/
        .apiInfo(apiInfo())
        // 设置哪些接口暴露给Swagger展示
        .select()
        // 扫描所有有注解的api，用这种方式更灵活
        //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
        // 扫描指定包中的swagger注解
        .apis(RequestHandlerSelectors.basePackage("com.study.cloud.alibaba.api.v2"))
        // 扫描所有 .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.regex("(?!/ApiError.*).*"))
        .paths(PathSelectors.any())
        .build()
        // 支持的通讯协议集合
        .protocols(new HashSet<String>(Arrays.asList("https", "http")))

        .securitySchemes(securitySchemes())
        .securityContexts(securityContexts());
/*

    return new Docket(DocumentationType.OAS_30).groupName("v2")

        .pathMapping("/")

        // 是否启用

        .enable(enableSwagger)

        // Swagger UI 头部信息

        .apiInfo( apiInfo())

        // 指定生成文档的接口

        .select()

        //类上有RestController的就进行扫描

        //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .apis(RequestHandlerSelectors.basePackage("com.study.cloud.alibaba.api.v2"))


        .paths(PathSelectors.any())

        .build()

     //设置安全认证信息

    .securitySchemes(securitySchemes())

     //设置安全认证应用范围

    .securityContexts(securityContexts());*/

  }

  /**
   * 支持的通讯协议集合
   * @param type1
   * @param type2
   * @return
   */
  private Set<String> newHashSet(String type1, String type2){
    Set<String> set = new HashSet<>();
    set.add(type1);
    set.add(type2);
    return set;
  }

  /**
   * 认证的安全上下文
   */
  private List<SecurityScheme> securitySchemes() {
    List<SecurityScheme> securitySchemes = new ArrayList<>();
    securitySchemes.add(new ApiKey("token", "token", "header"));
    return securitySchemes;
  }

  /**
   * 授权信息全局应用
   */
  private List<SecurityContext> securityContexts() {
    List<SecurityContext> securityContexts = new ArrayList<>();
    securityContexts.add(SecurityContext.builder()
        .securityReferences(defaultAuth())
        .forPaths(PathSelectors.any()).build());
    return securityContexts;
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    List<SecurityReference> securityReferences = new ArrayList<>();
    securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
    return securityReferences;
  }


  /**

   * Swagger UI 头部信息

   * @return Api

   */

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(this.title)
        .description(this.description)
        .version(this.version)
        .build();
  }



  /**

   * 权限认证信息(token)

   * @return 集合

   */

  /*private List<SecurityScheme> securitySchemes() {

    ApiKey apiKey = new ApiKey(authHeaderKey, authHeaderKey, In.HEADER.toString());

    return Collections.singletonList(apiKey);

  }



  *//**

   * 设置权限认证应用范围

   *//*

  private List<SecurityContext> securityContexts() {

    return Collections.singletonList(

        SecurityContext.builder()

            .securityReferences(Collections.singletonList(new SecurityReference(authHeaderKey,

                new AuthorizationScope[]{new AuthorizationScope("global", "")})))

            .build()

    );

  }*/


}
