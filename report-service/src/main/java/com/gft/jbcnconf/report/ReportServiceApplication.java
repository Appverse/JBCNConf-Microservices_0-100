package com.gft.jbcnconf.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import com.gft.jbcnconf.report.remote.CustomerClient;
/**
 * Reporting Application 
 * @author MOCR
 *
 */
@SpringBootApplication
@EnableHypermediaSupport(type = {EnableHypermediaSupport.HypermediaType.HAL})
@EnableFeignClients(clients = {CustomerClient.class})
@EnableDiscoveryClient  
@EnableHystrix 
public class ReportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportServiceApplication.class, args);
	}
}
