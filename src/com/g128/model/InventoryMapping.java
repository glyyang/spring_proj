package com.g128.model;

import javax.persistence.*;

@Entity
@Table(name="inventory_map", uniqueConstraints= {@UniqueConstraint(columnNames={"asin", "sku"})})
public class InventoryMapping {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private String asin;
	
	@Column
	private String sku;
	
	@Column
	private String title;
	
	@Column
	private String category;
	
	@Column
	private String type;
	
	@Column
	private String size;
	
	@Column
	private String thickness;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAsin() {
		return asin;
	}
	public void setAsin(String asin) {
		this.asin = asin;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getThickness() {
		return thickness;
	}
	public void setThickness(String thickness) {
		this.thickness = thickness;
	}
	
	
}
