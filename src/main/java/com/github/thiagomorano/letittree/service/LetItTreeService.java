package com.github.thiagomorano.letittree.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<Plant> getPlantsToBeWatered() {
		// @TODO: evaluate whether filtering should be performed
		// in the repository layer in query level

		// @TODO: evaluate extracting a date filter that depends on
		// a custom date provider
		List<Plant> plantsList = plantRepository.findAllPlants();
		LocalDate today = LocalDate.now();
		List<Plant> plantsToBeWatered = plantsList.stream()
				.filter(plant -> {
					Period periodSinceLastWater = Period.between(plant.getLastWateringDate(), today);
					int daysSinceLastWater = periodSinceLastWater.getDays();

					return daysSinceLastWater > plant.getDaysBetweenWatering();
				}).collect(Collectors.toList());
		return plantsToBeWatered;
	}
}
