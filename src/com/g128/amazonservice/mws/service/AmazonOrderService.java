package com.g128.amazonservice.mws.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.g128.amazonservice.mws.client.MwsUtl;
import com.g128.amazonservice.mws.order.*;
import com.g128.dao.Dao;
import com.g128.dao.StartUpDao;
import com.g128.model.*;


//@ComponentScan("com.g128.dao")
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
		// TODO Auto-generated constructor stub
    	
    	//dao = new Dao();
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
//    	System.out.println("time zone from2: " + updatedAfter.getTimezone());
//    	System.out.println("time zone to2: " + updatedBefore.getTimezone());
		List<Orders> retOrd = new ArrayList<Orders>();
        // Create a request.
        ListOrdersRequest request = new ListOrdersRequest();
        
        request.setSellerId(sellerId);
        
        request.setMWSAuthToken(mwsAuthToken);
        //XMLGregorianCalendar createdAfter = MwsUtl.getDTF().newXMLGregorianCalendar();
        
//        GregorianCalendar clda = new GregorianCalendar();
//        clda.setTimeZone(TimeZone.getTimeZone("PDT"));
//        clda.set(2018, 04, 21, 0+7, 0, 0);
//        createdAfter.clear();
//        createdAfter = DatatypeFactory.newInstance().newXMLGregorianCalendar(clda);
//        System.out.println(createdAfter.toString());
        //createdAfter.setYear(2016);
        //createdAfter.setMonth(5);
        //createdAfter.setDay(22);
        //createdAfter.setHour(0+7);
        //createdAfter.setMinute(0);
        //createdAfter.setSecond(0);
        
        
        //request.setCreatedAfter(createdAfter);
        //XMLGregorianCalendar createdBefore = MwsUtl.getDTF().newXMLGregorianCalendar();
        
//        GregorianCalendar cldb = new GregorianCalendar();
//        cldb.setTimeZone(TimeZone.getTimeZone("PDT"));
//        cldb.set(2018, 04, 22, 0+7, 0, 0);
//        
//        createdBefore.clear();
//        createdBefore = DatatypeFactory.newInstance().newXMLGregorianCalendar(cldb);
//        System.out.println(createdBefore.toString());
//        createdBefore.setYear(2016);
//        createdBefore.setMonth(5);
//        createdBefore.setDay(23);
//        createdBefore.setHour(0+7);
//        createdBefore.setMinute(0);
//        createdBefore.setSecond(0);
        
        
        
        //request.setCreatedBefore(createdBefore);
        //request.setLastUpdatedAfter(lastUpdatedAfter);
//        XMLGregorianCalendar lastUpdatedAfter = MwsUtl.getDTF().newXMLGregorianCalendar();
//        request.setLastUpdatedAfter(lastUpdatedAfter);
//        XMLGregorianCalendar lastUpdatedBefore = MwsUtl.getDTF().newXMLGregorianCalendar();
//        request.setLastUpdatedBefore(lastUpdatedBefore);
//        List<String> orderStatus = new ArrayList<String>();
//        request.setOrderStatus(orderStatus);
        List<String> marketplaceId = new ArrayList<String>();
        marketplaceId.add("ATVPDKIKX0DER");
        marketplaceId.add("A2EUQ1WTGCTBG2");
        marketplaceId.add("A1AM78C64UM0Y8");
        request.setMarketplaceId(marketplaceId);
//        List<String> fulfillmentChannel = new ArrayList<String>();
//        request.setFulfillmentChannel(fulfillmentChannel);
//        List<String> paymentMethod = new ArrayList<String>();
//        request.setPaymentMethod(paymentMethod);
//        String buyerEmail = "example";
//        request.setBuyerEmail(buyerEmail);
//        String sellerOrderId = "example";
//        request.setSellerOrderId(sellerOrderId);
        Integer maxResultsPerPage = 100;
        request.setMaxResultsPerPage(maxResultsPerPage);
