package com.github.thiagomorano.letittree.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thiagomorano.letittree.model.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long>{
	boolean existsById(Long id);
}
