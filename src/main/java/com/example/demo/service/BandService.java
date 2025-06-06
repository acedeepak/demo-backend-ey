package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Band;
import com.example.demo.repository.BandRepository;

@Service
public class BandService {

	@Autowired
	BandRepository bandRepository;
	
	public List<Band> getAllBands()
	{
		return bandRepository.findAll();
	}
}
