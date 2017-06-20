package com.gft.jbcnconf.movements.test.event;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gft.jbcnconf.movements.domain.Movement;
import com.gft.jbcnconf.movements.domain.MovementType;
import com.gft.jbcnconf.movements.event.MovementEvent;
import com.gft.jbcnconf.movements.event.MovementEventEmitterService;
import com.gft.jbcnconf.movements.event.MovementEventSource;
import com.gft.jbcnconf.movements.event.MovementEventType;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
public class MovementEventEmmiterTest {

	@MockBean
	private MovementEventSource source;

	@Autowired
	MovementEventEmitterService emitter;

	@TestConfiguration
	static class MovementEventEmitterTestContextConfiguration {
		@Bean
		public MovementEventEmitterService movementEventEmitter() {
			return new MovementEventEmitterService();
		}
	}

	@Test
	public void buildEventTest() {
		Movement mv = getMovement();
		MovementEvent event = emitter.buildEvent(mv, MovementEventType.CREATED);
		assertThat(event.getEntity().getAmount()).isEqualTo(mv.getAmount());
	}

	private Movement getMovement() {
		Movement mov = new Movement();
		mov.setAccountId(1L);
		mov.setAmount(100);
		mov.setType(MovementType.HOTEL);
		mov.setWhen(new Date());
		return mov;
	}
}
