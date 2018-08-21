package com.g128.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.renjin.math.idamax__;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.g128.model.Money;
import com.g128.model.OrderItem;
import com.g128.model.Orders;

@Component
public class Dao {
	
	private SessionFactory sessionFactory;
	

	public Dao(){}
	
	private OrderDao oDao = new OrderDao();
	
	private OrderItemDao oIDao = new OrderItemDao();
	
	private InventoryDao iDao = new InventoryDao();
	
	private InventoryMappingDao mDao = new InventoryMappingDao();
	
	//@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
	}
	
	@Transactional
	public void saveOrders(List<Orders> ord){
		//System.out.println("session factory status: " + (sessionFactory==null));
		oDao.setSession(sessionFactory);
		oDao.fetchOrderToDb(ord);
		System.out.println("In here");
	}
	
	@Transactional
	public void saveOrderItems(List<OrderItem> ord){
		oIDao.setSession(sessionFactory);
		oIDao.fetchOrderItemsToDb(ord);
		System.out.println("In here");
	}
	
	@Transactional
	public List<Orders> getAllOrders(){
		oDao.setSession(sessionFactory);
		return oDao.showDbOrders();
	}
	
	@Transactional
	public List<Orders>	getOrders(Calendar cal1, Calendar cal2, String fname, String lname, String fn, String oStat, String shipment, String orderId){
		System.out.println("passed 1");
		oDao.setSession(sessionFactory);
		
		String buyer = fname + " " + lname;
		return oDao.getOrders(cal1, cal2, buyer, fn, oStat, shipment, orderId);
	}
	
	@Transactional
	public List<OrderItem> getOrderItemsByOrderId(String id){
		oDao.setSession(sessionFactory);
		return oDao.getOrderItem(id);
	}
	
	@Transactional
	public List<OrderItem> getOrderItemsByName(String title){
		oDao.setSession(sessionFactory);
		return oDao.getOrderItemByItemName(title);
	}
	
	@Transactional
	public List<OrderItem> getOrderItemsByAsinSku(String asin, String sku){
		oIDao.setSession(sessionFactory);
		return oIDao.getOrderItemByAsinSku(asin, sku);
	}
//	@Transactional
//	public Orders getOrderInformation(String orderId){
//		return oDao.orderInfo(orderId);
//	}
	@Transactional
	public void updateItem(String itemName, long numberLeft, String itemCode, Money cost) {
		iDao.setSession(sessionFactory);
		iDao.updateItem(itemName, numberLeft, itemCode, cost);
	}
	@Transactional
	public void deleteItem(String itemCode, String itemName) {
		iDao.setSession(sessionFactory);
		iDao.deleteItem(itemCode, itemName);
	}
	@Transactional
	public void saveItem(String itemName, long numberLeft, String itemCode, Money cost) {
		iDao.setSession(sessionFactory);
		iDao.saveItem(itemName, numberLeft, itemCode, cost);
	}
	
	@Transactional
	public void saveMapping(String asin, String sku, String title, String category, String type, String size, String thickness) {
		mDao.setSession(sessionFactory);
		mDao.storeRecord(asin, sku, title, category, type, size, thickness);
	}
	
	@Transactional
	public void deleteMapping(String asin, String sku, String title, String category, String type, String size, String thickness) {
		mDao.setSession(sessionFactory);
		mDao.deleteRecord(asin, sku, title, category, type, size, thickness);
	}
	
	@Transactional
	public List<String> getSku(String asin, String title, String category, String type, String size, String thickness){
		mDao.setSession(sessionFactory);
		return mDao.getSku(asin, title, category, type, size, thickness);
	}
	
	@Transactional
	public List<String> getAsin(String sku, String title, String category, String type, String size, String thickness){
		mDao.setSession(sessionFactory);
		return mDao.getAsin(sku, title, category, type, size, thickness);
	}
	
	@Transactional
	public Set<List<String>> getUnmapped(){
		iDao.setSession(sessionFactory);
		oIDao.setSession(sessionFactory);
		
		List<List<String>> strL = iDao.getMappedAsinSku();
		return oIDao.getAsinSku(strL);
	}
	
	@Transactional
	public Set<List<String>> getMapped(){
		iDao.setSession(sessionFactory);
		oIDao.setSession(sessionFactory);
		
		Set<List<String>> strL = iDao.getMappedAsinSkuTitle();
		return strL;
	}
}
