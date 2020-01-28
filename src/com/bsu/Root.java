package com.bsu;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Root {

	@XmlElementWrapper(name = "LedLamps")
	@XmlElement(name = "LedLamp")
	private List<LedLamp> ledLamps = new ArrayList<LedLamp>();

	@XmlElementWrapper(name = "GlowLamps")
	@XmlElement(name = "GlowLamp")
	private List<GlowLamp> glowLamps = new ArrayList<GlowLamp>();

	public Root() {
	}

	public List<LedLamp> getLedLamps() {
		return ledLamps;
	}

	public void setLedLamps(List<LedLamp> ledLamps) {
		this.ledLamps = ledLamps;
	}

	public List<GlowLamp> getGlowLamps() {
		return glowLamps;
	}

	public void setGlowLamps(List<GlowLamp> glowLamps) {
		this.glowLamps = glowLamps;
	}

}
