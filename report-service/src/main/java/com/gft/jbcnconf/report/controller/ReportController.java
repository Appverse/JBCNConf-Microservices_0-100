package com.gft.jbcnconf.report.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.jbcnconf.report.resource.AccountReportAssembler;
import com.gft.jbcnconf.report.service.ReportService;
/**
 *  Report controller
 *  
 *  A Customer subresource 
 * @author MOCR
 *
 */
@RestController
@RequestMapping("/{id}/report")
public class ReportController {
	
	@Autowired ReportService service;
	@Autowired AccountReportAssembler assembler;
	
	/**
	 * Get {@link AccountReport} - C[R]UD
	 * 
	 * @param id String
	 * @return response ResponseEntity 
	 */
	@GetMapping()
	public ResponseEntity<AccountReportAssembler.AccountReportResource> getReport(@PathVariable String id) {
		return Optional.ofNullable(service.getReport(id))
				.map(c -> new ResponseEntity<>(assembler.toResource(c), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
