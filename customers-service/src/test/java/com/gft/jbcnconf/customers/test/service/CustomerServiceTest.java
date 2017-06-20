package com.gft.jbcnconf.customers.test.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.gft.jbcnconf.customers.domain.Account;
import com.gft.jbcnconf.customers.domain.Customer;
import com.gft.jbcnconf.customers.repository.CustomerRepository;
import com.gft.jbcnconf.customers.service.AccountService;
import com.gft.jbcnconf.customers.service.CustomerService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@DirtiesContext
public class CustomerServiceTest {

	@TestConfiguration
	static class CustomerServiceImplTestContextConfiguration {
		
		@MockBean
		private static AccountService accountService;	 

		@MockBean
		private static CustomerRepository repository;

		@Bean
		public CustomerService customerService() {
			return new CustomerService(accountService, repository);
		}
	}

	@Autowired
	CustomerService customerService;  

	@Test
	public void paymentTest() { 
		double amount = 100;
		Customer cu = getCustomer();
		given(CustomerServiceImplTestContextConfiguration.repository.findByAccount(cu.getAccount())).willReturn(cu);
		given(CustomerServiceImplTestContextConfiguration.accountService.get(1L)).willReturn(cu.getAccount());   
		customerService.updateBalance(cu.getAccount().getId(), amount);  
		verify(CustomerServiceImplTestContextConfiguration.accountService, times(1)).decreaseBalance(cu.getAccount(), amount);
	}

	@Test
	public void testCreateCustomer() {
		Customer newC = getNewCustomer();
		customerService.create(newC);
		verify(CustomerServiceImplTestContextConfiguration.repository, times(1)).save(newC);
	}

	@Test
	public void testUpdateCustomer() {
		given(CustomerServiceImplTestContextConfiguration.repository.findOne(1L)).willReturn(getCustomer());
		given(CustomerServiceImplTestContextConfiguration.repository.exists(1L)).willReturn(true);
		Customer cust = CustomerServiceImplTestContextConfiguration.repository.findOne(1L);
		cust.setName("Updated");
		customerService.update(cust);
		verify(CustomerServiceImplTestContextConfiguration.repository, times(1)).save(cust);
	}

	@Test
	public void testGetCustomer() {
		given(CustomerServiceImplTestContextConfiguration.repository.findOne(1L)).willReturn(getCustomer());
		Customer cust = customerService.getCustomer(1L);
		assertThat(cust.getEmail()).isEqualTo(getCustomer().getEmail());
		assertThat(cust.getName()).isEqualTo(getCustomer().getName());
	}

	@Test
	public void testGetCustomers() {
		List<Customer> getList = new ArrayList<Customer>();
		getList.add(getCustomer());
		given(CustomerServiceImplTestContextConfiguration.repository.findAll()).willReturn(getList);
		List<Customer> getCustomerList = customerService.findCustomers();
		assertThat(getCustomerList.size()).isEqualTo(1);
		assertThat(getCustomerList.get(0).getName()).isEqualTo(getCustomer().getName());
	}

	@Test
	public void testFindCustomerByAccountId() {
		Customer cu = getCustomer();
		given(CustomerServiceImplTestContextConfiguration.repository.findByAccount(cu.getAccount())).willReturn(cu);
		given(CustomerServiceImplTestContextConfiguration.accountService.get(1L)).willReturn(cu.getAccount());
		Customer cuFound = customerService.findCustomersByAccountId(1L);
		verify(CustomerServiceImplTestContextConfiguration.repository, times(1)).findByAccount(cu.getAccount());
		assertThat(cuFound.getEmail()).isEqualTo(cu.getEmail());
	}

	private Customer getCustomer() {
		Customer customer = new Customer();
		String name = "Customer 1";
		String email = "Customer1@gft.com";
		customer.setName(name);
		customer.setEmail(email);
		customer.setId(1L);
		Account ac = new Account("Account 1", (double) 1000);
		customer.setAccount(ac);
		ac.setId(1L);
		return customer;
	}

	private Customer getNewCustomer() {
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
