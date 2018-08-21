package com.g128.businessdelegate;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.persistence.Persistence;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.g128.amazonservice.mws.service.AmazonOrderItemService;
import com.g128.amazonservice.mws.service.AmazonOrderService;
import com.g128.dao.Dao;
import com.g128.dao.HibernateConnector;
import com.g128.dao.OrderDao;
import com.g128.dao.StartUpDao;
import com.g128.model.InventoryMapping;
import com.g128.model.OffsetRecords;
import com.g128.model.OrderItem;
import com.g128.model.Orders;
import com.g128.model.PaymentExecutionDetailItem;


@Component
@ComponentScan("com.g128.dao")
public class OrderDelegation {
	//@Autowired
	private AmazonOrderService amws;
	
	private AmazonOrderItemService amwsi;
	private List<OrderItem> o_items;
	private List<Orders> ord;
	
	@Autowired
	private Dao dao;
	
	private StartUpDao sDao;
	
	public OrderDelegation(){
		amwsi = new AmazonOrderItemService();
		amws = new AmazonOrderService();
		sDao = new StartUpDao();
		o_items = new ArrayList<>();
	}
	
	public void setCount(long count) {
		amwsi.setCount(count);
		amws.setCount(count);
	}
	
	//@SuppressWarnings("deprecation")
	
