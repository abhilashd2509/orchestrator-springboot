package com.service1.orchestrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import jakarta.annotation.PostConstruct;




@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.service1.orchestrator",
"com.service1.orchestrator.controller","com.service1.orchestrator.service","com.service1.orchestrator.config"})
public class OrchestratorApplication {
	
	 @Autowired
	    private Environment environment;
	 
	 
    public static void main(String[] args) {
        SpringApplication.run(OrchestratorApplication.class, args);
    }
    
    @PostConstruct
    public void setSystemProperty() {
        System.setProperty("service.name", environment.getProperty("service.name"));
    }
}