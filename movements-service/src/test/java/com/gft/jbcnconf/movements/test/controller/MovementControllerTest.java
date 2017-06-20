package com.gft.jbcnconf.movements.test.controller;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.gft.jbcnconf.movements.controller.MovementController;
import com.gft.jbcnconf.movements.domain.Movement;
import com.gft.jbcnconf.movements.domain.MovementType;
import com.gft.jbcnconf.movements.resource.MovementAssembler;
import com.gft.jbcnconf.movements.resource.MovementAssembler.MovementResource;
import com.gft.jbcnconf.movements.service.MovementService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovementController.class)
@ActiveProfiles("test")
public class MovementControllerTest {
	
	@Autowired MockMvc mvc;

	@MockBean private MovementService movementService;
	@MockBean private MovementAssembler movementAssembler;

	@Test
	public void getMovementTest() throws Exception {
		Date when = new Date();
		String movementJson = "{\"id\": \"1\",\"accountId\": 1,\"type\": \"HOTEL\",\"amount\": 100,\"when\":" + when.getTime() + "}";
		Movement movement = getMovement (when);
		given(this.movementService.get("1")).willReturn(movement);
		MovementResource mResource = new MovementResource(movement);
		given(this.movementAssembler.toResource(movement)).willReturn(mResource); 
		this.mvc.perform(get("/movement/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(movementJson));
	}
	
	@Test
	public void postMovementTest() throws Exception {
		Date when = new Date();
		String movementJson = "{\"id\": \"1\",\"accountId\": 1,\"type\": \"HOTEL\",\"amount\": 100,\"when\":" + when.getTime() + "}";
		Movement movement = getMovement (when);
		given(this.movementService.save(Mockito.any(Movement.class))).willReturn(movement); 
		MovementResource mResource = new MovementResource(movement);
		given(this.movementAssembler.toResource(movement)).willReturn(mResource);  
		this.mvc.perform(post("/movement").contentType(MediaType.APPLICATION_JSON)
				.content(movementJson.getBytes("UTF8")))
				.andExpect(status().isCreated())
				.andExpect(content().json(movementJson));
	}
	
	private Movement getMovement (Date when) {
		Movement mov = new Movement ();
		mov.setId("1");
		mov.setAccountId(1L);
		mov.setAmount(100);
		mov.setType(MovementType.HOTEL);
		mov.setWhen(when);
		return mov;
	}

}
