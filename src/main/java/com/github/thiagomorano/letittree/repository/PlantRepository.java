package com.github.thiagomorano.letittree.repository;

import java.util.List;
import java.util.Optional;

import com.github.thiagomorano.letittree.model.Plant;

public interface PlantRepository {
	List<Plant> findAllPlants();

	Optional<Plant> findById(Long id);
}
