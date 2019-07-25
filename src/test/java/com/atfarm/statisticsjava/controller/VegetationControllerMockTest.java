/**
 * 
 */
package com.atfarm.statisticsjava.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.atfarm.statisticsjava.dto.VegetationStatisticsDTO;
import com.atfarm.statisticsjava.model.Vegetation;
import com.atfarm.statisticsjava.service.VegetationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(VegetationController.class)
public class VegetationControllerMockTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	VegetationService vegetationService;
	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void testSaveVegetationMock() throws Exception {
		Vegetation vegetation = new Vegetation();
		vegetation.setDate("2019-07-15T08:50Z");
		vegetation.setVegetation(9.0);

		Vegetation vegetationReturn = new Vegetation();
		vegetationReturn.setDate("2019-07-15T08:50Z");
		vegetationReturn.setVegetation(9.0);
		vegetationReturn.setId("1224545dsaf");
		when(vegetationService.save(vegetation)).thenReturn(vegetationReturn);

		String vegetationJson = objectMapper.writeValueAsString(vegetation);

		RequestBuilder request = MockMvcRequestBuilders.post("/api/vegetation").content(vegetationJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(status().isOk());
	}

	@Test
	public void testVegetationStatsSuccessfullMock() throws Exception {
		VegetationStatisticsDTO vegetationStatisticsDTO = new VegetationStatisticsDTO();
		vegetationStatisticsDTO.setAvg(4);
		vegetationStatisticsDTO.setMax(4);
		vegetationStatisticsDTO.setMin(4);
		when(vegetationService.getVegetationStatistics()).thenReturn(vegetationStatisticsDTO);

		RequestBuilder request = MockMvcRequestBuilders.get("/api/vegetation/statistics")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.min", Matchers.is(4.0)));
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.max", Matchers.is(4.0)));
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.avg", Matchers.is(4.0)));
	}
}
