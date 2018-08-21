package com.g128.model;

import java.util.List;

import javax.persistence.*;

@Embeddable
public class InventoryItem {
	private String description;
	
	@Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount",
                               column=@Column(name="unit_cost")),
            @AttributeOverride(name="currencyCode",
                               column=@Column(name="unit_cost_currency"))
    })
	private Money unitPrice;
	private long numberLeft;
	
	
	
	@OneToMany(mappedBy="invItem", fetch=FetchType.LAZY, cascade= {CascadeType.ALL})
	private List<Items> itemInv;
	
	public InventoryItem() {
		// TODO Auto-generated constructor stub
	}
	
	
	public List<Items> getItemInv() {
		return itemInv;
	}
	public void setItemInv(List<Items> itemInv) {
		this.itemInv = itemInv;
	}
	
//	public List<Items> getPendingForReturn() {
//		return pendingForReturn;
//	}
//	public void setPendingForReturn(List<Items> pendingForReturn) {
//		this.pendingForReturn = pendingForReturn;
//	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Money getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Money unitPrice) {
		this.unitPrice = unitPrice;
	}
	public long getNumberLeft() {
		return numberLeft;
	}
	public void setNumberLeft(long numberLeft) {
		this.numberLeft = numberLeft;
	}
	
	@Override
	public String toString() {
		return "InventoryItem [description=" + description + ", unitPrice=" + unitPrice + ", numberLeft=" + numberLeft
				+ "]";
	}

}
