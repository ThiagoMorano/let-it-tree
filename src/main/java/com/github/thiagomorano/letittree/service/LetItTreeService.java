package com.github.thiagomorano.letittree.service;

import java.util.List;

import com.github.thiagomorano.letittree.model.Plant;
import com.github.thiagomorano.letittree.repository.PlantRepository;

public class LetItTreeService {

	private final PlantRepository plantRepository;

	public LetItTreeService(PlantRepository plantRepository) {
		this.plantRepository = plantRepository;
	}

	public List<Plant> getAllPlants() {
		return plantRepository.findAllPlants();
	}
}
