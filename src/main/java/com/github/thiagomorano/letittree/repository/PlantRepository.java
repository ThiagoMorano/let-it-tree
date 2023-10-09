package com.github.thiagomorano.letittree.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.thiagomorano.letittree.model.Plant;

public interface PlantRepository {
	List<Plant> findAllPlants();

	Optional<Plant> findById(UUID id);

	default Plant addPlant(Plant plant) {
		UUID id = UUID.randomUUID();
		return addPlant(id, plant);
	}

	Plant addPlant(UUID id, Plant plant);

	void deleteById(UUID id);

	boolean existById(UUID id);
}
