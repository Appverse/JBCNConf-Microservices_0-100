package com.gft.jbcnconf.report.domain;

import java.util.Date;
/**
 * Amount by movement type
 * 
 * @author MOCR
 *
 */
public class TypedSummary {  
    /**
     * Sum amount
     */
	private double amount;
	/**
	 * movements
	 */
	private int movements;
	/**
	 * last updated
	 */
	private Date lastUpdated;
	
	 
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getMovements() {
		return movements;
	}
	public void setMovements(int movements) {
		this.movements = movements;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	
	
}
