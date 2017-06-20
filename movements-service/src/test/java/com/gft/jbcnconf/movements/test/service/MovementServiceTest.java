package com.gft.jbcnconf.movements.test.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.gft.jbcnconf.movements.domain.Movement;
import com.gft.jbcnconf.movements.domain.MovementType;
import com.gft.jbcnconf.movements.event.MovementEventEmitterService;
import com.gft.jbcnconf.movements.event.MovementEventType;
import com.gft.jbcnconf.movements.repository.MovementRepository;
import com.gft.jbcnconf.movements.service.MovementService;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DirtiesContext
public class MovementServiceTest {
	
	@Autowired MovementService service;
	
	@TestConfiguration
	static class MovementServiceImplTestContextConfiguration {
		
		@MockBean static MovementRepository repository;
		
		@MockBean static MovementEventEmitterService eventEmitter;
		
		@Bean
		public MovementService movementService() {
			return new MovementService(repository, eventEmitter);
		}
	}
	
	@Test
	public void testGetMovement() { 
		Movement mov = getMovement (); 
		when(MovementServiceImplTestContextConfiguration.repository.findOne("1")).thenReturn(mov); 
		Movement found  = service.get("1");
		verify(MovementServiceImplTestContextConfiguration.repository, times(1)).findOne(Mockito.any(String.class));
		assertThat (found.getAmount()).isEqualTo(mov.getAmount());
	}
	
	@Test
	public void testNewMovement() { 
		Movement mov = getMovement (); 
		when(MovementServiceImplTestContextConfiguration.repository.save(mov)).thenReturn(mov); 
		when(MovementServiceImplTestContextConfiguration.eventEmitter.sendMovementEvent(mov, MovementEventType.CREATED)).thenReturn(true); 
		Movement created  = service.save(mov);
		verify(MovementServiceImplTestContextConfiguration.repository, times(1)).save(Mockito.any(Movement.class));
		verify(MovementServiceImplTestContextConfiguration.eventEmitter, times(1)).sendMovementEvent(Mockito.any(Movement.class), Mockito.any(MovementEventType.class));
		assertThat (created.getAmount()).isEqualTo(mov.getAmount());
	}
	
	private Movement getMovement () {
		Movement mov = new Movement ();
		mov.setAccountId(1L);
		mov.setAmount(100);
		mov.setType(MovementType.HOTEL);
		mov.setWhen(new Date());
		return mov;
	}

}
