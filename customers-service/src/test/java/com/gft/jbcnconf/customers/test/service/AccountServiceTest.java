package com.gft.jbcnconf.customers.test.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.gft.jbcnconf.customers.domain.Account;
import com.gft.jbcnconf.customers.repository.AccountRepository;
import com.gft.jbcnconf.customers.service.AccountService;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DirtiesContext
public class AccountServiceTest {

	@Autowired
	AccountService service; 
	
	@TestConfiguration
	static class AccountServiceImplTestContextConfiguration {
		
		@MockBean
		private static AccountRepository accountRepository;

		@Bean
		public AccountService accountService() {
			return new AccountService(accountRepository);
		}
	}

	@Test
	public void testAccount() {
		given(AccountServiceImplTestContextConfiguration.accountRepository.findOne(1L)).willReturn(getAccount());
		Account acc = service.get(1L);
		assertThat(acc.getDescription()).isEqualTo(getAccount().getDescription());
		assertThat(acc.getBalance()).isEqualTo(getAccount().getBalance());
	} 

	@Test
	public void testBalanceNegativeUpdate() {
		Account acc = getAccount();
		given(AccountServiceImplTestContextConfiguration.accountRepository.findOne(1L)).willReturn(acc);
		service.decreaseBalance(acc, 100);
		Account acc2 = service.get(1L);
		verify(AccountServiceImplTestContextConfiguration.accountRepository, times(1)).save(acc2);
		assertThat(acc2.getBalance()).isEqualTo(900);
	} 
	 
	private Account getAccount() {
		Account ac = new Account("Account 1", (double) 1000);
		ac.setId(1L);
		return ac;
	}
}
