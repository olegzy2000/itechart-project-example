package com.bsu;

import javax.xml.bind.annotation.XmlElement;

public class GlowLamp extends Lamp {
	private long time;
	private final float constCost = (float) 4.55;

	public GlowLamp() {

	}

	public GlowLamp(String name, int power, int time) {
		super(name, power);
		this.time = time;
	}

	@Override
	public String toString() {
		return "GlowLamp [time=" + time + ", countCost()=" + countCost() + ", constCost=" + constCost + ", toString()="
				+ super.toString() + "]";
	}

	public long getTime() {
		return time;
	}

	@XmlElement
	public void setTime(long time) {
		this.time = time;
	}

	public Float countCost() {
		return this.time * this.constCost * super.getPower();
	}
}
