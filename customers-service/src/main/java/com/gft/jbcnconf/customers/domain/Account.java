package com.gft.jbcnconf.customers.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity Account 
 * @author MOCR
 *
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {
	/**
	 * Account ID
	 */
	@Id @GeneratedValue(strategy=GenerationType.AUTO) 
	private long id;
	/**
	 * Account Description
	 */
	private String description; 
	/**
	 * Account balance
	 */
	private Double balance = Double.valueOf(0L);
	
	public Account () {}
	
	public Account(String description, Double balance) {
		super(); 
		this.description = description;
		this.balance = balance;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
