package com.gft.jbcnconf.movements.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Movement Document for MongoDB
 * @author MOCR
 *
 */
@Document(collection = "movements")
public class Movement {
	/**
	 * Movement ID
	 */
	@Id 
	private String id;
	
	/**
	 * Account ID
	 */
	private long accountId;
	/**
	 * Movement Type
	 */
	private MovementType type; 
	
	/**
	 * Amount
	 */
	private double amount;
	
	/**
	 * When 
	 */
	private Date when;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public MovementType getType() {
		return type;
	}

	public void setType(MovementType type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getWhen() {
		return when;
	}

	public void setWhen(Date when) {
		this.when = when;
	} 
	
}
