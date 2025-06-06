package com.example.demo.service;

import com.example.demo.entity.Band;
import com.example.demo.repository.BandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BandServiceTest {

    @Mock
    private BandRepository bandRepository;

    @InjectMocks
    private BandService bandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // JUnit 5 style setup
    }

    @Test
    void testGetAllBands_returnsBandList() {
        Band band1 = new Band();
        Band band2 = new Band();
        List<Band> expectedBands = Arrays.asList(band1, band2);

        when(bandRepository.findAll()).thenReturn(expectedBands);

        List<Band> actualBands = bandService.getAllBands();

        assertEquals(expectedBands, actualBands);
        verify(bandRepository, times(1)).findAll();
    }

    @Test
    void testGetAllBands_returnsEmptyList() {
        when(bandRepository.findAll()).thenReturn(Collections.emptyList());

        List<Band> actualBands = bandService.getAllBands();

        assertNotNull(actualBands);
        assertTrue(actualBands.isEmpty());
        verify(bandRepository, times(1)).findAll();
    }

    @Test
    void testGetAllBands_throwsException() {
        when(bandRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bandService.getAllBands();
        });

        assertEquals("Database error", exception.getMessage());
        verify(bandRepository, times(1)).findAll();
    }
}
