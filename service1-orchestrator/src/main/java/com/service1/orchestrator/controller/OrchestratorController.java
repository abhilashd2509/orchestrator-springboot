package com.service1.orchestrator.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.service1.orchestrator.dto.NameRequest;
import com.service1.orchestrator.dto.OrchestratorResponse;
import com.service1.orchestrator.service.OrchestrationService;


@RestController
@RequestMapping("/api/orchestrator")
@Tag(name = "Orchestrator Controller", description = "Orchestrates calls to other services")
public class OrchestratorController {
	
	private final OrchestrationService orchestrationService;
	private static final Logger log = LogManager.getLogger(OrchestratorController.class);

	public OrchestratorController(OrchestrationService orchestrationService) {

		this.orchestrationService = orchestrationService;
	}

	
    
    @GetMapping("/status")
    @Operation(summary = "Check service status")
    public String getStatus() {
        return "Up";
    }

    @PostMapping("/process")
    @Operation(summary = "Process name data")
    public ResponseEntity<OrchestratorResponse> processNames(
            @Valid @RequestBody NameRequest request,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {
        
        traceId = traceId != null ? traceId : UUID.randomUUID().toString();
        MDC.put("traceId", traceId);
        
        log.info("Starting orchestration with traceId: {}", traceId);
        
        OrchestratorResponse response = orchestrationService.orchestrate(request, traceId);
        
        log.info("Orchestration completed successfully");
        MDC.remove("traceId");
        
        return ResponseEntity.ok(response);
    }
}
