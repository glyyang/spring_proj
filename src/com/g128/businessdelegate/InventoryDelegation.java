package com.g128.businessdelegate;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.g128.dao.Dao;
import com.g128.model.Money;

@Component
@ComponentScan("com.g128.dao")
public class InventoryDelegation {
	@Autowired
	Dao dao;
	public InventoryDelegation() {
		
	}
	
	public void saveInventoryItem(String itemName, long numberLeft, String itemCode, Money cost) {
		dao.saveItem(itemName, numberLeft, itemCode, cost);
	}
	
	public void updateInventoryItem(String itemName, long numberLeft, String itemCode, Money cost) {
		dao.updateItem(itemName, numberLeft, itemCode, cost);
	}
	
	public void removeInventoryItem(String itemCode, String itemName) {
		dao.deleteItem(itemCode, itemName);
	}
	
	public void saveMapping(String asin, String sku, String title, String category, String type, String size, String thickness) {
		dao.saveMapping(asin, sku, title, category, type, size, thickness);
	}
	
	public void deleteMapping(String asin, String sku, String title, String category, String type, String size, String thickness) {
		dao.deleteMapping(asin, sku, title, category, type, size, thickness);
	}
	
	public Set<List<String>> getUnmapped() {
		return dao.getUnmapped();
	}
	
	public Set<List<String>> getMapped() {
		return dao.getMapped();
	}
}
