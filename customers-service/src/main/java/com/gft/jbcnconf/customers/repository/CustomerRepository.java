package com.gft.jbcnconf.customers.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.jbcnconf.customers.domain.Account;
import com.gft.jbcnconf.customers.domain.Customer;
/**
 * Spring Data JPA repository for {@link Customer}
 * @author MOCR
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> { 
	/**
	 * Find {@link Customer} by  {@link Account}
	 * @param acc
	 * @return
	 */
	Customer findByAccount(Account acc);
}
