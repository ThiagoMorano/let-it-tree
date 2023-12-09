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
	public List<Plant> findAll() {
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
	public Plant save(UUID id, Plant plant) {
		Plant newPlant = new Plant(id, plant.getName(), plant.getDaysBetweenWatering(), plant.getLastWateringDate());
		plantDatabase.add(newPlant);
		return newPlant;
	}

	@Override
	public void deleteById(UUID id) {
		Optional<Plant> plant = findById(id);
		if (plant.isPresent()) {
			plantDatabase.remove(plant.get());
		}
	}

	@Override
	public boolean existsById(UUID id) {
		Optional<Plant> plant = findById(id);
		if (plant.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void updateById(UUID id, Plant updatedPlant) {
		for (int i = 0; i < plantDatabase.size(); i++) {
			Plant plant = plantDatabase.get(i);
			if (plant.getId().equals(id)) {
				plantDatabase.set(i, updatedPlant);
				return;
			}
		}
	}
}
