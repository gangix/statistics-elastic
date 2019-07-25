package com.atfarm.statisticsjava.dto;

public class VegetationStatisticsDTO {
	private double min;
	private double max;
	private double avg;

	public VegetationStatisticsDTO() {
	}

	public VegetationStatisticsDTO(double min, double max, double avg) {
		super();
		this.min = min;
		this.max = max;
		this.avg = avg;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

}
