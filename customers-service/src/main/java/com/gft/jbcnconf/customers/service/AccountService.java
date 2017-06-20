package com.gft.jbcnconf.customers.service;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.gft.jbcnconf.customers.domain.Account;
import com.gft.jbcnconf.customers.repository.AccountRepository;

/**
 * Account service class
 * @author MOCR
 *
 */
@Service
public class AccountService {	
	
	private final AccountRepository accountRepository;
	
	/**
	 * Constructor 
	 * @param accountRepository {@link AccountRepository)
	 */
	@Autowired
	public AccountService (AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
    /**
     * 
     * @param id long - account id 
     * @return account {@link Account)
     */
	public Account get(long id) {
		// Get the customer from the repository
		Account account = accountRepository.findOne(id);
		Assert.notNull(account, "An Account with the supplied id doesn't exist");
		return account;
	}
    /**
     * Decrease Account Balance and update
     * 
     * @param account {@link Account}
     * @param ammount - double
     */
	public void decreaseBalance(Account account, double ammount) { 
		account.setBalance(account.getBalance() - ammount);
		accountRepository.save(account); 
	}
}
