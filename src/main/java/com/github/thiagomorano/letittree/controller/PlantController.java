package com.github.thiagomorano.letittree.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.thiagomorano.letittree.model.Plant;
import com.github.thiagomorano.letittree.service.PlantService;

@RequestMapping("api/v1/plant")
@RestController
public class PlantController {
	private PlantService plantService;

	@Autowired
	public PlantController(PlantService service) {
		this.plantService = service;
	}

	@GetMapping(path = "/", produces = "application/json")
	public List<Plant> getAllPlants() {
		return this.plantService.getAllPlants();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Plant> getById(@PathVariable Long id) {
		Optional<Plant> optionalPlant = this.plantService.getPlantById(id);
		if (optionalPlant.isPresent()) {
			return ResponseEntity.ok(optionalPlant.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/need-water", produces = "application/json")
	public List<Plant> getPlantsToWater() {
		return this.plantService.getPlantsToWater();
	}

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Plant> addPlant(@RequestBody Plant plant) {
		// @TODO: evaluate returning the newly added object
		Plant addedPlant = this.plantService.addPlant(plant);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedPlant);
	}

	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Void> updatePlant(@PathVariable Long id, @RequestBody Plant plant) {
		if (plantService.exists(id)) {
			this.plantService.updatePlant(id, plant);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
		if (this.plantService.exists(id)) {
			this.plantService.deletePlant(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
