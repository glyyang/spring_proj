package com.g128.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Message", namespace="com.g128.model.MWSEnv")
public class OrderMessage {
//	@XmlElement(name="Order")
	private List<Orders> ords;

	@XmlElement(name="Order")
	public List<Orders> getOrds() {
		return ords;
	}

	public void setOrds(List<Orders> ords) {
		this.ords = ords;
	}
	
	
}
