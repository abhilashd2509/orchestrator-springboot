package org.service1.orchestrator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service1.orchestrator.OrchestratorApplication;
import com.service1.orchestrator.config.FeignConfig;
import com.service1.orchestrator.controller.OrchestratorController;
import com.service1.orchestrator.dto.NameRequest;
import com.service1.orchestrator.dto.OrchestratorResponse;
import com.service1.orchestrator.exception.GlobalExceptionHandler;
import com.service1.orchestrator.service.OrchestrationService;


@WebMvcTest(OrchestratorController.class)
@Import({GlobalExceptionHandler.class, FeignConfig.class})
@ContextConfiguration(classes = com.service1.orchestrator.OrchestratorApplication.class)
class OrchestratorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrchestrationService orchestrationService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String TRACE_ID = "test-trace-id-123";



    @Test
    void getStatus_shouldReturnUp() throws Exception {
        mockMvc.perform(get("/api/orchestrator/status"))
               .andExpect(status().isOk())
               .andExpect(content().string("Up"));
    }

    @Test
    void processNames_shouldReturnConcatenatedResponse() throws Exception {
        NameRequest request = new NameRequest("John", "Doe");
        OrchestratorResponse mockResponse = new OrchestratorResponse("Hello John Doe");

        when(orchestrationService.orchestrate(any(NameRequest.class), eq(TRACE_ID)))
            .thenReturn(mockResponse);

        mockMvc.perform(post("/api/orchestrator/process")
               .header("X-Trace-Id", TRACE_ID)
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.response").value("Hello John Doe"));
    }
    
    @Test
    void processNames_shouldGenerateTraceIdWhenNotProvided() throws Exception {
        NameRequest request = new NameRequest("John", "Doe");
        OrchestratorResponse mockResponse = new OrchestratorResponse("Hello John Doe");

        when(orchestrationService.orchestrate(any(NameRequest.class), any(String.class)))
            .thenReturn(mockResponse);

        mockMvc.perform(post("/api/orchestrator/process")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.response").value("Hello John Doe"));
    }

    @Test
    void processNames_shouldValidateRequest() throws Exception {
        // Test empty name
        NameRequest invalidRequest = new NameRequest("", "Doe");

        mockMvc.perform(post("/api/orchestrator/process")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(invalidRequest)))
               .andExpect(status().isBadRequest());

        // Test null surname
        invalidRequest = new NameRequest("John", null);

        mockMvc.perform(post("/api/orchestrator/process")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(invalidRequest)))
               .andExpect(status().isBadRequest());
    }
    
    
    @Test
    void processNames_shouldHandleServiceException() throws Exception {
        NameRequest request = new NameRequest("John", "Doe");

        when(orchestrationService.orchestrate(any(NameRequest.class), any(String.class)))
            .thenThrow(new RuntimeException("Service error"));

        mockMvc.perform(post("/api/orchestrator/process")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isInternalServerError())
               .andExpect(jsonPath("$.message").value("Orchestration failed: Service error"));
    }
    
}
