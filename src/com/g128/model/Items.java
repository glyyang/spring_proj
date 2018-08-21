package com.g128.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="items")
public class Items {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="order_id")
	private String orderId;
	
	@Column
	private String asin;
	
	@Column
	private long number;
	
	@Column
	private String status;
	
	@Column
	private boolean completion;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name="item_code", referencedColumnName="item_code")
	private Inventory invItem;
	
//	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
//	@JoinColumn(name="pending_items", referencedColumnName="itemCode")
//	private InventoryItem pendingItem;

	
	public Items() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public long getId() {
		return id;
	}

	

	public void setId(long id) {
		this.id = id;
	}

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public boolean isCompletion() {
		return completion;
	}

	public void setCompletion(boolean completion) {
		this.completion = completion;
	}

	public Inventory getInvItem() {
		return invItem;
	}

	public void setInvItem(Inventory invItem) {
		this.invItem = invItem;
	}

	
	
	@Override
	public String toString() {
		return "Items [id=" + id + ", asin=" + asin + ", number=" + number + ", completion="
				+ completion + ", invItem=" + invItem + "]";
	}
	
	
	
	
}
