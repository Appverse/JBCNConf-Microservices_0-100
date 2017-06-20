package com.gft.jbcnconf.movements.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.jbcnconf.movements.domain.Movement;
import com.gft.jbcnconf.movements.resource.MovementAssembler;
import com.gft.jbcnconf.movements.service.MovementService;

@RestController
@RequestMapping("/movement")
public class MovementController {
	
	 @Autowired private MovementService movementService;
	 @Autowired private MovementAssembler movementAssembler;

	/**
	 * Get {@link Movement} Movement - C[R]UD
	 * 
	 * @param id String
	 * @return response ResponseEntity 
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<MovementAssembler.MovementResource> getMovement(@PathVariable String id) {
		return Optional.ofNullable(movementService.get(id))
				.map(c -> new ResponseEntity<>(movementAssembler.toResource(c), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	/**
	 * Post a movement - [C]RUD
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping()
	public ResponseEntity createMovement(@RequestBody Movement movement) {
		return Optional.ofNullable(movementService.save(movement)).map(e -> new ResponseEntity<>(e, HttpStatus.CREATED))
				.orElseThrow(() -> new RuntimeException("Movement creation failed"));
	}

}
