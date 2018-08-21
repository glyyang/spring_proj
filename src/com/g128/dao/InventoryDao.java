package com.g128.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.g128.model.Inventory;
import com.g128.model.InventoryItem;
import com.g128.model.InventoryMapping;
import com.g128.model.Money;
import com.g128.model.OrderItem;

public class InventoryDao {
	private Session ses;
	
	public InventoryDao() {
		
	}
	
	public void setSession(SessionFactory sf){
		ses = sf.getCurrentSession();
	}
	@Transactional
	public void closeSession(){
		ses.close();
	}
	
	@Transactional
	public void updateItem(String itemName, long numberLeft, String itemCode, Money cost) {
		Criteria crit = ses.createCriteria(Inventory.class).add(Restrictions.like("itemCode", itemCode));
		List<Inventory> invList = crit.list();
		
		if(invList == null || invList.isEmpty() || invList.get(0) == null) {
			saveItem(itemName, numberLeft, itemCode, cost);
		}
		else {
			SQLQuery sql = ses.createSQLQuery("update inventory set description = :name, unit_cost = :cost, unit_cost_currency = :currency, numberLeft = :number where item_code = :ic");
			//sql.setParameter("id", invList.get(0).getId());
			
			sql.setParameter("name", itemName);
			sql.setParameter("number", numberLeft);
			sql.setParameter("cost", cost.getAmount());
			sql.setParameter("currency", cost.getCurrencyCode());
			sql.setParameter("ic", itemCode);
			sql.executeUpdate();
		}
		
	}
	
	@Transactional
	public void reduceItemCount(long numberReduced, String itemCode) {
		Criteria crit = ses.createCriteria(Inventory.class).add(Restrictions.like("itemCode", itemCode));
		List<Inventory> invList = crit.list();
		
		if(invList == null || invList.isEmpty() || invList.get(0) == null) {
			
		}
		else {
			SQLQuery sql = ses.createSQLQuery("update inventory set numberLeft = :number where item_code = :ic");
			
			
			sql.setParameter("number", invList.get(0).getItem().getNumberLeft()-numberReduced);
			sql.setParameter("ic", itemCode);
			sql.executeUpdate();
		}
		
	}
	
	@Transactional
	public void increaseItemCount(long numberAdded, String itemCode) {
		Criteria crit = ses.createCriteria(Inventory.class).add(Restrictions.like("itemCode", itemCode));
		List<Inventory> invList = crit.list();
		
		if(invList == null || invList.isEmpty() || invList.get(0) == null) {
			
		}
		else {
			SQLQuery sql = ses.createSQLQuery("update inventory set numberLeft = :number where item_code = :ic");
			
			
			sql.setParameter("number", invList.get(0).getItem().getNumberLeft()+numberAdded);
			sql.setParameter("ic", itemCode);
			sql.executeUpdate();
		}
		
	}
	
	@Transactional
	public void deleteItem(String itemCode, String itemName) {
		if(itemCode != null && !itemCode.equalsIgnoreCase("")) {
			String hql = "DELETE FROM Inventory WHERE itemCode = :code";
			Query qry = ses.createQuery(hql);
			//sqry.addEntity(OrderItem.class);
			qry.setParameter("code", itemCode);
			qry.executeUpdate();
		}else if(itemName != null && !itemName.equalsIgnoreCase("")) {
			String sql = "DELETE FROM inventory where description = :name";
			SQLQuery sqry = ses.createSQLQuery(sql);
			sqry.setParameter("name", itemName);
			sqry.executeUpdate();
		}else {
			
		}
	}
	
	@Transactional
	public void saveItem(String itemName, long numberLeft, String itemCode, Money cost) {
//		try {
//			SQLQuery sql = ses.createSQLQuery("update inventory set description = :name, unit_cost = :cost, unit_cost_currency = :currency, numberLeft = :number where item_code = :ic;");
//			sql.setParameter("ic", itemCode);
//			sql.setParameter("name", itemName);
//			sql.setParameter("number", numberLeft);
//			sql.setParameter("cost", cost.getAmount());
//			sql.setParameter("currency", cost.getCurrencyCode());
//			
//			sql.executeUpdate();
//		}catch(Exception e) {
		Inventory inv = new Inventory();
		inv.setItemCode(itemCode);
		InventoryItem invIt = new InventoryItem();
		invIt.setDescription(itemName);
		invIt.setNumberLeft(numberLeft);
		invIt.setUnitPrice(cost);
		inv.setItem(invIt);
		ses.save(inv);
			
//		}
	}
	
	@Transactional
	public List<List<String>> getMappedAsinSku(){
		List<List<String>> strL = new ArrayList<>();
		
		Criteria crit = ses.createCriteria(InventoryMapping.class);
		
		List<InventoryMapping> iMp = crit.list();
		for(InventoryMapping im : iMp) {
			List<String> holder = new ArrayList<>();
			holder.add(im.getAsin());
			holder.add(im.getSku());
			strL.add(holder);
		}
		
		return strL;
	}
	
	@Transactional
	public Set<List<String>> getMappedAsinSkuTitle(){
		Set<List<String>> strL = new HashSet();
		
		Criteria crit = ses.createCriteria(InventoryMapping.class);
		
		List<InventoryMapping> iMp = crit.list();
		for(InventoryMapping im : iMp) {
			List<String> holder = new ArrayList<>();
			holder.add(im.getAsin());
			holder.add(im.getSku());
			holder.add(im.getTitle());
			holder.add(im.getCategory());
			holder.add(im.getType());
			holder.add(im.getSize());
			holder.add(im.getThickness());
			strL.add(holder);
		}
		
		return strL;
	}
	
	
}
