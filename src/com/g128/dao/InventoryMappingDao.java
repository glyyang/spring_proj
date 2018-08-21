package com.g128.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.g128.model.InventoryMapping;

public class InventoryMappingDao {
	
	private Session ses;

	public InventoryMappingDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void setSession(SessionFactory sf){
		ses = sf.getCurrentSession();
	}
	@Transactional
	public void closeSession(){
		ses.close();
	}
	
	@Transactional
	public void storeRecord(String asin, String sku, String title, String category, String type, String size, String thickness) {
		
		
		InventoryMapping iMp;
		Criteria crit = ses.createCriteria(InventoryMapping.class).add(Restrictions.like("asin", asin)).add(Restrictions.like("sku",sku));
		List<InventoryMapping> iMpL = crit.list();
		if(iMpL == null || iMpL.isEmpty() || iMpL.get(0) == null)
			iMp = new InventoryMapping();
		else
			iMp = iMpL.get(0);
		
		iMp.setAsin(asin);
		iMp.setCategory(category);
		iMp.setSize(size);
		iMp.setSku(sku);
		iMp.setThickness(thickness);
		iMp.setTitle(title);
		iMp.setType(type);
		ses.saveOrUpdate(iMp);
	}
	
	@Transactional
	public void deleteRecord(String asin, String sku, String title, String category, String type, String size, String thickness) {
		
		
		InventoryMapping iMp;
		Criteria crit = ses.createCriteria(InventoryMapping.class).add(Restrictions.like("asin", asin)).add(Restrictions.like("sku",sku));
		List<InventoryMapping> iMpL = crit.list();
		if(iMpL == null || iMpL.isEmpty() || iMpL.get(0) == null)
			iMp = new InventoryMapping();
		else
			iMp = iMpL.get(0);
		
		iMp.setAsin(asin);
		iMp.setCategory(category);
		iMp.setSize(size);
		iMp.setSku(sku);
		iMp.setThickness(thickness);
		iMp.setTitle(title);
		iMp.setType(type);
		ses.saveOrUpdate(iMp);
		
		
		String sql = "delete from inventory_map where ";
		
		if((asin != null) && (!(asin.trim().equalsIgnoreCase("")))) {
			sql = sql + "asin = :asin, ";
		}
		if((sku != null) && (!(sku.trim().equalsIgnoreCase("")))) {
			sql = sql + "sku = :sku, ";
		}
		if((title != null) && (!(title.trim().equalsIgnoreCase("")))) {
			sql = sql + "title = :title, ";
		}
		if((category != null) && (!(category.trim().equalsIgnoreCase("")))) {
			sql = sql + "category = :category, ";
		}
		if((type != null) && (!(type.trim().equalsIgnoreCase("")))) {
			sql = sql + "type = :type, ";
		}
		if((size != null) && (!(size.trim().equalsIgnoreCase("")))) {
			sql = sql + "size = :size, ";
		}
		if((thickness != null) && (!(thickness.trim().equalsIgnoreCase("")))) {
			sql = sql + "thickness = :thickness, ";
		}
		
	}
	
	@Transactional
	public List<InventoryMapping> getFullMapping(){
		Criteria crit = ses.createCriteria(InventoryMapping.class);
		List<InventoryMapping> iMpL = crit.list();
		return iMpL;
		
	}
	
	@Transactional
	public List<String> getAsin(String sku, String title, String category, String type, String size, String thickness){
		Criteria crit = ses.createCriteria(InventoryMapping.class);
		if((sku != null) && (!(sku.trim().equalsIgnoreCase("")))) {
			crit.add(Restrictions.like("sku", sku));
		}
		if((title != null) && (!(title.trim().equalsIgnoreCase("")))) {
			crit.add(Restrictions.ilike("title",  "%" +title+ "%" ));
		}
		if((category != null) && (!(category.trim().equalsIgnoreCase("")))) {
			crit.add(Restrictions.ilike("category",  "%" + category+ "%" ));
		}
		if((type != null) && (!(type.trim().equalsIgnoreCase("")))) {
			crit.add(Restrictions.ilike("type",  "%" + type+ "%"));
		}
		if((size != null) && (!(size.trim().equalsIgnoreCase("")))) {
			crit.add(Restrictions.ilike("size",  "%" + size + "%"));
		}
		if((thickness != null) && (!(thickness.trim().equalsIgnoreCase("")))) {
			crit.add(Restrictions.ilike("thickness", "%" + thickness +  "%" ));
		}
		List<InventoryMapping> ivM = crit.list();
		
		System.out.println("List4 size: " + ivM.size());
		
		List<String> asinL = new ArrayList<>();
		
		for(InventoryMapping inv : ivM) {
			asinL.add(inv.getAsin());
		}
		
		return asinL;
	}
	
	@Transactional
	public List<String> getSku(String asin, String title, String category, String type, String size, String thickness){
		Criteria crit = ses.createCriteria(InventoryMapping.class);
		
		System.out.println("asin: " + asin);
		if((asin != null) && (!(asin.trim().equalsIgnoreCase("")))) {
			crit.add(Restrictions.like("asin", asin));
			
		}
		
		System.out.println("title: " + title);
		if((title != null) && (!(title.trim().equalsIgnoreCase("")))) {
			crit.add(Restrictions.ilike("title", "%" + title + "%"));
		}
		
		System.out.println("category: " + category);
		if((category != null) && (!(category.trim().equalsIgnoreCase("")))) {
			crit.add(Restrictions.ilike("category", "%" + category + "%"));
		}
		
		System.out.println("type: " + type);
		if((type != null) && (!(type.trim().equalsIgnoreCase("")))) {
			crit.add(Restrictions.ilike("type", "%" + type + "%"));
		}
		
		System.out.println("size: " + size);
		if((size != null) && (!(size.trim().equalsIgnoreCase("")))) {
			crit.add(Restrictions.ilike("size",  "%" + size + "%"));
		}
		
		System.out.println("thickness: " + thickness);
		if((thickness != null) && (!(thickness.trim().equalsIgnoreCase("")))) {
			crit.add(Restrictions.ilike("thickness",  "%" + thickness + "%"));
		}
		
		List<InventoryMapping> ivM = crit.list();
		
		System.out.println("List3 size: " + ivM.size());
		
		List<String> skuL = new ArrayList<>();
		
		for(InventoryMapping inv : ivM) {
			skuL.add(inv.getSku());
		}
		
		return skuL;
	}
}

