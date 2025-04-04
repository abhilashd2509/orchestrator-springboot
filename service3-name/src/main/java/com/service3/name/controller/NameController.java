package com.service3.name.controller;

import jakarta.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service3.name.dto.NameRequest;
import com.service3.name.exception.InvalidNameException;




@RestController
@RequestMapping("/api/v1")
public class NameController {

	 private static final Logger logger = LogManager.getLogger(NameController.class);

    @PostMapping("/concatenate")
    public ResponseEntity<String> concatenateNames(
            @Valid @RequestBody NameRequest request,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {
        
    	logger.info("Received POST request with traceId: {}", traceId);
        
        if (request.getName() == null || request.getSurname() == null) {
            throw new InvalidNameException("Name and Surname must not be null");
        }
        
        String result = request.getName() + " " + request.getSurname();
        logger.info("Returning concatenated result: {}", result);
        
        return ResponseEntity.ok(result);
    }
}