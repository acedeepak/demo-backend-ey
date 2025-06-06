package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Band;
import com.example.demo.entity.Designation;
import com.example.demo.service.BandService;
import com.example.demo.service.DesignationService;

@RestController
@RequestMapping("v1/")
public class DesignationController {

	@Autowired
	DesignationService designationService;
	
    private final String urlBase = "designation";
    
	@GetMapping(urlBase)
	@CrossOrigin
	public List<Designation> getAllBands()
	{
		return designationService.getAllDesignations();
	}
}
