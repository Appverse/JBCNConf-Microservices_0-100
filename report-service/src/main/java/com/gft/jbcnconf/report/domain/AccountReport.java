package com.gft.jbcnconf.report.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * AccountReport document
 * @author MOCR
 *
 */
@Document (collection="reports")
public class AccountReport {
	
	/**
	 * Match the customerID
	 */
	@Id
	private String id;
	
	/**
	 * Account details
	 */ 
	private Account account;
	
	/**
	 * Last update
	 */
	private Date lastUpdate;
	
	/**
	 * Type/Ammount
	 */
	private Map<String, TypedSummary> typeGroup = new HashMap<String, TypedSummary> ();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	} 

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Map<String, TypedSummary> getTypeGroup() {
		return typeGroup;
	}

	public void setTypeGroup(Map<String, TypedSummary> typeGroup) {
		this.typeGroup = typeGroup;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

 
	
	

}
