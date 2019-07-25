/**
 * 
 */
package com.atfarm.statisticsjava.controller;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atfarm.statisticsjava.dto.VegetationStatisticsDTO;
import com.atfarm.statisticsjava.model.Vegetation;
import com.atfarm.statisticsjava.service.VegetationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VegetationControllerIntegrationTest {

	@Autowired
	private TestRestTemplate template;
	@Autowired
	private VegetationService vegetationService;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void testVegetationSaveSuccessfull() throws Exception {
		Vegetation vegetation = new Vegetation();
		vegetation.setDate("2019-07-15T08:50Z");
		vegetation.setVegetation(9.0);
		String jSon = objectMapper.writeValueAsString(vegetation);
		HttpEntity<Object> vegetationEntity = getHttpEntity(jSon);
		ResponseEntity<Vegetation> response = template.postForEntity("/api/vegetation", vegetationEntity,
				Vegetation.class);
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals(200, response.getStatusCode().value());
		Assert.assertNotNull(response.getBody().getId());

		vegetationService.deleteById(response.getBody().getId());
	}

	@Test
	public void testVegetationStatsSuccessfull() throws Exception {
		ResponseEntity<VegetationStatisticsDTO> response = template.getForEntity("/api/vegetation/statistics",
				VegetationStatisticsDTO.class);

		Assert.assertNotNull(response.getBody().getMax());
		Assert.assertEquals(200, response.getStatusCode().value());
		Assert.assertEquals(BigDecimal.valueOf(5).setScale(2),
				new BigDecimal(String.valueOf(response.getBody().getMax())).setScale(2));
		Assert.assertEquals(BigDecimal.valueOf(2).setScale(2),
				new BigDecimal(String.valueOf(response.getBody().getMin())).setScale(2));
		Assert.assertEquals(BigDecimal.valueOf(3.4).setScale(2),
				new BigDecimal(String.valueOf(response.getBody().getAvg())).setScale(2));

	}

	private HttpEntity<Object> getHttpEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}

}
