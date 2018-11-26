package com.nebula.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
/**
 * 
 * @author zheyan.yan
 * @EnableZuulProxy zuul路由分发注解
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
public class NebulaZuulAppalication {
	
	
	 public static void main(String[] args) {
	        SpringApplication.run( NebulaZuulAppalication.class, args );
	    }
	 
	 
}
