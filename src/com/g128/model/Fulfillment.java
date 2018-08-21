package com.g128.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="FulfillmentData", namespace="com.g128.model.Orders")
public class Fulfillment {
//	@XmlElement(name="FulfillmentChannel")
	private String ffmtCh;
//	@XmlElement(name="ShipServiceLevel")
	private String shipServ;
//	@XmlElement(name="Address")
	private Address addr;
	
	@XmlElement(name="FulfillmentChannel")
	public String getFfmtCh() {
		return ffmtCh;
	}
	public void setFfmtCh(String ffmtCh) {
		this.ffmtCh = ffmtCh;
	}
	
	@XmlElement(name="ShipServiceLevel")
	public String getShipServ() {
		return shipServ;
	}
	public void setShipServ(String shipServ) {
		this.shipServ = shipServ;
	}
	
	@XmlElement(name="Address")
	public Address getAddr() {
		return addr;
	}
	public void setAddr(Address addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "Fulfillment [ffmtCh=" + ffmtCh + ", shipServ=" + shipServ + ", addr=" + addr + "]";
	}

	
}
