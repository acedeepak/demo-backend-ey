package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Band;
import com.example.demo.entity.Designation;
import com.example.demo.repository.BandRepository;
import com.example.demo.repository.DesignationRepository;

@Service
public class DesignationService {

	@Autowired
	DesignationRepository designationRepository;
	
	public List<Designation> getAllDesignations()
	{
		return designationRepository.findAll();
	}
}
