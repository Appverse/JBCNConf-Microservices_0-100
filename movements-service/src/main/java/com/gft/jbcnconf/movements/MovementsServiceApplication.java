package com.gft.jbcnconf.movements;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type = {EnableHypermediaSupport.HypermediaType.HAL})
@EnableHystrix 
@EnableDiscoveryClient 
public class MovementsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovementsServiceApplication.class, args);
	}
}
