package com.service1.orchestrator.service;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


import com.service1.orchestrator.clients.HelloServiceClient;
import com.service1.orchestrator.clients.NameConcatenatorServiceClient;
import com.service1.orchestrator.dto.NameRequest;
import com.service1.orchestrator.dto.OrchestratorResponse;

import com.service1.orchestrator.exception.InvalidRequestException;


@Service
public class OrchestrationService {
    private static final Logger log = LogManager.getLogger(OrchestrationService.class);
    
    private final HelloServiceClient helloServiceClient;
    private final NameConcatenatorServiceClient nameConcatenatorServiceClient;
    

    public OrchestrationService( HelloServiceClient helloServiceClient,
			NameConcatenatorServiceClient nameConcatenatorServiceClient) {
		this.helloServiceClient = helloServiceClient;
		this.nameConcatenatorServiceClient = nameConcatenatorServiceClient;
	}
    
    public OrchestratorResponse orchestrate(NameRequest request, String traceId) {
        try {
            log.info("Calling Hello Service...");
            String helloResponse = helloServiceClient.getHello(traceId);
            
            log.info("Calling Name Concatenator Service...");
            String concatenatedNames = nameConcatenatorServiceClient.concatenateNames(request, traceId);
            
            return new OrchestratorResponse(helloResponse + " " + concatenatedNames);
        } catch (Exception e) {
            log.error("Orchestration failed: {}", e.getMessage());
            throw new InvalidRequestException("Orchestration failed: " + e.getMessage());
        }
    }

}