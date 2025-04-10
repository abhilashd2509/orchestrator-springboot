package org.service2.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = "com.service2.hello.controller")
public class HelloServiceApplication {
	 @Autowired
	    private Environment environment;
	
    public static void main(String[] args) {
        SpringApplication.run(HelloServiceApplication.class, args);
    }
    
    @PostConstruct
    public void setSystemProperty() {
        System.setProperty("service.name", environment.getProperty("service.name"));
    }
}