package com.g128.amazonservice.mws.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.g128.amazonservice.mws.client.MarketplaceWebService;
import com.g128.amazonservice.mws.client.MarketplaceWebServiceClient;
import com.g128.amazonservice.mws.client.MarketplaceWebServiceConfig;
import com.g128.amazonservice.mws.client.MarketplaceWebServiceException;
import com.g128.model.Error;
import com.g128.model.GetReportRequest;
import com.g128.model.GetReportResponse;
import com.g128.model.Orders;
import com.g128.model.ResponseMetadata;

public class GetReport {
    /**
     * Get Report  request sample
     * The GetReport operation returns the contents of a report. Reports can potentially be
     * very large (>100MB) which is why we only return one report at a time, and in a
     * streaming fashion.
     *   
     * @param service instance of MarketplaceWebService service
     * @param request Action to invoke
     */
	private static String accessKeyId;
	private static String secretAccessKey;
	private static String merchantId;
	private static String sellerDevAuthToken;
	
	public static void setCredential(String kid, String akey, String mid, String auth) {
		accessKeyId = kid;
		secretAccessKey = akey;
		merchantId = mid;
		sellerDevAuthToken = auth;
	}
	
    public static String invokeGetReport(String reportId) {
    	
    	/************************************************************************
         * Access Key ID and Secret Access Key ID, obtained from:
         * http://aws.amazon.com
         ***********************************************************************/

        final String appName = "Company_name";
        final String appVersion = "1.0";
        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();

        /************************************************************************
         * Uncomment to set the appropriate MWS endpoint.
         ************************************************************************/
        // US
        config.setServiceURL("https://mws.amazonservices.com/");
        // UK
        // config.setServiceURL("https://mws.amazonservices.co.uk/");
        // Germany
        // config.setServiceURL("https://mws.amazonservices.de/");
        // France
        // config.setServiceURL("https://mws.amazonservices.fr/");
        // Italy
        // config.setServiceURL("https://mws.amazonservices.it/");
        // Japan
        // config.setServiceURL("https://mws.amazonservices.jp/");
        // China
        // config.setServiceURL("https://mws.amazonservices.com.cn/");
        // Canada
        // config.setServiceURL("https://mws.amazonservices.ca/");
        // India
        // config.setServiceURL("https://mws.amazonservices.in/");

        /************************************************************************
         * You can also try advanced configuration options. Available options are:
         *
         *  - Signature Version
         *  - Proxy Host and Proxy Port
         *  - User Agent String to be sent to Marketplace Web Service
         *
         ***********************************************************************/

        /************************************************************************
         * Instantiate Http Client Implementation of Marketplace Web Service        
         ***********************************************************************/

        MarketplaceWebService service = new MarketplaceWebServiceClient(
                accessKeyId, secretAccessKey, appName, appVersion, config);

        /************************************************************************
         * Setup request parameters and uncomment invoke to try out 
         * sample for Get Report 
         ***********************************************************************/

        /************************************************************************
         * Marketplace and Merchant IDs are required parameters for all 
         * Marketplace Web Service calls.
         ***********************************************************************/

        GetReportRequest request = new GetReportRequest();
        request.setMerchant( merchantId );
        request.setMWSAuthToken(sellerDevAuthToken);
        
        request.setReportId(reportId);
        

        // Note that depending on the type of report being downloaded, a report can reach 
        // sizes greater than 1GB. For this reason we recommend that you _always_ program to
        // MWS in a streaming fashion. Otherwise, as your business grows you may silently reach
        // the in-memory size limit and have to re-work your solution.
        //
        OutputStream report;
        
//					try {
//						report = new FileOutputStream( "report.csv" );
//						request.setReportOutputStream( report );
//					} catch (FileNotFoundException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					
		report = new ByteArrayOutputStream();
		request.setReportOutputStream(report);
        try {
        	
            
        	GetReportResponse response = service.getReport(request);


//            System.out.println ("GetReport Action Response");
//            System.out.println ("=============================================================================");
//            System.out.println ();
//
//            System.out.print("    GetReportResponse");
//            System.out.println();
//            System.out.print("    GetReportResult");
//            System.out.println();
//            System.out.print("            MD5Checksum");
//            System.out.println();
//            System.out.print("                " + response.getGetReportResult().getMD5Checksum());
//            System.out.println();
            if (response.isSetResponseMetadata()) {
//                System.out.print("        ResponseMetadata");
//                System.out.println();
                ResponseMetadata  responseMetadata = response.getResponseMetadata();
//                if (responseMetadata.isSetRequestId()) {
//                    System.out.print("            RequestId");
//                    System.out.println();
//                    System.out.print("                " + responseMetadata.getRequestId());
//                    System.out.println();
//                }
            } 
//            System.out.println();
//
//            System.out.println("Report");
//            System.out.println ("=============================================================================");
//            System.out.println();
//            System.out.println( request.getReportOutputStream().toString() );
//            System.out.println();
//
//            System.out.println(response.getResponseHeaderMetadata());
//            System.out.println();


        } catch (MarketplaceWebServiceException ex) {

            System.out.println("Caught Exception: " + ex.getMessage());
            System.out.println("Response Status Code: " + ex.getStatusCode());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("Error Type: " + ex.getErrorType());
            System.out.println("Request ID: " + ex.getRequestId());
            System.out.print("XML: " + ex.getXML());
            System.out.println("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
        }
        
        String str = request.getReportOutputStream().toString();
//        System.out.println(str);
//        String [] strArr =  str.split("\n");
//        System.out.println(str);
//        List<List<String>> arl = new ArrayList<>();
//        
//        for(int i = 0; i < strArr.length; i++) {
//        	List<String> arHold = Arrays.asList(strArr[i].split("\\s+"));
//        	arl.add(arHold);
//        }
//        
//        List<String> label = arl.get(0);
//        Field fields[] = Error.class.getDeclaredFields();
//        //for(int i = 0; i < fields.length; i++)  					{	System.out.println("field: " + fields[i].getName());}
//        for(String sr : label) {
//        	
//        }
		return str;
    }
}
