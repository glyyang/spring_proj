package com.g128.amazonservice.mws.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.g128.amazonservice.mws.client.MarketplaceWebService;
import com.g128.amazonservice.mws.client.MarketplaceWebServiceClient;
import com.g128.amazonservice.mws.client.MarketplaceWebServiceConfig;
import com.g128.model.GetReportRequest;

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
        config.setServiceURL("https://mws.amazonservices.com/");

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
        

        OutputStream report;
        
					
		report = new ByteArrayOutputStream();
		request.setReportOutputStream(report);
        
        String str = request.getReportOutputStream().toString();
		return str;
    }
}
