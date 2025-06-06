package com.example.demo.controller;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.entity.Band;
import com.example.demo.entity.Designation;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllEmployees_returnsList() throws Exception {
        Employee emp1 = Employee.builder().id(1L).name("John Doe").build();
        Employee emp2 = Employee.builder().id(2L).name("Jane Smith").build();

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(emp1, emp2));

        mockMvc.perform(get("/v1/employee/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void testGetEmployeeById_returnsEmployee() throws Exception {
        Employee emp = Employee.builder().id(1L).name("John Doe").build();

        when(employeeService.getEmployee(1L)).thenReturn(emp);

        mockMvc.perform(get("/v1/employee/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetEmployeeById_notFound_returnsNull() throws Exception {
        when(employeeService.getEmployee(99L)).thenReturn(null);

        mockMvc.perform(get("/v1/employee/getById/99"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void testCreateEmployee_returnsEmployee() throws Exception {
        EmployeeRequest request = EmployeeRequest.builder()
                .name("John Doe")
                .emailId("john@example.com")
                .phoneNumber("1234567890")
                .salary(60000.0)
                .address("Test Address")
                .imageURL("http://img.com/1.png")
                .bandId(1L)
                .designationId(1L)
                .build();

        Employee emp = Employee.builder().id(1L).name("John Doe").build();

        when(employeeService.createEmployee(any(EmployeeRequest.class))).thenReturn(emp);

        mockMvc.perform(post("/v1/employee/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }


    @Test
    void testDeleteEmployee_success() throws Exception {
        doNothing().when(employeeService).deleteEmployee(1L);

        mockMvc.perform(delete("/v1/employee/deleteEmployee/1"))
                .andExpect(status().isOk());
    }


}
