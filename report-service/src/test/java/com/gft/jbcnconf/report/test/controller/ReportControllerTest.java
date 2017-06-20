package com.gft.jbcnconf.report.test.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.gft.jbcnconf.report.controller.ReportController;
import com.gft.jbcnconf.report.domain.Account;
import com.gft.jbcnconf.report.domain.AccountReport;
import com.gft.jbcnconf.report.domain.TypedSummary;
import com.gft.jbcnconf.report.resource.AccountReportAssembler;
import com.gft.jbcnconf.report.resource.AccountReportAssembler.AccountReportResource;
import com.gft.jbcnconf.report.service.ReportService;
 
@RunWith(SpringRunner.class)
@WebMvcTest(ReportController.class)
@ActiveProfiles("test")
public class ReportControllerTest {
	
	@Autowired MockMvc mvc;

	@MockBean private ReportService service;
	@MockBean private AccountReportAssembler assembler;

	@Test
	public void getMovementTest() throws Exception {
		Date when = new Date(); 
		
		String accrJson = "{ \"id\": \"1\",\"account\": {\"id\": 1, \"description\": \"description\"}, \"lastUpdate\": " + when.getTime() + 
				",\"typeGroup\": {\"TYPE\": {\"amount\": 100, \"movements\": 1, \"lastUpdated\": " + when.getTime() + "}}}";
		 
		AccountReport accr  = getAccountReport (when);
		given(this.service.getReport("1")).willReturn(accr);
		AccountReportResource mResource = new AccountReportResource(accr);
		given(this.assembler.toResource(accr)).willReturn(mResource); 
		this.mvc.perform(get("/1/report").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(accrJson));
	}
	
	private AccountReport getAccountReport(Date when) {
		AccountReport accr = new AccountReport ();
		Account ac = new Account ();
		ac.setDescription("description");
		ac.setId(1);
		accr.setAccount(ac);
		accr.setId("1");
		accr.setLastUpdate(when);
		Map<String, TypedSummary> tGroup = new HashMap<String, TypedSummary> ();
		TypedSummary ts = new TypedSummary ();
		ts.setAmount(100);
		ts.setLastUpdated(when);
		ts.setMovements(1);
		tGroup.put("TYPE", ts);
		accr.setTypeGroup(tGroup); 
		return accr;
	}
}