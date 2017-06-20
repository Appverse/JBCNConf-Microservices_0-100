package com.gft.jbcnconf.report.remote;
 
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gft.jbcnconf.report.domain.Customer;

@FeignClient (value="customers", configuration = ClientConfig.class)
public interface CustomerClient { 
	
	@RequestMapping(method = RequestMethod.GET, path = "/customers/search")
	public Customer getCustomerByAccountId(@RequestParam(value = "accountId") long accountId);
}
