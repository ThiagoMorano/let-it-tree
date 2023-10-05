package com.github.thiagomorano.letittree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

		List<Plant> response = letItTreeService.getAllPlants();
		assertEquals(0, response.size());
	}
}
