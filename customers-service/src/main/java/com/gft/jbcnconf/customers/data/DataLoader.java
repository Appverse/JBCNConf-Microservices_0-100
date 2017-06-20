package com.gft.jbcnconf.customers.data;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.gft.jbcnconf.customers.domain.Account;
import com.gft.jbcnconf.customers.domain.Customer;
import com.gft.jbcnconf.customers.repository.CustomerRepository;

/**
 * Add some data on start 
 * @author MOCR
 *
 */
@Component
public class DataLoader implements ApplicationRunner {

	private CustomerRepository customerRepository;
	 

	@Autowired
	public DataLoader(CustomerRepository customerRepository ) {
		this.customerRepository = customerRepository;
	 
	}

	public void run(ApplicationArguments args) {
		for (int i = 1; i < 4; i++) {
			Account ac = new Account("Account " + i, (double) 1000); 
			String name = "Customer " + i;
			String email = "Customer" + i + "@jbcnconf.com";
			customerRepository.save(new Customer(name, email, ac));
		}
	}
}