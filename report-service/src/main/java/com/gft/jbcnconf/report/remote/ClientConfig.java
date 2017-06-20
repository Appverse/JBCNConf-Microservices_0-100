package com.gft.jbcnconf.report.remote;

import org.slf4j.event.Level;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Feign configuration
 * @author MOCR
 *
 */
@Configuration
public class ClientConfig {
	@Bean
	public Level feignLoggerLevel() {
		return Level.TRACE;
	}

}
