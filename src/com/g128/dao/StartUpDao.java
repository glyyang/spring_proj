package com.g128.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.transaction.annotation.Transactional;

import com.g128.model.Inventory;
import com.g128.model.InventoryItem;
import com.g128.model.Items;
import com.g128.model.OffsetRecords;
import com.g128.model.OrderItem;
import com.g128.model.Orders;
import com.g128.model.logger;

public class StartUpDao {
	public void startUpOrderUpdate(List<Orders> ord){
		HibernateConnector hcl = HibernateConnector.getInstance();
		
		Session ses = hcl.getSession();
		
		
		for(Orders o : ord){
			logCurrentRecord("Saving Order info.", System.currentTimeMillis(), o.getPurchaseDateConverted());
			
			Criteria crit1 = ses.createCriteria(OrderItem.class).add(Restrictions.like("ord.amazonOrderId", o.getAmazonOrderId()));
			List<OrderItem> ordIList = crit1.list();
			if(!(ordIList == null || ordIList.isEmpty() || ordIList.get(0) == null)) {
				for(OrderItem oii : ordIList) {				
					String sql2 = "DELETE FROM promotion_ids WHERE promo_id = :id";
					SQLQuery sqry2 = ses.createSQLQuery(sql2);
					//sqry.addEntity(OrderItem.class);
					sqry2.setParameter("id", oii.getId());
					int res = sqry2.executeUpdate();
					System.out.println("# promo deleted: " + res);
				}
			
			}
			for(OrderItem oi: o.getOrd_items()) {
				
				
				Criteria crit = ses.createCriteria(Inventory.class).add(Restrictions.like("itemCode", oi.getAsin()));
				List<Inventory> invList = crit.list();
				
				Criteria crit2 = ses.createCriteria(Items.class).add(Restrictions.like("asin", oi.getAsin())).add(Restrictions.like("orderId", o.getAmazonOrderId()));
				List<Items> itemList = crit2.list();
				
				
				Inventory inv;
				Items it;
				InventoryItem invItem;
				
				if(invList.isEmpty()) {
					inv = new Inventory();
					inv.setItemCode(oi.getAsin());
					invItem = new InventoryItem();
					invItem.setDescription(oi.getTitle());
					invItem.setNumberLeft(0);
					invItem.setUnitPrice(oi.getItemPrice());
					invItem.setItemInv(new ArrayList<Items>());
				}else {
					inv = invList.get(0);
					invItem = inv.getItem();
					if(inv.equals(null)) {
						inv = new Inventory();
						inv.setItemCode(oi.getAsin());
						invItem = new InventoryItem();
						invItem.setDescription(oi.getTitle());
						invItem.setNumberLeft(0);
						invItem.setUnitPrice(oi.getItemPrice());
						invItem.setItemInv(new ArrayList<Items>());
					}
				}
				
				if(itemList.isEmpty()) {
					it = new Items();
					it.setAsin(oi.getAsin());
					
					it.setOrderId(o.getAmazonOrderId());
					
				}else {
					it = itemList.get(0);
					if(it.equals(null)) {
						it = new Items();
						it.setAsin(oi.getAsin());
						
						it.setOrderId(o.getAmazonOrderId());
					}
				}
				
				
				
				
				switch(o.getOrderStatus()) {
				case "Pending":
				case "Unshipped":
					it.setNumber(oi.getQuantityOrdered() - oi.getQuantityShipped());
					break;
				case "Shipped":
					it.setNumber(0);
					break;
				case "Canceled":
					if(it.getStatus() != null && !(it.getStatus().equalsIgnoreCase("Canceled")))
						it.setNumber(oi.getQuantityOrdered() - it.getNumber());
					
					break;
				default:
					break;
				
				}
				it.setStatus(o.getOrderStatus());
				List<Items> itL = invItem.getItemInv();
				itL.add(it);
				invItem.setItemInv(itL);
				inv.setItem(invItem);
				//tx.commit();
				//ses.flush();
				ses.saveOrUpdate(inv);
				ses.flush();
			}
			
			
			String sql = "DELETE FROM amazon_order_item WHERE ordered_id = :id";
			SQLQuery sqry = ses.createSQLQuery(sql);
			sqry.setParameter("id", o.getAmazonOrderId());
			int res = sqry.executeUpdate();
			
			System.out.println("Number of orders deleted: " + res);
			
			
			String sql2 = "DELETE FROM payment_detail WHERE orders_id = :id";
			SQLQuery sqry2 = ses.createSQLQuery(sql2);
			sqry2.setParameter("id", o.getAmazonOrderId());
			sqry2.executeUpdate();
				
				
			String hql = "DELETE FROM Orders WHERE amazonOrderId = :order_id";
			Query qry = ses.createQuery(hql);
			qry.setParameter("order_id", o.getAmazonOrderId());
			qry.executeUpdate();
				
			
			ses.save(o);
			
			
			
		}
		
		ses.close();
		
	}
		
	
	
	public void logCurrentRecord(String desc, long offset_value, Timestamp time) {
		
		logRecord(desc, offset_value, time);
			
	}
	
