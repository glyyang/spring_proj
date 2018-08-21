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
 * Order Item
 * API Version: 2013-09-01
 * Library Version: 2018-01-31
 * Generated: Tue Jan 30 16:03:16 PST 2018
 */
package com.g128.model;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;

import com.g128.amazonservice.mws.client.*;

/**
 * OrderItem complex type.
 *
 * XML schema:
 *
 * <pre>
 * &lt;complexType name="OrderItem"&gt;
 *    &lt;complexContent&gt;
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *          &lt;sequence&gt;
 *             &lt;element name="ASIN" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *             &lt;element name="SellerSKU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="OrderItemId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *             &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="QuantityOrdered" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *             &lt;element name="QuantityShipped" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *             &lt;element name="ProductInfo" type="{https://mws.amazonservices.com/Orders/2013-09-01}ProductInfoDetail" minOccurs="0"/&gt;
 *             &lt;element name="PointsGranted" type="{https://mws.amazonservices.com/Orders/2013-09-01}PointsGrantedDetail" minOccurs="0"/&gt;
 *             &lt;element name="ItemPrice" type="{https://mws.amazonservices.com/Orders/2013-09-01}Money" minOccurs="0"/&gt;
 *             &lt;element name="ShippingPrice" type="{https://mws.amazonservices.com/Orders/2013-09-01}Money" minOccurs="0"/&gt;
 *             &lt;element name="GiftWrapPrice" type="{https://mws.amazonservices.com/Orders/2013-09-01}Money" minOccurs="0"/&gt;
 *             &lt;element name="ItemTax" type="{https://mws.amazonservices.com/Orders/2013-09-01}Money" minOccurs="0"/&gt;
 *             &lt;element name="ShippingTax" type="{https://mws.amazonservices.com/Orders/2013-09-01}Money" minOccurs="0"/&gt;
 *             &lt;element name="GiftWrapTax" type="{https://mws.amazonservices.com/Orders/2013-09-01}Money" minOccurs="0"/&gt;
 *             &lt;element name="ShippingDiscount" type="{https://mws.amazonservices.com/Orders/2013-09-01}Money" minOccurs="0"/&gt;
 *             &lt;element name="PromotionDiscount" type="{https://mws.amazonservices.com/Orders/2013-09-01}Money" minOccurs="0"/&gt;
 *             &lt;element name="PromotionIds" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
 *             &lt;element name="CODFee" type="{https://mws.amazonservices.com/Orders/2013-09-01}Money" minOccurs="0"/&gt;
 *             &lt;element name="CODFeeDiscount" type="{https://mws.amazonservices.com/Orders/2013-09-01}Money" minOccurs="0"/&gt;
 *             &lt;element name="IsGift" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *             &lt;element name="GiftMessageText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="GiftWrapLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="InvoiceData" type="{https://mws.amazonservices.com/Orders/2013-09-01}InvoiceData" minOccurs="0"/&gt;
 *             &lt;element name="ConditionNote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="ConditionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="ConditionSubtypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="ScheduledDeliveryStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="ScheduledDeliveryEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="PriceDesignation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *             &lt;element name="BuyerCustomizedInfo" type="{https://mws.amazonservices.com/Orders/2013-09-01}BuyerCustomizedInfoDetail" minOccurs="0"/&gt;
 *             &lt;element name="TaxCollection" type="{https://mws.amazonservices.com/Orders/2013-09-01}TaxCollection" minOccurs="0"/&gt;
 *          &lt;/sequence&gt;
 *       &lt;/restriction&gt;
 *    &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlRootElement(name="OrderItem", namespace="com.g128.model.Orders")
@Entity
@Table(name="Amazon_Order_Item")
public class OrderItem extends AbstractMwsObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
//	@XmlElement(name="ASIN")
	@Column
    private String asin;

//	@XmlElement(name="SKU")
	@Column
    private String sellerSKU;

	@Column(name="item_id")
    private String orderItemId;

//	@XmlElement(name="ProductName")
	@Column(name="item_name")
    private String title;

	@Column(name="ordered_quantity")
    private int quantityOrdered;

	@Column(name="shipped_quantity")
    private Integer quantityShipped;
	
//	@XmlElement(name="ItemStatus")
	@Transient
	private String itemStatus;

	@Transient
    private ProductInfoDetail productInfo;
	
//	@XmlElement(name="Quantity")
	@Column(name="number_items")
	private int itemCount;

	@ManyToOne
	@JoinColumn(name="strategy_id", referencedColumnName="id")
    private PointsGrantedDetail pointsGranted;

	@Embedded
	@AttributeOverrides(value={@AttributeOverride(name="currencyCode", column=@Column(name="item_price_currency")), @AttributeOverride(name="amount",column=@Column(name="item_price_amount"))})
//    @XmlElement(name="ItemPrice")
	private Money itemPrice;
	
	@Transient
	private XmlMoney item_price;

	

	@Embedded
	@AttributeOverrides(value={@AttributeOverride(name="currencyCode", column=@Column(name="shipping_price_currency")), @AttributeOverride(name="amount",column=@Column(name="shipping_price_amount"))})
    private Money shippingPrice;

	@Embedded
	@AttributeOverrides(value={@AttributeOverride(name="currencyCode", column=@Column(name="gift_wrap_price_currency")), @AttributeOverride(name="amount",column=@Column(name="gift_wrap_price_amount"))})
    private Money giftWrapPrice;

	@Embedded
	@AttributeOverrides(value={@AttributeOverride(name="currencyCode", column=@Column(name="item_tax_currency")), @AttributeOverride(name="amount",column=@Column(name="item_tax_amount"))})
    private Money itemTax;

	@Embedded
	@AttributeOverrides(value={@AttributeOverride(name="currencyCode", column=@Column(name="shipping_tax_currency")), @AttributeOverride(name="amount",column=@Column(name="shipping_tax_amount"))})
    private Money shippingTax;

	@Embedded
	@AttributeOverrides(value={@AttributeOverride(name="currencyCode", column=@Column(name="gift_wrapping_tax_currency")), @AttributeOverride(name="amount",column=@Column(name="gift_wrapping_tax_amount"))})
    private Money giftWrapTax;

	@Embedded
	@AttributeOverrides(value={@AttributeOverride(name="currencyCode", column=@Column(name="shipping_discount_currency")), @AttributeOverride(name="amount",column=@Column(name="shipping_discount_amount"))})
    private Money shippingDiscount;

//	@XmlElement(name="Promotion")
	@Transient
	private Promotion promo;
	@Embedded
	@AttributeOverrides(value={@AttributeOverride(name="currencyCode", column=@Column(name="promo_discount_currency")), @AttributeOverride(name="amount",column=@Column(name="promo_discount_amount"))})
    private Money promotionDiscount;

	@ElementCollection
	@CollectionTable(name="promotion_ids", joinColumns=@JoinColumn(name="promo_id"))
	@Column(name="promotion_id")
    private List<String> promotionIds;

	@Embedded
	@AttributeOverrides(value={@AttributeOverride(name="currencyCode", column=@Column(name="cod_fee_currency")), @AttributeOverride(name="amount",column=@Column(name="cod_fee_amount"))})
    private Money codFee;

	@Embedded
	@AttributeOverrides(value={@AttributeOverride(name="currencyCode", column=@Column(name="cod_fee_discount_currency")), @AttributeOverride(name="amount",column=@Column(name="cod_fee_discount_amount"))})
    private Money codFeeDiscount;

	@Column(name="is_gift")
    private Boolean isGift;

	@Column(name="gift_msg")
    private String giftMessageText;

	@Column(name="wrapper_lvl")
    private String giftWrapLevel;

	@Embedded
	@AttributeOverrides(value={@AttributeOverride(name="invoiceRequirement", column=@Column(name="invoice_requirement")), @AttributeOverride(name="buyerSelectedInvoiceCategory", column=@Column(name="invoice_category")), @AttributeOverride(name="invoiceTitle", column=@Column(name="invoice_title")), @AttributeOverride(name="invoiceInformation", column=@Column(name="invoice_info"))})
    private InvoiceData invoiceData;

	@Column(name="condition_note")
    private String conditionNote;

	@Column(name="condition_id")
    private String conditionId;

	@Column(name="condition_subtype_id")
    private String conditionSubtypeId;

	@Column(name="sched_deliv_start")
    private String scheduledDeliveryStartDate;

	@Column(name="sched_deliv_end")
    private String scheduledDeliveryEndDate;

	@Column(name="price_designation")
    private String priceDesignation;

	@Embedded
	@AttributeOverride(name="customizedURL", column=@Column(name="buyer_url"))
    private BuyerCustomizedInfoDetail buyerCustomizedInfo;
	
	@Embedded
	@AttributeOverrides(value={@AttributeOverride(name="model", column=@Column(name="tax_collection_model")),@AttributeOverride(name="responsibleParty", column=@Column(name="tax_collection_party"))})
    private TaxCollection taxCollection;

	@ManyToOne
	@JoinColumn(name="ordered_id", referencedColumnName="order_id")
	private Orders ord;
	
	@XmlElement(name="ItemPrice")
	public XmlMoney getItem_price() {
		return item_price;
	}

	public void setItem_price(XmlMoney item_price) {
		this.item_price = item_price;
	}
	
	@XmlElement(name="ItemStatus")
    public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	@XmlElement(name="Promotion")
	public Promotion getPromo() {
		return promo;
	}

	public void setPromo(Promotion promo) {
		this.promo = promo;
	}

	public Orders getOrd() {
		return ord;
	}

	public void setOrd(Orders ord) {
		this.ord = ord;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name="ASIN")
	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	@XmlElement(name="Quantity")
	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public Money getCodFee() {
		return codFee;
	}

	public void setCodFee(Money codFee) {
		this.codFee = codFee;
	}

	public Money getCodFeeDiscount() {
		return codFeeDiscount;
	}

	public void setCodFeeDiscount(Money codFeeDiscount) {
		this.codFeeDiscount = codFeeDiscount;
	}

	/**
     * Get the value of ASIN.
     *
     * @return The value of ASIN.
     */
    public String getASIN() {
        return asin;
    }

    /**
     * Set the value of ASIN.
     *
     * @param asin
     *            The new value to set.
     */
    public void setASIN(String asin) {
        this.asin = asin;
    }

    /**
     * Check to see if ASIN is set.
     *
     * @return true if ASIN is set.
     */
    public boolean isSetASIN() {
        return asin != null;
    }

    /**
     * Set the value of ASIN, return this.
     *
     * @param asin
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withASIN(String asin) {
        this.asin = asin;
        return this;
    }

    /**
     * Get the value of SellerSKU.
     *
     * @return The value of SellerSKU.
     */
    @XmlElement(name="SKU")
    public String getSellerSKU() {
        return sellerSKU;
    }

    /**
     * Set the value of SellerSKU.
     *
     * @param sellerSKU
     *            The new value to set.
     */
    public void setSellerSKU(String sellerSKU) {
        this.sellerSKU = sellerSKU;
    }

    /**
     * Check to see if SellerSKU is set.
     *
     * @return true if SellerSKU is set.
     */
    public boolean isSetSellerSKU() {
        return sellerSKU != null;
    }

    /**
     * Set the value of SellerSKU, return this.
     *
     * @param sellerSKU
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withSellerSKU(String sellerSKU) {
        this.sellerSKU = sellerSKU;
        return this;
    }

    /**
     * Get the value of OrderItemId.
     *
     * @return The value of OrderItemId.
     */
    public String getOrderItemId() {
        return orderItemId;
    }

    /**
     * Set the value of OrderItemId.
     *
     * @param orderItemId
     *            The new value to set.
     */
    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    /**
     * Check to see if OrderItemId is set.
     *
     * @return true if OrderItemId is set.
     */
    public boolean isSetOrderItemId() {
        return orderItemId != null;
    }

    /**
     * Set the value of OrderItemId, return this.
     *
     * @param orderItemId
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
        return this;
    }

    /**
     * Get the value of Title.
     *
     * @return The value of Title.
     */
    @XmlElement(name="ProductName")
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of Title.
     *
     * @param title
     *            The new value to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Check to see if Title is set.
     *
     * @return true if Title is set.
     */
    public boolean isSetTitle() {
        return title != null;
    }

    /**
     * Set the value of Title, return this.
     *
     * @param title
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get the value of QuantityOrdered.
     *
     * @return The value of QuantityOrdered.
     */
    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    /**
     * Set the value of QuantityOrdered.
     *
     * @param quantityOrdered
     *            The new value to set.
     */
    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    /**
     * Set the value of QuantityOrdered, return this.
     *
     * @param quantityOrdered
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
        return this;
    }

    /**
     * Get the value of QuantityShipped.
     *
     * @return The value of QuantityShipped.
     */
    public Integer getQuantityShipped() {
        return quantityShipped;
    }

    /**
     * Set the value of QuantityShipped.
     *
     * @param quantityShipped
     *            The new value to set.
     */
    public void setQuantityShipped(Integer quantityShipped) {
        this.quantityShipped = quantityShipped;
    }

    /**
     * Check to see if QuantityShipped is set.
     *
     * @return true if QuantityShipped is set.
     */
    public boolean isSetQuantityShipped() {
        return quantityShipped != null;
    }

    /**
     * Set the value of QuantityShipped, return this.
     *
     * @param quantityShipped
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withQuantityShipped(Integer quantityShipped) {
        this.quantityShipped = quantityShipped;
        return this;
    }

    /**
     * Get the value of ProductInfo.
     *
     * @return The value of ProductInfo.
     */
    public ProductInfoDetail getProductInfo() {
        return productInfo;
    }

    /**
     * Set the value of ProductInfo.
     *
     * @param productInfo
     *            The new value to set.
     */
    public void setProductInfo(ProductInfoDetail productInfo) {
        this.productInfo = productInfo;
    }

    /**
     * Check to see if ProductInfo is set.
     *
     * @return true if ProductInfo is set.
     */
    public boolean isSetProductInfo() {
        return productInfo != null;
    }

    /**
     * Set the value of ProductInfo, return this.
     *
     * @param productInfo
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withProductInfo(ProductInfoDetail productInfo) {
        this.productInfo = productInfo;
        return this;
    }

    /**
     * Get the value of PointsGranted.
     *
     * @return The value of PointsGranted.
     */
    public PointsGrantedDetail getPointsGranted() {
        return pointsGranted;
    }

    /**
     * Set the value of PointsGranted.
     *
     * @param pointsGranted
     *            The new value to set.
     */
    public void setPointsGranted(PointsGrantedDetail pointsGranted) {
        this.pointsGranted = pointsGranted;
    }

    /**
     * Check to see if PointsGranted is set.
     *
     * @return true if PointsGranted is set.
     */
    public boolean isSetPointsGranted() {
        return pointsGranted != null;
    }

    /**
     * Set the value of PointsGranted, return this.
     *
     * @param pointsGranted
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withPointsGranted(PointsGrantedDetail pointsGranted) {
        this.pointsGranted = pointsGranted;
        return this;
    }

    /**
     * Get the value of ItemPrice.
     *
     * @return The value of ItemPrice.
     */
