package com.g128.amazonservice.mws.service;

import java.util.ArrayList;
import java.util.List;

import com.g128.amazonservice.mws.client.MarketplaceWebService;
import com.g128.amazonservice.mws.client.MarketplaceWebServiceClient;
import com.g128.amazonservice.mws.client.MarketplaceWebServiceConfig;
import com.g128.amazonservice.mws.client.MarketplaceWebServiceException;
import com.g128.model.GetReportRequestListRequest;
import com.g128.model.GetReportRequestListResponse;
import com.g128.model.GetReportRequestListResult;
import com.g128.model.ReportRequestInfo;
import com.g128.model.ResponseMetadata;
import com.g128.model.TypeList;

public class ListRequestReport {
	

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

    /**
     * Get Report Request List  request sample
     * returns a list of report requests ids and their associated metadata
     *   
     * @param service instance of MarketplaceWebService service
     * @param request Action to invoke
     */
    public static String invokeGetReportRequestList(String requestId) {
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
         * Uncomment to try out Mock Service that simulates Marketplace Web Service 
         * responses without calling Marketplace Web Service  service.
         *
         * Responses are loaded from local XML files. You can tweak XML files to
         * experiment with various outputs during development
         *
         * XML files available under com/amazonaws/mws/mock tree
         *
         ***********************************************************************/
        // MarketplaceWebService service = new MarketplaceWebServiceMock();

        /************************************************************************
         * Setup request parameters and uncomment invoke to try out 
         * sample for Get Report Request List 
         ***********************************************************************/

        /************************************************************************
         * Marketplace and Merchant IDs are required parameters for all 
         * Marketplace Web Service calls.
         ***********************************************************************/

        GetReportRequestListRequest request = new GetReportRequestListRequest();
        request.setMerchant( merchantId );
        request.setMWSAuthToken(sellerDevAuthToken);
        request.setMaxCount(100);
        TypeList ntpl = new TypeList();
        List<String> types = new ArrayList<>();
        types.add("_GET_ORDERS_DATA_");
        types.add("_GET_FLAT_FILE_ORDERS_DATA_");
        types.add("_GET_FLAT_FILE_ACTIONABLE_ORDER_DATA_");
        types.add("_GET_CONVERGED_FLAT_FILE_ORDER_REPORT_DATA_");
        types.add("_GET_XML_ALL_ORDERS_DATA_BY_ORDER_DATE_");
        types.add("_GET_FLAT_FILE_ALL_ORDERS_DATA_BY_ORDER_DATE_");
        
        ntpl.setType(types);
//        IdList idl = new IdList();
//        List<String> lstr = new ArrayList<String>();
//        lstr.add("bc110ca7-6525-408c-ace2-74ff00c06d6e");
//        idl.setId(lstr);
//        request.setReportRequestIdList(idl);
        request.setReportTypeList(ntpl);

        // @TODO: set request parameters here
        try {

            GetReportRequestListResponse response = service.getReportRequestList(request);


//            System.out.println ("GetReportRequestList Action Response");
//            System.out.println ("=============================================================================");
//            System.out.println ();
//
//            System.out.print("    GetReportRequestListResponse");
//            System.out.println();
            if (response.isSetGetReportRequestListResult()) {
//                System.out.print("        GetReportRequestListResult");
//                System.out.println();
                GetReportRequestListResult  getReportRequestListResult = response.getGetReportRequestListResult();
//                if (getReportRequestListResult.isSetNextToken()) {
//                    System.out.print("            NextToken");
//                    System.out.println();
//                    System.out.print("                " + getReportRequestListResult.getNextToken());
//                    System.out.println();
//                }
//                if (getReportRequestListResult.isSetHasNext()) {
//                    System.out.print("            HasNext");
//                    System.out.println();
//                    System.out.print("                " + getReportRequestListResult.isHasNext());
//                    System.out.println();
//                }
                java.util.List<ReportRequestInfo> reportRequestInfoList = getReportRequestListResult.getReportRequestInfoList();
                for (ReportRequestInfo reportRequestInfo : reportRequestInfoList) {
//                    System.out.print("            ReportRequestInfo");
//                    System.out.println();
//                    if (reportRequestInfo.isSetReportRequestId()) {
//                        System.out.print("                ReportRequestId");
//                        System.out.println();
//                        System.out.print("                    " + reportRequestInfo.getReportRequestId());
//                        System.out.println();
//                    }
//                    if (reportRequestInfo.isSetReportType()) {
//                        System.out.print("                ReportType");
//                        System.out.println();
//                        System.out.print("                    " + reportRequestInfo.getReportType());
//                        System.out.println();
//                    }
//                    if (reportRequestInfo.isSetStartDate()) {
//                        System.out.print("                StartDate");
//                        System.out.println();
//                        System.out.print("                    " + reportRequestInfo.getStartDate());
//                        System.out.println();
//                    }
//                    if (reportRequestInfo.isSetEndDate()) {
//                        System.out.print("                EndDate");
//                        System.out.println();
//                        System.out.print("                    " + reportRequestInfo.getEndDate());
//                        System.out.println();
//                    }
//                    if (reportRequestInfo.isSetSubmittedDate()) {
//                        System.out.print("                SubmittedDate");
//                        System.out.println();
//                        System.out.print("                    " + reportRequestInfo.getSubmittedDate());
//                        System.out.println();
//                    }
//                    if (reportRequestInfo.isSetCompletedDate()) {
//                        System.out.print("                CompletedDate");
//                        System.out.println();
//                        System.out.print("                    " + reportRequestInfo.getCompletedDate());
//                        System.out.println();
//                    }                    
//                    if (reportRequestInfo.isSetReportProcessingStatus()) {
//                        System.out.print("                ReportProcessingStatus");
//                        System.out.println();
//                        System.out.print("                    " + reportRequestInfo.getReportProcessingStatus());
//                        System.out.println();
//                    }
                    if (requestId.equalsIgnoreCase(reportRequestInfo.getReportRequestId())){
//	                    System.out.print("                ReportProcessingStatus");
//	                    System.out.println();
//	                    System.out.print("                    " + reportRequestInfo.getGeneratedReportId());
//	                    System.out.println();
	                    return reportRequestInfo.getGeneratedReportId();
                    }
                }
            } 
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
		return null;
    }
}
