package com.service1.orchestrator.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.service1.orchestrator.config.FeignConfig;
import com.service1.orchestrator.dto.NameRequest;

@FeignClient(name = "name-service", url = "${service.name-concatenator.url}", configuration = FeignConfig.class)
//@FeignClient(name = "name-concatenator-service", url = "http://localhost:8082", configuration = FeignConfig.class)
public interface NameConcatenatorServiceClient {
    @PostMapping("/api/v1/concatenate")
    String concatenateNames(@RequestBody NameRequest request, @RequestHeader("X-Trace-Id") String traceId);
}