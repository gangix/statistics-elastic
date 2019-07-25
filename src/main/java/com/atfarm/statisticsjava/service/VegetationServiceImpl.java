package com.atfarm.statisticsjava.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atfarm.statisticsjava.dto.VegetationStatisticsDTO;
import com.atfarm.statisticsjava.model.Vegetation;
import com.atfarm.statisticsjava.repository.ElasticQueryBuilderRepository;
import com.atfarm.statisticsjava.repository.VegetationFarmRepository;

@Service
public class VegetationServiceImpl implements VegetationService {

	@Autowired
	VegetationFarmRepository vegetationFarmRepository;
	@Autowired
	ElasticQueryBuilderRepository builderRepository;

	@Override
	public VegetationStatisticsDTO getVegetationStatistics() {
		VegetationStatisticsDTO vegetationStatisticsDTO = builderRepository.getVegetationStatistics();
		return vegetationStatisticsDTO;
	}

	public Vegetation save(Vegetation v) {
		return vegetationFarmRepository.save(v);
	}

	@Override
	public Vegetation deleteById(String id) {
		Optional<Vegetation> findById = vegetationFarmRepository.findById(id);
		if(findById.isPresent()) {
			vegetationFarmRepository.delete(findById.get());
		}
		return null;
	}

}
