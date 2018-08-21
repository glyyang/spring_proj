package com.g128.amazonservice.mws.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import com.g128.amazonservice.mws.order.MarketplaceWebServiceOrders;
import com.g128.amazonservice.mws.order.MarketplaceWebServiceOrdersClient;
import com.g128.amazonservice.mws.order.MarketplaceWebServiceOrdersException;
import com.g128.dao.StartUpDao;
import com.g128.model.ListOrderItemsByNextTokenRequest;
import com.g128.model.ListOrderItemsByNextTokenResponse;
import com.g128.model.ListOrderItemsRequest;
import com.g128.model.ListOrderItemsResponse;
import com.g128.model.OrderItem;
import com.g128.model.ResponseOrderHeaderMetadata;

public class AmazonOrderItemService {
	/**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     *
     * @return The response.
     */
    
    private StartUpDao sDao;
    private long count;
    private static List<Double> globalMeanDiff = new ArrayList<Double>();
    private static long uMin = 2000;
    private static long standardMin = 2000;
    
    public AmazonOrderItemService() {
    	sDao = new StartUpDao();
    	
    }
    
    public static void reset() {
    	standardMin = standardMin+100;
    	globalMeanDiff = new ArrayList<Double>();
    }
    
    public void setCount(long count)
    {
    	this.count=count;
    }
    public List<OrderItem> fetchOrderItemService(String stat, String sellerId, String mwsAuthToken, String order_id) {

        // Get a client connection.
        // Make sure you've set the variables in MarketplaceWebServiceOrdersSampleConfig.
    	
        MarketplaceWebServiceOrdersClient client = MarketplaceWebServiceOrdersAuthConfig.getClient();

        // Create a request.
        ListOrderItemsRequest request = new ListOrderItemsRequest();
        request.setSellerId(sellerId);
        request.setMWSAuthToken(mwsAuthToken);
        request.setAmazonOrderId(order_id);
        List<OrderItem> item_list = new ArrayList<>();
        long timeFrom;
        long timeTo;
        long tDiffMill;
        Timestamp t;
        try {
        	timeFrom = System.currentTimeMillis();
        	t = new Timestamp(timeFrom);
            // Call the service.
            ListOrderItemsResponse response = client.listOrderItems(request);
            
            ListOrderItemsByNextTokenRequest req = null;
            ListOrderItemsByNextTokenResponse resp = null;
            String nextTok = null;
            
            while(true){
            	if(req == null && resp == null && nextTok == null){
            		ResponseOrderHeaderMetadata rhmd = response.getResponseHeaderMetadata();
                    // We recommend logging every the request id and timestamp of every call.
                    System.out.println("Response:");
                    System.out.println("RequestId: "+rhmd.getRequestId());
                    System.out.println("Timestamp: "+rhmd.getTimestamp());
                    String responseXml = response.toXML();
                    System.out.println(responseXml);
            		nextTok = response.getListOrderItemsResult().getNextToken();
	            	item_list.addAll(response.getListOrderItemsResult().getOrderItems());
	                
	                
	                
            	}else{
            		ResponseOrderHeaderMetadata rhmd = resp.getResponseHeaderMetadata();
                    // We recommend logging every the request id and timestamp of every call.
                    System.out.println("Response:");
                    System.out.println("RequestId: "+rhmd.getRequestId());
                    System.out.println("Timestamp: "+rhmd.getTimestamp());
                    String responseXml = resp.toXML();
                    System.out.println(responseXml);
            		nextTok = resp.getListOrderItemsByNextTokenResult().getNextToken();
            		item_list.addAll(resp.getListOrderItemsByNextTokenResult().getOrderItems());
                	
                	
            	}
            	timeTo = System.currentTimeMillis();
            	tDiffMill = timeTo - timeFrom;
            	
            	
            	//OptionalDouble average = (globalMeanDiff.isEmpty())?null:;
            	double avg = (globalMeanDiff.isEmpty())?tDiffMill+0.00:globalMeanDiff.stream().mapToDouble(a -> a).average().getAsDouble();
            	double newDiff = tDiffMill+0.00-avg;
            	if(globalMeanDiff.size() > 10) {
            		
            		globalMeanDiff.remove(0);
            	}
            	globalMeanDiff.add(tDiffMill+0.00);
            	if(stat.equalsIgnoreCase("startup_update") || stat.equalsIgnoreCase("startup"))
            		sDao.logCurrentLogger("Saving OrderItems fetched" , newDiff, count, t, tDiffMill);
            	
            	uMin = (long) ((standardMin)+ Math.max(0, (30*uMin/30*(newDiff/(avg)))));
            	Thread.sleep(uMin);
            	
            	if(nextTok == null)		break;
            	req = new ListOrderItemsByNextTokenRequest();
            	req.setMWSAuthToken(mwsAuthToken);
            	req.setSellerId(sellerId);
            	req.setNextToken(nextTok);
            	timeFrom = System.currentTimeMillis();
            	t = new Timestamp(timeFrom);
            	resp = client.listOrderItemsByNextToken(req);
            	
            	
            	
            }
            
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
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return item_list;

    }
}
