package com.github.thiagomorano.letittree.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
		return plantRepository.findAll();
	}

	public List<Plant> getPlantsToWater() {
		// @TODO: evaluate whether filtering should be performed
		// in the repository layer at query level

		// @TODO: evaluate extracting a date filter that depends on
		// a custom date provider
		List<Plant> plantsList = plantRepository.findAll();
		LocalDate today = LocalDate.now();
		List<Plant> plantsToBeWatered = plantsList.stream()
				.filter(plant -> {
					long daysSinceLastWater = ChronoUnit.DAYS.between(plant.getLastWateringDate(), today);

					return daysSinceLastWater > plant.getDaysBetweenWatering();
				}).collect(Collectors.toList());
		return plantsToBeWatered;
	}

	public Optional<Plant> getPlantById(UUID id) {
		return plantRepository.findById(id);
	}

	public Plant addPlant(Plant plant) {
		return plantRepository.save(plant);
	}

	public void updatePlant(UUID id, Plant plant) {
		// @TODO: create custom exception
		Plant existingPlant = plantRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(String.format("Plant of %id not found", id)));
		existingPlant.setName(plant.getName());
		existingPlant.setDaysBetweenWatering(plant.getDaysBetweenWatering());
		existingPlant.setLastWateringDate(plant.getLastWateringDate());
		plantRepository.save(existingPlant);
	}

	public void deletePlant(UUID id) {
		plantRepository.deleteById(id);
	}

	public boolean exists(UUID id) {
		return plantRepository.existsById(id);
	}
}
