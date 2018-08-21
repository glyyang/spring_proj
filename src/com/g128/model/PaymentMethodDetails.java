package com.g128.model;

import javax.persistence.*;

public class PaymentMethodDetails {
	
	
	private String id;
	
	
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PaymentMethodDetails(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public PaymentMethodDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PaymentMethodDetails [id=" + id + ", description=" + description + "]";
	}
	
}
