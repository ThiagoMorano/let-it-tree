package com.github.thiagomorano.letittree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
	void givenEmptyRepository_whenGetAllPlants_thenReturnsEmptyArray() {
		when(mockPlantRepository.findAllPlants()).thenReturn(new ArrayList<>());

		List<Plant> result = letItTreeService.getAllPlants();
		assertEquals(0, result.size());
	}

	@Test
	void givenRepository_whenGetAllPlants_thenReturnsExistingPlants() {
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

	@Test
	void givenFilledRepository_whenGetPlantsToBeWatered_thenReturnsOnlyPlantsPastWaterDate() {
		// @TODO: revisit this once custom filter is extracted
		LocalDate today = LocalDate.now();
		Plant plant1 = new Plant(0L, 1, LocalDate.MIN);
		Plant plant2 = new Plant(1L, 1, today);
		List<Plant> plants = List.of(plant1, plant2);

		when(mockPlantRepository.findAllPlants()).thenReturn(plants);

		List<Plant> result = letItTreeService.getPlantsToBeWatered();
		assertEquals(1, result.size());
		assertEquals(plant1.getId(), result.get(0).getId());
	}

	@Test
	void givenPlantInRepository_whenGetPlantById_thenReturnsCorrectPlant() {
		Long id = 123L;
		Plant plant = new Plant(id);

		when(mockPlantRepository.findById(id)).thenReturn(Optional.of(plant));

		Optional<Plant> result = letItTreeService.getPlantById(id);
		assertTrue(result.isPresent());
		assertEquals(id, result.get().getId());
	}

	@Test
	void givenPlantNotInRepository_whenGetPlantById_thenReturnsNull() {
		Long id = 123L;

		when(mockPlantRepository.findById(id)).thenReturn(Optional.empty());

		Optional<Plant> result = letItTreeService.getPlantById(id);
		assertTrue(result.isEmpty());
	}
}
