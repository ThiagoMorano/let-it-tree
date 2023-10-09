package com.github.thiagomorano.letittree.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.github.thiagomorano.letittree.model.Plant;

@Repository
public class FakePlantRepository implements PlantRepository {
	private List<Plant> plantDatabase = new ArrayList<>();

	@Override
	public List<Plant> findAllPlants() {
		return plantDatabase;
	}

	@Override
	public Optional<Plant> findById(UUID id) {
		Optional<Plant> result = plantDatabase.stream()
				.filter(plant -> plant.getId().equals(id))
				.findFirst();
		return result;
	}

	@Override
	public Plant addPlant(UUID id, Plant plant) {
		Plant newPlant = new Plant(id, plant.getName(), plant.getDaysBetweenWatering(), plant.getLastWateringDate());
		plantDatabase.add(newPlant);
		return newPlant;
	}
}
