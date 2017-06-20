package com.gft.jbcnconf.report.service;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.jbcnconf.report.domain.AccountReport;
import com.gft.jbcnconf.report.domain.Customer;
import com.gft.jbcnconf.report.domain.TypedSummary;
import com.gft.jbcnconf.report.remote.CustomerClient;
import com.gft.jbcnconf.report.repository.AccountReportRepository;
/**
 * The report service
 * @author MOCR
 *
 */
@Service
public class ReportService {
	/**
	 * Account repository
	 */
	private final AccountReportRepository repository;
	/**
	 * Customer Feign client
	 */
	@Autowired CustomerClient customerClient;
	/**
	 * Constructor
	 * @param repository
	 */
	@Autowired
	public ReportService (AccountReportRepository repository) {
		this.repository = repository;
	}
	/**
	 * Get {@link AccontReport} by id
	 * @param id String
	 * @return {@link AccontReport}
	 */
	public AccountReport getReport (String id) {
		return repository.findOne(id);
	}
    /**
     * Report movement 
     ** Get the Account information from the customer id
     ** Create or update report
     ** Build the summary by type
     ** Count movements by type 
     ** 
     * @param accountId
     * @param amount
     * @param type
     */
	public void reportMovement (long accountId, double amount, String type) {
		Customer customer = customerClient.getCustomerByAccountId(accountId);
		AccountReport report = repository.findOne(String.valueOf(customer.getId()));
		if (report==null) {
			report = buildAccountReport(customer);
		}
		TypedSummary tSummary = report.getTypeGroup().get(type);
		if (tSummary != null) {
			tSummary.setAmount(tSummary.getAmount() + amount);
			tSummary.setMovements(tSummary.getMovements() + 1);
		} else {
			tSummary = new TypedSummary();
			tSummary.setAmount(amount);
			tSummary.setMovements(1); 	 
		}
		tSummary.setLastUpdated(new Date());
		report.getTypeGroup().put(type, tSummary); 
		report.setLastUpdate(new Date());
		repository.save(report);
	}
	/**
	 * Build {@link AccountReport} from Customer 
	 * @param customer
	 * @return AccountReport {@link AccountReport} 
	 */
	private AccountReport buildAccountReport (Customer customer) {
		AccountReport report = new AccountReport ();
		report.setAccount(customer.getAccount());
		report.setId(String.valueOf(customer.getId()));
		report.setTypeGroup(new HashMap<String,TypedSummary>());
		return report;
	}
}
