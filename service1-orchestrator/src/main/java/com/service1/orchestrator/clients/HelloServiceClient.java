package com.service1.orchestrator.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.service1.orchestrator.config.FeignConfig;

@FeignClient(name = "hello-service", url = "${service.hello.url}", configuration = FeignConfig.class)
//@FeignClient(name = "hello-service", url = "http://localhost:8081", configuration = FeignConfig.class)
public interface HelloServiceClient {
    @GetMapping("/api/hello")
    String getHello(@RequestHeader("X-Trace-Id") String traceId);
}
