package com.gft.jbcnconf.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type = {EnableHypermediaSupport.HypermediaType.HAL})
@EnableHystrix 
@EnableDiscoveryClient 
public class CustomerApplication { 
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }  
}
