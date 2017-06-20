package com.gft.jbcnconf.movements.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
/**
 * Output channel binder 
 * @author MOCR
 *
 */
public interface MovementEventSource {
	@Output 
    MessageChannel output();
}
