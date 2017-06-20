package com.gft.jbcnconf.movements.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gft.jbcnconf.movements.domain.Movement;

/**
 * MongoDB {@link Movement} repository
 * @author MOCR
 *
 */
public interface MovementRepository extends MongoRepository<Movement, String> {  
}
