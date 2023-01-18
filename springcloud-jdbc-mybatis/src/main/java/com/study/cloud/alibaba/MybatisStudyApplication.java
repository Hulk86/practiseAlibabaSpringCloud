package com.study.cloud.alibaba;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ComponentScans(value = {
        @ComponentScan(value="com.study.cloud.alibaba.**")
})
@EnableDiscoveryClient
@SpringBootApplication

@MapperScans(value = {
    @MapperScan(value = "com.study.cloud.alibaba.mybatisplus.**")
})

public class MybatisStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisStudyApplication.class, args);
    }
}

