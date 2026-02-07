package com.company.employeemanagement;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.company.employeemanagement.config.TestSecurityConfig;
import com.company.employeemanagement.dto.EmployeeRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    
    @Test
    void shouldCreateEmployee() throws Exception {

        EmployeeRequest request = new EmployeeRequest();
        request.setName("John");
        request.setEmail("john@test.com");
        request.setDepartment("IT");
        request.setRole("Dev");

        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.email").value("john@test.com"));
    }

    @Test
    void shouldFailValidation() throws Exception {

        EmployeeRequest request = new EmployeeRequest();
        request.setName("");
        request.setEmail("wrong");

        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.name").exists())
            .andExpect(jsonPath("$.email").exists());
    }

    @Test
    void shouldFailOnDuplicateEmail() throws Exception {

        EmployeeRequest request = new EmployeeRequest();
        request.setName("John");
        request.setEmail("dup@test.com");
        request.setDepartment("IT");
        request.setRole("Dev");

        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated());

        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isConflict());
    }

}

