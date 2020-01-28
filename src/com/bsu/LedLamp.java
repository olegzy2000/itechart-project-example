package com.bsu;

import javax.xml.bind.annotation.XmlElement;

public class LedLamp extends Lamp {
	private int ledNumber;
	final float constCost = (float) 3.55;

	public LedLamp(String name, int power, int ledNumber) {
		super(name, power);
		this.ledNumber = ledNumber;
	}

	public LedLamp() {
		super();
	}

	@Override
	public String toString() {
		return "LedLamp [ledNumber=" + ledNumber + ", constCost=" + constCost + ", countCost()=" + countCost()
				+ ", toString()=" + super.toString() + "]";
	}

	public Float countCost() {
		return this.ledNumber * super.getPower() / this.constCost;
	}

	public int getLedNumber() {
		return ledNumber;
	}

	@XmlElement
	public void setLedNumber(int ledNumer) {
		this.ledNumber = ledNumer;
	}

}
