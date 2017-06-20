package com.gft.jbcnconf.report.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gft.jbcnconf.report.domain.AccountReport;
 
/**
 * Repository for {@link AccountReport}
 * @author MOCR
 *
 */
public interface AccountReportRepository extends MongoRepository<AccountReport, String> {  

}
