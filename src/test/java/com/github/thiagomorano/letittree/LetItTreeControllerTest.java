package com.github.thiagomorano.letittree;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.github.thiagomorano.letittree.model.Plant;
import com.github.thiagomorano.letittree.service.LetItTreeService;

@SpringBootTest
@AutoConfigureMockMvc
public class LetItTreeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LetItTreeService letItTreeService;

	private String apiPath = "/api/v1/plant/";

	@Test
	void givenController_whenGetAllPlantsNotEmpty_thenResponseIsOk() throws Exception {
		Plant plant1 = new Plant(1L);
		Plant plant2 = new Plant(2L);
		List<Plant> plants = List.of(plant1, plant2);

		when(letItTreeService.getAllPlants()).thenReturn(plants);

		mockMvc.perform(MockMvcRequestBuilders
				.get(apiPath).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));
	}

	@Test
	void givenController_whenGetAllPlantsEmpty_thenResponseOkSize0() throws Exception {
		List<Plant> plants = List.of();

		when(letItTreeService.getAllPlants()).thenReturn(plants);

		mockMvc.perform(MockMvcRequestBuilders
				.get(apiPath).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0));
	}

	@Test
	void givenController_whenGetByIdAndServiceEmpty_thenRespondsNotFound() throws Exception {
		Long id = 1L;

		when(letItTreeService.getPlantById(id)).thenReturn(Optional.empty());

		mockMvc.perform(MockMvcRequestBuilders.get(apiPath + "/" + id.toString()))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void givenController_whenGetByIdAndServiceContains_thenRespondsOk() throws Exception {
		Long id = 1L;
		Plant plant = new Plant(id);

		when(letItTreeService.getPlantById(id)).thenReturn(Optional.of(plant));

		mockMvc.perform(MockMvcRequestBuilders.get(apiPath + "/" + id.toString()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
	}

	@Test
	void givenController_whenAddPlant_thenRespondsCreated() throws Exception {
		Plant savedPlant = new Plant(1L);

		when(letItTreeService.addPlant(any(Plant.class))).thenReturn(savedPlant);
		var requestBody = "{ \"id\": " + savedPlant.getId() + " }";

		mockMvc.perform(MockMvcRequestBuilders
				.post(apiPath + "/")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
}