	public List<Orders> storeOrderFromService(String server, Calendar from, Calendar to, String d1, String d2) throws IOException, InterruptedException, DatatypeConfigurationException{
		switch(server){
		case "amazon":
			String sellerId = "seller_id";
			String mwsAuthToken = "auth_token";
			ord = amws.invokeListOrders(dao, "fetch_update", sellerId, mwsAuthToken, DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(from.get(Calendar.YEAR), from.get(Calendar.MONTH), from.get(Calendar.DAY_OF_MONTH), from.get(Calendar.HOUR_OF_DAY), from.get(Calendar.MINUTE),from.get(Calendar.SECOND))), DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(to.get(Calendar.YEAR), to.get(Calendar.MONTH), to.get(Calendar.DAY_OF_MONTH), to.get(Calendar.HOUR_OF_DAY), to.get(Calendar.MINUTE),to.get(Calendar.SECOND))));
//			Thread.sleep(11000);
//			for(Orders i : ord){
//				//try{
//				if(i.getShippingAddress() != null){
//					i.setAddressLine1(i.getShippingAddress().getAddressLine1());
//					i.setAddressLine2(i.getShippingAddress().getAddressLine2());
//					i.setAddressLine3(i.getShippingAddress().getAddressLine3());
//					i.setAddressType(i.getShippingAddress().getAddressType());
//					i.setName(i.getShippingAddress().getName());
//					i.setCity(i.getShippingAddress().getCity());
//					i.setCountryCode(i.getShippingAddress().getCountryCode());
//					i.setCounty(i.getShippingAddress().getCounty());
//					i.setDistrict(i.getShippingAddress().getDistrict());
//					i.setStateOrRegion(i.getShippingAddress().getStateOrRegion());
//					i.setPostalCode(i.getShippingAddress().getPostalCode());
//					i.setPhone(i.getShippingAddress().getPhone());
//				}
//				
//				if(i.getEarliestDeliveryDate() != null)
//					i.setEarliestDelivery(new Timestamp((i.getEarliestDeliveryDate().toGregorianCalendar()).getTimeInMillis()));
////					System.out.println(i.getLatestDeliveryDate().toGregorianCalendar());
////					System.out.println(i.getEarliestDeliveryDate().toGregorianCalendar());
//				
//				if(i.getLatestDeliveryDate() != null)
//					i.setLatestDelivery(new Timestamp((i.getLatestDeliveryDate().toGregorianCalendar()).getTimeInMillis()));
//				
//				if(i.getLatestShipDate() != null)
//					i.setLatestShipment(new Timestamp((i.getLatestShipDate().toGregorianCalendar()).getTimeInMillis()));
//				
//				if(i.getLastUpdateDate() != null)
//					i.setLastUpdated(new Timestamp((i.getLastUpdateDate().toGregorianCalendar()).getTimeInMillis()));
//				
//				if(i.getEarliestShipDate() != null)
//					i.setEarliestShipment(new Timestamp((i.getEarliestShipDate().toGregorianCalendar()).getTimeInMillis()));
//					//System.out.println(i.getOrderTotal().toString());
//				
//				if(i.getPurchaseDate() != null)
//					i.setPurchaseDateConverted(new Timestamp((i.getPurchaseDate().toGregorianCalendar()).getTimeInMillis()));
//				
//				
//				if(i.getOrderTotal() != null){
//					i.setOrderCost(i.getOrderTotal().getAmount());
//					i.setOrderCurrency(i.getOrderTotal().getCurrencyCode());
//				}
//				
//				if(i.getBuyerTaxInfo() != null)
//					i.setBuyerTIString(i.getBuyerTaxInfo().toString());
//				
//				if(i.getPaymentMethodDetails() != null)
//					i.setPaymentMethodDetail(i.getPaymentMethodDetails().toString());
//				
//				for(PaymentExecutionDetailItem pid : i.getPaymentExecutionDetail()){
//					if(pid != null)
//							pid.setCost(pid.getPayment().toString());
//				}
//				//}catch(Exception e){
//				//	continue;
//				//}
//				List<OrderItem> oi = amwsi.fetchOrderItemService(sellerId, mwsAuthToken, i.getAmazonOrderId());
//				for(OrderItem it : oi){
//					it.setOrd(i);
//					it.setItemCount(it.getProductInfo().getNumberOfItems());
//				}
//				i.setOrd_items(oi);
////				o_items.addAll(oi);
//				//Thread.sleep(11000);
//				
//			}
			
//			for(OrderItem oi : o_items){
//				oi.setItemCount(oi.getProductInfo().getNumberOfItems());
//				
//			}
			//System.out.println(ord);
			//System.out.println(o_items);
//			dao.saveOrders(ord);
			//dao.saveOrderItems(o_items);
			break;
		case "ebay":
			break;
		case "walmart":
			break;
		default:
			break;	
		}
		
		return ord;
	}
	
	
	
	public void upToday(long tt) throws IOException, InterruptedException, DatatypeConfigurationException{
		//long dt = System.currentTimeMillis();
		List<Timestamp> tL = sDao.getMaxTimeORecUpdate();
		long tf;
		if(tL == null || tL.isEmpty() || tL.get(0) == null) {
			tf = new GregorianCalendar(2018,7,15,0,0,0).getTimeInMillis();
		}else {
			tf = tL.get(0).getTime();
		}
		//tf = new GregorianCalendar(2018,6,31,0,0,0).getTimeInMillis();
		Date timef = new Date(tf);
		GregorianCalendar from = new GregorianCalendar();
		from.setTime(timef);
		
		//from.set(Calendar.HOUR_OF_DAY, from.get(Calendar.HOUR_OF_DAY)-1);
		//from.set(Calendar.DAY_OF_MONTH, from.get(Calendar.DAY_OF_MONTH)-1);
		//from.set(Calendar.MINUTE, from.get(Calendar.MINUTE)-3);
		from.set(Calendar.MINUTE, 0);
		from.set(Calendar.SECOND, 0);
		
		Date timet = new Date(tt-60*1000*2);
		GregorianCalendar to = new GregorianCalendar();
		to.setTime(timet);
		//to.set(Calendar.MINUTE, to.get(Calendar.MINUTE)-3);
		to.set(Calendar.MINUTE, 0);
		to.set(Calendar.SECOND, 0);
		File f = new File("credential.txt");
		
		System.out.println(f.getAbsolutePath());
		String sellerId = "seller_id";
		String mwsAuthToken = "auth_token";
		
//		System.out.println("Time Zone1: " + from.getTimeZone());
//		System.out.println("Time Zone2: " + to.getTimeZone());
//		ord = amws.invokeListOrders("startup" , sellerId,mwsAuthToken,DatatypeFactory.newInstance().newXMLGregorianCalendar(from),DatatypeFactory.newInstance().newXMLGregorianCalendar(to));
		ord = amws.updateListOrders("startup_update", sellerId, mwsAuthToken, DatatypeFactory.newInstance().newXMLGregorianCalendar(from), DatatypeFactory.newInstance().newXMLGregorianCalendar(to));
		//System.out.println("Pass this 1");
//		for(Orders i : ord){
//			//try{
//			if(i.getShippingAddress() != null){
//				i.setAddressLine1(i.getShippingAddress().getAddressLine1());
//				i.setAddressLine2(i.getShippingAddress().getAddressLine2());
//				i.setAddressLine3(i.getShippingAddress().getAddressLine3());
//				i.setAddressType(i.getShippingAddress().getAddressType());
//				i.setName(i.getShippingAddress().getName());
//				i.setCity(i.getShippingAddress().getCity());
//				i.setCountryCode(i.getShippingAddress().getCountryCode());
//				i.setCounty(i.getShippingAddress().getCounty());
//				i.setDistrict(i.getShippingAddress().getDistrict());
//				i.setStateOrRegion(i.getShippingAddress().getStateOrRegion());
//				i.setPostalCode(i.getShippingAddress().getPostalCode());
//				i.setPhone(i.getShippingAddress().getPhone());
//			}
//			
//			if(i.getEarliestDeliveryDate() != null)
//				i.setEarliestDelivery(new Timestamp((i.getEarliestDeliveryDate().toGregorianCalendar()).getTimeInMillis()));
////				System.out.println(i.getLatestDeliveryDate().toGregorianCalendar());
////				System.out.println(i.getEarliestDeliveryDate().toGregorianCalendar());
//			
//			if(i.getLatestDeliveryDate() != null)
//				i.setLatestDelivery(new Timestamp((i.getLatestDeliveryDate().toGregorianCalendar()).getTimeInMillis()));
//			
//			if(i.getLatestShipDate() != null)
//				i.setLatestShipment(new Timestamp((i.getLatestShipDate().toGregorianCalendar()).getTimeInMillis()));
//			
//			if(i.getLastUpdateDate() != null)
//				i.setLastUpdated(new Timestamp((i.getLastUpdateDate().toGregorianCalendar()).getTimeInMillis()));
//			
//			if(i.getEarliestShipDate() != null)
//				i.setEarliestShipment(new Timestamp((i.getEarliestShipDate().toGregorianCalendar()).getTimeInMillis()));
//				//System.out.println(i.getOrderTotal().toString());
//			
//			if(i.getPurchaseDate() != null)
//				i.setPurchaseDateConverted(new Timestamp((i.getPurchaseDate().toGregorianCalendar()).getTimeInMillis()));
//			
//			
//			if(i.getOrderTotal() != null){
//				i.setOrderCost(i.getOrderTotal().getAmount());
//				i.setOrderCurrency(i.getOrderTotal().getCurrencyCode());
//			}
//			
//			if(i.getBuyerTaxInfo() != null)
//				i.setBuyerTIString(i.getBuyerTaxInfo().toString());
//			
//			if(i.getPaymentMethodDetails() != null)
//				i.setPaymentMethodDetail(i.getPaymentMethodDetails().toString());
//			
//			for(PaymentExecutionDetailItem pid : i.getPaymentExecutionDetail()){
//				if(pid != null)
//						pid.setCost(pid.getPayment().toString());
//			}
//			//}catch(Exception e){
//			//	continue;
//			//}
//			List<OrderItem> oi = amwsi.fetchOrderItemService(sellerId, mwsAuthToken, i.getAmazonOrderId());
//			for(OrderItem it : oi){
//				it.setOrd(i);
//			}
//			i.setOrd_items(oi);
//			o_items.addAll(oi);
//			//Thread.sleep(11000);
//			
//		}
//		for(OrderItem oi : o_items){
//			oi.setItemCount(oi.getProductInfo().getNumberOfItems());
//			
//		}
		
		//System.out.println(ord);
		
		//sDao.startUpOrderUpdate(ord);
	}
	
	public double getTotalByPurchaseTime(long purchaseFrom, long purchaseTo) {
		Calendar from = new GregorianCalendar();
		
		Calendar to = new GregorianCalendar();
		from.setTimeInMillis(purchaseFrom);
		to.setTimeInMillis(purchaseTo);
		//System.out.println(from);
		//System.out.println(to);
		List<Orders> ord = getOrders(from, to, "", "", "", "", "", "");
		
		double sum = 0;
		for(Orders o : ord) {
			List<OrderItem> oiL;
			try {
				oiL = dao.getOrderItemsByOrderId(o.getAmazonOrderId());
			}catch(Exception e) {
				oiL = sDao.getOrderItem(o.getAmazonOrderId());
			}
			//System.out.println(o);
			for(OrderItem oi : oiL) {	
				
				if(oi.getItemPrice()!=null) {
					//System.out.println(oi.getItemPrice().getAmount());
					if(oi.getItemPrice().getAmount()!=null && !oi.getItemPrice().getAmount().trim().equalsIgnoreCase("NULL") && !oi.getItemPrice().getAmount().trim().equalsIgnoreCase("")) {
						sum += Double.parseDouble(oi.getItemPrice().getAmount());
					}
				}
			}
		}
		return sum;
	}
	
	public long getTotalItemsByPurchaseTime(long purchaseFrom, long purchaseTo) {
		Calendar from = new GregorianCalendar();
		
		Calendar to = new GregorianCalendar();
		from.setTimeInMillis(purchaseFrom);
		to.setTimeInMillis(purchaseTo);
		//System.out.println(from);
		//System.out.println(to);
		List<Orders> ord = getOrders(from, to, "", "", "", "", "", "");
		long sum = 0;
		for(Orders o: ord) {
			for(OrderItem oi: dao.getOrderItemsByOrderId(o.getAmazonOrderId())) {
				sum += oi.getItemCount();
			}
			
		}
		
		return sum;
	}
	
	public long getTotalOrdersByPurchaseTime(long purchaseFrom, long purchaseTo) {
		Calendar from = new GregorianCalendar();
		
		Calendar to = new GregorianCalendar();
		from.setTimeInMillis(purchaseFrom);
		to.setTimeInMillis(purchaseTo);
		//System.out.println(from);
		//System.out.println(to);
		List<Orders> ord = getOrders(from, to, "", "", "", "", "", "");
		
		long sum = ord.size();
		return sum;
	}
	
	public double getTotalByFlagType(String type) {
		List<OrderItem> oitems = dao.getOrderItemsByName(type);
		double sum = 0;
		for(OrderItem oi : oitems) 			sum += Double.parseDouble(oi.getItemPrice().getAmount());
		return sum;
	}
	
	public List<Orders> getAllOrders(){
		return dao.getAllOrders();
	}
	
	public List<Orders> getOrdersBrowse(Calendar cal1, Calendar cal2, String fname, String lname, String fn, String oStat, String shipment, String orderId){
		
		return dao.getOrders(cal1,cal2,fname,lname,fn,oStat,shipment,orderId);
	}
	
	public List<Orders> getOrders(Calendar cal1, Calendar cal2, String fname, String lname, String fn, String oStat, String shipment, String orderId){
		
		return sDao.getOrders(cal1,cal2,fname,lname,fn,oStat,shipment,orderId);
	}
	
	public List<Timestamp> fetchMaxTimeRec(){
		return sDao.getMaxTimeORecOffset();
	}
