package com.taozhi.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.taozhi.svc", "com.taozhi.controller", "com.taozhi.filter" })
@MapperScan({ "com.taozhi.mapper" })

public class Application extends SpringBootServletInitializer {

    // jar启动  
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // 部署 war启动  
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
