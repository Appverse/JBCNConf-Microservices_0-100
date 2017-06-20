package com.gft.jbcnconf.customers.service;

import java.util.List;

import org.apache.log4j.Logger; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.gft.jbcnconf.customers.domain.Account;
import com.gft.jbcnconf.customers.domain.Customer;
import com.gft.jbcnconf.customers.repository.CustomerRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
/**
 * Service for Customer management 
 * @author MOCR
 *
 */
@Service
public class CustomerService {
	
	private final Logger log = Logger.getLogger(CustomerService.class); 
	 
	private final AccountService accountService;
	
	private final CustomerRepository repository;
	
	/**
	 * Constructor 
	 * @param accountService {@link AccountService}
	 * @param repository {@link CustomerRepository}
	 */
	@Autowired
	public CustomerService (AccountService accountService, CustomerRepository repository) {
		this.accountService = accountService;
		this.repository = repository;
	}
	
	public Customer create (Customer customer) {
		return repository.save(customer);
	}
	/**
	 * Get All customers
	 * @return customers List<Customer>
	 */ 
	public List<Customer> findCustomers() {
		return repository.findAll();
	}
    /**
     * Get a {@link Customer} entity by the given id
     *
     * @param id is the unique identifier of the {@link Customer} entity
     * @return an {@link Customer} entity
     */  
    public Customer getCustomer(long id) {
        // Get the customer from the repository
        Customer customer = repository.findOne(id);
        Assert.notNull(customer, "A Customer with the supplied id doesn't exist"); 
        return customer;
    }
	 
	/**
	 * Update a {@link Customer} entity with the supplied identifier
	 *
	 * @param customer is the {@link Customer} containing the updated fields
	 * @return the updated {@link Customer} entity
	 */ 
	public Customer update(Customer customer) {
		Assert.notNull(customer.getId(), "Customer id must be present in the resource URL");
		Assert.notNull(customer, "Customer request body cannot be null"); 
		Assert.state(repository.exists(customer.getId()), "The customer with the supplied id does not exist"); 
		Customer currentCustomer = getCustomer(customer.getId());
		currentCustomer.setEmail(customer.getEmail());
		currentCustomer.setName(customer.getName()); 
		currentCustomer.setAccount(customer.getAccount()); 
		return repository.save(currentCustomer);
	}
	/**
	 * Find a {@link Customer} by Account ID
	 * @param accountId long - the account id
	 * @return customer - {@link Customer} entity
	 */
	public Customer findCustomersByAccountId (long accountId) {
		Account acc = accountService.get (accountId);
		Customer customer = repository.findByAccount(acc); 
		Assert.notNull(customer, "A Customer with accountId doesn't exist"); 
	    return customer;
	}
	/**
	 * Payment operation 
	 * 
	 * @param accountId long
	 * @param amount double
	 */
	@HystrixCommand (fallbackMethod = "updateBalanceFallback")
	 public void updateBalance (long accountId, double amount ) { 
		log.info("Payment for Account - " + accountId + " - Amount - " + amount);
		Account acc = accountService.get (accountId);
		accountService.decreaseBalance(acc, amount); 
	 
	}
	/**
	 * Payment Hystrix fallback 
	 * @param accountId long 
	 * @param amount double
	 */
	public void updateBalanceFallback  (long accountId, double amount ) {
		log.info("Payment Fallback for Account - " + accountId + " - Amount - " + amount);
	}
	 
}