	public void logCurrentLogger(String desc, double difscore, long fetchCount, Timestamp time, long assocTime) {
		
		logLogger(desc, difscore, fetchCount ,time, assocTime);
	}
	//@Transactional
	private void logLogger(String desc, double difScore, long fetchCount, Timestamp time, long assocTime) {
		HibernateConnector hcl = HibernateConnector.getInstance();
		
		Session ses = hcl.getSession();
		Transaction tx = ses.beginTransaction();
		
		logger log = new logger();
		log.setAssocTime(assocTime);
		log.setTime(time);
		log.setFetchingCount(fetchCount);
		log.setDescription(desc);
		log.setDifferentialScore(difScore);
		//log.setRec(recs);
		ses.save(log);
		tx.commit();
		ses.close();
	}
	
	private void logRecord(String desc, long value, Timestamp timeIn) {
		HibernateConnector hcl = HibernateConnector.getInstance();
		
		Session ses = hcl.getSession();
		Transaction tx = ses.beginTransaction();
		
		OffsetRecords or = new OffsetRecords();
		or.setDescription(desc);
		or.setTimeIn(timeIn);
		or.setValue(value);
		ses.save(or);
		tx.commit();
		ses.close();
		
	}
	
	public List<Orders>	getOrders(Calendar cal1, Calendar cal2, String fname, String lname, String fn, String oStat, String shipment, String orderId){
		System.out.println("passed 1");
		HibernateConnector hcl = HibernateConnector.getInstance();
		
		Session ses = hcl.getSession();
		
		String buyer = fname + " " + lname;
		Criteria crit = ses.createCriteria(Orders.class);
		if(cal1 != null && cal2 != null){
			crit.add(Restrictions.between("purchaseDateConverted", new Timestamp(cal1.getTimeInMillis()), new Timestamp(cal2.getTimeInMillis()))).add(Restrictions.isNotNull("purchaseDateConverted"));
			System.out.println("Failed1");
		}
		if(!buyer.equalsIgnoreCase(" ")){
			crit.add(Restrictions.like("buyerName", buyer)).add(Restrictions.isNotNull("buyerName"));
			System.out.println("Failed2");
		}
		if(!(fn.equalsIgnoreCase(""))){
			crit.add(Restrictions.like("fulfillmentChannel", fn)).add(Restrictions.isNotNull("fulfillmentChannel"));
			System.out.println("Failed3");
		}
		if(!(oStat.equalsIgnoreCase(""))){
			crit.add(Restrictions.like("orderStatus", oStat)).add(Restrictions.isNotNull("orderStatus"));
			System.out.println("Failed4");
		}
		if(!(shipment.equalsIgnoreCase(""))){
			crit.add(Restrictions.like("shipServiceLevel", shipment)).add(Restrictions.isNotNull("shipServiceLevel"));
			System.out.println("Failed5");
		}
		if(!(orderId.equalsIgnoreCase(""))){
			crit.add(Restrictions.like("amazonOrderId", orderId)).add(Restrictions.isNotNull("amazonOrderId"));
			System.out.println("Failed6");
		}
		crit.addOrder(Order.asc("purchaseDateConverted"));
		List<Orders> ord = crit.list();
		ses.close();
		return ord;
		
	}
	
	public List<Timestamp> getMaxTimeORecUpdate() {
		HibernateConnector hcl = HibernateConnector.getInstance();
		
		Session ses = hcl.getSession();
		Criteria crit = ses.createCriteria(logger.class).add(Restrictions.like("description", "updating Order fetched")).setProjection(Projections.max("time")).addOrder(Order.desc("time"));
		List<Timestamp> result = (List<Timestamp>)crit.list();
		ses.close();
		return result;
	}
	
	public List<Timestamp> getMaxTimeORecPurchased() {
		HibernateConnector hcl = HibernateConnector.getInstance();
		
		Session ses = hcl.getSession();
		Criteria crit = ses.createCriteria(logger.class).add(Restrictions.like("description", "Saving Order fetched")).setProjection(Projections.max("time")).addOrder(Order.desc("time"));
		List<Timestamp> result = (List<Timestamp>)crit.list();
		ses.close();
		return result;
	}
	
	public List<Timestamp> getMaxTimeORecOffset() {
		HibernateConnector hcl = HibernateConnector.getInstance();
		
		Session ses = hcl.getSession();
		Criteria crit = ses.createCriteria(OffsetRecords.class).setProjection(Projections.max("timeIn")).addOrder(Order.desc("timeIn"));
		List<Timestamp> result = (List<Timestamp>)crit.list();
		ses.close();
		return result;
	}
	
	public List<OrderItem> getOrderItem(String orderId){
		HibernateConnector hcl = HibernateConnector.getInstance();
		
		Session ses = hcl.getSession();
		
		String sql = "select * from amazon_order_item where ordered_id = :orderId";
		
		SQLQuery sqlQ = ses.createSQLQuery(sql);
		
		sqlQ.addEntity(OrderItem.class);
		sqlQ.setParameter("orderId", orderId);
		List<OrderItem> result = (List<OrderItem>)sqlQ.list();
		ses.close();
		return result;
	}
}
