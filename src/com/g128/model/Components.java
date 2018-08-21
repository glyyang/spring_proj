package com.g128.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Component", namespace="com.g128.model.**")
public class Components {
//	@XmlElement(name="Type")
	private String type;
	
//	@XmlElement(name="Amount")
	private Amounts amount;

	@XmlElement(name="Type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name="Amount")
	public Amounts getAmount() {
		return amount;
	}

	public void setAmount(Amounts amount) {
		this.amount = amount;
	}
	
	
}
