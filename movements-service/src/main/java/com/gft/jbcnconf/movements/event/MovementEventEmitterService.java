package com.gft.jbcnconf.movements.event;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.gft.jbcnconf.movements.domain.Movement;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class MovementEventEmitterService {

	private final Logger log = Logger.getLogger(MovementEventEmitterService.class);

	@Autowired
	private MovementEventSource source;
   
	/**
	 * Build movement event
	 * @param movement {@link Movement}
	 * @param eventType
	 * @return MovementEvent
	 */
	public MovementEvent buildEvent(Movement movement, MovementEventType eventType) {
		MovementEvent event = new MovementEvent();
		event.setEntity(movement);
		event.setType(eventType);
		event.setCreatedAt(new Date());
		event.setEventId(UUID.randomUUID());
		event.setLastModified(new Date());
		return event;

	}

	/**
	 * Build and send Event to the output channel
	 * 
	 * @param movement
	 *            {@link Movement}
	 * @param eventType
	 *            {@link MovementEventType}
	 * @return boolean true if sent
	 */
	@HystrixCommand(fallbackMethod = "sendMovementEventFallback")
	public Boolean sendMovementEvent(Movement movement, MovementEventType eventType) {
		log.info("::: SEND EVENT :::: " + eventType); 
		return source.output().send(MessageBuilder.withPayload(buildEvent(movement, eventType))
				.setHeader("contentType", MediaType.APPLICATION_JSON_UTF8_VALUE).build());
	}

	/**
	 * Fallback method
	 * 
	 * @param movement
	 *            {@link Movement}
	 * @param eventType
	 *            {@link MovementEventType}
	 * @return false
	 */
	private Boolean sendMovementEventFallback(Movement movement, MovementEventType eventType) {
		// Store event to be send it later
		log.info("::: SEND EVENT Fallback :::: " + movement + " " + eventType);
		return false;
	}
}
