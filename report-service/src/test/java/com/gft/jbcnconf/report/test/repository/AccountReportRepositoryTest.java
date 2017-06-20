package com.gft.jbcnconf.report.test.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.gft.jbcnconf.report.domain.Account;
import com.gft.jbcnconf.report.domain.AccountReport;
import com.gft.jbcnconf.report.domain.TypedSummary;
import com.gft.jbcnconf.report.repository.AccountReportRepository;
import com.gft.jbcnconf.report.test.config.MongoConfiguration;

@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { MongoConfiguration.class })
public class AccountReportRepositoryTest {

	@Autowired
	private AccountReportRepository repository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	public void getAccountReportRepositoryTest() {
		// given
		AccountReport accountReport = getAccountReport();
		mongoTemplate.save(accountReport);
		// when
		AccountReport found = repository.findOne("1");
		// then
		assertThat(found.getLastUpdate()).isEqualTo(accountReport.getLastUpdate());
	}

	private AccountReport getAccountReport() {
		AccountReport accr = new AccountReport ();
		Account ac = new Account ();
		ac.setDescription("description");
		accr.setAccount(ac);
		accr.setId("1");
		accr.setLastUpdate(new Date());
		Map<String, TypedSummary> tGroup = new HashMap<String, TypedSummary> ();
		TypedSummary ts = new TypedSummary ();
		ts.setAmount(100);
		ts.setLastUpdated(new Date());
		ts.setMovements(1);
		tGroup.put("TYPE", ts);
		accr.setTypeGroup(tGroup); 
		return accr;
	}
}
