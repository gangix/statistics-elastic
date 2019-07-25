package com.atfarm.statisticsjava.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.atfarm.statisticsjava.model.Vegetation;

public interface VegetationFarmRepository extends ElasticsearchRepository<Vegetation, String> {
	
}