package org.service3.name;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.service3.name.controller")
public class NameServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NameServiceApplication.class, args);
    }
}