package com.gft.jbcnconf.admin.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

import de.codecentric.boot.admin.config.EnableAdminServer;

@EnableAutoConfiguration
@ComponentScan
@EnableEurekaClient
@EnableDiscoveryClient
@EnableAdminServer
@EnableRetry
@EnableTurbine
public class SpringBootAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminApplication.class, args);
	}
}
