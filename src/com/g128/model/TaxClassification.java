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
 * Tax Classification
 * API Version: 2013-09-01
 * Library Version: 2018-01-31
 * Generated: Tue Jan 30 16:03:16 PST 2018
 */
package com.g128.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

import com.g128.amazonservice.mws.client.*;

/**
 * TaxClassification complex type.
 *
 * XML schema:
 *
 * <pre>
 * &lt;complexType name="TaxClassification"&gt;
 *    &lt;complexContent&gt;
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *          &lt;sequence&gt;
 *             &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *          &lt;/sequence&gt;
 *       &lt;/restriction&gt;
 *    &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */

@Entity
@Table(name = "Tax_Classification")
public class TaxClassification extends AbstractMwsObject {
	@Id
	@Column(name = "classification_name")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column
    private String name;

	@Column(name = "classification_value")
    private String value;


	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "buyer_tax_id", nullable = false)
	private BuyerTaxInfo txInfo;
	
    /**
     * Get the value of Name.
     *
     * @return The value of Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of Name.
     *
     * @param name
     *            The new value to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Check to see if Name is set.
     *
     * @return true if Name is set.
     */
    public boolean isSetName() {
        return name != null;
    }

    /**
     * Set the value of Name, return this.
     *
     * @param name
     *             The new value to set.
     *
     * @return This instance.
     */
    public TaxClassification withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the value of Value.
     *
     * @return The value of Value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value of Value.
     *
     * @param value
     *            The new value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Check to see if Value is set.
     *
     * @return true if Value is set.
     */
    public boolean isSetValue() {
        return value != null;
    }

    /**
     * Set the value of Value, return this.
     *
     * @param value
     *             The new value to set.
     *
     * @return This instance.
     */
    public TaxClassification withValue(String value) {
        this.value = value;
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
        name = r.read("Name", String.class);
        value = r.read("Value", String.class);
    }

    /**
     * Write members to a MwsWriter.
     *
     * @param w
     *      The writer to write to.
     */
    @Override
    public void writeFragmentTo(MwsWriter w) {
        w.write("Name", name);
        w.write("Value", value);
    }

    /**
     * Write tag, xmlns and members to a MwsWriter.
     *
     * @param w
     *         The Writer to write to.
     */
    @Override
    public void writeTo(MwsWriter w) {
        w.write("https://mws.amazonservices.com/Orders/2013-09-01", "TaxClassification",this);
    }

    

    public BuyerTaxInfo getTxInfo() {
		return txInfo;
	}

	public void setTxInfo(BuyerTaxInfo txInfo) {
		this.txInfo = txInfo;
	}

	@Override
	public String toString() {
		return "TaxClassification [id=" + id + ", name=" + name + ", value=" + value + ", txInfo=" + txInfo + "]";
	}

	/** Default constructor. */
    public TaxClassification() {
        super();
    }

}
