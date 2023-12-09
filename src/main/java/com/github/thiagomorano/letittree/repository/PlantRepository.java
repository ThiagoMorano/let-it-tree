package com.github.thiagomorano.letittree.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thiagomorano.letittree.model.Plant;

public interface PlantRepository extends JpaRepository<Plant, UUID>{
	boolean existsById(UUID id);
}