//    @XmlElement(name="ItemPrice")
    public Money getItemPrice() {
        return itemPrice;
    }

    /**
     * Set the value of ItemPrice.
     *
     * @param itemPrice
     *            The new value to set.
     */
    public void setItemPrice(Money itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * Check to see if ItemPrice is set.
     *
     * @return true if ItemPrice is set.
     */
    public boolean isSetItemPrice() {
        return itemPrice != null;
    }

    /**
     * Set the value of ItemPrice, return this.
     *
     * @param itemPrice
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withItemPrice(Money itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    /**
     * Get the value of ShippingPrice.
     *
     * @return The value of ShippingPrice.
     */
    public Money getShippingPrice() {
        return shippingPrice;
    }

    /**
     * Set the value of ShippingPrice.
     *
     * @param shippingPrice
     *            The new value to set.
     */
    public void setShippingPrice(Money shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    /**
     * Check to see if ShippingPrice is set.
     *
     * @return true if ShippingPrice is set.
     */
    public boolean isSetShippingPrice() {
        return shippingPrice != null;
    }

    /**
     * Set the value of ShippingPrice, return this.
     *
     * @param shippingPrice
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withShippingPrice(Money shippingPrice) {
        this.shippingPrice = shippingPrice;
        return this;
    }

    /**
     * Get the value of GiftWrapPrice.
     *
     * @return The value of GiftWrapPrice.
     */
    public Money getGiftWrapPrice() {
        return giftWrapPrice;
    }

    /**
     * Set the value of GiftWrapPrice.
     *
     * @param giftWrapPrice
     *            The new value to set.
     */
    public void setGiftWrapPrice(Money giftWrapPrice) {
        this.giftWrapPrice = giftWrapPrice;
    }

    /**
     * Check to see if GiftWrapPrice is set.
     *
     * @return true if GiftWrapPrice is set.
     */
    public boolean isSetGiftWrapPrice() {
        return giftWrapPrice != null;
    }

    /**
     * Set the value of GiftWrapPrice, return this.
     *
     * @param giftWrapPrice
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withGiftWrapPrice(Money giftWrapPrice) {
        this.giftWrapPrice = giftWrapPrice;
        return this;
    }

    /**
     * Get the value of ItemTax.
     *
     * @return The value of ItemTax.
     */
    public Money getItemTax() {
        return itemTax;
    }

    /**
     * Set the value of ItemTax.
     *
     * @param itemTax
     *            The new value to set.
     */
    public void setItemTax(Money itemTax) {
        this.itemTax = itemTax;
    }

    /**
     * Check to see if ItemTax is set.
     *
     * @return true if ItemTax is set.
     */
    public boolean isSetItemTax() {
        return itemTax != null;
    }

    /**
     * Set the value of ItemTax, return this.
     *
     * @param itemTax
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withItemTax(Money itemTax) {
        this.itemTax = itemTax;
        return this;
    }

    /**
     * Get the value of ShippingTax.
     *
     * @return The value of ShippingTax.
     */
    public Money getShippingTax() {
        return shippingTax;
    }

    /**
     * Set the value of ShippingTax.
     *
     * @param shippingTax
     *            The new value to set.
     */
    public void setShippingTax(Money shippingTax) {
        this.shippingTax = shippingTax;
    }

    /**
     * Check to see if ShippingTax is set.
     *
     * @return true if ShippingTax is set.
     */
    public boolean isSetShippingTax() {
        return shippingTax != null;
    }

    /**
     * Set the value of ShippingTax, return this.
     *
     * @param shippingTax
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withShippingTax(Money shippingTax) {
        this.shippingTax = shippingTax;
        return this;
    }

    /**
     * Get the value of GiftWrapTax.
     *
     * @return The value of GiftWrapTax.
     */
    public Money getGiftWrapTax() {
        return giftWrapTax;
    }

    /**
     * Set the value of GiftWrapTax.
     *
     * @param giftWrapTax
     *            The new value to set.
     */
    public void setGiftWrapTax(Money giftWrapTax) {
        this.giftWrapTax = giftWrapTax;
    }

    /**
     * Check to see if GiftWrapTax is set.
     *
     * @return true if GiftWrapTax is set.
     */
    public boolean isSetGiftWrapTax() {
        return giftWrapTax != null;
    }

    /**
     * Set the value of GiftWrapTax, return this.
     *
     * @param giftWrapTax
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withGiftWrapTax(Money giftWrapTax) {
        this.giftWrapTax = giftWrapTax;
        return this;
    }

    /**
     * Get the value of ShippingDiscount.
     *
     * @return The value of ShippingDiscount.
     */
    public Money getShippingDiscount() {
        return shippingDiscount;
    }

    /**
     * Set the value of ShippingDiscount.
     *
     * @param shippingDiscount
     *            The new value to set.
     */
    public void setShippingDiscount(Money shippingDiscount) {
        this.shippingDiscount = shippingDiscount;
    }

    /**
     * Check to see if ShippingDiscount is set.
     *
     * @return true if ShippingDiscount is set.
     */
    public boolean isSetShippingDiscount() {
        return shippingDiscount != null;
    }

    /**
     * Set the value of ShippingDiscount, return this.
     *
     * @param shippingDiscount
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withShippingDiscount(Money shippingDiscount) {
        this.shippingDiscount = shippingDiscount;
        return this;
    }

    /**
     * Get the value of PromotionDiscount.
     *
     * @return The value of PromotionDiscount.
     */
    public Money getPromotionDiscount() {
        return promotionDiscount;
    }

    /**
     * Set the value of PromotionDiscount.
     *
     * @param promotionDiscount
     *            The new value to set.
     */
    public void setPromotionDiscount(Money promotionDiscount) {
        this.promotionDiscount = promotionDiscount;
    }

    /**
     * Check to see if PromotionDiscount is set.
     *
     * @return true if PromotionDiscount is set.
     */
    public boolean isSetPromotionDiscount() {
        return promotionDiscount != null;
    }

    /**
     * Set the value of PromotionDiscount, return this.
     *
     * @param promotionDiscount
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withPromotionDiscount(Money promotionDiscount) {
        this.promotionDiscount = promotionDiscount;
        return this;
    }

    /**
     * Get the value of PromotionIds.
     *
     * @return The value of PromotionIds.
     */
    public List<String> getPromotionIds() {
        if (promotionIds==null) {
            promotionIds = new ArrayList<String>();
        }
        return promotionIds;
    }

    /**
     * Set the value of PromotionIds.
     *
     * @param promotionIds
     *            The new value to set.
     */
    public void setPromotionIds(List<String> promotionIds) {
        this.promotionIds = promotionIds;
    }

    /**
     * Clear PromotionIds.
     */
    public void unsetPromotionIds() {
        this.promotionIds = null;
    }

    /**
     * Check to see if PromotionIds is set.
     *
     * @return true if PromotionIds is set.
     */
    public boolean isSetPromotionIds() {
        return promotionIds != null && !promotionIds.isEmpty();
    }

    /**
     * Add values for PromotionIds, return this.
     *
     * @param promotionIds
     *             New values to add.
     *
     * @return This instance.
     */
    public OrderItem withPromotionIds(String... values) {
        List<String> list = getPromotionIds();
        for (String value : values) {
            list.add(value);
        }
        return this;
    }

    /**
     * Get the value of CODFee.
     *
     * @return The value of CODFee.
     */
    public Money getCODFee() {
        return codFee;
    }

    /**
     * Set the value of CODFee.
     *
     * @param codFee
     *            The new value to set.
     */
    public void setCODFee(Money codFee) {
        this.codFee = codFee;
    }

    /**
     * Check to see if CODFee is set.
     *
     * @return true if CODFee is set.
     */
    public boolean isSetCODFee() {
        return codFee != null;
    }

    /**
     * Set the value of CODFee, return this.
     *
     * @param codFee
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withCODFee(Money codFee) {
        this.codFee = codFee;
        return this;
    }

    /**
     * Get the value of CODFeeDiscount.
     *
     * @return The value of CODFeeDiscount.
     */
    public Money getCODFeeDiscount() {
        return codFeeDiscount;
    }

    /**
     * Set the value of CODFeeDiscount.
     *
     * @param codFeeDiscount
     *            The new value to set.
     */
    public void setCODFeeDiscount(Money codFeeDiscount) {
        this.codFeeDiscount = codFeeDiscount;
    }

    /**
     * Check to see if CODFeeDiscount is set.
     *
     * @return true if CODFeeDiscount is set.
     */
    public boolean isSetCODFeeDiscount() {
        return codFeeDiscount != null;
    }

    /**
     * Set the value of CODFeeDiscount, return this.
     *
     * @param codFeeDiscount
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withCODFeeDiscount(Money codFeeDiscount) {
        this.codFeeDiscount = codFeeDiscount;
        return this;
    }

    /**
     * Check the value of IsGift.
     *
     * @return true if IsGift is set to true.
     */
    public boolean isIsGift() {
        return isGift!=null && isGift.booleanValue();
    }

    /**
     * Get the value of IsGift.
     *
     * @return The value of IsGift.
     */
    public Boolean getIsGift() {
        return isGift;
    }

    /**
     * Set the value of IsGift.
     *
     * @param isGift
     *            The new value to set.
     */
    public void setIsGift(Boolean isGift) {
        this.isGift = isGift;
    }

    /**
     * Check to see if IsGift is set.
     *
     * @return true if IsGift is set.
     */
    public boolean isSetIsGift() {
        return isGift != null;
    }

    /**
     * Set the value of IsGift, return this.
     *
     * @param isGift
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withIsGift(Boolean isGift) {
        this.isGift = isGift;
        return this;
    }

    /**
     * Get the value of GiftMessageText.
     *
     * @return The value of GiftMessageText.
     */
    public String getGiftMessageText() {
        return giftMessageText;
    }

    /**
     * Set the value of GiftMessageText.
     *
     * @param giftMessageText
     *            The new value to set.
     */
    public void setGiftMessageText(String giftMessageText) {
        this.giftMessageText = giftMessageText;
    }

    /**
     * Check to see if GiftMessageText is set.
     *
     * @return true if GiftMessageText is set.
     */
    public boolean isSetGiftMessageText() {
        return giftMessageText != null;
    }

    /**
     * Set the value of GiftMessageText, return this.
     *
     * @param giftMessageText
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withGiftMessageText(String giftMessageText) {
        this.giftMessageText = giftMessageText;
        return this;
    }

    /**
     * Get the value of GiftWrapLevel.
     *
     * @return The value of GiftWrapLevel.
     */
    public String getGiftWrapLevel() {
        return giftWrapLevel;
    }

    /**
     * Set the value of GiftWrapLevel.
     *
     * @param giftWrapLevel
     *            The new value to set.
     */
    public void setGiftWrapLevel(String giftWrapLevel) {
        this.giftWrapLevel = giftWrapLevel;
    }

    /**
     * Check to see if GiftWrapLevel is set.
     *
     * @return true if GiftWrapLevel is set.
     */
    public boolean isSetGiftWrapLevel() {
        return giftWrapLevel != null;
    }

    /**
     * Set the value of GiftWrapLevel, return this.
     *
     * @param giftWrapLevel
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withGiftWrapLevel(String giftWrapLevel) {
        this.giftWrapLevel = giftWrapLevel;
        return this;
    }

    /**
     * Get the value of InvoiceData.
     *
     * @return The value of InvoiceData.
     */
    public InvoiceData getInvoiceData() {
        return invoiceData;
    }

    /**
     * Set the value of InvoiceData.
     *
     * @param invoiceData
     *            The new value to set.
     */
    public void setInvoiceData(InvoiceData invoiceData) {
        this.invoiceData = invoiceData;
    }

    /**
     * Check to see if InvoiceData is set.
     *
     * @return true if InvoiceData is set.
     */
    public boolean isSetInvoiceData() {
        return invoiceData != null;
    }

    /**
     * Set the value of InvoiceData, return this.
     *
     * @param invoiceData
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withInvoiceData(InvoiceData invoiceData) {
        this.invoiceData = invoiceData;
        return this;
    }

    /**
     * Get the value of ConditionNote.
     *
     * @return The value of ConditionNote.
     */
    public String getConditionNote() {
        return conditionNote;
    }

    /**
     * Set the value of ConditionNote.
     *
     * @param conditionNote
     *            The new value to set.
     */
    public void setConditionNote(String conditionNote) {
        this.conditionNote = conditionNote;
    }

    /**
     * Check to see if ConditionNote is set.
     *
     * @return true if ConditionNote is set.
     */
    public boolean isSetConditionNote() {
        return conditionNote != null;
    }

    /**
     * Set the value of ConditionNote, return this.
     *
     * @param conditionNote
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withConditionNote(String conditionNote) {
        this.conditionNote = conditionNote;
        return this;
    }

    /**
     * Get the value of ConditionId.
     *
     * @return The value of ConditionId.
     */
    public String getConditionId() {
        return conditionId;
    }

    /**
     * Set the value of ConditionId.
     *
     * @param conditionId
     *            The new value to set.
     */
    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    /**
     * Check to see if ConditionId is set.
     *
     * @return true if ConditionId is set.
     */
    public boolean isSetConditionId() {
        return conditionId != null;
    }

    /**
     * Set the value of ConditionId, return this.
     *
     * @param conditionId
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withConditionId(String conditionId) {
        this.conditionId = conditionId;
        return this;
    }

    /**
     * Get the value of ConditionSubtypeId.
     *
     * @return The value of ConditionSubtypeId.
     */
    public String getConditionSubtypeId() {
        return conditionSubtypeId;
    }

    /**
     * Set the value of ConditionSubtypeId.
     *
     * @param conditionSubtypeId
     *            The new value to set.
     */
    public void setConditionSubtypeId(String conditionSubtypeId) {
        this.conditionSubtypeId = conditionSubtypeId;
    }

    /**
     * Check to see if ConditionSubtypeId is set.
     *
     * @return true if ConditionSubtypeId is set.
     */
    public boolean isSetConditionSubtypeId() {
        return conditionSubtypeId != null;
    }

    /**
     * Set the value of ConditionSubtypeId, return this.
     *
     * @param conditionSubtypeId
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withConditionSubtypeId(String conditionSubtypeId) {
        this.conditionSubtypeId = conditionSubtypeId;
        return this;
    }

    /**
     * Get the value of ScheduledDeliveryStartDate.
     *
     * @return The value of ScheduledDeliveryStartDate.
     */
    public String getScheduledDeliveryStartDate() {
        return scheduledDeliveryStartDate;
    }

    /**
     * Set the value of ScheduledDeliveryStartDate.
     *
     * @param scheduledDeliveryStartDate
     *            The new value to set.
     */
    public void setScheduledDeliveryStartDate(String scheduledDeliveryStartDate) {
        this.scheduledDeliveryStartDate = scheduledDeliveryStartDate;
    }

    /**
     * Check to see if ScheduledDeliveryStartDate is set.
     *
     * @return true if ScheduledDeliveryStartDate is set.
     */
    public boolean isSetScheduledDeliveryStartDate() {
        return scheduledDeliveryStartDate != null;
    }

    /**
     * Set the value of ScheduledDeliveryStartDate, return this.
     *
     * @param scheduledDeliveryStartDate
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withScheduledDeliveryStartDate(String scheduledDeliveryStartDate) {
        this.scheduledDeliveryStartDate = scheduledDeliveryStartDate;
        return this;
    }

    /**
     * Get the value of ScheduledDeliveryEndDate.
     *
     * @return The value of ScheduledDeliveryEndDate.
     */
    public String getScheduledDeliveryEndDate() {
        return scheduledDeliveryEndDate;
    }

    /**
     * Set the value of ScheduledDeliveryEndDate.
     *
     * @param scheduledDeliveryEndDate
     *            The new value to set.
     */
    public void setScheduledDeliveryEndDate(String scheduledDeliveryEndDate) {
        this.scheduledDeliveryEndDate = scheduledDeliveryEndDate;
    }

    /**
     * Check to see if ScheduledDeliveryEndDate is set.
     *
     * @return true if ScheduledDeliveryEndDate is set.
     */
    public boolean isSetScheduledDeliveryEndDate() {
        return scheduledDeliveryEndDate != null;
    }

    /**
     * Set the value of ScheduledDeliveryEndDate, return this.
     *
     * @param scheduledDeliveryEndDate
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withScheduledDeliveryEndDate(String scheduledDeliveryEndDate) {
        this.scheduledDeliveryEndDate = scheduledDeliveryEndDate;
        return this;
    }

    /**
     * Get the value of PriceDesignation.
     *
     * @return The value of PriceDesignation.
     */
    public String getPriceDesignation() {
        return priceDesignation;
    }

    /**
     * Set the value of PriceDesignation.
     *
     * @param priceDesignation
     *            The new value to set.
     */
    public void setPriceDesignation(String priceDesignation) {
        this.priceDesignation = priceDesignation;
    }

    /**
     * Check to see if PriceDesignation is set.
     *
     * @return true if PriceDesignation is set.
     */
    public boolean isSetPriceDesignation() {
        return priceDesignation != null;
    }

    /**
     * Set the value of PriceDesignation, return this.
     *
     * @param priceDesignation
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withPriceDesignation(String priceDesignation) {
        this.priceDesignation = priceDesignation;
        return this;
    }

    /**
     * Get the value of BuyerCustomizedInfo.
     *
     * @return The value of BuyerCustomizedInfo.
     */
    public BuyerCustomizedInfoDetail getBuyerCustomizedInfo() {
        return buyerCustomizedInfo;
    }

    /**
     * Set the value of BuyerCustomizedInfo.
     *
     * @param buyerCustomizedInfo
     *            The new value to set.
     */
    public void setBuyerCustomizedInfo(BuyerCustomizedInfoDetail buyerCustomizedInfo) {
        this.buyerCustomizedInfo = buyerCustomizedInfo;
    }

    /**
     * Check to see if BuyerCustomizedInfo is set.
     *
     * @return true if BuyerCustomizedInfo is set.
     */
    public boolean isSetBuyerCustomizedInfo() {
        return buyerCustomizedInfo != null;
    }

    /**
     * Set the value of BuyerCustomizedInfo, return this.
     *
     * @param buyerCustomizedInfo
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withBuyerCustomizedInfo(BuyerCustomizedInfoDetail buyerCustomizedInfo) {
        this.buyerCustomizedInfo = buyerCustomizedInfo;
        return this;
    }

    /**
     * Get the value of TaxCollection.
     *
     * @return The value of TaxCollection.
     */
    public TaxCollection getTaxCollection() {
        return taxCollection;
    }

    /**
     * Set the value of TaxCollection.
     *
     * @param taxCollection
     *            The new value to set.
     */
    public void setTaxCollection(TaxCollection taxCollection) {
        this.taxCollection = taxCollection;
    }

    /**
     * Check to see if TaxCollection is set.
     *
     * @return true if TaxCollection is set.
     */
    public boolean isSetTaxCollection() {
        return taxCollection != null;
    }

    /**
     * Set the value of TaxCollection, return this.
     *
     * @param taxCollection
     *             The new value to set.
     *
     * @return This instance.
     */
    public OrderItem withTaxCollection(TaxCollection taxCollection) {
        this.taxCollection = taxCollection;
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
        asin = r.read("ASIN", String.class);
        sellerSKU = r.read("SellerSKU", String.class);
        orderItemId = r.read("OrderItemId", String.class);
        title = r.read("Title", String.class);
        quantityOrdered = r.read("QuantityOrdered", int.class);
        quantityShipped = r.read("QuantityShipped", Integer.class);
        productInfo = r.read("ProductInfo", ProductInfoDetail.class);
        pointsGranted = r.read("PointsGranted", PointsGrantedDetail.class);
        itemPrice = r.read("ItemPrice", Money.class);
        shippingPrice = r.read("ShippingPrice", Money.class);
        giftWrapPrice = r.read("GiftWrapPrice", Money.class);
        itemTax = r.read("ItemTax", Money.class);
        shippingTax = r.read("ShippingTax", Money.class);
        giftWrapTax = r.read("GiftWrapTax", Money.class);
        shippingDiscount = r.read("ShippingDiscount", Money.class);
        promotionDiscount = r.read("PromotionDiscount", Money.class);
        promotionIds = r.readList("PromotionIds", "PromotionId", String.class);
        codFee = r.read("CODFee", Money.class);
        codFeeDiscount = r.read("CODFeeDiscount", Money.class);
        isGift = r.read("IsGift", Boolean.class);
        giftMessageText = r.read("GiftMessageText", String.class);
        giftWrapLevel = r.read("GiftWrapLevel", String.class);
        invoiceData = r.read("InvoiceData", InvoiceData.class);
        conditionNote = r.read("ConditionNote", String.class);
        conditionId = r.read("ConditionId", String.class);
        conditionSubtypeId = r.read("ConditionSubtypeId", String.class);
        scheduledDeliveryStartDate = r.read("ScheduledDeliveryStartDate", String.class);
        scheduledDeliveryEndDate = r.read("ScheduledDeliveryEndDate", String.class);
        priceDesignation = r.read("PriceDesignation", String.class);
        buyerCustomizedInfo = r.read("BuyerCustomizedInfo", BuyerCustomizedInfoDetail.class);
        taxCollection = r.read("TaxCollection", TaxCollection.class);
    }

    /**
     * Write members to a MwsWriter.
     *
     * @param w
     *      The writer to write to.
     */
    @Override
    public void writeFragmentTo(MwsWriter w) {
        w.write("ASIN", asin);
        w.write("SellerSKU", sellerSKU);
        w.write("OrderItemId", orderItemId);
        w.write("Title", title);
        w.write("QuantityOrdered", quantityOrdered);
        w.write("QuantityShipped", quantityShipped);
        w.write("ProductInfo", productInfo);
        w.write("PointsGranted", pointsGranted);
        w.write("ItemPrice", itemPrice);
        w.write("ShippingPrice", shippingPrice);
        w.write("GiftWrapPrice", giftWrapPrice);
        w.write("ItemTax", itemTax);
        w.write("ShippingTax", shippingTax);
        w.write("GiftWrapTax", giftWrapTax);
        w.write("ShippingDiscount", shippingDiscount);
        w.write("PromotionDiscount", promotionDiscount);
        w.writeList("PromotionIds", "PromotionId", promotionIds);
        w.write("CODFee", codFee);
        w.write("CODFeeDiscount", codFeeDiscount);
        w.write("IsGift", isGift);
        w.write("GiftMessageText", giftMessageText);
        w.write("GiftWrapLevel", giftWrapLevel);
        w.write("InvoiceData", invoiceData);
        w.write("ConditionNote", conditionNote);
        w.write("ConditionId", conditionId);
        w.write("ConditionSubtypeId", conditionSubtypeId);
        w.write("ScheduledDeliveryStartDate", scheduledDeliveryStartDate);
        w.write("ScheduledDeliveryEndDate", scheduledDeliveryEndDate);
        w.write("PriceDesignation", priceDesignation);
        w.write("BuyerCustomizedInfo", buyerCustomizedInfo);
        w.write("TaxCollection", taxCollection);
    }

    /**
     * Write tag, xmlns and members to a MwsWriter.
     *
     * @param w
     *         The Writer to write to.
     */
    @Override
    public void writeTo(MwsWriter w) {
        w.write("https://mws.amazonservices.com/Orders/2013-09-01", "OrderItem",this);
    }

    /** Value constructor. */
    public OrderItem(String asin,String orderItemId,int quantityOrdered) {
        this.asin = asin;
        this.orderItemId = orderItemId;
        this.quantityOrdered = quantityOrdered;
    }    

    /** Default constructor. */
    public OrderItem() {
        super();
    }

/** Value constructor. */
    public OrderItem(String asin,String sellerSKU,String orderItemId,String title,int quantityOrdered,Integer quantityShipped,PointsGrantedDetail pointsGranted,Money itemPrice,Money shippingPrice,Money giftWrapPrice,Money itemTax,Money shippingTax,Money giftWrapTax,Money shippingDiscount,Money promotionDiscount,List<String> promotionIds,Money codFee,Money codFeeDiscount,String giftMessageText,String giftWrapLevel,InvoiceData invoiceData,String conditionNote,String conditionId,String conditionSubtypeId,String scheduledDeliveryStartDate,String scheduledDeliveryEndDate,String priceDesignation) {
        this.asin = asin;
        this.sellerSKU = sellerSKU;
        this.orderItemId = orderItemId;
        this.title = title;
        this.quantityOrdered = quantityOrdered;
        this.quantityShipped = quantityShipped;
        this.pointsGranted = pointsGranted;
        this.itemPrice = itemPrice;
        this.shippingPrice = shippingPrice;
        this.giftWrapPrice = giftWrapPrice;
        this.itemTax = itemTax;
        this.shippingTax = shippingTax;
        this.giftWrapTax = giftWrapTax;
        this.shippingDiscount = shippingDiscount;
        this.promotionDiscount = promotionDiscount;
        this.promotionIds = promotionIds;
        this.codFee = codFee;
        this.codFeeDiscount = codFeeDiscount;
        this.giftMessageText = giftMessageText;
        this.giftWrapLevel = giftWrapLevel;
        this.invoiceData = invoiceData;
        this.conditionNote = conditionNote;
        this.conditionId = conditionId;
        this.conditionSubtypeId = conditionSubtypeId;
        this.scheduledDeliveryStartDate = scheduledDeliveryStartDate;
        this.scheduledDeliveryEndDate = scheduledDeliveryEndDate;
        this.priceDesignation = priceDesignation;
    }

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", asin=" + asin + ", sellerSKU=" + sellerSKU + ", orderItemId=" + orderItemId
				+ ", title=" + title + ", quantityOrdered=" + quantityOrdered + ", quantityShipped=" + quantityShipped
				+ ", itemStatus=" + itemStatus + ", productInfo=" + productInfo + ", itemCount=" + itemCount
				+ ", pointsGranted=" + pointsGranted + ", itemPrice=" + itemPrice + ", item_price=" + item_price
				+ ", shippingPrice=" + shippingPrice + ", giftWrapPrice=" + giftWrapPrice + ", itemTax=" + itemTax
				+ ", shippingTax=" + shippingTax + ", giftWrapTax=" + giftWrapTax + ", shippingDiscount=" + shippingDiscount
				+ ", promo=" + promo + ", promotionDiscount=" + promotionDiscount + ", promotionIds=" + promotionIds
				+ ", codFee=" + codFee + ", codFeeDiscount=" + codFeeDiscount + ", isGift=" + isGift + ", giftMessageText="
				+ giftMessageText + ", giftWrapLevel=" + giftWrapLevel + ", invoiceData=" + invoiceData + ", conditionNote="
				+ conditionNote + ", conditionId=" + conditionId + ", conditionSubtypeId=" + conditionSubtypeId
				+ ", scheduledDeliveryStartDate=" + scheduledDeliveryStartDate + ", scheduledDeliveryEndDate="
				+ scheduledDeliveryEndDate + ", priceDesignation=" + priceDesignation + ", buyerCustomizedInfo="
				+ buyerCustomizedInfo + ", taxCollection=" + taxCollection + ", ord=" + ord + "]";
	}

//    @Override
//    public boolean equals(Object obj) {
//    	if(obj.getClass() == this.getClass() && ((OrderItem)obj).getId() == this.getId()) {
//    		return true;
//    	}
//    	return false;
//    }
}
