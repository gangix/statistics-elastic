package com.atfarm.statisticsjava.service;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.atfarm.statisticsjava.dto.VegetationStatisticsDTO;
import com.atfarm.statisticsjava.model.Vegetation;
import com.atfarm.statisticsjava.repository.ElasticQueryBuilderRepository;
import com.atfarm.statisticsjava.repository.VegetationFarmRepository;

@RunWith(MockitoJUnitRunner.class)
public class VegetationServiceMockTest {
	@InjectMocks
	VegetationServiceImpl vegetationServiceImpl;
	@Mock
	VegetationFarmRepository vegetationFarmRepository;
	@Mock
	ElasticQueryBuilderRepository elasticQueryBuilderRepository;

	@Test
	public void testSaveVegetationSuccessfull() {
		Vegetation vegetation = new Vegetation();
		vegetation.setDate("2019-07-15T08:50Z");
		vegetation.setVegetation(9.0);
		Vegetation vegetationReturn = new Vegetation();
		vegetationReturn.setDate("2019-07-15T08:50Z");
		vegetationReturn.setVegetation(9.0);
		vegetationReturn.setId("12545");

		when(vegetationFarmRepository.save(vegetation)).thenReturn(vegetationReturn);

		Vegetation saved = vegetationServiceImpl.save(vegetation);
		Assert.assertNotNull(saved);
		Assert.assertEquals("12545", saved.getId());
	}

	@Test
	public void testGetVegetationStatisticsSuccessfull() {
		VegetationStatisticsDTO vegetationStatisticsDTO = new VegetationStatisticsDTO();
		vegetationStatisticsDTO.setAvg(4);
		vegetationStatisticsDTO.setMax(4);
		vegetationStatisticsDTO.setMin(4);

		when(elasticQueryBuilderRepository.getVegetationStatistics()).thenReturn(vegetationStatisticsDTO);

		VegetationStatisticsDTO statistics = vegetationServiceImpl.getVegetationStatistics();
		Assert.assertNotNull(statistics);
		Assert.assertEquals(BigDecimal.valueOf(4).setScale(2),
				new BigDecimal(String.valueOf(statistics.getMax())).setScale(2));
	}

}
