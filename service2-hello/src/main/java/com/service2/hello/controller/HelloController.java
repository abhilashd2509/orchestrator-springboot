package com.service2.hello.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);

    @GetMapping("/hello")
    public ResponseEntity<String> getHello(@RequestHeader(value = "X-Trace-Id", required = false) String traceId) {
    	logger.info("Received GET requests with traceId: {}", traceId);
        return ResponseEntity.ok("Hello");
    }
}