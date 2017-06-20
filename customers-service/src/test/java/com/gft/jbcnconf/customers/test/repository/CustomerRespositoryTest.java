package com.gft.jbcnconf.customers.test.repository;

 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.gft.jbcnconf.customers.domain.Account;
import com.gft.jbcnconf.customers.domain.Customer;
import com.gft.jbcnconf.customers.repository.CustomerRepository;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRespositoryTest {
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private CustomerRepository customerRepository;
    
    
    @Test
    public void getCustomerTest() {
        // given
       Customer customer = getCustomer ();
        entityManager.persist(customer);
        entityManager.flush();
     
        // when
        Customer found = customerRepository.findOne(1L);
     
        // then
        assertThat(found.getName())
          .isEqualTo(customer.getName());
    }
    
    private Customer getCustomer () {
		Customer customer = new Customer(); 
        String name = "Customer 1";
		String email = "Customer1@gft.com";
		customer.setName(name);
        customer.setEmail(email);
        
        Account ac = new Account("Account 1", (double) 1000); 
		customer.setAccount(ac);
		return customer;
	}
}
