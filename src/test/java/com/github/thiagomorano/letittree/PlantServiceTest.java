package com.github.thiagomorano.letittree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.thiagomorano.letittree.model.Plant;
import com.github.thiagomorano.letittree.repository.PlantRepository;
import com.github.thiagomorano.letittree.service.PlantService;

@ExtendWith(MockitoExtension.class)
public class PlantServiceTest {

	@Mock
	private PlantRepository mockPlantRepository;

	@InjectMocks
	private PlantService plantSertvice;

	@Test
	void givenEmptyRepository_whenGetAllPlants_thenReturnsEmptyArray() {
		when(mockPlantRepository.findAll()).thenReturn(new ArrayList<>());

		List<Plant> result = plantSertvice.getAllPlants();
		assertEquals(0, result.size());
	}

	@Test
	void givenRepository_whenGetAllPlants_thenReturnsExistingPlants() {
		Plant plant1 = new Plant(UUID.randomUUID());
		Plant plant2 = new Plant(UUID.randomUUID());
		List<Plant> plants = List.of(plant1, plant2);

		when(mockPlantRepository.findAll()).thenReturn(plants);

		List<Plant> result = plantSertvice.getAllPlants();
		assertEquals(2, result.size());
		Set<Plant> expectedSet = new HashSet<>(plants);
		Set<Plant> resultSet = new HashSet<>(result);
		assertEquals(expectedSet, resultSet);
	}

	@Test
	void givenFilledRepository_whenGetPlantsToWater_thenReturnsOnlyPlantsPastWaterDate() {
		// @TODO: revisit this once custom filter is extracted
		LocalDate today = LocalDate.now();
		Plant plant1 = new Plant(UUID.randomUUID(), 1, LocalDate.MIN);
		Plant plant2 = new Plant(UUID.randomUUID(), 1, today);
		List<Plant> plants = List.of(plant1, plant2);

		when(mockPlantRepository.findAll()).thenReturn(plants);

		List<Plant> result = plantSertvice.getPlantsToWater();
		assertEquals(1, result.size());
		assertTrue(plant1.getId().equals(result.get(0).getId()));
	}

	@Test
	void givenPlantInRepository_whenGetPlantById_thenReturnsCorrectPlant() {
		UUID id = UUID.randomUUID();
		Plant plant = new Plant(id);

		when(mockPlantRepository.findById(id)).thenReturn(Optional.of(plant));

		Optional<Plant> result = plantSertvice.getPlantById(id);
		assertTrue(result.isPresent());
		assertTrue(id.equals(result.get().getId()));
	}

	@Test
	void givenPlantNotInRepository_whenGetPlantById_thenReturnsNull() {
		UUID id = UUID.randomUUID();

		when(mockPlantRepository.findById(id)).thenReturn(Optional.empty());

		Optional<Plant> result = plantSertvice.getPlantById(id);
		assertTrue(result.isEmpty());
	}

	@Test
	void givenExistingPlant_whenExistsById_thenReturnsTrue() {
		UUID id = UUID.randomUUID();

		when(mockPlantRepository.existsById(id)).thenReturn(true);

		assertTrue(plantSertvice.exists(id));
		verify(mockPlantRepository).existsById(id);
	}

	@Test
	void givenMissingPlant_whenExistsById_thenReturnsFalse() {
		UUID id = UUID.randomUUID();

		when(mockPlantRepository.existsById(id)).thenReturn(false);

		assertFalse(plantSertvice.exists(id));
		verify(mockPlantRepository).existsById(id);
	}

	@Test
	void givenService_whenAddPlant_thenReturnsAddedPlant() {
		UUID id = UUID.randomUUID();
		Plant newPlant = new Plant(id);

		when(mockPlantRepository.save(newPlant)).thenReturn(newPlant);

		Plant response = plantSertvice.addPlant(newPlant);
		assertEquals(id, response.getId());
		verify(mockPlantRepository).save(newPlant);
	}

	@Test
	void givenService_whenUpdateExistingPlant_thenReturnsUpdatedPlant() {
		UUID id = UUID.randomUUID();
		Plant updatedPlant = new Plant(id, 1);

		plantSertvice.updatePlant(id, updatedPlant);
		verify(mockPlantRepository).updateById(id, updatedPlant);
	}

	@Test
	void givenService_whenDeletePlant_thenRepositoryDeleteByIdIsCalled() {
		UUID id = UUID.randomUUID();

		plantSertvice.deletePlant(id);
		verify(mockPlantRepository).deleteById(id);
	}
}
