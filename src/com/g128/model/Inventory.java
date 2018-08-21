package com.g128.model;

import javax.persistence.*;

@Entity
@Table(name="inventory")
public class Inventory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Embedded
	private InventoryItem item;
	
	@Column(name="item_code", unique=true)
	private String itemCode;
	
	
	
	

//	@Embedded
//	private Money amount;
	
	public Inventory() {
		// TODO Auto-generated constructor stub
	}

	
	
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public InventoryItem getItem() {
		return item;
	}

	public void setItem(InventoryItem item) {
		this.item = item;
	}

//	public Money getAmount() {
//		return amount;
//	}
//
//	public void setAmount(Money amount) {
//		this.amount = amount;
//	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", item=" + item + ", amount=" + getItem().getUnitPrice().getAmount() + "]";
	}

}
