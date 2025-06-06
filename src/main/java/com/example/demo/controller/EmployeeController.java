package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.entity.Band;
import com.example.demo.entity.Designation;
import com.example.demo.entity.Employee;
import com.example.demo.service.BandService;
import com.example.demo.service.DesignationService;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("v1/")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
    private final String urlBase = "employee";
    
	@GetMapping(urlBase + "/getAll")
	@CrossOrigin
	public List<Employee> getAllEmployees()
	{
		return employeeService.getAllEmployees();
	}
	
	@GetMapping(urlBase + "/getById/{id}")
	@CrossOrigin
	public Employee getAllEmployees(@PathVariable Long id)
	{
		return employeeService.getEmployee(id);
	}
	
	@PostMapping(urlBase + "/create")
	@CrossOrigin
	public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest)
	{
		return employeeService.createEmployee(employeeRequest);
	}
	
	@DeleteMapping(urlBase + "/deleteEmployee/{id}")
	@CrossOrigin
	public void deleteEmployee(@PathVariable Long id)
	{
		employeeService.deleteEmployee(id);
	}
}
