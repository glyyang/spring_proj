package com.g128.amazonservice.mws.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import com.g128.amazonservice.mws.order.*;
import com.g128.dao.Dao;
import com.g128.dao.StartUpDao;
import com.g128.model.*;


public class AmazonOrderService {
	
	private StartUpDao sDao;
	private long count;
    private static List<Double> globalMeanDiff = new ArrayList<Double>();
    private static long uMin = 10000;
    private static long standardMin = 10000;
    
    
    private AmazonOrderItemService amwsi;
    
    private List<OrderItem> o_items;
    
    
    private Dao dao;
    
    public AmazonOrderService() {
    	
    	sDao = new StartUpDao();
    	amwsi = new AmazonOrderItemService();
    	o_items = new ArrayList<>();
    }
	
    public void setCount(long count) {
    	this.count=count;
    }
    
    public static void reset() {
    	standardMin = standardMin+500;
    	globalMeanDiff = new ArrayList<Double>();
    }
    
    public List<Orders> updateListOrders(String status, String sellerId, String mwsAuthToken, XMLGregorianCalendar updatedAfter, XMLGregorianCalendar updatedBefore) throws IOException, InterruptedException {
    	MarketplaceWebServiceOrdersClient client = MarketplaceWebServiceOrdersAuthConfig.getClient();
		List<Orders> retOrd = new ArrayList<Orders>();
        ListOrdersRequest request = new ListOrdersRequest();
        
        request.setSellerId(sellerId);
        
        request.setMWSAuthToken(mwsAuthToken);
        
        List<String> marketplaceId = new ArrayList<String>();
        marketplaceId.add("ATVPDKIKX0DER");
        marketplaceId.add("A2EUQ1WTGCTBG2");
        marketplaceId.add("A1AM78C64UM0Y8");
        request.setMarketplaceId(marketplaceId);
        Integer maxResultsPerPage = 100;
        request.setMaxResultsPerPage(maxResultsPerPage);
        request.setLastUpdatedAfter(updatedAfter);
        request.setLastUpdatedBefore(updatedBefore);
        
        long timeFrom;
        long timeTo;
        long tDiffMill;
        Timestamp t;
        try {
        	timeFrom = System.currentTimeMillis();
        	t = new Timestamp(updatedAfter.toGregorianCalendar().getTimeInMillis());

            String nextToken = null;
            ListOrdersResponse response = client.listOrders(request);
            ResponseOrderHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            ListOrdersByNextTokenResponse resp = null;
            ListOrdersByNextTokenRequest req = null;
            while(true){
	            ListOrdersResult lsp1;
	            ListOrdersByNextTokenResult lsp2;
	            List<Orders> ord;
	            
	            
	            if(resp == null && req == null && nextToken == null){
	            	lsp1 = response.getListOrdersResult();
	            	ord = lsp1.getOrders();
	            	nextToken = lsp1.getNextToken();
	            }
	            else{
	            	lsp2 = resp.getListOrdersByNextTokenResult();
	            	ord = lsp2.getOrders();
	            	nextToken = lsp2.getNextToken();
	            }
	            for(Orders i : ord){
	    			if(i.getShippingAddress() != null){
	    				i.setAddressLine1(i.getShippingAddress().getAddressLine1());
	    				i.setAddressLine2(i.getShippingAddress().getAddressLine2());
	    				i.setAddressLine3(i.getShippingAddress().getAddressLine3());
	    				i.setAddressType(i.getShippingAddress().getAddressType());
	    				i.setName(i.getShippingAddress().getName());
	    				i.setCity(i.getShippingAddress().getCity());
	    				i.setCountryCode(i.getShippingAddress().getCountryCode());
	    				i.setCounty(i.getShippingAddress().getCounty());
	    				i.setDistrict(i.getShippingAddress().getDistrict());
	    				i.setStateOrRegion(i.getShippingAddress().getStateOrRegion());
	    				i.setPostalCode(i.getShippingAddress().getPostalCode());
	    				i.setPhone(i.getShippingAddress().getPhone());
	    			}
	    			
	    			if(i.getEarliestDeliveryDate() != null)
	    				i.setEarliestDelivery(new Timestamp((i.getEarliestDeliveryDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getLatestDeliveryDate() != null)
	    				i.setLatestDelivery(new Timestamp((i.getLatestDeliveryDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getLatestShipDate() != null)
	    				i.setLatestShipment(new Timestamp((i.getLatestShipDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getLastUpdateDate() != null)
	    				i.setLastUpdated(new Timestamp((i.getLastUpdateDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getEarliestShipDate() != null)
	    				i.setEarliestShipment(new Timestamp((i.getEarliestShipDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getPurchaseDate() != null)
	    				i.setPurchaseDateConverted(new Timestamp((i.getPurchaseDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			
	    			if(i.getOrderTotal() != null){
	    				i.setOrderCost(i.getOrderTotal().getAmount());
	    				i.setOrderCurrency(i.getOrderTotal().getCurrencyCode());
	    			}
	    			
	    			if(i.getBuyerTaxInfo() != null)
	    				i.setBuyerTIString(i.getBuyerTaxInfo().toString());
	    			
	    			if(i.getPaymentMethodDetails() != null)
	    				i.setPaymentMethodDetail(i.getPaymentMethodDetails().toString());
	    			
	    			for(PaymentExecutionDetailItem pid : i.getPaymentExecutionDetail()){
	    				if(pid != null)
	    						pid.setCost(pid.getPayment().toString());
	    			}
	    			List<OrderItem> oi = amwsi.fetchOrderItemService(status, sellerId, mwsAuthToken, i.getAmazonOrderId());
	    			for(OrderItem it : oi){
	    				it.setOrd(i);
	    				it.setItemCount(it.getProductInfo().getNumberOfItems());
	    			}
	    			
	    			i.setOrd_items(oi);
	    			o_items.addAll(oi);
	    			t = i.getPurchaseDateConverted();
	    			
	    		}
	            if(status.equalsIgnoreCase("startup_update"))						sDao.startUpOrderUpdate(ord);
	            else																dao.saveOrders(ord);
	            
	            timeTo = System.currentTimeMillis();
            	tDiffMill = timeTo - timeFrom;
            	
            	
            	double avg = (globalMeanDiff.isEmpty())?tDiffMill+0.00:globalMeanDiff.stream().mapToDouble(a -> a).average().getAsDouble();
            	double newDiff = tDiffMill+0.00-avg;
            	if(globalMeanDiff.size() > 10) {
            		globalMeanDiff.remove(0);
            	}
            	globalMeanDiff.add(tDiffMill+0.00);
            	if(status.equalsIgnoreCase("startup_update") || status.equalsIgnoreCase("startup"))
            		sDao.logCurrentLogger("updating Order fetched" , newDiff, count, t, tDiffMill);
            	
            	uMin = (long) ((standardMin)+ Math.max(0,(6*uMin/6*(newDiff/(avg)))));
            	Thread.sleep(uMin);
	           
	            if(nextToken == null)		break;
	            
	            req = new ListOrdersByNextTokenRequest();
	            
	            req.setSellerId(request.getSellerId());
	            
	            req.setMWSAuthToken(request.getMWSAuthToken());
	            
	            
	            req.setNextToken(nextToken);
	            timeFrom = System.currentTimeMillis();
	            resp = client.listOrdersByNextToken(req);
	            
	            rhmd = resp.getResponseHeaderMetadata();
	            retOrd.addAll(ord);
	            
	            
            }
            
            return retOrd;
        } catch (MarketplaceWebServiceOrdersException ex) {
            System.out.println("Service Exception:");
            ResponseOrderHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if(rhmd != null) {
                System.out.println("RequestId: "+rhmd.getRequestId());
                System.out.println("Timestamp: "+rhmd.getTimestamp());
            }
            System.out.println("Message: "+ex.getMessage());
            System.out.println("StatusCode: "+ex.getStatusCode());
            System.out.println("ErrorCode: "+ex.getErrorCode());
            System.out.println("ErrorType: "+ex.getErrorType());
            throw ex;
        }
    	
    }
    
	public List<Orders> invokeListOrders(Dao dao, String status, String sellerId, String mwsAuthToken, XMLGregorianCalendar createdAfter, XMLGregorianCalendar createdBefore) throws IOException, InterruptedException {
		this.dao = dao;
		MarketplaceWebServiceOrdersClient client = MarketplaceWebServiceOrdersAuthConfig.getClient();

		List<Orders> retOrd = new ArrayList<Orders>();
        ListOrdersRequest request = new ListOrdersRequest();
        
        request.setSellerId(sellerId);
        
        request.setMWSAuthToken(mwsAuthToken);
        
        request.setCreatedAfter(createdAfter);
        
        request.setCreatedBefore(createdBefore);
        List<String> marketplaceId = new ArrayList<String>();
        marketplaceId.add("ATVPDKIKX0DER");
        marketplaceId.add("A2EUQ1WTGCTBG2");
        marketplaceId.add("A1AM78C64UM0Y8");
        request.setMarketplaceId(marketplaceId);
        Integer maxResultsPerPage = 100;
        request.setMaxResultsPerPage(maxResultsPerPage);
        
        long timeFrom;
        long timeTo;
        long tDiffMill;
        Timestamp t;
        try {
        	timeFrom = System.currentTimeMillis();
        	t = new Timestamp(timeFrom);
        	File fi = new File("Orders.csv");
            FileWriter sc = new FileWriter(fi);
            String nextToken = null;
            ListOrdersResponse response = client.listOrders(request);
            ResponseOrderHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            ListOrdersByNextTokenResponse resp = null;
            ListOrdersByNextTokenRequest req = null;
            while(true){
	            
	            System.out.println("Response:");
	            System.out.println("RequestId: "+rhmd.getRequestId());
	            System.out.println("Timestamp: "+rhmd.getTimestamp());
	            
	            ListOrdersResult lsp1;
	            ListOrdersByNextTokenResult lsp2;
	            List<Orders> ord;
	            
	            if(resp == null && req == null && nextToken == null){
	            	lsp1 = response.getListOrdersResult();
	            	ord = lsp1.getOrders();
	            	nextToken = lsp1.getNextToken();
	            }
	            else{
	            	lsp2 = resp.getListOrdersByNextTokenResult();
	            	ord = lsp2.getOrders();
	            	nextToken = lsp2.getNextToken();
	            }
	            
	            for(Orders i : ord){
	    			if(i.getShippingAddress() != null){
	    				i.setAddressLine1(i.getShippingAddress().getAddressLine1());
	    				i.setAddressLine2(i.getShippingAddress().getAddressLine2());
	    				i.setAddressLine3(i.getShippingAddress().getAddressLine3());
	    				i.setAddressType(i.getShippingAddress().getAddressType());
	    				i.setName(i.getShippingAddress().getName());
	    				i.setCity(i.getShippingAddress().getCity());
	    				i.setCountryCode(i.getShippingAddress().getCountryCode());
	    				i.setCounty(i.getShippingAddress().getCounty());
	    				i.setDistrict(i.getShippingAddress().getDistrict());
	    				i.setStateOrRegion(i.getShippingAddress().getStateOrRegion());
	    				i.setPostalCode(i.getShippingAddress().getPostalCode());
	    				i.setPhone(i.getShippingAddress().getPhone());
	    			}
	    			
	    			if(i.getEarliestDeliveryDate() != null)
	    				i.setEarliestDelivery(new Timestamp((i.getEarliestDeliveryDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getLatestDeliveryDate() != null)
	    				i.setLatestDelivery(new Timestamp((i.getLatestDeliveryDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getLatestShipDate() != null)
	    				i.setLatestShipment(new Timestamp((i.getLatestShipDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getLastUpdateDate() != null)
	    				i.setLastUpdated(new Timestamp((i.getLastUpdateDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getEarliestShipDate() != null)
	    				i.setEarliestShipment(new Timestamp((i.getEarliestShipDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getPurchaseDate() != null)
	    				i.setPurchaseDateConverted(new Timestamp((i.getPurchaseDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			
	    			if(i.getOrderTotal() != null){
	    				i.setOrderCost(i.getOrderTotal().getAmount());
	    				i.setOrderCurrency(i.getOrderTotal().getCurrencyCode());
	    			}
	    			
	    			if(i.getBuyerTaxInfo() != null)
	    				i.setBuyerTIString(i.getBuyerTaxInfo().toString());
	    			
	    			if(i.getPaymentMethodDetails() != null)
	    				i.setPaymentMethodDetail(i.getPaymentMethodDetails().toString());
	    			
	    			for(PaymentExecutionDetailItem pid : i.getPaymentExecutionDetail()){
	    				if(pid != null)
	    						pid.setCost(pid.getPayment().toString());
	    			}
	    			//}
	    			List<OrderItem> oi = amwsi.fetchOrderItemService(status, sellerId, mwsAuthToken, i.getAmazonOrderId());
	    			for(OrderItem it : oi){
	    				it.setOrd(i);
	    				it.setItemCount(it.getProductInfo().getNumberOfItems());
	    			}
	    			
	    			i.setOrd_items(oi);
	    			o_items.addAll(oi);
	    			System.out.println("local time converted: " + i.getLastUpdated().toLocalDateTime());
	    			t = i.getPurchaseDateConverted();
	    			
	    		}
	            if(status.equalsIgnoreCase("startup"))						sDao.startUpOrderUpdate(ord);
	            else														dao.saveOrders(ord);
	            
	            timeTo = System.currentTimeMillis();
            	tDiffMill = timeTo - timeFrom;
            	
            	
            	double avg = (globalMeanDiff.isEmpty())?tDiffMill+0.00:globalMeanDiff.stream().mapToDouble(a -> a).average().getAsDouble();
            	double newDiff = tDiffMill+0.00-avg;
            	if(globalMeanDiff.size() > 10) {
            		globalMeanDiff.remove(0);
            	}
            	globalMeanDiff.add(tDiffMill+0.00);
            	if(status.equalsIgnoreCase("startup_update") || status.equalsIgnoreCase("startup"))
            		sDao.logCurrentLogger("Saving Order fetched" , newDiff, count, t, tDiffMill);
            	
            	uMin = (long) ((standardMin)+ Math.max(0,(6*uMin/6*(newDiff/(avg)))));
            	Thread.sleep(uMin);
	           
	            if(nextToken == null)		break;
	            
	            req = new ListOrdersByNextTokenRequest();
	            
	            req.setSellerId(request.getSellerId());
	            
	            req.setMWSAuthToken(request.getMWSAuthToken());
	            
	            
	            req.setNextToken(nextToken);
	            timeFrom = System.currentTimeMillis();
            	t = new Timestamp(timeFrom);
	            resp = client.listOrdersByNextToken(req);
	            
	            rhmd = resp.getResponseHeaderMetadata();
	            retOrd.addAll(ord);
	            
	            
            }
            
            sc.close();
            return retOrd;
        } catch (MarketplaceWebServiceOrdersException ex) {
            System.out.println("Service Exception:");
            ResponseOrderHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if(rhmd != null) {
                System.out.println("RequestId: "+rhmd.getRequestId());
                System.out.println("Timestamp: "+rhmd.getTimestamp());
            }
            System.out.println("Message: "+ex.getMessage());
            System.out.println("StatusCode: "+ex.getStatusCode());
            System.out.println("ErrorCode: "+ex.getErrorCode());
            System.out.println("ErrorType: "+ex.getErrorType());
            throw ex;
        } catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
    }
}
