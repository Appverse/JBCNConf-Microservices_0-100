package com.gft.jbcnconf.movements.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.gft.jbcnconf.movements.controller.MovementController;
import com.gft.jbcnconf.movements.domain.Movement;

 

@Component
public class MovementAssembler extends ResourceAssemblerSupport<Movement, MovementAssembler.MovementResource> { 
	 public MovementAssembler() {
	        super(MovementController.class, MovementResource.class);
	    } 
	 
	    /**
	     * Create the aggregate resource and add the self reference link
	     *
	     * @param customer
	     * @return
	     */
	    @Override
	    public MovementResource toResource(Movement movement) {
	        final MovementResource resource = new MovementResource(movement);
	        // Add self reference link
	        resource.add(linkTo(methodOn(MovementController.class, movement.getId()).getMovement(movement.getId()))
	                .withSelfRel());
	        return resource;
	    }

	    
	    public static class MovementResource extends Resource<Movement> {
	        public MovementResource(Movement content) {
	            super(content);
	        }
	    }
	 
}
 
 
