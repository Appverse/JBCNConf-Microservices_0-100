package com.gft.jbcnconf.movements.test.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.gft.jbcnconf.movements.domain.Movement;
import com.gft.jbcnconf.movements.domain.MovementType;
import com.gft.jbcnconf.movements.repository.MovementRepository;
import com.gft.jbcnconf.movements.test.config.MongoConfiguration;

@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { MongoConfiguration.class })
public class MovementRepositoryTest {

	@Autowired
	private MovementRepository movementsRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	public void getMovementTest() {
		// given
		Movement movement = getMovement();
		mongoTemplate.save(movement);
		// when
		Movement found = movementsRepository.findOne("1");
		// then
		assertThat(found.getAmount()).isEqualTo(movement.getAmount());
	}

	private Movement getMovement() {
		Movement mov = new Movement();
		mov.setId("1");
		mov.setAccountId(1L);
		mov.setAmount(100);
		mov.setType(MovementType.HOTEL);
		mov.setWhen(new Date());
		return mov;
	}
}
