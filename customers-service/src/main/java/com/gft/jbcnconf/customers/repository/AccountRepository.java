package com.gft.jbcnconf.customers.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.jbcnconf.customers.domain.Account;

/**
 * Spring Data JPA repository for {@link Account}
 * @author MOCR
 *
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
