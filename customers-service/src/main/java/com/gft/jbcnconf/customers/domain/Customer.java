package com.gft.jbcnconf.customers.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Customer Entity
 * @author MOCR
 *
 */
@Entity
public class Customer {
	/**
	 * Customer ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	/**
	 * Customer Name
	 */
	private String name;
	/**
	 * Customer E-mail
	 */
	private String email;
	/**
	 * Customer Account
	 */
	@OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
	private Account account;

	public Customer() {
	}

	public Customer(String name, String email, Account account) {
		super(); 
		this.name = name;
		this.email = email;
		this.account = account;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
