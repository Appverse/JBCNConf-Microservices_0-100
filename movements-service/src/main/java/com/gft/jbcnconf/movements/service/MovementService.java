package com.gft.jbcnconf.movements.service;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.gft.jbcnconf.movements.domain.Movement;
import com.gft.jbcnconf.movements.event.MovementEventEmitterService;
import com.gft.jbcnconf.movements.event.MovementEventType;
import com.gft.jbcnconf.movements.repository.MovementRepository;

/**
 * Movement service for {@link Movement} entity 
 * 
 * @author MOCR
 *
 */
@Service
public class MovementService {

	private final Logger log = Logger.getLogger(MovementService.class);
	
	private final MovementRepository repository;
	
	private final MovementEventEmitterService eventEmitter;
	
	/**
	 * Constructor 
	 * @param repository MovementRepository
	 */
	@Autowired
	public MovementService (MovementRepository repository, MovementEventEmitterService eventEmitter ) {
		this.repository = repository;
		this.eventEmitter = eventEmitter;
	}
	/**
	 * Get a {@link Movement} entity by the given id
	 *
	 * @param id
	 *            is the unique identifier of the {@link Movement} entity
	 * @return an {@link Movement} entity
	 */
	public Movement get(String id) {
		// Get the Movement from the repository
		Movement mov = repository.findOne(id);
		Assert.notNull(mov, "A Movement with the supplied id doesn't exist");
		return mov;
	}
	/**
	 * Create a new {@link Movement} entity.
	 *
	 * @param movement is the {@link Movement} to create
	 * @return the newly created {@link Movement}
	 */
	public Movement save(Movement mov) {
		// Save the Movement to the repository
		log.info("::: SAVE Movement :::: " + mov); 
		mov.setId(UUID.randomUUID().toString());
		mov.setWhen(new Date());
		Movement saved = repository.save(mov);
		eventEmitter.sendMovementEvent(saved, MovementEventType.CREATED);
		return saved;
	}


}