//        List<String> tfmShipmentStatus = new ArrayList<String>();
//        request.setTFMShipmentStatus(tfmShipmentStatus);
        request.setLastUpdatedAfter(updatedAfter);
        request.setLastUpdatedBefore(updatedBefore);
        // Make the call.
        
        long timeFrom;
        long timeTo;
        long tDiffMill;
        Timestamp t;
        try {
        	timeFrom = System.currentTimeMillis();
        	t = new Timestamp(updatedAfter.toGregorianCalendar().getTimeInMillis());
            // Call the service.
        	File fi = new File("Orders.csv");
            FileWriter sc = new FileWriter(fi);
            String nextToken = null;
            ListOrdersResponse response = client.listOrders(request);
            ResponseOrderHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            String responseXml = response.toXML();
            ListOrdersByNextTokenResponse resp = null;
            ListOrdersByNextTokenRequest req = null;
            while(true){
	            
	            // We recommend logging every the request id and timestamp of every call.
	            System.out.println("Response:");
	            System.out.println("RequestId: "+rhmd.getRequestId());
	            System.out.println("Timestamp: "+rhmd.getTimestamp());
	            
	            
	            
	            
	            //System.out.println(responseXml);
	            
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
	            //retOrd.addAll(ord);
	            //retOrd = ord;
	            for(Orders i : ord){
	    			//try{
	            	
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
//	    				System.out.println(i.getLatestDeliveryDate().toGregorianCalendar());
//	    				System.out.println(i.getEarliestDeliveryDate().toGregorianCalendar());
	    			
	    			if(i.getLatestDeliveryDate() != null)
	    				i.setLatestDelivery(new Timestamp((i.getLatestDeliveryDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getLatestShipDate() != null)
	    				i.setLatestShipment(new Timestamp((i.getLatestShipDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getLastUpdateDate() != null)
	    				i.setLastUpdated(new Timestamp((i.getLastUpdateDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getEarliestShipDate() != null)
	    				i.setEarliestShipment(new Timestamp((i.getEarliestShipDate().toGregorianCalendar()).getTimeInMillis()));
	    				//System.out.println(i.getOrderTotal().toString());
	    			
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
	    			//}catch(Exception e){
	    			//	continue;
	    			//}
	    			List<OrderItem> oi = amwsi.fetchOrderItemService(status, sellerId, mwsAuthToken, i.getAmazonOrderId());
	    			for(OrderItem it : oi){
	    				it.setOrd(i);
	    				it.setItemCount(it.getProductInfo().getNumberOfItems());
	    			}
	    			
	    			i.setOrd_items(oi);
	    			o_items.addAll(oi);
	    			//Thread.sleep(11000);
//	    			System.out.println("timezone3: " + i.getLastUpdateDate().getTimezone());
//	    			System.out.println("XML time: " + i.getLastUpdateDate().toGregorianCalendar().getTime().getTimezoneOffset());
//	    			
//	    			System.out.println("local time unconvereted: " + i.getLastUpdated());
//	    			System.out.println("local time converted: " + i.getLastUpdated().toLocalDateTime());
	    			t = i.getPurchaseDateConverted();
	    			
	    		}
	            if(status.equalsIgnoreCase("startup_update"))						sDao.startUpOrderUpdate(ord);
	            else																dao.saveOrders(ord);
	            
	            //System.out.println(lsp.toXML());
	             
//	            System.out.println();
//	            
//	            sc.append(String.valueOf("id"));
//	    		sc.append(String.valueOf(","));
//	    		
//	    		sc.append(String.valueOf("Items amount shipped"));
//	    		sc.append(String.valueOf(","));
//	    		
//	    		sc.append(String.valueOf("Time Created"));
//	    		sc.append(String.valueOf(","));
//	    		
//	    		sc.append(String.valueOf("Status"));
//	    		sc.append(String.valueOf(","));
//	    		
//	//    		String method = (o.getPaymentMethod() == null?" ":o.getPaymentMethod());
//	    		sc.append(String.valueOf("method"));
//	    		sc.append(String.valueOf(","));
//	    		
//	    		sc.append(String.valueOf("Fulfill"));
//	    		sc.append(String.valueOf(","));
//	    		
//	    		sc.append(String.valueOf("Buyer id"));
//	    		sc.append(String.valueOf(","));
//	    		
//	//    		String amount = (o.getOrderStatus().equalsIgnoreCase("Canceled")?"0":(o.getOrderTotal().getAmount() + o.getOrderTotal().getCurrencyCode()));
//	    		sc.append(String.valueOf("amount"));
//	    		sc.append(String.valueOf("\n"));
//	    		
//	    		
//	    		sc.flush();
//	            for(Orders o : ord){
//	            	//System.out.println(o);
//	            	if(o != null){
//	            		//sc.write("Order Id: " + (o.getAmazonOrderId() == null?" ":o.getAmazonOrderId()) + "; Order payment method: " + (o.getPaymentMethod() == null?" ":o.getPaymentMethod()) + "; Order value: " + (o.getOrderStatus().equalsIgnoreCase("Canceled")?"0":(o.getOrderTotal().getAmount() + o.getOrderTotal().getCurrencyCode())) + "\n");
//	            		String id = (o.getAmazonOrderId() == null?" ":o.getAmazonOrderId());
//	            		sc.append(String.valueOf(id));
//	            		sc.append(String.valueOf(","));
//	            		
//	            		String item = ""+(o.getNumberOfItemsShipped() == null?0:(o.getNumberOfItemsShipped()+o.getNumberOfItemsUnshipped()));
//	            		sc.append(String.valueOf(item));
//	            		sc.append(String.valueOf(","));
//	            		
//	            		String time = ""+(o.getPurchaseDate() == null?"null":o.getPurchaseDate().toString());
//	            		sc.append(String.valueOf(time));
//	            		sc.append(String.valueOf(","));
//	            		
//	            		String status = ""+(o.getOrderStatus() == null?"null":o.getOrderStatus());
//	            		sc.append(String.valueOf(status));
//	            		sc.append(String.valueOf(","));
//	            		
//	            		String method = (o.getPaymentMethod() == null?" ":o.getPaymentMethod());
//	            		sc.append(String.valueOf(method));
//	            		sc.append(String.valueOf(","));
//	            		
//	            		String ff = (o.getFulfillmentChannel() == null?" ":o.getFulfillmentChannel());
//	            		sc.append(String.valueOf(ff));
//	            		sc.append(String.valueOf(","));
//	            		
//	            		String bid = (o.getBuyerEmail() == null?" ":o.getBuyerEmail());
//	            		sc.append(String.valueOf(bid));
//	            		sc.append(String.valueOf(","));
//	            		
//	            		String amount = ((status.equalsIgnoreCase("Canceled") || status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("null"))?"0":(o.getOrderTotal().getAmount()));
//	            		sc.append(String.valueOf(amount));
//	            		sc.append(String.valueOf("\n"));
//	            		sc.flush();
//	            	}
//	            }
//	            String [] ntok = responseXml.split("<NextToken>");
//	            
//	            
//	            
//	            nextToken = ntok[1].substring(0, ntok[1].length()-1);
//	            
//	            ntok = responseXml.split("</NextToken>");
//	            nextToken = ntok[0];
//	            System.out.println("next token 1: " + nextToken);
	            timeTo = System.currentTimeMillis();
            	tDiffMill = timeTo - timeFrom;
            	
            	
            	//OptionalDouble average = (globalMeanDiff.isEmpty())?null:;
            	double avg = (globalMeanDiff.isEmpty())?tDiffMill+0.00:globalMeanDiff.stream().mapToDouble(a -> a).average().getAsDouble();
            	double newDiff = tDiffMill+0.00-avg;
            	if(globalMeanDiff.size() > 10) {
            		//if(!globalMeanDiff.isEmpty())
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
	            
	            
	            //System.out.println(responseXml);
	            req.setNextToken(nextToken);
	            timeFrom = System.currentTimeMillis();
            	//t = new Timestamp(timeFrom);
	            resp = client.listOrdersByNextToken(req);
	            
	            rhmd = resp.getResponseHeaderMetadata();
	            // We recommend logging every the request id and timestamp of every call.
	           // responseXml = resp.toXML();
	            
	            //System.out.println(responseXml);
	            //System.out.println("next token 2: " + nextToken);
	            
	            //Thread.sleep(100);
	            retOrd.addAll(ord);
	            
	            
            }
            
            sc.close();
            return retOrd;
        } catch (MarketplaceWebServiceOrdersException ex) {
            // Exception properties are important for diagnostics.
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
    	
    }
    
	public List<Orders> invokeListOrders(Dao dao, String status, String sellerId, String mwsAuthToken, XMLGregorianCalendar createdAfter, XMLGregorianCalendar createdBefore) throws IOException, InterruptedException {
		//System.out.println("Dao: " + (dao==null));
		this.dao = dao;
		MarketplaceWebServiceOrdersClient client = MarketplaceWebServiceOrdersAuthConfig.getClient();

		List<Orders> retOrd = new ArrayList<Orders>();
        // Create a request.
        ListOrdersRequest request = new ListOrdersRequest();
        
        request.setSellerId(sellerId);
        
        request.setMWSAuthToken(mwsAuthToken);
        //XMLGregorianCalendar createdAfter = MwsUtl.getDTF().newXMLGregorianCalendar();
        
//        GregorianCalendar clda = new GregorianCalendar();
//        clda.setTimeZone(TimeZone.getTimeZone("PDT"));
//        clda.set(2018, 04, 21, 0+7, 0, 0);
//        createdAfter.clear();
//        createdAfter = DatatypeFactory.newInstance().newXMLGregorianCalendar(clda);
//        System.out.println(createdAfter.toString());
        //createdAfter.setYear(2016);
        //createdAfter.setMonth(5);
        //createdAfter.setDay(22);
        //createdAfter.setHour(0+7);
        //createdAfter.setMinute(0);
        //createdAfter.setSecond(0);
        
        
        request.setCreatedAfter(createdAfter);
        //XMLGregorianCalendar createdBefore = MwsUtl.getDTF().newXMLGregorianCalendar();
        
//        GregorianCalendar cldb = new GregorianCalendar();
//        cldb.setTimeZone(TimeZone.getTimeZone("PDT"));
//        cldb.set(2018, 04, 22, 0+7, 0, 0);
//        
//        createdBefore.clear();
//        createdBefore = DatatypeFactory.newInstance().newXMLGregorianCalendar(cldb);
//        System.out.println(createdBefore.toString());
//        createdBefore.setYear(2016);
//        createdBefore.setMonth(5);
//        createdBefore.setDay(23);
//        createdBefore.setHour(0+7);
//        createdBefore.setMinute(0);
//        createdBefore.setSecond(0);
        
        
        
        request.setCreatedBefore(createdBefore);
        //request.setLastUpdatedAfter(lastUpdatedAfter);
//        XMLGregorianCalendar lastUpdatedAfter = MwsUtl.getDTF().newXMLGregorianCalendar();
//        request.setLastUpdatedAfter(lastUpdatedAfter);
//        XMLGregorianCalendar lastUpdatedBefore = MwsUtl.getDTF().newXMLGregorianCalendar();
//        request.setLastUpdatedBefore(lastUpdatedBefore);
//        List<String> orderStatus = new ArrayList<String>();
//        request.setOrderStatus(orderStatus);
        List<String> marketplaceId = new ArrayList<String>();
        marketplaceId.add("ATVPDKIKX0DER");
        marketplaceId.add("A2EUQ1WTGCTBG2");
        marketplaceId.add("A1AM78C64UM0Y8");
        request.setMarketplaceId(marketplaceId);
//        List<String> fulfillmentChannel = new ArrayList<String>();
//        request.setFulfillmentChannel(fulfillmentChannel);
//        List<String> paymentMethod = new ArrayList<String>();
//        request.setPaymentMethod(paymentMethod);
//        String buyerEmail = "example";
//        request.setBuyerEmail(buyerEmail);
//        String sellerOrderId = "example";
//        request.setSellerOrderId(sellerOrderId);
        Integer maxResultsPerPage = 100;
        request.setMaxResultsPerPage(maxResultsPerPage);
//        List<String> tfmShipmentStatus = new ArrayList<String>();
//        request.setTFMShipmentStatus(tfmShipmentStatus);

        // Make the call.
        
        long timeFrom;
        long timeTo;
        long tDiffMill;
        Timestamp t;
        try {
        	timeFrom = System.currentTimeMillis();
        	t = new Timestamp(timeFrom);
            // Call the service.
        	File fi = new File("Orders.csv");
            FileWriter sc = new FileWriter(fi);
            String nextToken = null;
            ListOrdersResponse response = client.listOrders(request);
            ResponseOrderHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            String responseXml = response.toXML();
            ListOrdersByNextTokenResponse resp = null;
            ListOrdersByNextTokenRequest req = null;
            while(true){
	            
	            // We recommend logging every the request id and timestamp of every call.
	            System.out.println("Response:");
	            System.out.println("RequestId: "+rhmd.getRequestId());
	            System.out.println("Timestamp: "+rhmd.getTimestamp());
	            
	            
	            
	            
	            //System.out.println(responseXml);
	            
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
	            
	            //retOrd = ord;
	            for(Orders i : ord){
	    			//try{
	            	
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
//	    				System.out.println(i.getLatestDeliveryDate().toGregorianCalendar());
//	    				System.out.println(i.getEarliestDeliveryDate().toGregorianCalendar());
	    			
	    			if(i.getLatestDeliveryDate() != null)
	    				i.setLatestDelivery(new Timestamp((i.getLatestDeliveryDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getLatestShipDate() != null)
	    				i.setLatestShipment(new Timestamp((i.getLatestShipDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getLastUpdateDate() != null)
	    				i.setLastUpdated(new Timestamp((i.getLastUpdateDate().toGregorianCalendar()).getTimeInMillis()));
	    			
	    			if(i.getEarliestShipDate() != null)
	    				i.setEarliestShipment(new Timestamp((i.getEarliestShipDate().toGregorianCalendar()).getTimeInMillis()));
	    				//System.out.println(i.getOrderTotal().toString());
	    			
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
	    			//}catch(Exception e){
	    			//	continue;
	    			//}
	    			List<OrderItem> oi = amwsi.fetchOrderItemService(status, sellerId, mwsAuthToken, i.getAmazonOrderId());
	    			for(OrderItem it : oi){
	    				it.setOrd(i);
	    				it.setItemCount(it.getProductInfo().getNumberOfItems());
	    			}
	    			
	    			i.setOrd_items(oi);
	    			o_items.addAll(oi);
	    			//Thread.sleep(11000);
	    			System.out.println("local time converted: " + i.getLastUpdated().toLocalDateTime());
	    			t = i.getPurchaseDateConverted();
	    			
	    		}
	            if(status.equalsIgnoreCase("startup"))						sDao.startUpOrderUpdate(ord);
	            else														dao.saveOrders(ord);
	            
	            //System.out.println(lsp.toXML());
	             
//	            System.out.println();
//	            
//	            sc.append(String.valueOf("id"));
//	    		sc.append(String.valueOf(","));
//	    		
//	    		sc.append(String.valueOf("Items amount shipped"));
//	    		sc.append(String.valueOf(","));
//	    		
//	    		sc.append(String.valueOf("Time Created"));
//	    		sc.append(String.valueOf(","));
//	    		
//	    		sc.append(String.valueOf("Status"));
//	    		sc.append(String.valueOf(","));
//	    		
//	//    		String method = (o.getPaymentMethod() == null?" ":o.getPaymentMethod());
//	    		sc.append(String.valueOf("method"));
//	    		sc.append(String.valueOf(","));
//	    		
//	    		sc.append(String.valueOf("Fulfill"));
//	    		sc.append(String.valueOf(","));
//	    		
//	    		sc.append(String.valueOf("Buyer id"));
//	    		sc.append(String.valueOf(","));
//	    		
//	//    		String amount = (o.getOrderStatus().equalsIgnoreCase("Canceled")?"0":(o.getOrderTotal().getAmount() + o.getOrderTotal().getCurrencyCode()));
//	    		sc.append(String.valueOf("amount"));
//	    		sc.append(String.valueOf("\n"));
//	    		
//	    		
//	    		sc.flush();
//	            for(Orders o : ord){
//	            	//System.out.println(o);
//	            	if(o != null){
//	            		//sc.write("Order Id: " + (o.getAmazonOrderId() == null?" ":o.getAmazonOrderId()) + "; Order payment method: " + (o.getPaymentMethod() == null?" ":o.getPaymentMethod()) + "; Order value: " + (o.getOrderStatus().equalsIgnoreCase("Canceled")?"0":(o.getOrderTotal().getAmount() + o.getOrderTotal().getCurrencyCode())) + "\n");
//	            		String id = (o.getAmazonOrderId() == null?" ":o.getAmazonOrderId());
//	            		sc.append(String.valueOf(id));
//	            		sc.append(String.valueOf(","));
//	            		
//	            		String item = ""+(o.getNumberOfItemsShipped() == null?0:(o.getNumberOfItemsShipped()+o.getNumberOfItemsUnshipped()));
//	            		sc.append(String.valueOf(item));
//	            		sc.append(String.valueOf(","));
//	            		
//	            		String time = ""+(o.getPurchaseDate() == null?"null":o.getPurchaseDate().toString());
//	            		sc.append(String.valueOf(time));
//	            		sc.append(String.valueOf(","));
//	            		
//	            		String status = ""+(o.getOrderStatus() == null?"null":o.getOrderStatus());
//	            		sc.append(String.valueOf(status));
//	            		sc.append(String.valueOf(","));
//	            		
//	            		String method = (o.getPaymentMethod() == null?" ":o.getPaymentMethod());
//	            		sc.append(String.valueOf(method));
//	            		sc.append(String.valueOf(","));
//	            		
//	            		String ff = (o.getFulfillmentChannel() == null?" ":o.getFulfillmentChannel());
//	            		sc.append(String.valueOf(ff));
//	            		sc.append(String.valueOf(","));
//	            		
//	            		String bid = (o.getBuyerEmail() == null?" ":o.getBuyerEmail());
//	            		sc.append(String.valueOf(bid));
//	            		sc.append(String.valueOf(","));
//	            		
//	            		String amount = ((status.equalsIgnoreCase("Canceled") || status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("null"))?"0":(o.getOrderTotal().getAmount()));
//	            		sc.append(String.valueOf(amount));
//	            		sc.append(String.valueOf("\n"));
//	            		sc.flush();
//	            	}
//	            }
//	            String [] ntok = responseXml.split("<NextToken>");
//	            
//	            
//	            
//	            nextToken = ntok[1].substring(0, ntok[1].length()-1);
//	            
//	            ntok = responseXml.split("</NextToken>");
//	            nextToken = ntok[0];
//	            System.out.println("next token 1: " + nextToken);
	            timeTo = System.currentTimeMillis();
            	tDiffMill = timeTo - timeFrom;
            	
            	
            	//OptionalDouble average = (globalMeanDiff.isEmpty())?null:;
            	double avg = (globalMeanDiff.isEmpty())?tDiffMill+0.00:globalMeanDiff.stream().mapToDouble(a -> a).average().getAsDouble();
            	double newDiff = tDiffMill+0.00-avg;
            	if(globalMeanDiff.size() > 10) {
            		//if(!globalMeanDiff.isEmpty())
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
	            
	            
	            //System.out.println(responseXml);
	            req.setNextToken(nextToken);
	            timeFrom = System.currentTimeMillis();
            	t = new Timestamp(timeFrom);
	            resp = client.listOrdersByNextToken(req);
	            
	            rhmd = resp.getResponseHeaderMetadata();
	            // We recommend logging every the request id and timestamp of every call.
	           // responseXml = resp.toXML();
	            
	            //System.out.println(responseXml);
	            //System.out.println("next token 2: " + nextToken);
	            
	            //Thread.sleep(100);
	            retOrd.addAll(ord);
	            
	            
            }
            
            sc.close();
            return retOrd;
        } catch (MarketplaceWebServiceOrdersException ex) {
            // Exception properties are important for diagnostics.
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
    }
}
