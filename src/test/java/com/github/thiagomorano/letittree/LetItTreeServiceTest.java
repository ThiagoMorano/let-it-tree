package com.github.thiagomorano.letittree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.thiagomorano.letittree.model.Plant;
import com.github.thiagomorano.letittree.repository.PlantRepository;
import com.github.thiagomorano.letittree.service.LetItTreeService;

@ExtendWith(MockitoExtension.class)
public class LetItTreeServiceTest {

	@Mock
	private PlantRepository mockPlantRepository;

	@InjectMocks
	private LetItTreeService letItTreeService;

	@Test
	public void givenEmptyRepository_whenGetAllPlants_thenReturnsEmptyArray() {
		when(mockPlantRepository.findAllPlants()).thenReturn(new ArrayList<>());

		List<Plant> result = letItTreeService.getAllPlants();
		assertEquals(0, result.size());
	}

	@Test
	public void givenRepository_whenGetAllPlants_thenReturnsExistingPlants() {
		Plant plant1 = new Plant(0L);
		Plant plant2 = new Plant(1L);
		List<Plant> plants = List.of(plant1, plant2);

		when(mockPlantRepository.findAllPlants()).thenReturn(plants);

		List<Plant> result = letItTreeService.getAllPlants();
		assertEquals(2, result.size());
		Set<Plant> expectedSet = new HashSet<>(plants);
		Set<Plant> resultSet = new HashSet<>(result);
		assertEquals(expectedSet, resultSet);
	}
}
