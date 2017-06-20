package com.gft.jbcnconf.movements.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

import com.gft.jbcnconf.movements.event.MovementEventSource;

@Configuration
@EnableBinding(MovementEventSource.class)
public class MovementStreamConfig {
}