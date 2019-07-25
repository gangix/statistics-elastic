package com.atfarm.statisticsjava.service;

import com.atfarm.statisticsjava.dto.VegetationStatisticsDTO;
import com.atfarm.statisticsjava.model.Vegetation;

public interface VegetationService {
	VegetationStatisticsDTO getVegetationStatistics();

	Vegetation save(Vegetation p);

	Vegetation deleteById(String id);

}
