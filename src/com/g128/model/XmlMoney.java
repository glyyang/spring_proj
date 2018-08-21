package com.g128.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ItemPrice", namespace="com.g128.OrderItem")
public class XmlMoney extends Money{
//	@XmlElement(name="Component")
	private List<Components> cmp;
	
    private String currencyCode;

    private String amount;

    @XmlElement(name="Component")
	public List<Components> getCmp() {
		return cmp;
	}

	public void setCmp(List<Components> cmp) {
		this.cmp = cmp;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
    
    
}
