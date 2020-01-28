package com.bsu;

public class Range {
	private Float min;
	private Float max;

	public Range(Float min, Float max) {
		super();
		this.min = min;
		this.max = max;
	}

	public Float getMin() {
		return min;
	}

	public void setMin(Float min) {
		this.min = min;
	}

	public Float getMax() {
		return max;
	}

	public void setMax(Float max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "Range [min=" + min + ", max=" + max + "]";
	}

}
