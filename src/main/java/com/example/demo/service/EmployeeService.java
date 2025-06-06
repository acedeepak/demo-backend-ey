package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.entity.Band;
import com.example.demo.entity.Designation;
import com.example.demo.entity.Employee;
import com.example.demo.repository.BandRepository;
import com.example.demo.repository.DesignationRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	BandRepository bandRepository;
	
	@Autowired
	DesignationRepository designationRepository;
	
	public List<Employee> getAllEmployees()
	{
		return employeeRepository.findAll();
	}
	
	public Employee getEmployee(Long id)
	{
		Optional<Employee> optional = employeeRepository.findById(id);
		return optional.orElse(null);
	}
	
	public Employee createEmployee(EmployeeRequest request)
	{
		var band = bandRepository.findById(request.getBandId());
		var designation = designationRepository.findById(request.getDesignationId());
		
		Employee newEMployee = Employee.builder()
                .name(request.getName())
                .designation(designation.get())
                .band(band.get())
                .createdOn(request.getCreatedOn())
                .updatedOn(request.getUpdatedOn())
                .emailId(request.getEmailId())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .imageURL(request.getImageURL())
                .salary(request.getSalary())
                .build();
		
        return employeeRepository.saveAndFlush(newEMployee);
	}
	
	public void deleteEmployee(Long id)
	{
		employeeRepository.deleteById(id);
		employeeRepository.flush();
	}
}
