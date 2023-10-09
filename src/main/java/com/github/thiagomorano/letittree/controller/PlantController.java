package com.github.thiagomorano.letittree.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.thiagomorano.letittree.model.Plant;
import com.github.thiagomorano.letittree.service.PlantService;

@RequestMapping("api/v1/plant")
@RestController
public class PlantController {
	private PlantService letItTreeService;

	@Autowired
	public PlantController(PlantService service) {
		this.letItTreeService = service;
	}

	@GetMapping(path = "/", produces = "application/json")
	public List<Plant> getAllPlants() {
		return this.letItTreeService.getAllPlants();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Plant> getById(@PathVariable UUID id) {
		Optional<Plant> optionalPlant = this.letItTreeService.getPlantById(id);
		if (optionalPlant.isPresent()) {
			return ResponseEntity.ok(optionalPlant.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Plant> addPlant(@RequestBody Plant plant) {
		// @TODO: evaluate returning the newly added object
		Plant addedPlant = this.letItTreeService.addPlant(plant);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedPlant);
	}
}
