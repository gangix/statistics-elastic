/**
 * 
 */
package com.atfarm.statisticsjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atfarm.statisticsjava.dto.VegetationStatisticsDTO;
import com.atfarm.statisticsjava.model.Vegetation;
import com.atfarm.statisticsjava.service.VegetationService;

@RestController
public class VegetationController {

	@Autowired
	private VegetationService vegetationService;

	@GetMapping(path = "/api/vegetations/statistics")
	public ResponseEntity<VegetationStatisticsDTO> getVegetationStatistics() {
		return ResponseEntity.ok(vegetationService.getVegetationStatistics());
	}

	@PostMapping(path = "/api/vegetations")
	public ResponseEntity<Vegetation> saveVegetation(@RequestBody Vegetation vegetation) {
		return ResponseEntity.ok(vegetationService.save(vegetation));
	}
}
