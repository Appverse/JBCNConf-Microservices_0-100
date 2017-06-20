package com.gft.jbcnconf.customers.test.event;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gft.jbcnconf.customers.event.movement.Movement;
import com.gft.jbcnconf.customers.event.movement.MovementEvent;
import com.gft.jbcnconf.customers.event.movement.MovementEventListener;
import com.gft.jbcnconf.customers.event.movement.MovementEventType;
import com.gft.jbcnconf.customers.event.movement.MovementType;
import com.gft.jbcnconf.customers.service.CustomerService;
 

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)  
public class MovementEventListenerTest { 
	
	@Autowired MovementEventListener eventListener;
	
	@TestConfiguration
	static class MovementEventListenerConfiguration {
		
		@MockBean private static CustomerService customerService;

		@Bean
		public MovementEventListener movementEventListenerService() {
			return new MovementEventListener(customerService);
		}
	}
	
	@Test 
	  public void testNewMovement() {
		MovementEvent event = getMovementEvent();
		eventListener.process(MessageBuilder.withPayload(event).build()); 
		Mockito.doNothing().when(MovementEventListenerConfiguration.customerService).updateBalance(event.getEntity().getAccountId(), event.getEntity().getAmount());  
		verify(MovementEventListenerConfiguration.customerService, times(1)).updateBalance(event.getEntity().getAccountId(), event.getEntity().getAmount()); 
	  }
	
	/**
	 * Mock MovementEvent
	 * @return MovementEvent
	 */
	private MovementEvent getMovementEvent() {
		MovementEvent event = new MovementEvent (); 
		event.setCreatedAt(new Date());
		event.setEntity(getMovement());
		event.setEventId(UUID.randomUUID());
		event.setLastModified(new Date());
		event.setType(MovementEventType.CREATED);
		return event;
	}
	/**
	 * Mock Movement
	 * @return Movement
	 */
	private Movement getMovement () {
		Movement mov = new Movement ();
		mov.setId("id");
		mov.setAccountId(1L);
		mov.setAmount(100);
		mov.setType(MovementType.HOTEL);
		mov.setWhen(new Date());
		return mov;
	}
}
