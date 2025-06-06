package com.example.demo.controller;

import com.example.demo.entity.Band;
import com.example.demo.service.BandService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BandController.class)
class BandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BandService bandService;

    @Test
    void testGetAllBands_returnsList() throws Exception {
        Band band1 = Band.builder().id(1L).name("Band A").build();
        Band band2 = Band.builder().id(2L).name("Band B").build();

        when(bandService.getAllBands()).thenReturn(Arrays.asList(band1, band2));

        mockMvc.perform(get("/v1/band"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("Band A"));
    }

    @Test
    void testGetAllBands_returnsEmptyList() throws Exception {
        when(bandService.getAllBands()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/v1/band"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(0));
    }


    @Test
    void testGetAllBands_invalidPath_returns404() throws Exception {
        mockMvc.perform(get("/v1/bands"))
            .andExpect(status().isNotFound());
    }
}
