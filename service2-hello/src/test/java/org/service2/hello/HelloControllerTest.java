package org.service2.hello;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.service2.hello.controller.HelloController;

//HelloControllerTest.java
@WebMvcTest(HelloController.class)
class HelloControllerTest {

 @Autowired
 private MockMvc mockMvc;
 
 @Test
 void getHello_shouldReturnHello() throws Exception {
     mockMvc.perform(get("/api/hello"))
            .andExpect(status().isOk())
            .andExpect(content().string("Hello"));
 }
}