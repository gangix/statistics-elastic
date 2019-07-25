package com.atfarm.statisticsjava.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "vegetation")
public class Vegetation {

	@Id
	private String id;
	private String date;
	private double vegetation;

	public Vegetation() {
	}

	public Vegetation(String id, String date, double vegetation) {
		super();
		this.id = id;
		this.date = date;
		this.vegetation = vegetation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getVegetation() {
		return vegetation;
	}

	public void setVegetation(double vegetation) {
		this.vegetation = vegetation;
	}

}
