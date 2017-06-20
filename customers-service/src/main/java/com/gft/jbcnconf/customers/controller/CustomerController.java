package com.gft.jbcnconf.customers.controller;

import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gft.jbcnconf.customers.resource.CustomerAssembler;
import com.gft.jbcnconf.customers.service.CustomerService;

/**
 *  {@link Customer} Controller 
 * @author MOCR
 *
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {
     
	 /**
	  *  {@link Customer} Service 
	  */
	 @Autowired private CustomerService customerService;
	 /**
	  *  {@link Customer} Resource Assembler 
	  */
	 @Autowired private CustomerAssembler customerAssembler;
	
	 /**
     * C[R]UD
     * @param id - {@link Customer} ID
     * @return response ResponseEntity<CustomerAssembler.CustomerResource>
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerAssembler.CustomerResource> getCustomer(@PathVariable long id) {
        return Optional.ofNullable(customerService.getCustomer(id))              
                .map(c -> new ResponseEntity<>(customerAssembler.toResource(c), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * C[R]UD 
     * @return ResponseEntity<List<CustomerAssembler.CustomerResource>> 
     */
    @GetMapping()
    public ResponseEntity<List<CustomerAssembler.CustomerResource>> getCustomers() {
        return  Optional.ofNullable(customerService.findCustomers())              
                .map(c -> new ResponseEntity<>(customerAssembler.toResources(c), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Search {@link Customer} by {@link Account} ID
     * @param accountId
     * @return response ResponseEntity<CustomerAssembler.CustomerResource> 
     */
    @GetMapping(path = "/search")
    public ResponseEntity<CustomerAssembler.CustomerResource> getByAccount(@RequestParam("accountId") long accountId) {
    	 return  Optional.ofNullable(customerService.findCustomersByAccountId(accountId))              
                 .map(c -> new ResponseEntity<>(customerAssembler.toResource(c), HttpStatus.OK))
                 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}