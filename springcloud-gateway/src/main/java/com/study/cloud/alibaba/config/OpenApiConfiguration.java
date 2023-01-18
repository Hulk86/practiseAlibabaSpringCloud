package com.study.cloud.alibaba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*@Configuration
@EnableSwagger2*/
public class OpenApiConfiguration{
/*

  public static final String CLIENT_CREDENTIALS = "Client Credentials";

  //swagger2 的地址 http://127.0.0.1:8810/swagger-ui.html
  //swagger3 的地址 http://127.0.0.1:8810/swagger-ui.html
  private final String defaultBuildVersion = "1.0.0";
  @Bean
  public Docket api() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2).groupName("v1")
        .forCodeGeneration(true)
        .useDefaultResponseMessages(false)
        .genericModelSubstitutes(ResponseEntity.class)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.study.cloud.alibaba.api.v1"))
        .paths(PathSelectors.any())
        .build();
    docket.apiInfo(apiInfo());
    return docket;
  }

  @Bean
  public Docket api2() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2).groupName("v2")
        .forCodeGeneration(true)
        .useDefaultResponseMessages(false)
        .genericModelSubstitutes(ResponseEntity.class)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.study.cloud.alibaba.api.v2"))
        .paths(PathSelectors.any())
        .build();
    docket.apiInfo(apiInfo());
    return docket;
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("alibaba study")
        .description("study alibaba nacos desciption.")
        .version("0.0.1")
        .build();
  }
*/


/*  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }*/
}
