/*******************************************************************************
 * Copyright 2009-2018 Amazon Services. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 *
 * You may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations under the License.
 *******************************************************************************
 * Marketplace Web Service Orders
 * API Version: 2013-09-01
 * Library Version: 2018-01-31
 * Generated: Tue Jan 30 16:03:16 PST 2018
 */
package com.g128.amazonservice.mws.service;

import com.g128.amazonservice.mws.order.*;

/**
 * Configuration for MarketplaceWebServiceOrders samples.
 */
public class MarketplaceWebServiceOrdersAuthConfig {

	 /** Developer AWS access key. */
    private static final String accessKey = "access_key";

    /** Developer AWS secret key. */
    private static final String secretKey = "secret_key";

    /** The client application name. */
    private static final String appName = "company_name";

    /** The client application version. */
    private static final String appVersion = "1.0";

    /**
     * The endpoint for region service and version.
     serviceURL = MWSEndpoint.NA_PROD.toString();
     */
    private static final String serviceURL = MWSEndpoint.NA_PROD.toString();

    /** The client, lazy initialized. Async client is also a sync client. */
    private static MarketplaceWebServiceOrdersAsyncClient client = null;

    /**
     * Get a client connection ready to use.
     *
     * @return A ready to use client connection.
     */
    public static MarketplaceWebServiceOrdersClient getClient() {
        return getAsyncClient();
    }

    /**
     * Get an async client connection ready to use.
     *
     * @return A ready to use client connection.
     */
    public static synchronized MarketplaceWebServiceOrdersAsyncClient getAsyncClient() {
        if (client==null) {
            MarketplaceWebServiceOrdersConfig config = new MarketplaceWebServiceOrdersConfig();
            config.setServiceURL(serviceURL);
            client = new MarketplaceWebServiceOrdersAsyncClient(accessKey, secretKey, 
                    appName, appVersion, config, null);
        }
        return client;
    }

}
