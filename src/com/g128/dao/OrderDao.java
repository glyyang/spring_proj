package com.g128.dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import javax.xml.datatype.DatatypeConfigurationException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import com.g128.model.OrderItem;
import com.g128.model.Orders;


public class OrderDao {
	private Session ses;
	
	
	public OrderDao(){
		
	}
	
	
	public void setSession(SessionFactory sf){
		ses = sf.getCurrentSession();
		
	}
	@Transactional
	public void closeSession(){
		ses.close();
	}
	@Transactional
	public void fetchOrderToDb(List<Orders> ord){
		
		for(Orders o : ord){
			List<OrderItem> mL = ses.createCriteria(OrderItem.class).add(Restrictions.like("ord.amazonOrderId", o.getAmazonOrderId())).list();
			
			if(!(mL == null || mL.isEmpty() || mL.get(0) == null)) {
			
				for(OrderItem oio : mL){
					String hql1 = "DELETE FROM promotion_ids WHERE promo_id = :id";
					SQLQuery sqry = ses.createSQLQuery(hql1);
					sqry.setParameter("id", oio.getId());
					sqry.executeUpdate();
					ses.flush();
					try{
						String hql = "DELETE FROM OrderItem WHERE orderItemId = :oi_id";
						Query qry = ses.createQuery(hql);
						qry.setParameter("oi_id", oio.getOrderItemId());
						int res = qry.executeUpdate();
						ses.flush();
					}catch(Exception e){
						continue;
					}
					
				}
			}
			
			String hql = "DELETE FROM Orders WHERE amazonOrderId = :order_id";
			Query qry = ses.createQuery(hql);
			qry.setParameter("order_id", o.getAmazonOrderId());
			qry.executeUpdate();
			ses.flush();
			ses.save(o);
			ses.flush();
			System.out.println("Succeed");
		}
		
		
	}
	@Transactional
	public List<Orders> showDbOrders(){
		List<Orders> ord = ses.createCriteria(Orders.class).addOrder(Order.desc("purchaseDateConverted")).setMaxResults(1000).list();
		
		return ord;
	}
	
	@Transactional
	public List<Orders> getOrders(Calendar cal1, Calendar cal2, String buyer, String fn, String oStat, String shipment, String orderId){
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
		return ord;
		
	}

	
	@Transactional
	public List<OrderItem> getOrderItem(String orderId){
		String sql = "select * from amazon_order_item where ordered_id = :orderId";
		
		SQLQuery sqlQ = ses.createSQLQuery(sql);
		
		sqlQ.addEntity(OrderItem.class);
		sqlQ.setParameter("orderId", orderId);
		List<OrderItem> result = (List<OrderItem>)sqlQ.list();
		
		return result;
		
	}
	
	@Transactional
	public List<OrderItem> getOrderItemByItemName(String name){
		Criteria crit = ses.createCriteria(OrderItem.class);
		
		if(!name.trim().equalsIgnoreCase("")){
			crit.add(Restrictions.like("title", name)).add(Restrictions.isNotNull("title"));
		}
		
		
		return crit.list();
		
	}
	
	
}	


