package com.gft.jbcnconf.customers.test.controller;

 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.gft.jbcnconf.customers.controller.CustomerController;
import com.gft.jbcnconf.customers.domain.Account;
import com.gft.jbcnconf.customers.domain.Customer;
import com.gft.jbcnconf.customers.resource.CustomerAssembler;
import com.gft.jbcnconf.customers.resource.CustomerAssembler.CustomerResource;
import com.gft.jbcnconf.customers.service.CustomerService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
@ActiveProfiles("test") 
public class CustomerControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CustomerService customerService;
    
    @MockBean
    CustomerAssembler customerAssembler;
 

    @Test
    public void getCustomer() throws Exception {    	
    	String customerJson = "{\"id\": 1,\"name\": \"Customer 1\",\"email\": \"Customer1@gft.com\",\"account\": {\"id\": 1,\"description\": \"Account 1\",\"balance\": 1000}}}";
        Customer customer = new Customer(); 
        String name = "Customer 1";
		String email = "Customer1@gft.com";
		customer.setName(name);
        customer.setEmail(email);
        customer.setId(1L);
        Account ac = new Account("Account 1", (double) 1000);
        ac.setId(1L);
		customer.setAccount(ac);
        given(this.customerService.getCustomer(1L)).willReturn(customer);
        CustomerResource cResource = new CustomerResource (customer);
        given (this.customerAssembler.toResource(customer)).willReturn(cResource);

        this.mvc.perform(get("/customers/1").contentType(MediaType.APPLICATION_JSON)) 
                .andExpect(status().isOk())
                .andExpect(content().json(customerJson));
    } 
    
    @Test
    public void getCustomers() throws Exception {    	
    	String customerJson = "[{\"id\": 1,\"name\": \"Customer 1\",\"email\": \"Customer1@gft.com\",\"account\": {\"id\": 1,\"description\": \"Account 1\",\"balance\": 1000}}]";
        Customer customer = new Customer(); 
        String name = "Customer 1";
		String email = "Customer1@gft.com";
		customer.setName(name);
        customer.setEmail(email);
        customer.setId(1L);
        Account ac = new Account("Account 1", (double) 1000);
        ac.setId(1L);
		customer.setAccount(ac);
		List<Customer> cList = new ArrayList<Customer>();
		cList.add(customer);
        given(this.customerService.findCustomers()).willReturn(cList);
        CustomerResource cResource = new CustomerResource (customer);
        List<CustomerResource> cResources = new ArrayList<CustomerResource> ();
        cResources.add(cResource);
        given (this.customerAssembler.toResources(cList)).willReturn(cResources);

        this.mvc.perform(get("/customers").contentType(MediaType.APPLICATION_JSON)) 
                .andExpect(status().isOk())
                .andExpect(content().json(customerJson));
    } 
    
    @Test
    public void getByAccount() throws Exception {    	
    	String customerJson = "{\"id\": 1,\"name\": \"Customer 1\",\"email\": \"Customer1@gft.com\",\"account\": {\"id\": 1,\"description\": \"Account 1\",\"balance\": 1000}}}";
        Customer customer = new Customer(); 
        String name = "Customer 1";
		String email = "Customer1@gft.com";
		customer.setName(name);
        customer.setEmail(email);
        customer.setId(1L);
        Account ac = new Account("Account 1", (double) 1000);
        ac.setId(1L);
		customer.setAccount(ac);
        given(this.customerService.findCustomersByAccountId(1L)).willReturn(customer);
        CustomerResource cResource = new CustomerResource (customer);
        given (this.customerAssembler.toResource(customer)).willReturn(cResource);

        this.mvc.perform(get("/customers/search?accountId=1").contentType(MediaType.APPLICATION_JSON)) 
                .andExpect(status().isOk())
                .andExpect(content().json(customerJson));
    }
}