package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Band;
import com.example.demo.service.BandService;

@RestController
@RequestMapping("v1/")
public class BandController {

	@Autowired
	BandService bandService;
	
    private final String urlBase = "band";
    
	@GetMapping(urlBase)
	@CrossOrigin
	public List<Band> getAllBands()
	{
		return bandService.getAllBands();
	}
}
