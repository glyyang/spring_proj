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
 * Order
 * API Version: 2013-09-01
 * Library Version: 2018-01-31
 * Generated: Tue Jan 30 16:03:16 PST 2018
 */
package com.g128.model;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;
import com.g128.amazonservice.mws.client.*;

/**
 * Order complex type.
 *
 * XML schema:
 *
 * <pre>
 * &lt;complexType name="Order"&gt;
 *    &lt;complexContent&gt;
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *          &lt;sequence&gt;
 *             &lt;element name="AmazonOrderId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *             &lt;element name="SellerOrderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="PurchaseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *             &lt;element name="LastUpdateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *             &lt;element name="OrderStatus" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *             &lt;element name="FulfillmentChannel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="SalesChannel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="OrderChannel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="ShipServiceLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="ShippingAddress" type="{https://mws.amazonservices.com/Orders/2013-09-01}Address" minOccurs="0"/&gt;
 *             &lt;element name="OrderTotal" type="{https://mws.amazonservices.com/Orders/2013-09-01}Money" minOccurs="0"/&gt;
 *             &lt;element name="NumberOfItemsShipped" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *             &lt;element name="NumberOfItemsUnshipped" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *             &lt;element name="PaymentExecutionDetail" type="{https://mws.amazonservices.com/Orders/2013-09-01}PaymentExecutionDetailItem" maxOccurs="unbounded"/&gt;
 *             &lt;element name="PaymentMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="PaymentMethodDetails" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
 *             &lt;element name="MarketplaceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="BuyerEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="BuyerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="BuyerCounty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="BuyerTaxInfo" type="{https://mws.amazonservices.com/Orders/2013-09-01}BuyerTaxInfo" minOccurs="0"/&gt;
 *             &lt;element name="ShipmentServiceLevelCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="ShippedByAmazonTFM" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *             &lt;element name="TFMShipmentStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="CbaDisplayableShippingLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="OrderType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="EarliestShipDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *             &lt;element name="LatestShipDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *             &lt;element name="EarliestDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *             &lt;element name="LatestDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *             &lt;element name="IsBusinessOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *             &lt;element name="PurchaseOrderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="IsPrime" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *             &lt;element name="IsPremiumOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *             &lt;element name="ReplacedOrderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="IsReplacementOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *          &lt;/sequence&gt;
 *       &lt;/restriction&gt;
 *    &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
//@Component
@Entity
@Table(name="Amazon_Order")
@XmlRootElement(name="Order", namespace="com.g128.model.OrderMessage")
//@SQLInsert(sql = "INSERT INTO Amazon_Order(order_id) VALUES(?) ON DUPLICATE KEY UPDATE id = id")
public class Orders extends AbstractMwsObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
//	@XmlElement(name="AmazonOrderID")
	@Column(name = "order_id", unique=true)
    private String amazonOrderId;

//	@XmlElement(name="MerchantOrderID")
	@Column(name = "sid")
	private String sellerOrderId;

//	@XmlElement(name="PurchaseDate")
	@Transient
    private XMLGregorianCalendar purchaseDate;
	
	@Column(name = "purchase_date")
	private Timestamp purchaseDateConverted;
	
//	@XmlElement(name="LastUpdatedDate")
	@Transient
    private XMLGregorianCalendar lastUpdateDate;
	
	@Column(name = "last_updated")
	private Timestamp lastUpdated;

//	@XmlElement(name="OrderStatus")
	@Column(name = "order_status")
    private String orderStatus;

//	@XmlElement(name="FulfillmentData")
	@Transient
	private Fulfillment ffmt;
	
	@Column(name = "fulfillment_type")
    private String fulfillmentChannel;

//	@XmlElement(name="SalesChannel")
	@Column(name = "sale_method")
    private String salesChannel;

	@Column(name = "order_method")
    private String orderChannel;

	@Column(name = "shipment_strategy")
    private String shipServiceLevel;

    @Transient
    private Address shippingAddress;
    
    @Column(name = "shipment_name")
    private String name;

    @Column(name = "shipment_addressline1")
    private String addressLine1;

    @Column(name = "shipment_addressline2")
    private String addressLine2;

    @Column(name = "shipment_addressline3")
    private String addressLine3;

    @Column(name = "shipment_destination_city")
    private String city;

    @Column(name = "shipment_destination_county")
    private String county;

    @Column(name = "shipment_destination_district")
    private String district;

    @Column(name = "shipment_destination_state")
    private String stateOrRegion;

    @Column(name = "shipment_destination_zipcode")
    private String postalCode;

    @Column(name = "shipment_country_code")
    private String countryCode;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "address_type")
    private String addressType;
    
    @Transient
    private Money orderTotal;
    
    @Column(name = "total_cost")
    private String orderCost;
    
    @Column(name = "currency")
    private String orderCurrency;

    
    @Column(name = "items_shipped")
    private Integer numberOfItemsShipped;

    @Column(name = "items_pending_for_shipping")
    private Integer numberOfItemsUnshipped;

    @OneToMany(mappedBy="order_id", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PaymentExecutionDetailItem> paymentExecutionDetail;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Transient
    private List<String> paymentMethodDetails;
    
    @Column(name = "payment_details")
    private String paymentMethodDetail;

    @Column(name = "marketplace_id")
    private String marketplaceId;

    @Column(name = "buyer_email")
    private String buyerEmail;
    
    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "buyer_county")
    private String buyerCounty;

    @Transient
    private BuyerTaxInfo buyerTaxInfo;
    
    @Column(name = "buyer_tax_info")
    private String buyerTIString;

    @Column(name = "shipment_category")
    private String shipmentServiceLevelCategory;

    @Column(name = "is_amazon_fulfilled")
    private Boolean shippedByAmazonTFM;

    @Column(name = "shipment_status")
    private String tfmShipmentStatus;

    @Column(name = "shipment_label")
    private String cbaDisplayableShippingLabel;

    @Column(name = "order_type")
    private String orderType;

    
    @Transient
    private XMLGregorianCalendar earliestShipDate;

    @Column(name = "earliest_shipping_date")
    private Timestamp earliestShipment;
    
    @Transient
    private XMLGregorianCalendar latestShipDate;

    @Column(name = "latest_shipping_date")
    private Timestamp latestShipment;
    
    @Transient
    private XMLGregorianCalendar earliestDeliveryDate;

    @Column(name = "earliest_delivery_date")
    private Timestamp earliestDelivery;
    
    @Transient
    private XMLGregorianCalendar latestDeliveryDate;

    @Column(name = "latest_delivery_date")
    private Timestamp latestDelivery;
//    
//    @XmlElement(name="IsBusinessOrder")
    @Column(name = "is_business_order")
    private Boolean isBusinessOrder;

    
    @Column(name = "purchase_order_number")
    private String purchaseOrderNumber;
    
    @Column(name = "is_amazon_prime")
    private Boolean isPrime;

    @Column(name = "is_premium_order")
    private Boolean isPremiumOrder;

    @Column(name = "replaced_order_id")
    private String replacedOrderId;

//    @XmlElement(name="IsReplacementOrder")
    @Column(name = "is_replacement_order")
    private Boolean isReplacementOrder;
    
    //@XmlElement(name="OrderItem")
    @OneToMany(mappedBy="ord", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
    List<OrderItem> ord_items;
    

	public Orders(String amazonOrderId, String sellerOrderId, XMLGregorianCalendar purchaseDate,
			Timestamp purchaseDateConverted, XMLGregorianCalendar lastUpdateDate, Timestamp lastUpdated,
			String orderStatus, String fulfillmentChannel, String salesChannel, String orderChannel,
			String shipServiceLevel, Address shippingAddress, String name, String addressLine1, String addressLine2,
			String addressLine3, String city, String county, String district, String stateOrRegion, String postalCode,
			String countryCode, String phone, String addressType, Money orderTotal, String orderCost,
			String orderCurrency, Integer numberOfItemsShipped, Integer numberOfItemsUnshipped,
			List<PaymentExecutionDetailItem> paymentExecutionDetail, String paymentMethod,
			List<String> paymentMethodDetails, String paymentMethodDetail, String marketplaceId, String buyerEmail,
			String buyerName, String buyerCounty, BuyerTaxInfo buyerTaxInfo, String buyerTIString,
			String shipmentServiceLevelCategory, Boolean shippedByAmazonTFM, String tfmShipmentStatus,
			String cbaDisplayableShippingLabel, String orderType, XMLGregorianCalendar earliestShipDate,
			Timestamp earliestShipment, XMLGregorianCalendar latestShipDate, Timestamp latestShipment,
			XMLGregorianCalendar earliestDeliveryDate, Timestamp earliestDelivery,
			XMLGregorianCalendar latestDeliveryDate, Timestamp latestDelivery, Boolean isBusinessOrder,
			String purchaseOrderNumber, Boolean isPrime, Boolean isPremiumOrder, String replacedOrderId,
			Boolean isReplacementOrder) {
		super();
		this.amazonOrderId = amazonOrderId;
		this.sellerOrderId = sellerOrderId;
		this.purchaseDate = purchaseDate;
		this.purchaseDateConverted = purchaseDateConverted;
		this.lastUpdateDate = lastUpdateDate;
		this.lastUpdated = lastUpdated;
		this.orderStatus = orderStatus;
		this.fulfillmentChannel = fulfillmentChannel;
		this.salesChannel = salesChannel;
		this.orderChannel = orderChannel;
		this.shipServiceLevel = shipServiceLevel;
		this.shippingAddress = shippingAddress;
		this.name = name;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.city = city;
		this.county = county;
		this.district = district;
		this.stateOrRegion = stateOrRegion;
		this.postalCode = postalCode;
		this.countryCode = countryCode;
		this.phone = phone;
		this.addressType = addressType;
		this.orderTotal = orderTotal;
		this.orderCost = orderCost;
		this.orderCurrency = orderCurrency;
		this.numberOfItemsShipped = numberOfItemsShipped;
		this.numberOfItemsUnshipped = numberOfItemsUnshipped;
		this.paymentExecutionDetail = paymentExecutionDetail;
		this.paymentMethod = paymentMethod;
		this.paymentMethodDetails = paymentMethodDetails;
		this.paymentMethodDetail = paymentMethodDetail;
		this.marketplaceId = marketplaceId;
		this.buyerEmail = buyerEmail;
		this.buyerName = buyerName;
		this.buyerCounty = buyerCounty;
		this.buyerTaxInfo = buyerTaxInfo;
		this.buyerTIString = buyerTIString;
		this.shipmentServiceLevelCategory = shipmentServiceLevelCategory;
		this.shippedByAmazonTFM = shippedByAmazonTFM;
		this.tfmShipmentStatus = tfmShipmentStatus;
		this.cbaDisplayableShippingLabel = cbaDisplayableShippingLabel;
		this.orderType = orderType;
		this.earliestShipDate = earliestShipDate;
		this.earliestShipment = earliestShipment;
		this.latestShipDate = latestShipDate;
		this.latestShipment = latestShipment;
		this.earliestDeliveryDate = earliestDeliveryDate;
		this.earliestDelivery = earliestDelivery;
		this.latestDeliveryDate = latestDeliveryDate;
		this.latestDelivery = latestDelivery;
		this.isBusinessOrder = isBusinessOrder;
		this.purchaseOrderNumber = purchaseOrderNumber;
		this.isPrime = isPrime;
		this.isPremiumOrder = isPremiumOrder;
		this.replacedOrderId = replacedOrderId;
		this.isReplacementOrder = isReplacementOrder;
	}

	
	
	@XmlElement(name="FulfillmentData")
	public Fulfillment getFfmt() {
		return ffmt;
	}




	public void setFfmt(Fulfillment ffmt) {
		this.ffmt = ffmt;
	}




	/**
     * Get the value of AmazonOrderId.
     *
     * @return The value of AmazonOrderId.
     */
	@XmlElement(name="AmazonOrderID")
    public String getAmazonOrderId() {
        return amazonOrderId;
    }

    /**
     * Set the value of AmazonOrderId.
     *
     * @param amazonOrderId
     *            The new value to set.
     */
    public void setAmazonOrderId(String amazonOrderId) {
        this.amazonOrderId = amazonOrderId;
    }

    @XmlElement(name="OrderItem")
    public List<OrderItem> getOrd_items() {
		return ord_items;
	}

	public void setOrd_items(List<OrderItem> ord_items) {
		this.ord_items = ord_items;
	}

	/**
     * Check to see if AmazonOrderId is set.
     *
     * @return true if AmazonOrderId is set.
     */
    public boolean isSetAmazonOrderId() {
        return amazonOrderId != null;
    }

    /**
     * Set the value of AmazonOrderId, return this.
     *
     * @param amazonOrderId
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withAmazonOrderId(String amazonOrderId) {
        this.amazonOrderId = amazonOrderId;
        return this;
    }

    /**
     * Get the value of SellerOrderId.
     *
     * @return The value of SellerOrderId.
     */
    @XmlElement(name="MerchantOrderID")
    public String getSellerOrderId() {
        return sellerOrderId;
    }

    /**
     * Set the value of SellerOrderId.
     *
     * @param sellerOrderId
     *            The new value to set.
     */
    public void setSellerOrderId(String sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }

    /**
     * Check to see if SellerOrderId is set.
     *
     * @return true if SellerOrderId is set.
     */
    public boolean isSetSellerOrderId() {
        return sellerOrderId != null;
    }

    /**
     * Set the value of SellerOrderId, return this.
     *
     * @param sellerOrderId
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withSellerOrderId(String sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
        return this;
    }

    /**
     * Get the value of PurchaseDate.
     *
     * @return The value of PurchaseDate.
     */
    @XmlElement(name="PurchaseDate")
    @XmlSchemaType(name="datetime")
    public XMLGregorianCalendar getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Set the value of PurchaseDate.
     *
     * @param purchaseDate
     *            The new value to set.
     */
    public void setPurchaseDate(XMLGregorianCalendar purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * Check to see if PurchaseDate is set.
     *
     * @return true if PurchaseDate is set.
     */
    public boolean isSetPurchaseDate() {
        return purchaseDate != null;
    }

    /**
     * Set the value of PurchaseDate, return this.
     *
     * @param purchaseDate
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withPurchaseDate(XMLGregorianCalendar purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    /**
     * Get the value of LastUpdateDate.
     *
     * @return The value of LastUpdateDate.
     */
    @XmlElement(name="LastUpdatedDate")
    @XmlSchemaType(name = "dateTime")
    public XMLGregorianCalendar getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Set the value of LastUpdateDate.
     *
     * @param lastUpdateDate
     *            The new value to set.
     */
    public void setLastUpdateDate(XMLGregorianCalendar lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * Check to see if LastUpdateDate is set.
     *
     * @return true if LastUpdateDate is set.
     */
    public boolean isSetLastUpdateDate() {
        return lastUpdateDate != null;
    }

    /**
     * Set the value of LastUpdateDate, return this.
     *
     * @param lastUpdateDate
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withLastUpdateDate(XMLGregorianCalendar lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }

    /**
     * Get the value of OrderStatus.
     *
     * @return The value of OrderStatus.
     */
    @XmlElement(name="OrderStatus")
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Set the value of OrderStatus.
     *
     * @param orderStatus
     *            The new value to set.
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Check to see if OrderStatus is set.
     *
     * @return true if OrderStatus is set.
     */
    public boolean isSetOrderStatus() {
        return orderStatus != null;
    }

    /**
     * Set the value of OrderStatus, return this.
     *
     * @param orderStatus
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    /**
     * Get the value of FulfillmentChannel.
     *
     * @return The value of FulfillmentChannel.
     */
    public String getFulfillmentChannel() {
        return fulfillmentChannel;
    }

    /**
     * Set the value of FulfillmentChannel.
     *
     * @param fulfillmentChannel
     *            The new value to set.
     */
    public void setFulfillmentChannel(String fulfillmentChannel) {
        this.fulfillmentChannel = fulfillmentChannel;
    }

    /**
     * Check to see if FulfillmentChannel is set.
     *
     * @return true if FulfillmentChannel is set.
     */
    public boolean isSetFulfillmentChannel() {
        return fulfillmentChannel != null;
    }

    /**
     * Set the value of FulfillmentChannel, return this.
     *
     * @param fulfillmentChannel
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withFulfillmentChannel(String fulfillmentChannel) {
        this.fulfillmentChannel = fulfillmentChannel;
        return this;
    }

    /**
     * Get the value of SalesChannel.
     *
     * @return The value of SalesChannel.
     */
    @XmlElement(name="SalesChannel")
    public String getSalesChannel() {
        return salesChannel;
    }

    /**
     * Set the value of SalesChannel.
     *
     * @param salesChannel
     *            The new value to set.
     */
    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    /**
     * Check to see if SalesChannel is set.
     *
     * @return true if SalesChannel is set.
     */
    public boolean isSetSalesChannel() {
        return salesChannel != null;
    }

    /**
     * Set the value of SalesChannel, return this.
     *
     * @param salesChannel
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
        return this;
    }

    /**
     * Get the value of OrderChannel.
     *
     * @return The value of OrderChannel.
     */
    public String getOrderChannel() {
        return orderChannel;
    }

    /**
     * Set the value of OrderChannel.
     *
     * @param orderChannel
     *            The new value to set.
     */
    public void setOrderChannel(String orderChannel) {
        this.orderChannel = orderChannel;
    }

    /**
     * Check to see if OrderChannel is set.
     *
     * @return true if OrderChannel is set.
     */
    public boolean isSetOrderChannel() {
        return orderChannel != null;
    }

    /**
     * Set the value of OrderChannel, return this.
     *
     * @param orderChannel
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withOrderChannel(String orderChannel) {
        this.orderChannel = orderChannel;
        return this;
    }

    /**
     * Get the value of ShipServiceLevel.
     *
     * @return The value of ShipServiceLevel.
     */
    public String getShipServiceLevel() {
        return shipServiceLevel;
    }

    /**
     * Set the value of ShipServiceLevel.
     *
     * @param shipServiceLevel
     *            The new value to set.
     */
    public void setShipServiceLevel(String shipServiceLevel) {
        this.shipServiceLevel = shipServiceLevel;
    }

    /**
     * Check to see if ShipServiceLevel is set.
     *
     * @return true if ShipServiceLevel is set.
     */
    public boolean isSetShipServiceLevel() {
        return shipServiceLevel != null;
    }

    /**
     * Set the value of ShipServiceLevel, return this.
     *
     * @param shipServiceLevel
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withShipServiceLevel(String shipServiceLevel) {
        this.shipServiceLevel = shipServiceLevel;
        return this;
    }

    /**
     * Get the value of ShippingAddress.
     *
     * @return The value of ShippingAddress.
     */
    public Address getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Set the value of ShippingAddress.
     *
     * @param shippingAddress
     *            The new value to set.
     */
    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * Check to see if ShippingAddress is set.
     *
     * @return true if ShippingAddress is set.
     */
    public boolean isSetShippingAddress() {
        return shippingAddress != null;
    }

    /**
     * Set the value of ShippingAddress, return this.
     *
     * @param shippingAddress
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    /**
     * Get the value of OrderTotal.
     *
     * @return The value of OrderTotal.
     */
    public Money getOrderTotal() {
        return orderTotal;
    }

    /**
     * Set the value of OrderTotal.
     *
     * @param orderTotal
     *            The new value to set.
     */
    public void setOrderTotal(Money orderTotal) {
        this.orderTotal = orderTotal;
    }

    /**
     * Check to see if OrderTotal is set.
     *
     * @return true if OrderTotal is set.
     */
    public boolean isSetOrderTotal() {
        return orderTotal != null;
    }

    /**
     * Set the value of OrderTotal, return this.
     *
     * @param orderTotal
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withOrderTotal(Money orderTotal) {
        this.orderTotal = orderTotal;
        return this;
    }

    /**
     * Get the value of NumberOfItemsShipped.
     *
     * @return The value of NumberOfItemsShipped.
     */
    public Integer getNumberOfItemsShipped() {
        return numberOfItemsShipped;
    }

    /**
     * Set the value of NumberOfItemsShipped.
     *
     * @param numberOfItemsShipped
     *            The new value to set.
     */
    public void setNumberOfItemsShipped(Integer numberOfItemsShipped) {
        this.numberOfItemsShipped = numberOfItemsShipped;
    }

    /**
     * Check to see if NumberOfItemsShipped is set.
     *
     * @return true if NumberOfItemsShipped is set.
     */
    public boolean isSetNumberOfItemsShipped() {
        return numberOfItemsShipped != null;
    }

    /**
     * Set the value of NumberOfItemsShipped, return this.
     *
     * @param numberOfItemsShipped
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withNumberOfItemsShipped(Integer numberOfItemsShipped) {
        this.numberOfItemsShipped = numberOfItemsShipped;
        return this;
    }

    /**
     * Get the value of NumberOfItemsUnshipped.
     *
     * @return The value of NumberOfItemsUnshipped.
     */
    public Integer getNumberOfItemsUnshipped() {
        return numberOfItemsUnshipped;
    }

    /**
     * Set the value of NumberOfItemsUnshipped.
     *
     * @param numberOfItemsUnshipped
     *            The new value to set.
     */
    public void setNumberOfItemsUnshipped(Integer numberOfItemsUnshipped) {
        this.numberOfItemsUnshipped = numberOfItemsUnshipped;
    }

    /**
     * Check to see if NumberOfItemsUnshipped is set.
     *
     * @return true if NumberOfItemsUnshipped is set.
     */
    public boolean isSetNumberOfItemsUnshipped() {
        return numberOfItemsUnshipped != null;
    }

    /**
     * Set the value of NumberOfItemsUnshipped, return this.
     *
     * @param numberOfItemsUnshipped
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withNumberOfItemsUnshipped(Integer numberOfItemsUnshipped) {
        this.numberOfItemsUnshipped = numberOfItemsUnshipped;
        return this;
    }

    /**
     * Get the value of PaymentExecutionDetail.
     *
     * @return The value of PaymentExecutionDetail.
     */
    public List<PaymentExecutionDetailItem> getPaymentExecutionDetail() {
        if (paymentExecutionDetail==null) {
            paymentExecutionDetail = new ArrayList<PaymentExecutionDetailItem>();
        }
        return paymentExecutionDetail;
    }

    /**
     * Set the value of PaymentExecutionDetail.
     *
     * @param paymentExecutionDetail
     *            The new value to set.
     */
    public void setPaymentExecutionDetail(List<PaymentExecutionDetailItem> paymentExecutionDetail) {
        this.paymentExecutionDetail = paymentExecutionDetail;
    }

    /**
     * Clear PaymentExecutionDetail.
     */
    public void unsetPaymentExecutionDetail() {
        this.paymentExecutionDetail = null;
    }

    /**
     * Check to see if PaymentExecutionDetail is set.
     *
     * @return true if PaymentExecutionDetail is set.
     */
    public boolean isSetPaymentExecutionDetail() {
        return paymentExecutionDetail != null && !paymentExecutionDetail.isEmpty();
    }

    /**
     * Add values for PaymentExecutionDetail, return this.
     *
     * @param paymentExecutionDetail
     *             New values to add.
     *
     * @return This instance.
     */
    public Orders withPaymentExecutionDetail(PaymentExecutionDetailItem... values) {
        List<PaymentExecutionDetailItem> list = getPaymentExecutionDetail();
        for (PaymentExecutionDetailItem value : values) {
            list.add(value);
        }
        return this;
    }

    /**
     * Get the value of PaymentMethod.
     *
     * @return The value of PaymentMethod.
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Set the value of PaymentMethod.
     *
     * @param paymentMethod
     *            The new value to set.
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Check to see if PaymentMethod is set.
     *
     * @return true if PaymentMethod is set.
     */
    public boolean isSetPaymentMethod() {
        return paymentMethod != null;
    }

    /**
     * Set the value of PaymentMethod, return this.
     *
     * @param paymentMethod
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    /**
     * Get the value of PaymentMethodDetails.
     *
     * @return The value of PaymentMethodDetails.
     */
    public List<String> getPaymentMethodDetails() {
        if (paymentMethodDetails==null) {
            paymentMethodDetails = new ArrayList<String>();
        }
        return paymentMethodDetails;
    }

    /**
     * Set the value of PaymentMethodDetails.
     *
     * @param paymentMethodDetails
     *            The new value to set.
     */
    public void setPaymentMethodDetails(List<String> paymentMethodDetails) {
        this.paymentMethodDetails = paymentMethodDetails;
    }

    /**
     * Clear PaymentMethodDetails.
     */
    public void unsetPaymentMethodDetails() {
        this.paymentMethodDetails = null;
    }

    /**
     * Check to see if PaymentMethodDetails is set.
     *
     * @return true if PaymentMethodDetails is set.
     */
    public boolean isSetPaymentMethodDetails() {
        return paymentMethodDetails != null && !paymentMethodDetails.isEmpty();
    }

    /**
     * Add values for PaymentMethodDetails, return this.
     *
     * @param paymentMethodDetails
     *             New values to add.
     *
     * @return This instance.
     */
    public Orders withPaymentMethodDetails(String... values) {
        List<String> list = getPaymentMethodDetails();
        for (String value : values) {
            list.add(value);
        }
        return this;
    }

    /**
     * Get the value of MarketplaceId.
     *
     * @return The value of MarketplaceId.
     */
    public String getMarketplaceId() {
        return marketplaceId;
    }

    /**
     * Set the value of MarketplaceId.
     *
     * @param marketplaceId
     *            The new value to set.
     */
    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    /**
     * Check to see if MarketplaceId is set.
     *
     * @return true if MarketplaceId is set.
     */
    public boolean isSetMarketplaceId() {
        return marketplaceId != null;
    }

    /**
     * Set the value of MarketplaceId, return this.
     *
     * @param marketplaceId
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
        return this;
    }

    /**
     * Get the value of BuyerEmail.
     *
     * @return The value of BuyerEmail.
     */
    public String getBuyerEmail() {
        return buyerEmail;
    }

    /**
     * Set the value of BuyerEmail.
     *
     * @param buyerEmail
     *            The new value to set.
     */
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    /**
     * Check to see if BuyerEmail is set.
     *
     * @return true if BuyerEmail is set.
     */
    public boolean isSetBuyerEmail() {
        return buyerEmail != null;
    }

    /**
     * Set the value of BuyerEmail, return this.
     *
     * @param buyerEmail
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
        return this;
    }

    /**
     * Get the value of BuyerName.
     *
     * @return The value of BuyerName.
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * Set the value of BuyerName.
     *
     * @param buyerName
     *            The new value to set.
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * Check to see if BuyerName is set.
     *
     * @return true if BuyerName is set.
     */
    public boolean isSetBuyerName() {
        return buyerName != null;
    }

    /**
     * Set the value of BuyerName, return this.
     *
     * @param buyerName
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withBuyerName(String buyerName) {
        this.buyerName = buyerName;
        return this;
    }

    /**
     * Get the value of BuyerCounty.
     *
     * @return The value of BuyerCounty.
     */
    public String getBuyerCounty() {
        return buyerCounty;
    }

    /**
     * Set the value of BuyerCounty.
     *
     * @param buyerCounty
     *            The new value to set.
     */
    public void setBuyerCounty(String buyerCounty) {
        this.buyerCounty = buyerCounty;
    }

    /**
     * Check to see if BuyerCounty is set.
     *
     * @return true if BuyerCounty is set.
     */
    public boolean isSetBuyerCounty() {
        return buyerCounty != null;
    }

    /**
     * Set the value of BuyerCounty, return this.
     *
     * @param buyerCounty
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withBuyerCounty(String buyerCounty) {
        this.buyerCounty = buyerCounty;
        return this;
    }

    /**
     * Get the value of BuyerTaxInfo.
     *
     * @return The value of BuyerTaxInfo.
     */
    public BuyerTaxInfo getBuyerTaxInfo() {
        return buyerTaxInfo;
    }

    /**
     * Set the value of BuyerTaxInfo.
     *
     * @param buyerTaxInfo
     *            The new value to set.
     */
    public void setBuyerTaxInfo(BuyerTaxInfo buyerTaxInfo) {
        this.buyerTaxInfo = buyerTaxInfo;
    }

    /**
     * Check to see if BuyerTaxInfo is set.
     *
     * @return true if BuyerTaxInfo is set.
     */
    public boolean isSetBuyerTaxInfo() {
        return buyerTaxInfo != null;
    }

    /**
     * Set the value of BuyerTaxInfo, return this.
     *
     * @param buyerTaxInfo
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withBuyerTaxInfo(BuyerTaxInfo buyerTaxInfo) {
        this.buyerTaxInfo = buyerTaxInfo;
        return this;
    }

    /**
     * Get the value of ShipmentServiceLevelCategory.
     *
     * @return The value of ShipmentServiceLevelCategory.
     */
    public String getShipmentServiceLevelCategory() {
        return shipmentServiceLevelCategory;
    }

    /**
     * Set the value of ShipmentServiceLevelCategory.
     *
     * @param shipmentServiceLevelCategory
     *            The new value to set.
     */
    public void setShipmentServiceLevelCategory(String shipmentServiceLevelCategory) {
        this.shipmentServiceLevelCategory = shipmentServiceLevelCategory;
    }

    /**
     * Check to see if ShipmentServiceLevelCategory is set.
     *
     * @return true if ShipmentServiceLevelCategory is set.
     */
    public boolean isSetShipmentServiceLevelCategory() {
        return shipmentServiceLevelCategory != null;
    }

    /**
     * Set the value of ShipmentServiceLevelCategory, return this.
     *
     * @param shipmentServiceLevelCategory
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withShipmentServiceLevelCategory(String shipmentServiceLevelCategory) {
        this.shipmentServiceLevelCategory = shipmentServiceLevelCategory;
        return this;
    }

    /**
     * Check the value of ShippedByAmazonTFM.
     *
     * @return true if ShippedByAmazonTFM is set to true.
     */
    public boolean isShippedByAmazonTFM() {
        return shippedByAmazonTFM!=null && shippedByAmazonTFM.booleanValue();
    }

    /**
     * Get the value of ShippedByAmazonTFM.
     *
     * @return The value of ShippedByAmazonTFM.
     */
    public Boolean getShippedByAmazonTFM() {
        return shippedByAmazonTFM;
    }

    /**
     * Set the value of ShippedByAmazonTFM.
     *
     * @param shippedByAmazonTFM
     *            The new value to set.
     */
    public void setShippedByAmazonTFM(Boolean shippedByAmazonTFM) {
        this.shippedByAmazonTFM = shippedByAmazonTFM;
    }

    /**
     * Check to see if ShippedByAmazonTFM is set.
     *
     * @return true if ShippedByAmazonTFM is set.
     */
    public boolean isSetShippedByAmazonTFM() {
        return shippedByAmazonTFM != null;
    }

    /**
     * Set the value of ShippedByAmazonTFM, return this.
     *
     * @param shippedByAmazonTFM
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withShippedByAmazonTFM(Boolean shippedByAmazonTFM) {
        this.shippedByAmazonTFM = shippedByAmazonTFM;
        return this;
    }

    /**
     * Get the value of TFMShipmentStatus.
     *
     * @return The value of TFMShipmentStatus.
     */
    public String getTFMShipmentStatus() {
        return tfmShipmentStatus;
    }

    /**
     * Set the value of TFMShipmentStatus.
     *
     * @param tfmShipmentStatus
     *            The new value to set.
     */
    public void setTFMShipmentStatus(String tfmShipmentStatus) {
        this.tfmShipmentStatus = tfmShipmentStatus;
    }

    /**
     * Check to see if TFMShipmentStatus is set.
     *
     * @return true if TFMShipmentStatus is set.
     */
    public boolean isSetTFMShipmentStatus() {
        return tfmShipmentStatus != null;
    }

    /**
     * Set the value of TFMShipmentStatus, return this.
     *
     * @param tfmShipmentStatus
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withTFMShipmentStatus(String tfmShipmentStatus) {
        this.tfmShipmentStatus = tfmShipmentStatus;
        return this;
    }

    /**
     * Get the value of CbaDisplayableShippingLabel.
     *
     * @return The value of CbaDisplayableShippingLabel.
     */
    public String getCbaDisplayableShippingLabel() {
        return cbaDisplayableShippingLabel;
    }

    /**
     * Set the value of CbaDisplayableShippingLabel.
     *
     * @param cbaDisplayableShippingLabel
     *            The new value to set.
     */
    public void setCbaDisplayableShippingLabel(String cbaDisplayableShippingLabel) {
        this.cbaDisplayableShippingLabel = cbaDisplayableShippingLabel;
    }

    /**
     * Check to see if CbaDisplayableShippingLabel is set.
     *
     * @return true if CbaDisplayableShippingLabel is set.
     */
    public boolean isSetCbaDisplayableShippingLabel() {
        return cbaDisplayableShippingLabel != null;
    }

    /**
     * Set the value of CbaDisplayableShippingLabel, return this.
     *
     * @param cbaDisplayableShippingLabel
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withCbaDisplayableShippingLabel(String cbaDisplayableShippingLabel) {
        this.cbaDisplayableShippingLabel = cbaDisplayableShippingLabel;
        return this;
    }

    /**
     * Get the value of OrderType.
     *
     * @return The value of OrderType.
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * Set the value of OrderType.
     *
     * @param orderType
     *            The new value to set.
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * Check to see if OrderType is set.
     *
     * @return true if OrderType is set.
     */
    public boolean isSetOrderType() {
        return orderType != null;
    }

    /**
     * Set the value of OrderType, return this.
     *
     * @param orderType
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withOrderType(String orderType) {
        this.orderType = orderType;
        return this;
    }

    /**
     * Get the value of EarliestShipDate.
     *
     * @return The value of EarliestShipDate.
     */
    public XMLGregorianCalendar getEarliestShipDate() {
        return earliestShipDate;
    }

    /**
     * Set the value of EarliestShipDate.
     *
     * @param earliestShipDate
     *            The new value to set.
     */
    public void setEarliestShipDate(XMLGregorianCalendar earliestShipDate) {
        this.earliestShipDate = earliestShipDate;
    }

    /**
     * Check to see if EarliestShipDate is set.
     *
     * @return true if EarliestShipDate is set.
     */
    public boolean isSetEarliestShipDate() {
        return earliestShipDate != null;
    }

    /**
     * Set the value of EarliestShipDate, return this.
     *
     * @param earliestShipDate
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withEarliestShipDate(XMLGregorianCalendar earliestShipDate) {
        this.earliestShipDate = earliestShipDate;
        return this;
    }

    /**
     * Get the value of LatestShipDate.
     *
     * @return The value of LatestShipDate.
     */
    public XMLGregorianCalendar getLatestShipDate() {
        return latestShipDate;
    }

    /**
     * Set the value of LatestShipDate.
     *
     * @param latestShipDate
     *            The new value to set.
     */
    public void setLatestShipDate(XMLGregorianCalendar latestShipDate) {
        this.latestShipDate = latestShipDate;
    }

    /**
     * Check to see if LatestShipDate is set.
     *
     * @return true if LatestShipDate is set.
     */
    public boolean isSetLatestShipDate() {
        return latestShipDate != null;
    }

    /**
     * Set the value of LatestShipDate, return this.
     *
     * @param latestShipDate
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withLatestShipDate(XMLGregorianCalendar latestShipDate) {
        this.latestShipDate = latestShipDate;
        return this;
    }

    /**
     * Get the value of EarliestDeliveryDate.
     *
     * @return The value of EarliestDeliveryDate.
     */
    public XMLGregorianCalendar getEarliestDeliveryDate() {
        return earliestDeliveryDate;
    }

    /**
     * Set the value of EarliestDeliveryDate.
     *
     * @param earliestDeliveryDate
     *            The new value to set.
     */
    public void setEarliestDeliveryDate(XMLGregorianCalendar earliestDeliveryDate) {
        this.earliestDeliveryDate = earliestDeliveryDate;
    }

    /**
     * Check to see if EarliestDeliveryDate is set.
     *
     * @return true if EarliestDeliveryDate is set.
     */
    public boolean isSetEarliestDeliveryDate() {
        return earliestDeliveryDate != null;
    }

    /**
     * Set the value of EarliestDeliveryDate, return this.
     *
     * @param earliestDeliveryDate
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withEarliestDeliveryDate(XMLGregorianCalendar earliestDeliveryDate) {
        this.earliestDeliveryDate = earliestDeliveryDate;
        return this;
    }

    /**
     * Get the value of LatestDeliveryDate.
     *
     * @return The value of LatestDeliveryDate.
     */
    public XMLGregorianCalendar getLatestDeliveryDate() {
        return latestDeliveryDate;
    }

    /**
     * Set the value of LatestDeliveryDate.
     *
     * @param latestDeliveryDate
     *            The new value to set.
     */
    public void setLatestDeliveryDate(XMLGregorianCalendar latestDeliveryDate) {
        this.latestDeliveryDate = latestDeliveryDate;
    }

    /**
     * Check to see if LatestDeliveryDate is set.
     *
     * @return true if LatestDeliveryDate is set.
     */
    public boolean isSetLatestDeliveryDate() {
        return latestDeliveryDate != null;
    }

    /**
     * Set the value of LatestDeliveryDate, return this.
     *
     * @param latestDeliveryDate
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withLatestDeliveryDate(XMLGregorianCalendar latestDeliveryDate) {
        this.latestDeliveryDate = latestDeliveryDate;
        return this;
    }

    /**
     * Check the value of IsBusinessOrder.
     *
     * @return true if IsBusinessOrder is set to true.
     */
    public boolean isIsBusinessOrder() {
        return isBusinessOrder!=null && isBusinessOrder.booleanValue();
    }

    /**
     * Get the value of IsBusinessOrder.
     *
     * @return The value of IsBusinessOrder.
     */
    
    @XmlElement(name="IsBusinessOrder")
    public Boolean getIsBusinessOrder() {
        return isBusinessOrder;
    }

    /**
     * Set the value of IsBusinessOrder.
     *
     * @param isBusinessOrder
     *            The new value to set.
     */
    public void setIsBusinessOrder(Boolean isBusinessOrder) {
        this.isBusinessOrder = isBusinessOrder;
    }

    /**
     * Check to see if IsBusinessOrder is set.
     *
     * @return true if IsBusinessOrder is set.
     */
    public boolean isSetIsBusinessOrder() {
        return isBusinessOrder != null;
    }

    /**
     * Set the value of IsBusinessOrder, return this.
     *
     * @param isBusinessOrder
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withIsBusinessOrder(Boolean isBusinessOrder) {
        this.isBusinessOrder = isBusinessOrder;
        return this;
    }

    /**
     * Get the value of PurchaseOrderNumber.
     *
     * @return The value of PurchaseOrderNumber.
     */
    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    /**
     * Set the value of PurchaseOrderNumber.
     *
     * @param purchaseOrderNumber
     *            The new value to set.
     */
    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    /**
     * Check to see if PurchaseOrderNumber is set.
     *
     * @return true if PurchaseOrderNumber is set.
     */
    public boolean isSetPurchaseOrderNumber() {
        return purchaseOrderNumber != null;
    }

    /**
     * Set the value of PurchaseOrderNumber, return this.
     *
     * @param purchaseOrderNumber
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
        return this;
    }

    /**
     * Check the value of IsPrime.
     *
     * @return true if IsPrime is set to true.
     */
    public boolean isIsPrime() {
        return isPrime!=null && isPrime.booleanValue();
    }

    /**
     * Get the value of IsPrime.
     *
     * @return The value of IsPrime.
     */
    public Boolean getIsPrime() {
        return isPrime;
    }

    /**
     * Set the value of IsPrime.
     *
     * @param isPrime
     *            The new value to set.
     */
    public void setIsPrime(Boolean isPrime) {
        this.isPrime = isPrime;
    }

    /**
     * Check to see if IsPrime is set.
     *
     * @return true if IsPrime is set.
     */
    public boolean isSetIsPrime() {
        return isPrime != null;
    }

    /**
     * Set the value of IsPrime, return this.
     *
     * @param isPrime
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withIsPrime(Boolean isPrime) {
        this.isPrime = isPrime;
        return this;
    }

    /**
     * Check the value of IsPremiumOrder.
     *
     * @return true if IsPremiumOrder is set to true.
     */
    public boolean isIsPremiumOrder() {
        return isPremiumOrder!=null && isPremiumOrder.booleanValue();
    }

    /**
     * Get the value of IsPremiumOrder.
     *
     * @return The value of IsPremiumOrder.
     */
    public Boolean getIsPremiumOrder() {
        return isPremiumOrder;
    }

    /**
     * Set the value of IsPremiumOrder.
     *
     * @param isPremiumOrder
     *            The new value to set.
     */
    public void setIsPremiumOrder(Boolean isPremiumOrder) {
        this.isPremiumOrder = isPremiumOrder;
    }

    /**
     * Check to see if IsPremiumOrder is set.
     *
     * @return true if IsPremiumOrder is set.
     */
    public boolean isSetIsPremiumOrder() {
        return isPremiumOrder != null;
    }

    /**
     * Set the value of IsPremiumOrder, return this.
     *
     * @param isPremiumOrder
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withIsPremiumOrder(Boolean isPremiumOrder) {
        this.isPremiumOrder = isPremiumOrder;
        return this;
    }

    /**
     * Get the value of ReplacedOrderId.
     *
     * @return The value of ReplacedOrderId.
     */
    public String getReplacedOrderId() {
        return replacedOrderId;
    }

    /**
     * Set the value of ReplacedOrderId.
     *
     * @param replacedOrderId
     *            The new value to set.
     */
    public void setReplacedOrderId(String replacedOrderId) {
        this.replacedOrderId = replacedOrderId;
    }

    /**
     * Check to see if ReplacedOrderId is set.
     *
     * @return true if ReplacedOrderId is set.
     */
    public boolean isSetReplacedOrderId() {
        return replacedOrderId != null;
    }

    /**
     * Set the value of ReplacedOrderId, return this.
     *
     * @param replacedOrderId
     *             The new value to set.
     *
     * @return This instance.
     */
    public Orders withReplacedOrderId(String replacedOrderId) {
        this.replacedOrderId = replacedOrderId;
        return this;
    }

    /**
     * Check the value of IsReplacementOrder.
     *
     * @return true if IsReplacementOrder is set to true.
     */
    public boolean isIsReplacementOrder() {
        return isReplacementOrder!=null && isReplacementOrder.booleanValue();
    }

    /**
     * Get the value of IsReplacementOrder.
     *
     * @return The value of IsReplacementOrder.
     */
    @XmlElement(name="IsReplacementOrder")
    public Boolean getIsReplacementOrder() {
        return isReplacementOrder;
    }

    /**
     * Set the value of IsReplacementOrder.
     *
     * @param isReplacementOrder
     *            The new value to set.
     */
    public void setIsReplacementOrder(Boolean isReplacementOrder) {
        this.isReplacementOrder = isReplacementOrder;
    }

    /**
     * Check to see if IsReplacementOrder is set.
     *
     * @return true if IsReplacementOrder is set.
     */
    public boolean isSetIsReplacementOrder() {
        return isReplacementOrder != null;
    }

    @Override
	public String toString() {
		return "Orders [amazonOrderId=" + amazonOrderId + ", sellerOrderId=" + sellerOrderId + ", purchaseDate="
				+ purchaseDate + ", purchaseDateConverted=" + purchaseDateConverted + ", lastUpdateDate="
				+ lastUpdateDate + ", lastUpdated=" + lastUpdated + ", orderStatus=" + orderStatus
				+ ", fulfillmentChannel=" + fulfillmentChannel + ", salesChannel=" + salesChannel + ", orderChannel="
				+ orderChannel + ", shipServiceLevel=" + shipServiceLevel + ", shippingAddress=" + shippingAddress
				+ ", name=" + name + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", addressLine3=" + addressLine3 + ", city=" + city + ", county=" + county + ", district=" + district
				+ ", stateOrRegion=" + stateOrRegion + ", postalCode=" + postalCode + ", countryCode=" + countryCode
				+ ", phone=" + phone + ", addressType=" + addressType + ", orderTotal=" + orderTotal + ", orderCost="
				+ orderCost + ", orderCurrency=" + orderCurrency + ", numberOfItemsShipped=" + numberOfItemsShipped
				+ ", numberOfItemsUnshipped=" + numberOfItemsUnshipped + ", paymentExecutionDetail="
				+ paymentExecutionDetail + ", paymentMethod=" + paymentMethod + ", paymentMethodDetails="
				+ paymentMethodDetails + ", paymentMethodDetail=" + paymentMethodDetail + ", marketplaceId="
				+ marketplaceId + ", buyerEmail=" + buyerEmail + ", buyerName=" + buyerName + ", buyerCounty="
				+ buyerCounty + ", buyerTaxInfo=" + buyerTaxInfo + ", buyerTIString=" + buyerTIString
				+ ", shipmentServiceLevelCategory=" + shipmentServiceLevelCategory + ", shippedByAmazonTFM="
				+ shippedByAmazonTFM + ", tfmShipmentStatus=" + tfmShipmentStatus + ", cbaDisplayableShippingLabel="
				+ cbaDisplayableShippingLabel + ", orderType=" + orderType + ", earliestShipDate=" + earliestShipDate
				+ ", earliestShipment=" + earliestShipment + ", latestShipDate=" + latestShipDate + ", latestShipment="
				+ latestShipment + ", earliestDeliveryDate=" + earliestDeliveryDate + ", earliestDelivery="
				+ earliestDelivery + ", latestDeliveryDate=" + latestDeliveryDate + ", latestDelivery=" + latestDelivery
				+ ", isBusinessOrder=" + isBusinessOrder + ", purchaseOrderNumber=" + purchaseOrderNumber + ", isPrime="
				+ isPrime + ", isPremiumOrder=" + isPremiumOrder + ", replacedOrderId=" + replacedOrderId
				+ ", isReplacementOrder=" + isReplacementOrder + "]";
	}

	public String getTfmShipmentStatus() {
		return tfmShipmentStatus;
	}

	public void setTfmShipmentStatus(String tfmShipmentStatus) {
		this.tfmShipmentStatus = tfmShipmentStatus;
	}
    
    public Orders withIsReplacementOrder(Boolean isReplacementOrder) {
        this.isReplacementOrder = isReplacementOrder;
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
        amazonOrderId = r.read("AmazonOrderId", String.class);
        sellerOrderId = r.read("SellerOrderId", String.class);
        purchaseDate = r.read("PurchaseDate", XMLGregorianCalendar.class);
        lastUpdateDate = r.read("LastUpdateDate", XMLGregorianCalendar.class);
        orderStatus = r.read("OrderStatus", String.class);
        fulfillmentChannel = r.read("FulfillmentChannel", String.class);
        salesChannel = r.read("SalesChannel", String.class);
        orderChannel = r.read("OrderChannel", String.class);
        shipServiceLevel = r.read("ShipServiceLevel", String.class);
        shippingAddress = r.read("ShippingAddress", Address.class);
        orderTotal = r.read("OrderTotal", Money.class);
        numberOfItemsShipped = r.read("NumberOfItemsShipped", Integer.class);
        numberOfItemsUnshipped = r.read("NumberOfItemsUnshipped", Integer.class);
        paymentExecutionDetail = r.readList("PaymentExecutionDetail", "PaymentExecutionDetailItem", PaymentExecutionDetailItem.class);
        paymentMethod = r.read("PaymentMethod", String.class);
        paymentMethodDetails = r.readList("PaymentMethodDetails", "PaymentMethodDetail", String.class);
        marketplaceId = r.read("MarketplaceId", String.class);
        buyerEmail = r.read("BuyerEmail", String.class);
        buyerName = r.read("BuyerName", String.class);
        buyerCounty = r.read("BuyerCounty", String.class);
        buyerTaxInfo = r.read("BuyerTaxInfo", BuyerTaxInfo.class);
        shipmentServiceLevelCategory = r.read("ShipmentServiceLevelCategory", String.class);
        shippedByAmazonTFM = r.read("ShippedByAmazonTFM", Boolean.class);
        tfmShipmentStatus = r.read("TFMShipmentStatus", String.class);
        cbaDisplayableShippingLabel = r.read("CbaDisplayableShippingLabel", String.class);
        orderType = r.read("OrderType", String.class);
        earliestShipDate = r.read("EarliestShipDate", XMLGregorianCalendar.class);
        latestShipDate = r.read("LatestShipDate", XMLGregorianCalendar.class);
        earliestDeliveryDate = r.read("EarliestDeliveryDate", XMLGregorianCalendar.class);
        latestDeliveryDate = r.read("LatestDeliveryDate", XMLGregorianCalendar.class);
        isBusinessOrder = r.read("IsBusinessOrder", Boolean.class);
        purchaseOrderNumber = r.read("PurchaseOrderNumber", String.class);
        isPrime = r.read("IsPrime", Boolean.class);
        isPremiumOrder = r.read("IsPremiumOrder", Boolean.class);
        replacedOrderId = r.read("ReplacedOrderId", String.class);
        isReplacementOrder = r.read("IsReplacementOrder", Boolean.class);
    }

    /**
     * Write members to a MwsWriter.
     *
     * @param w
     *      The writer to write to.
     */
    @Override
    public void writeFragmentTo(MwsWriter w) {
        w.write("AmazonOrderId", amazonOrderId);
        w.write("SellerOrderId", sellerOrderId);
        w.write("PurchaseDate", purchaseDate);
        w.write("LastUpdateDate", lastUpdateDate);
        w.write("OrderStatus", orderStatus);
        w.write("FulfillmentChannel", fulfillmentChannel);
        w.write("SalesChannel", salesChannel);
        w.write("OrderChannel", orderChannel);
        w.write("ShipServiceLevel", shipServiceLevel);
        w.write("ShippingAddress", shippingAddress);
        w.write("OrderTotal", orderTotal);
        w.write("NumberOfItemsShipped", numberOfItemsShipped);
        w.write("NumberOfItemsUnshipped", numberOfItemsUnshipped);
        w.writeList("PaymentExecutionDetail", "PaymentExecutionDetailItem", paymentExecutionDetail);
        w.write("PaymentMethod", paymentMethod);
        w.writeList("PaymentMethodDetails", "PaymentMethodDetail", paymentMethodDetails);
        w.write("MarketplaceId", marketplaceId);
        w.write("BuyerEmail", buyerEmail);
        w.write("BuyerName", buyerName);
        w.write("BuyerCounty", buyerCounty);
        w.write("BuyerTaxInfo", buyerTaxInfo);
        w.write("ShipmentServiceLevelCategory", shipmentServiceLevelCategory);
        w.write("ShippedByAmazonTFM", shippedByAmazonTFM);
        w.write("TFMShipmentStatus", tfmShipmentStatus);
        w.write("CbaDisplayableShippingLabel", cbaDisplayableShippingLabel);
        w.write("OrderType", orderType);
        w.write("EarliestShipDate", earliestShipDate);
        w.write("LatestShipDate", latestShipDate);
        w.write("EarliestDeliveryDate", earliestDeliveryDate);
        w.write("LatestDeliveryDate", latestDeliveryDate);
        w.write("IsBusinessOrder", isBusinessOrder);
        w.write("PurchaseOrderNumber", purchaseOrderNumber);
        w.write("IsPrime", isPrime);
        w.write("IsPremiumOrder", isPremiumOrder);
        w.write("ReplacedOrderId", replacedOrderId);
        w.write("IsReplacementOrder", isReplacementOrder);
    }

    /**
     * Write tag, xmlns and members to a MwsWriter.
     *
     * @param w
     *         The Writer to write to.
     */
    @Override
    public void writeTo(MwsWriter w) {
        w.write("https://mws.amazonservices.com/Orders/2013-09-01", "Order",this);
    }

    /** Value constructor. */
    public Orders(String amazonOrderId,XMLGregorianCalendar purchaseDate,XMLGregorianCalendar lastUpdateDate,String orderStatus) {
        this.amazonOrderId = amazonOrderId;
        this.purchaseDate = purchaseDate;
        this.lastUpdateDate = lastUpdateDate;
        this.orderStatus = orderStatus;
    }    

    /** Default constructor. */
    public Orders() {
        super();
    }

/** Value constructor. */
    public Orders(String amazonOrderId,String sellerOrderId,XMLGregorianCalendar purchaseDate,XMLGregorianCalendar lastUpdateDate,String orderStatus,String fulfillmentChannel,String salesChannel,String orderChannel,String shipServiceLevel,Address shippingAddress,Money orderTotal,Integer numberOfItemsShipped,Integer numberOfItemsUnshipped,List<PaymentExecutionDetailItem> paymentExecutionDetail,String paymentMethod,String marketplaceId,String buyerEmail,String buyerName,String shipmentServiceLevelCategory,Boolean shippedByAmazonTFM,String tfmShipmentStatus,String cbaDisplayableShippingLabel,String orderType,XMLGregorianCalendar earliestShipDate,XMLGregorianCalendar latestShipDate,XMLGregorianCalendar earliestDeliveryDate,XMLGregorianCalendar latestDeliveryDate,Boolean isBusinessOrder,String purchaseOrderNumber,Boolean isPrime) {
        this.amazonOrderId = amazonOrderId;
        this.sellerOrderId = sellerOrderId;
        this.purchaseDate = purchaseDate;
        this.lastUpdateDate = lastUpdateDate;
        this.orderStatus = orderStatus;
        this.fulfillmentChannel = fulfillmentChannel;
        this.salesChannel = salesChannel;
        this.orderChannel = orderChannel;
        this.shipServiceLevel = shipServiceLevel;
        this.shippingAddress = shippingAddress;
        this.orderTotal = orderTotal;
        this.numberOfItemsShipped = numberOfItemsShipped;
        this.numberOfItemsUnshipped = numberOfItemsUnshipped;
        this.paymentExecutionDetail = paymentExecutionDetail;
        this.paymentMethod = paymentMethod;
        this.marketplaceId = marketplaceId;
        this.buyerEmail = buyerEmail;
        this.buyerName = buyerName;
        this.shipmentServiceLevelCategory = shipmentServiceLevelCategory;
        this.shippedByAmazonTFM = shippedByAmazonTFM;
        this.tfmShipmentStatus = tfmShipmentStatus;
        this.cbaDisplayableShippingLabel = cbaDisplayableShippingLabel;
        this.orderType = orderType;
        this.earliestShipDate = earliestShipDate;
        this.latestShipDate = latestShipDate;
        this.earliestDeliveryDate = earliestDeliveryDate;
        this.latestDeliveryDate = latestDeliveryDate;
        this.isBusinessOrder = isBusinessOrder;
        this.purchaseOrderNumber = purchaseOrderNumber;
        this.isPrime = isPrime;
    }

	public Timestamp getPurchaseDateConverted() {
		return purchaseDateConverted;
	}
	
	public void setPurchaseDateConverted(Timestamp purchaseDateConverted) {
		this.purchaseDateConverted = purchaseDateConverted;
	}
	
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}
	
	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddressLine1() {
		return addressLine1;
	}
	
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	public String getAddressLine2() {
		return addressLine2;
	}
	
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	public String getAddressLine3() {
		return addressLine3;
	}
	
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCounty() {
		return county;
	}
	
	public void setCounty(String county) {
		this.county = county;
	}
	
	public String getDistrict() {
		return district;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public String getStateOrRegion() {
		return stateOrRegion;
	}
	
	public void setStateOrRegion(String stateOrRegion) {
		this.stateOrRegion = stateOrRegion;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddressType() {
		return addressType;
	}
	
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	
	public String getOrderCost() {
		return orderCost;
	}
	
	public void setOrderCost(String orderCost) {
		this.orderCost = orderCost;
	}
	
	public String getOrderCurrency() {
		return orderCurrency;
	}
	
	public void setOrderCurrency(String string) {
		this.orderCurrency = string;
	}
	
	public Timestamp getEarliestShipment() {
		return earliestShipment;
	}
	
	public void setEarliestShipment(Timestamp earliestShipment) {
		this.earliestShipment = earliestShipment;
	}
	
	public Timestamp getLatestShipment() {
		return latestShipment;
	}
	
	public void setLatestShipment(Timestamp latestShipment) {
		this.latestShipment = latestShipment;
	}
	
	public Timestamp getEarliestDelivery() {
		return earliestDelivery;
	}
	
	public void setEarliestDelivery(Timestamp earliestDelivery) {
		this.earliestDelivery = earliestDelivery;
	}
	
	public Timestamp getLatestDelivery() {
		return latestDelivery;
	}
	
	public void setLatestDelivery(Timestamp latestDelivery) {
		this.latestDelivery = latestDelivery;
	}

	public String getBuyerTIString() {
		return buyerTIString;
	}

	public void setBuyerTIString(String buyerTIString) {
		this.buyerTIString = buyerTIString;
	}

	public String getPaymentMethodDetail() {
		return paymentMethodDetail;
	}

	public void setPaymentMethodDetail(String paymentMethodDetail) {
		this.paymentMethodDetail = paymentMethodDetail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    
    
    
}
