package com.github.thiagomorano.letittree.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.thiagomorano.letittree.model.Plant;
import com.github.thiagomorano.letittree.service.LetItTreeService;

@RequestMapping("api/v1/plant")
@RestController
public class LetItTreeController {
	private LetItTreeService letItTreeService;

	@Autowired
	public LetItTreeController(LetItTreeService service) {
		this.letItTreeService = service;
	}

	@GetMapping(path = "/", produces = "application/json")
	public List<Plant> getAllPlants() {
		return this.letItTreeService.getAllPlants();
	}
}
