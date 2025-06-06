package com.example.demo.service;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.entity.Band;
import com.example.demo.entity.Designation;
import com.example.demo.entity.Employee;
import com.example.demo.repository.BandRepository;
import com.example.demo.repository.DesignationRepository;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private BandRepository bandRepository;

    @Mock
    private DesignationRepository designationRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees_returnsList() {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetEmployee_found() {
        Employee mockEmployee = new Employee();
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(mockEmployee));

        Employee result = employeeService.getEmployee(1L);

        assertNotNull(result);
        assertEquals(mockEmployee, result);
        verify(employeeRepository).findById(1L);
    }

    @Test
    void testGetEmployee_notFound_returnsNull() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        Employee result = employeeService.getEmployee(99L);

        assertNull(result);
        verify(employeeRepository).findById(99L);
    }

    @Test
    void testCreateEmployee_successful() {
        Band band = Band.builder().id(1L).build();
        Designation designation = Designation.builder().id(2L).build();

        EmployeeRequest request = EmployeeRequest.builder()
                .name("John Doe")
                .emailId("john@example.com")
                .phoneNumber("1234567890")
                .address("123 Main St")
                .imageURL("http://example.com/image.png")
                .salary(100000.0)
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .bandId(1L)
                .designationId(2L)
                .build();

        when(bandRepository.findById(1L)).thenReturn(Optional.of(band));
        when(designationRepository.findById(2L)).thenReturn(Optional.of(designation));
        when(employeeRepository.saveAndFlush(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Employee result = employeeService.createEmployee(request);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(employeeRepository).saveAndFlush(any(Employee.class));
    }

    @Test
    void testDeleteEmployee_callsDeleteAndFlush() {
        doNothing().when(employeeRepository).deleteById(1L);
        doNothing().when(employeeRepository).flush();

        employeeService.deleteEmployee(1L);

        verify(employeeRepository).deleteById(1L);
        verify(employeeRepository).flush();
    }

    @Test
    void testCreateEmployee_bandOrDesignationNotFound_throwsException() {
        EmployeeRequest request = EmployeeRequest.builder()
                .bandId(99L)
                .designationId(100L)
                .build();

        when(bandRepository.findById(99L)).thenReturn(Optional.empty());
        when(designationRepository.findById(100L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> employeeService.createEmployee(request));

        verify(bandRepository).findById(99L);
        verify(designationRepository).findById(100L);
    }
}
