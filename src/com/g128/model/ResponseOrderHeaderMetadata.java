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
 * Library Version: 2018-01-31
 * Generated: Tue Jan 30 16:03:16 PST 2018
 */
package com.g128.model;

import java.util.List;
import java.util.Date;

import com.g128.amazonservice.mws.client.MwsResponseHeaderMetadata;

/**
 * ResponseHeaderMetadata
 */
public class ResponseOrderHeaderMetadata extends MwsResponseHeaderMetadata {

    /** Value constructor. */
    public ResponseOrderHeaderMetadata(String requestId, List<String> responseContext, String timestamp,
                                  Double quotaMax, Double quotaRemaining, Date quotaResetsAt) {
        super(requestId, responseContext, timestamp, quotaMax, quotaRemaining, quotaResetsAt);
    }

    /** Empty constructor. */
    public ResponseOrderHeaderMetadata() {
        super(null, null, null, null, null, null);
    }

    /** Copy constructor. */
    public ResponseOrderHeaderMetadata(MwsResponseHeaderMetadata rhmd) {
        super(rhmd);
    }

}
