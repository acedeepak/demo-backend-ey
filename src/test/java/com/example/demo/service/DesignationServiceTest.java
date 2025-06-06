package com.example.demo.service;

import com.example.demo.entity.Designation;
import com.example.demo.repository.DesignationRepository;
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

class DesignationServiceTest {

    @Mock
    private DesignationRepository designationRepository;

    @InjectMocks
    private DesignationService designationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks
    }

    @Test
    void testGetAllDesignations_returnsList() {
        Designation d1 = new Designation();
        Designation d2 = new Designation();
        List<Designation> expected = Arrays.asList(d1, d2);

        when(designationRepository.findAll()).thenReturn(expected);

        List<Designation> actual = designationService.getAllDesignations();

        assertEquals(expected, actual);
        verify(designationRepository, times(1)).findAll();
    }

    @Test
    void testGetAllDesignations_returnsEmptyList() {
        when(designationRepository.findAll()).thenReturn(Collections.emptyList());

        List<Designation> actual = designationService.getAllDesignations();

        assertNotNull(actual);
        assertTrue(actual.isEmpty());
        verify(designationRepository, times(1)).findAll();
    }

    @Test
    void testGetAllDesignations_throwsException() {
        when(designationRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            designationService.getAllDesignations();
        });

        assertEquals("DB error", ex.getMessage());
        verify(designationRepository, times(1)).findAll();
    }
}
