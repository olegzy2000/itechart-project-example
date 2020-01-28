package com.bsu;

import javax.xml.bind.annotation.XmlElement;

public abstract class Lamp {

	private String name;
	private int power;

	public String getName() {
		return name;
	}

	public Lamp() {
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public int getPower() {
		return power;
	}

	@Override
	public String toString() {
		return "Lamp [name=" + name + ", power=" + power + "]";
	}

	@XmlElement
	public void setPower(int power) {
		this.power = power;
	}

	public Lamp(String name, int power) {
		this.name = name;
		this.power = power;
	}

	public abstract Float countCost();
}
