package com.gft.jbcnconf.movements.test.config;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
 

@Configuration
@EnableMongoRepositories(basePackages = "com.gft.jbcnconf.movements.repository")
public class MongoConfiguration extends AbstractMongoConfiguration { 

    @Override
    protected String getDatabaseName() {
        return "test";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new Fongo(getDatabaseName()).getMongo();
    }
    
    @Override
    protected String getMappingBasePackage() {
        return "com.gft.jbcnconf.movements.domain";
    } 
    
}