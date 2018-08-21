package com.g128.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amazonaws.services.xray.model.Http;
import com.g128.businessdelegate.InventoryDelegation;
import com.g128.businessdelegate.OrderDelegation;
import com.g128.model.Money;

import io.netty.handler.codec.http.HttpRequest;

@Controller
@ComponentScan("com.g128.businessdelegate")
public class InventoryController {
	@Autowired
	InventoryDelegation idel;
	
//	@RequestMapping(value={"/updateInventory"}, method=RequestMethod.POST)
//	public String updateInventory(HttpServletRequest req) {
//		String code = req.getParameter("itemCode");
//		String cost = req.getParameter("cost");
//		String currency = req.getParameter("currency");
//		String name = req.getParameter("desc");
//		String amount = req.getParameter("count");
//		
//		Money money = new Money();
//		if(cost.equalsIgnoreCase(""))		cost = "0";
//		if(amount.equalsIgnoreCase(""))		amount = "0";
//		money.setAmount(cost);
//		money.setCurrencyCode(currency);
//		idel.updateInventoryItem(name, Long.parseLong(amount), code, money);
//		
//		return "inventory";
//	}
//	
//	@RequestMapping(value={"/deleteInventory"}, method=RequestMethod.POST)
//	public String deketeInventory(HttpServletRequest req) {
//		String code = req.getParameter("itemCode");
//		String name = req.getParameter("desc");
//		
//		idel.removeInventoryItem(code, name);
//		return "inventory";
//	}
//	
//	@RequestMapping(value={"/inventory"}, method=RequestMethod.GET)
//	public String inventory(HttpServletRequest req) {
//		return "inventory";
//	}
//	
//	@RequestMapping(value={"/update"}, method=RequestMethod.GET)
//	public String inventoryUpdate(HttpServletRequest req) {
//		return "update";
//	}
//	
//	@RequestMapping(value={"/delete"}, method=RequestMethod.GET)
//	public String inventoryDelete(HttpServletRequest req) {
//		return "delete";
//	}
	
	@RequestMapping(value={"/fetch_mapping_form"}, method=RequestMethod.GET)
	public String getMappingForm(HttpServletRequest req) {
		req.setAttribute("desc", "This page allows user to check and update the item to order mapping informations.");
		return "add_map";
	}
	
	@RequestMapping(value={"/add_mapping"}, method=RequestMethod.POST)
	public String inventoryAddMapping(HttpServletRequest req) {
		String asin = req.getParameter("asin");
		String sku = req.getParameter("sku");
		String title = req.getParameter("title");
		String category = req.getParameter("category");
		String type = req.getParameter("type");
		String size = req.getParameter("size");
		String thickness = req.getParameter("thickness");
		idel.saveMapping(asin, sku, title, category, type, size, thickness);
		return "add_map";
	}
	
	@RequestMapping(value={"/delete_mapping"}, method=RequestMethod.POST)
	public String inventoryDeleteMapping(HttpServletRequest req) {
		String asin = req.getParameter("asin");
		String sku = req.getParameter("sku");
		String title = req.getParameter("title");
		String category = req.getParameter("category");
		String type = req.getParameter("type");
		String size = req.getParameter("size");
		String thickness = req.getParameter("thickness");
		idel.deleteMapping(asin, sku, title, category, type, size, thickness);
		return "add_map";
	}
	
	@RequestMapping(value={"/check_mapping"}, method=RequestMethod.POST)
	public String inventoryCheckMapping(HttpServletRequest req) {
		System.out.println("Entering method!");
		Set<List<String>> sLT = idel.getUnmapped();
		//System.out.println("SKU AND ASIN: " + sLT);
		Set<List<String>> sULT = idel.getMapped();
		req.setAttribute("sLT", sLT);
		req.setAttribute("sULT", sULT);
		return "add_map";
	}
	
//	@RequestMapping(value={"/fillInfo"}, method=RequestMethod.POST)
//	public String inventoryFillMapping(HttpServletRequest req) {
//		System.out.println("Entering method!");
//		
//		//System.out.println("SKU AND ASIN: " + sLT);
////		req.setAttribute("sLT", sLT);
//		req.setAttribute("asinV", req.getParameter("asinV"));
//		req.setAttribute("skuV", req.getParameter("skuV"));
//		return "add_map";
//	}
}
