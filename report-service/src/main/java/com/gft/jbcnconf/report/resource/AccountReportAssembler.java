package com.gft.jbcnconf.report.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.gft.jbcnconf.report.controller.ReportController;
import com.gft.jbcnconf.report.domain.AccountReport;
/**
 * HATEOAS Resource builder
 * @author MOCR
 *
 */
@Component
public class AccountReportAssembler
		extends ResourceAssemblerSupport<AccountReport, AccountReportAssembler.AccountReportResource> {

	public AccountReportAssembler() {
		super(ReportController.class, AccountReportResource.class);
	}

	/**
	 * Create the aggregate resource and add the self reference link
	 *
	 * @param customer
	 * @return
	 */
	@Override
	public AccountReportResource toResource(AccountReport report) {
		final AccountReportResource resource = new AccountReportResource(report);
		// Add self reference link
		resource.add(linkTo(methodOn(ReportController.class, report.getId()).getReport(report.getId())).withSelfRel());
		return resource;
	}

	public static class AccountReportResource extends Resource<AccountReport> {
		public AccountReportResource(AccountReport content) {
			super(content);
		}
	}

}