//	public Orders getOrdersById(String orderId){
//		return dao.getOrderInformation(orderId);
//	}
	
//	public List<Orders> getOrdersByBuyer(String fname, String lname){
//		return dao.getAllOrders();
//	}
	
	public List<String> getAsin(String sku, String title, String category, String type, String size, String thickness){
		return dao.getAsin(sku, title, category, type, size, thickness);
	}
	
	public List<String> getSku(String asin, String title, String category, String type, String size, String thickness){
		return dao.getSku(asin, title, category, type, size, thickness);
	}
	
	public Set<OrderItem> getOrderItemsByDescription(String title, String category, String type, String size, String thickness){
		Set<OrderItem> lst = new HashSet();
		List<String> sku = dao.getSku(null, title, category, type, size, thickness);
		System.out.println("Size of list1: " + sku.size());
		for(String skuU : sku) {
			List<String> asinH = dao.getAsin(skuU, title, category, type, size, thickness);
			System.out.println("Size of list2: " + asinH.size());
			for(String asinU : asinH) {
				lst.addAll(dao.getOrderItemsByAsinSku(asinU, skuU));
			}
		}
		
		return lst;
	}
	
	public List<Map<String, Long>> getAsinsSkuItemCountByPurchaseTime(long purchaseFrom, long purchaseTo){
		Set<List<String>> mS = dao.getMapped();
		Set<List<String>> umS = dao.getUnmapped();
//		Set<String> skuS = new HashSet<>();
//		Set<String> asinS = new HashSet<>();
//		for(List<String> ls : mS) {
//			skuS.add(ls.get(0));
//			asinS.add(ls.get(1));
//		}
//		
//		for(List<String> ls : umS) {
//			skuS.add(ls.get(0));
//			asinS.add(ls.get(1));
//		}
		
		Map<String, Long> skuM = new HashMap<>();
		Map<String, Long> asinM = new HashMap<>();
		Map<String, Long> titleM = new HashMap<>();
		Calendar from = new GregorianCalendar();
		
		Calendar to = new GregorianCalendar();
		from.setTimeInMillis(purchaseFrom);
		to.setTimeInMillis(purchaseTo);
		//System.out.println(from);
		//System.out.println(to);
		List<Orders> ord = getOrders(from, to, "", "", "", "", "", "");
		
		for(Orders o: ord) {
			for(OrderItem oi: dao.getOrderItemsByOrderId(o.getAmazonOrderId())) {
				
//				skuM.put(oi.getSellerSKU(), (skuM.containsKey(oi.getSellerSKU())?skuM.get(oi.getSellerSKU()):0) +oi.getItemCount());
//				asinM.put(oi.getAsin(), (asinM.containsKey(oi.getAsin())?asinM.get(oi.getAsin()):0) +oi.getItemCount());
				for(List<String> lst:mS) {
					if(lst.get(0).equalsIgnoreCase(oi.getAsin()) && lst.get(1).equalsIgnoreCase(oi.getSellerSKU())) {
						titleM.put(lst.get(2), (titleM.containsKey(lst.get(2))?titleM.get(lst.get(2)):0) +oi.getItemCount());
					}
				}
				for(List<String> lst:umS) {
					if(lst.get(0).equalsIgnoreCase(oi.getAsin()) && lst.get(1).equalsIgnoreCase(oi.getSellerSKU())) {
						titleM.put(lst.get(2), (titleM.containsKey(lst.get(2))?titleM.get(lst.get(2)):0) +oi.getItemCount());
					}
				}
			}
			
		}
		List<Map<String,Long>> lstM = new ArrayList<>();
//		lstM.add(asinM);
//		lstM.add(skuM);
		lstM.add(titleM);
		return lstM;
	}
	
	public List<Map<String, Double>> getAsinsSkuPriceCountByPurchaseTimeTitleAsinSku(long purchaseFrom, long purchaseTo, String title, String asin, String sku){
		Set<List<String>> mS = dao.getMapped();
		Set<List<String>> umS = dao.getUnmapped();
//		Set<String> skuS = new HashSet<>();
//		Set<String> asinS = new HashSet<>();
//		for(List<String> ls : mS) {
//			skuS.add(ls.get(0));
//			asinS.add(ls.get(1));
//		}
//		
//		for(List<String> ls : umS) {
//			skuS.add(ls.get(0));
//			asinS.add(ls.get(1));
//		}
		
//		Map<String, Double> skuM = new HashMap<>();
//		Map<String, Double> asinM = new HashMap<>();
		Map<String, Double> titleM = new HashMap<>();
		Map<String, Double> titleC = new HashMap<>();
		
		Calendar from = new GregorianCalendar();
		
		Calendar to = new GregorianCalendar();
		from.setTimeInMillis(purchaseFrom);
		to.setTimeInMillis(purchaseTo);
		//System.out.println(from);
		//System.out.println(to);
		List<Orders> ord = getOrders(from, to, "", "", "", "", "", "");
		//if((!ord.isEmpty()) && (ord.get(0) != null)) {
		//System.out.println("Dao1: " + (ord.get(0)==null));
		for(Orders o: ord) {
			List<OrderItem> oiL = dao.getOrderItemsByOrderId(o.getAmazonOrderId());
			System.out.println("Dao2: " + (oiL.get(0)==null));
			for(OrderItem oi: oiL) {
				
				if(oi!=null) {
					System.out.println("Dao3: " + (oi.getItemPrice()==null));
					String amount = "0";
					if(oi.getItemPrice()!=null && oi.getItemPrice().getAmount()!= null)				amount = oi.getItemPrice().getAmount();
					if((sku.trim().equalsIgnoreCase("") || sku == null || sku.equalsIgnoreCase(oi.getSellerSKU()))
						&& (asin.trim().equalsIgnoreCase("") || asin == null || asin.equalsIgnoreCase(oi.getSellerSKU()))){
//						skuM.put(oi.getSellerSKU(), ((skuM.containsKey(oi.getSellerSKU()))?(skuM.get(oi.getSellerSKU())):0) +Double.parseDouble(amount));
//						asinM.put(oi.getAsin(), (asinM.containsKey(oi.getAsin())?asinM.get(oi.getAsin()):0) +Double.parseDouble(amount));
						 
						for(List<String> lst:mS) {
							if(lst.get(0).equalsIgnoreCase(oi.getAsin()) && lst.get(1).equalsIgnoreCase(oi.getSellerSKU()) && (title == null || title.trim().equalsIgnoreCase("") || lst.get(2).toLowerCase().contains(title.toLowerCase()))) {
								titleM.put(lst.get(2), (titleM.containsKey(lst.get(2))?titleM.get(lst.get(2)):0) +Double.parseDouble(amount));
								titleC.put(lst.get(2), (titleC.containsKey(lst.get(2))?titleC.get(lst.get(2)):0) +oi.getItemCount());
							}
						}
						for(List<String> lst:umS) {
							if(lst.get(0).equalsIgnoreCase(oi.getAsin()) && lst.get(1).equalsIgnoreCase(oi.getSellerSKU()) && (title == null || title.trim().equalsIgnoreCase("") || oi.getTitle().toLowerCase().contains(title.toLowerCase()))) {
								titleM.put(lst.get(2), (titleM.containsKey(lst.get(2))?titleM.get(lst.get(2)):0) +Double.parseDouble(amount));
								titleC.put(lst.get(2), (titleC.containsKey(lst.get(2))?titleC.get(lst.get(2)):0) +oi.getItemCount());
							}
						}
					}
				}
			}
			
		}
		//}
		List<Map<String,Double>> lstM = new ArrayList<>();
		
		lstM.add(titleM);
		lstM.add(titleC);
		return lstM;
	}
	
	public List<Map<String, Double>> getAsinsSkuPriceCountByPurchaseTime(long purchaseFrom, long purchaseTo){
		Set<List<String>> mS = dao.getMapped();
		Set<List<String>> umS = dao.getUnmapped();
//		Set<String> skuS = new HashSet<>();
//		Set<String> asinS = new HashSet<>();
//		for(List<String> ls : mS) {
//			skuS.add(ls.get(0));
//			asinS.add(ls.get(1));
//		}
//		
//		for(List<String> ls : umS) {
//			skuS.add(ls.get(0));
//			asinS.add(ls.get(1));
//		}
		
		Map<String, Double> skuM = new HashMap<>();
		Map<String, Double> asinM = new HashMap<>();
		Map<String, Double> titleM = new HashMap<>();
		
		Calendar from = new GregorianCalendar();
		
		Calendar to = new GregorianCalendar();
		from.setTimeInMillis(purchaseFrom);
		to.setTimeInMillis(purchaseTo);
		//System.out.println(from);
		//System.out.println(to);
		List<Orders> ord = getOrders(from, to, "", "", "", "", "", "");
		//if((!ord.isEmpty()) && (ord.get(0) != null)) {
		//System.out.println("Dao1: " + (ord.get(0)==null));
		for(Orders o: ord) {
			List<OrderItem> oiL = dao.getOrderItemsByOrderId(o.getAmazonOrderId());
			System.out.println("Dao2: " + (oiL.get(0)==null));
			for(OrderItem oi: oiL) {
				
				if(oi!=null) {
					System.out.println("Dao3: " + (oi.getItemPrice()==null));
					String amount = "0";
					if(oi.getItemPrice()!=null && oi.getItemPrice().getAmount()!= null)				amount = oi.getItemPrice().getAmount();
//					skuM.put(oi.getSellerSKU(), ((skuM.containsKey(oi.getSellerSKU()))?(skuM.get(oi.getSellerSKU())):0) +Double.parseDouble(amount));
//					asinM.put(oi.getAsin(), (asinM.containsKey(oi.getAsin())?asinM.get(oi.getAsin()):0) +Double.parseDouble(amount));
					for(List<String> lst:mS) {
						if(lst.get(0).equalsIgnoreCase(oi.getAsin()) && lst.get(1).equalsIgnoreCase(oi.getSellerSKU())) {
							titleM.put(lst.get(2), (titleM.containsKey(lst.get(2))?titleM.get(lst.get(2)):0) +Double.parseDouble(amount));
						}
					}
					for(List<String> lst:umS) {
						if(lst.get(0).equalsIgnoreCase(oi.getAsin()) && lst.get(1).equalsIgnoreCase(oi.getSellerSKU())) {
							titleM.put(lst.get(2), (titleM.containsKey(lst.get(2))?titleM.get(lst.get(2)):0) +Double.parseDouble(amount));
						}
					}
				}
			}
			
		}
		//}
		List<Map<String,Double>> lstM = new ArrayList<>();
//		lstM.add(asinM);
//		lstM.add(skuM);
		lstM.add(titleM);
		return lstM;
	}
	
	public List<OrderItem> getOrderItemsByAsinSKU(String asin, String sku){
		return dao.getOrderItemsByAsinSku(asin, sku);
	}
	
	
	
	public double getTotalByPurchaseTimeAndAsinSku(long begin, long end, String asin, String sku){
		Calendar from = new GregorianCalendar();
		
		Calendar to = new GregorianCalendar();
		from.setTimeInMillis(begin);
		to.setTimeInMillis(end);
		List<Orders> ord = getOrders(from, to, "", "", "", "", "", "");
		double sum = 0;
		for(Orders o : ord) {	
			//System.out.println(o);
			for(OrderItem oi : dao.getOrderItemsByOrderId(o.getAmazonOrderId())) {	
				if((asin.trim().equalsIgnoreCase("") || asin == null || oi.getAsin().equalsIgnoreCase(asin))
				&& (sku.trim().equalsIgnoreCase("") || sku == null || oi.getSellerSKU().equalsIgnoreCase(sku))) {
					if(oi.getItemPrice()!=null) {//if item price is null, it will not be counted
						//System.out.println(oi.getItemPrice().getAmount());
						if(oi.getItemPrice().getAmount()!=null && !oi.getItemPrice().getAmount().trim().equalsIgnoreCase("NULL") && !oi.getItemPrice().getAmount().trim().equalsIgnoreCase("")) {
							sum += Double.parseDouble(oi.getItemPrice().getAmount());
						}
					}
				}
			}
		}
		return sum;
	}
	
	public double getTotalByPurchaseTimeAndAsinSkuTitle(long begin, long end, String asin, String sku, String title){
		Set<List<String>> mS = dao.getMapped();
		Set<List<String>> umS = dao.getUnmapped();
		Calendar from = new GregorianCalendar();
		
		Calendar to = new GregorianCalendar();
		from.setTimeInMillis(begin);
		to.setTimeInMillis(end);
		List<Orders> ord = getOrders(from, to, "", "", "", "", "", "");
		double sum = 0;
		
		
		
		int count = 0;
		for(Orders o : ord) {	
			//System.out.println(o);
			for(OrderItem oi : dao.getOrderItemsByOrderId(o.getAmazonOrderId())) {	
				if((asin.trim().equalsIgnoreCase("") || asin == null || oi.getAsin().equalsIgnoreCase(asin))
					&& (sku.trim().equalsIgnoreCase("") || sku == null || oi.getSellerSKU().equalsIgnoreCase(sku))) {
					if(oi.getItemPrice()!=null) {
						//System.out.println(oi.getItemPrice().getAmount());
						if(oi.getItemPrice().getAmount()!=null && !oi.getItemPrice().getAmount().trim().equalsIgnoreCase("NULL") && !oi.getItemPrice().getAmount().trim().equalsIgnoreCase("")) {
							if(title.trim().equalsIgnoreCase("") || title == null) {
								sum += Double.parseDouble(oi.getItemPrice().getAmount());
							}
							else {
								boolean isHere = false;
								for(List<String> sT : mS) {
									if(sT.get(2).toLowerCase().contains(title.toLowerCase()) && sT.get(0).equalsIgnoreCase(oi.getAsin()) && sT.get(1).equalsIgnoreCase(oi.getSellerSKU())) {
										isHere = true;
									}
								}
								if(isHere || oi.getTitle().toLowerCase().contains(title.toLowerCase())) {
									sum += Double.parseDouble(oi.getItemPrice().getAmount());
									count++;
									//System.out.println(o.getPurchaseDateConverted());
								}
							}
						}
					}
				}
			}
		}
		System.out.println("total is: " + sum);
		return sum;
	}
	
	public long getTotalItemsByPurchaseTimeAndAsinSkuTitle(long begin, long end, String asin, String sku, String title){
		Set<List<String>> mS = dao.getMapped();
		Set<List<String>> umS = dao.getUnmapped();
		Calendar from = new GregorianCalendar();
		
		Calendar to = new GregorianCalendar();
		from.setTimeInMillis(begin);
		to.setTimeInMillis(end);
		List<Orders> ord = getOrders(from, to, "", "", "", "", "", "");
		double sum = 0;
		
		
		
		long count = 0;
		for(Orders o : ord) {	
			//System.out.println(o);
			for(OrderItem oi : dao.getOrderItemsByOrderId(o.getAmazonOrderId())) {	
				if((asin.trim().equalsIgnoreCase("") || asin == null || oi.getAsin().equalsIgnoreCase(asin))
					&& (sku.trim().equalsIgnoreCase("") || sku == null || oi.getSellerSKU().equalsIgnoreCase(sku))) {
					if(title.trim().equalsIgnoreCase("") || title == null) {
						//sum += Double.parseDouble(oi.getItemPrice().getAmount());
						count += oi.getItemCount();
					}
					else {
						//System.out.println("enter 1");
						boolean isHere = false;
						for(List<String> sT : mS) {
							if(sT.get(2).toLowerCase().contains(title.toLowerCase()) && sT.get(0).equalsIgnoreCase(oi.getAsin()) && sT.get(1).equalsIgnoreCase(oi.getSellerSKU())) {
								isHere = true;
							}
						}
						if(isHere || oi.getTitle().toLowerCase().contains(title.toLowerCase())) {
							//sum += Double.parseDouble(oi.getItemPrice().getAmount());
							count += oi.getItemCount();
							//System.out.println(o.getPurchaseDateConverted());
						}
					}
				}
			}
		}
		System.out.println(count);
		return count;
	}
	
	public List<OrderItem> getOrderItemsByOrderId(String id){
		return dao.getOrderItemsByOrderId(id);
	}
	
	
	public Set<List<String>> getUnmapped(){
		return dao.getUnmapped();
	}
	
	
	public Set<List<String>> getMapped(){
		return dao.getMapped();
	}
	
}
