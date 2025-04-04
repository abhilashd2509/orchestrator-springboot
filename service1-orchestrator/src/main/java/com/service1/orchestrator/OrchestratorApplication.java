package com.service1.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;




@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.service1.orchestrator",
"com.service1.orchestrator.controller","com.service1.orchestrator.service","com.service1.orchestrator.config"})
public class OrchestratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrchestratorApplication.class, args);
    }
}