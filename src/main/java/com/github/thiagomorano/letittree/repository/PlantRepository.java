package com.github.thiagomorano.letittree.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.thiagomorano.letittree.model.Plant;

public interface PlantRepository {
	List<Plant> findAll();

	Optional<Plant> findById(UUID id);

	default Plant save(Plant plant) {
		UUID id = UUID.randomUUID();
		return save(id, plant);
	}

	Plant save(UUID id, Plant plant);

	void updateById(UUID id, Plant any);

	void deleteById(UUID id);

	boolean existsById(UUID id);
}
