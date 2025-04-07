package org.service3.name;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service3.name.controller.NameController;
import com.service3.name.dto.NameRequest;
import com.service3.name.exception.GlobalExceptionHandler;

@ExtendWith(MockitoExtension.class)
class NameControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private NameController nameController;

    private ObjectMapper objectMapper = new ObjectMapper();
    private final String TRACE_ID = "test-trace-123";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(nameController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void concatenateNames_shouldReturnConcatenatedString() throws Exception {
        NameRequest request = new NameRequest("John", "Doe");
        String expectedResponse = "John Doe";

        mockMvc.perform(post("/api/v1/concatenate")
                        .header("X-Trace-Id", TRACE_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void concatenateNames_shouldHandleNullName() throws Exception {
        NameRequest request = new NameRequest(null, "Doe");

        mockMvc.perform(post("/api/v1/concatenate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"name\":\"Name is required\"}"));
    }

    @Test
    void concatenateNames_shouldHandleNullSurname() throws Exception {
        NameRequest request = new NameRequest("John", null);

        mockMvc.perform(post("/api/v1/concatenate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"surname\":\"Surname is required\"}"));
    }

    @Test
    void concatenateNames_shouldHandleEmptyName() throws Exception {
        NameRequest request = new NameRequest("", "Doe");

        mockMvc.perform(post("/api/v1/concatenate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"name\":\"Name is required\"}"));
    }

    @Test
    void concatenateNames_shouldHandleEmptySurname() throws Exception {
        NameRequest request = new NameRequest("John", "");

        mockMvc.perform(post("/api/v1/concatenate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"surname\":\"Surname is required\"}"));
    }

    @Test
    void concatenateNames_shouldWorkWithoutTraceId() throws Exception {
        NameRequest request = new NameRequest("John", "Doe");
        String expectedResponse = "John Doe";

        mockMvc.perform(post("/api/v1/concatenate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
