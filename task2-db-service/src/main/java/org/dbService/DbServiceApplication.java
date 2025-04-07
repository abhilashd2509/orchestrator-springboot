package org.dbService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {
	    "com.centime.parent",
	    "com.dbService.controller",  // Add this
	    "com.dbService.service" ,
	    "com.dbService.config"// And this
	})
@EnableJpaRepositories("com.dbService.repository")
@EntityScan("com.dbService.entity")
public class DbServiceApplication {
	
	 @Autowired
	    private Environment environment;
	 
    public static void main(String[] args) {
        SpringApplication.run(DbServiceApplication.class, args);
    }
    
    @PostConstruct
    public void setSystemProperty() {
        System.setProperty("service.name", environment.getProperty("service.name"));
    }
    
    
    
}