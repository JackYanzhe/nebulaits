package com.nebula.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 重要的事情说三遍：
 * 文档地址： http://cloud.spring.io/spring-cloud-static/Finchley.RELEASE/single/spring-cloud.html
 * 服务注册中心
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run( EurekaServerApplication.class, args );
    }
}
