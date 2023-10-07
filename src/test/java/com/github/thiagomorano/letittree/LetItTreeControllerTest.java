package com.github.thiagomorano.letittree;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
}
