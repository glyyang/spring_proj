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
 * Money
 * API Version: 2013-09-01
 * Library Version: 2018-01-31
 * Generated: Tue Jan 30 16:03:16 PST 2018
 */
package com.g128.model;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.g128.amazonservice.mws.client.*;

/**
 * Money complex type.
 *
 * XML schema:
 *
 * <pre>
 * &lt;complexType name="Money"&gt;
 *    &lt;complexContent&gt;
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *          &lt;sequence&gt;
 *             &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *          &lt;/sequence&gt;
 *       &lt;/restriction&gt;
 *    &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */

@Embeddable
//@XmlRootElement(name="ItemPrice", namespace="com.g128.OrderItem")
public class Money extends AbstractMwsObject {
//	@XmlElement(name="Component")
//	private List<Components> cmp;
	
    private String currencyCode;

    private String amount;

    /**
     * Get the value of CurrencyCode.
     *
     * @return The value of CurrencyCode.
     */
//    public List<Components> getCmp() {
//		return cmp;
//	}
//
//	public void setCmp(List<Components> cmp) {
//		this.cmp = cmp;
//	}
    
    public String getCurrencyCode() {
        return currencyCode;
    }

    
	

	/**
     * Set the value of CurrencyCode.
     *
     * @param currencyCode
     *            The new value to set.
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * Check to see if CurrencyCode is set.
     *
     * @return true if CurrencyCode is set.
     */
    public boolean isSetCurrencyCode() {
        return currencyCode != null;
    }

    /**
     * Set the value of CurrencyCode, return this.
     *
     * @param currencyCode
     *             The new value to set.
     *
     * @return This instance.
     */
    public Money withCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    /**
     * Get the value of Amount.
     *
     * @return The value of Amount.
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Set the value of Amount.
     *
     * @param amount
     *            The new value to set.
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Check to see if Amount is set.
     *
     * @return true if Amount is set.
     */
    public boolean isSetAmount() {
        return amount != null;
    }

    /**
     * Set the value of Amount, return this.
     *
     * @param amount
     *             The new value to set.
     *
     * @return This instance.
     */
    public Money withAmount(String amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Read members from a MwsReader.
     *
     * @param r
     *      The reader to read from.
     */
    @Override
    public void readFragmentFrom(MwsReader r) {
        currencyCode = r.read("CurrencyCode", String.class);
        amount = r.read("Amount", String.class);
    }

    /**
     * Write members to a MwsWriter.
     *
     * @param w
     *      The writer to write to.
     */
    @Override
    public void writeFragmentTo(MwsWriter w) {
        w.write("CurrencyCode", currencyCode);
        w.write("Amount", amount);
    }

    /**
     * Write tag, xmlns and members to a MwsWriter.
     *
     * @param w
     *         The Writer to write to.
     */
    @Override
    public void writeTo(MwsWriter w) {
        w.write("https://mws.amazonservices.com/Orders/2013-09-01", "Money",this);
    }


    /** Default constructor. */
    public Money() {
        super();
    }

	@Override
	public String toString() {
		return amount+currencyCode;
	}

    
}