package com.gft.jbcnconf.report.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.gft.jbcnconf.report.domain.Account;
import com.gft.jbcnconf.report.domain.AccountReport;
import com.gft.jbcnconf.report.domain.TypedSummary;
import com.gft.jbcnconf.report.remote.CustomerClient;
import com.gft.jbcnconf.report.repository.AccountReportRepository;
import com.gft.jbcnconf.report.service.ReportService;
 

@RunWith(SpringRunner.class)
@DirtiesContext
public class ReportServiceTest { 
		
		@Autowired ReportService service;
		
		@MockBean CustomerClient customerClient;
		
		@TestConfiguration
		static class ReportServiceImplTestContextConfiguration {
			
			@MockBean static AccountReportRepository repository; 
			
			@Bean
			public ReportService movementService() {
				return new ReportService(repository);
			}
		}
		
		@Test
		public void testGetAccountReport() { 
			AccountReport accr = getAccountReport(); 
			when(ReportServiceImplTestContextConfiguration.repository.findOne("id")).thenReturn(accr); 
			AccountReport found  = service.getReport("id");
			verify(ReportServiceImplTestContextConfiguration.repository, times(1)).findOne(Mockito.any(String.class));
			assertThat(found.getTypeGroup().get("TYPE")).isEqualTo(accr.getTypeGroup().get("TYPE"));
		}
		
		private AccountReport getAccountReport() {
			AccountReport accr = new AccountReport ();
			Account ac = new Account ();
			ac.setDescription("description");
			accr.setAccount(ac);
			accr.setId("id");
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