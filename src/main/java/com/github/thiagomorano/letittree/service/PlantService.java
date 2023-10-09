package com.github.thiagomorano.letittree.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.thiagomorano.letittree.model.Plant;
import com.github.thiagomorano.letittree.repository.PlantRepository;

@Service
public class PlantService {
	private final PlantRepository plantRepository;

	@Autowired
	public PlantService(PlantRepository plantRepository) {
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

	public Optional<Plant> getPlantById(UUID id) {
		return plantRepository.findById(id);
	}

	public Plant addPlant(Plant plant) {
		return plantRepository.addPlant(plant);
	}

	public void deletePlant(UUID id) {
		plantRepository.deleteById(id);
	}

	public boolean exists(UUID id) {
		return plantRepository.existById(id);
	}
}
