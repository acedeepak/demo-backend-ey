package com.example.demo.controller;

import com.example.demo.entity.Designation;
import com.example.demo.service.DesignationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DesignationController.class)
class DesignationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DesignationService designationService;

    @Test
    void testGetAllDesignations_returnsList() throws Exception {
        Designation d1 = Designation.builder().id(1L).name("Engineer").build();
        Designation d2 = Designation.builder().id(2L).name("Manager").build();

        when(designationService.getAllDesignations()).thenReturn(Arrays.asList(d1, d2));

        mockMvc.perform(get("/v1/designation"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("Engineer"));
    }

    @Test
    void testGetAllDesignations_returnsEmptyList() throws Exception {
        when(designationService.getAllDesignations()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/v1/designation"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void testGetAllDesignations_invalidPath_returns404() throws Exception {
        mockMvc.perform(get("/v1/designations"))
            .andExpect(status().isNotFound());
    }
}
