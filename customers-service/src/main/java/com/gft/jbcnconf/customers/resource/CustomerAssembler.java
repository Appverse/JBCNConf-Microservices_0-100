package com.gft.jbcnconf.customers.resource;
 
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.gft.jbcnconf.customers.controller.CustomerController;
import com.gft.jbcnconf.customers.domain.Customer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CustomerAssembler extends ResourceAssemblerSupport<Customer, CustomerAssembler.CustomerResource> { 

    public CustomerAssembler() {
        super(CustomerController.class, CustomerResource.class);
    } 
    
    /**
     * Create the aggregate resource and add the self reference link
     *
     * @param customer
     * @return
     */
    @Override
    public CustomerResource toResource(Customer customer) {
        final CustomerResource resource = new CustomerResource(customer);
        // Add self reference link
        resource.add(linkTo(methodOn(CustomerController.class, customer.getId()).getCustomer(customer.getId()))
                .withSelfRel());
        return resource;
    }

    
    public static class CustomerResource extends Resource<Customer> {
        public CustomerResource(Customer content) {
            super(content);
        }
    }
}