package com.g128.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.regexp.recompile;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.g128.model.Inventory;
import com.g128.model.InventoryItem;
import com.g128.model.OrderItem;
import com.g128.model.Orders;

public class OrderItemDao {
	private Session ses;
	
	
	public OrderItemDao(){
		
		
	}
	
	public void setSession(SessionFactory sf){
		ses = sf.getCurrentSession();
		System.out.println(ses == null);
	}
	@Transactional
	public void closeSession(){
		ses.close();
	}
	@Transactional
	public void fetchOrderItemsToDb(List<OrderItem> ord) {
		System.out.println(ses == null);
		// TODO Auto-generated method stub
		for(OrderItem o : ord){
			String hql = "DELETE FROM promotion_ids WHERE promo_id = :id";
			SQLQuery sqry = ses.createSQLQuery(hql);
			sqry.setParameter("id", o.getId());
			sqry.executeUpdate();
			ses.flush();
			
			hql = "DELETE FROM OrderItem WHERE orderItemId = :oi_id";
			Query qry = ses.createQuery(hql);
			qry.setParameter("oi_id", o.getOrderItemId());
			int res = qry.executeUpdate();
			ses.flush();
			ses.save(o);
		}
	}

	@Transactional
	public List<OrderItem> getOrderItemByAsinSku(String asin, String sku) {
		// TODO Auto-generated method stub
		System.out.println("ses exist: " + ses == null);
		Criteria crit = ses.createCriteria(OrderItem.class);
		if(asin != null && !asin.trim().equalsIgnoreCase(""))				crit.add(Restrictions.like("asin", asin));
		if(sku != null && !sku.trim().equalsIgnoreCase(""))				crit.add(Restrictions.like("sellerSKU", sku));
		return crit.list();
	}
	
	@Transactional
	public Set<List<String>> getAsinSku(List<List<String>> strL){
		Criteria criteria = ses.createCriteria(OrderItem.class);
		Set<List<String>> strS = new HashSet<>();
		for(List<String> combo : strL) {
			criteria.add(Restrictions.ne("asin", combo.get(0)));
			criteria.add(Restrictions.ne("sellerSKU", combo.get(1)));
		}
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("asin"));
		projList.add(Projections.property("sellerSKU"));
		projList.add(Projections.property("title"));
		criteria.setProjection(Projections.distinct(projList));
		List<Object[]> oitL = (List<Object[]>)criteria.list();
		
		for(Object[] oi : oitL) {
			List<String> cmb = new ArrayList<>();
			
			cmb.add((String)oi[0]);
			cmb.add((String)oi[1]);
			String strH = (String)oi[2];
			cmb.add(strH);
			List<String> holder = new ArrayList<>();
			for(List<String> strl : strS) {
				
				if(strl.get(0).equalsIgnoreCase(cmb.get(0)) && strl.get(1).equalsIgnoreCase(cmb.get(1))){
					cmb.set(2, cmb.get(2).length() > strl.get(2).length()?cmb.get(2):strl.get(2));
					holder = strl;
				}
				
			}
			if(holder != null)				strS.remove(holder);
			
			strS.add(cmb);
		}
		
		return strS;
	}

}
