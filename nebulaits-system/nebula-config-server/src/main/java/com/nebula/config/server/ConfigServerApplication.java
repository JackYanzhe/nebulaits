package com.nebula.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 
 * @author zheyan.yan
 *该项目无数据源操作（故配置忽略）
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableConfigServer
@EnableDiscoveryClient
@EnableEurekaClient
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
