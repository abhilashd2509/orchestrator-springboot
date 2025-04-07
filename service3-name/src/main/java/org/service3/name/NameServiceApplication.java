package org.service3.name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = "com.service3.name.controller")
public class NameServiceApplication {
	
	 @Autowired
	    private Environment environment;
	
    public static void main(String[] args) {
        SpringApplication.run(NameServiceApplication.class, args);
    }
    
    
    @PostConstruct
    public void setSystemProperty() {
        System.setProperty("service.name", environment.getProperty("service.name"));
    }
}