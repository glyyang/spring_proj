package com.g128.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Promotion", namespace="com.g128.model.OrderItem")
public class Promotion {
//	@XmlElement(name="PromotionIDs")
	private String promo_ids;
//	@XmlElement(name="ShipPromotionDiscount")
	private String shipPromoDisc;
	@XmlElement(name="PromotionIDs")
	public String getPromo_ids() {
		return promo_ids;
	}
	public void setPromo_ids(String promo_ids) {
		this.promo_ids = promo_ids;
	}
	@XmlElement(name="ShipPromotionDiscount")
	public String getShipPromoDisc() {
		return shipPromoDisc;
	}
	public void setShipPromoDisc(String shipPromoDisc) {
		this.shipPromoDisc = shipPromoDisc;
	}

	
}
