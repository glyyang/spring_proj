package com.g128.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.g128.businessdelegate.OrderDelegation;
import com.g128.model.OrderItem;

@Controller
@ComponentScan("com.g128.businessdelegate")
public class FetchController {
	@Autowired
	OrderDelegation odel;
	
	@Autowired
    private ServletContext servletContext;
	
	
	@RequestMapping(value={"/displayOrders"}, method = RequestMethod.POST)
	public String fetchingOrderIdOrder(HttpServletRequest request) throws IOException, InterruptedException, DatatypeConfigurationException, ParseException{
		//System.out.println("from year: " + request.getParameter("fyear"));
		String bid = request.getParameter("orderId");
		String fname = request.getParameter("fn");
		String lname = request.getParameter("ln");
		Calendar from, to;
		String ffStr = request.getParameter("ff");
		ffStr = ffStr + ":" + request.getParameter("ffHr");
		
		String ttStr = request.getParameter("tt");
		ttStr = ttStr + ":" + request.getParameter("ttHr");
		
		System.out.println(ffStr);
		System.out.println(ttStr);
		SimpleDateFormat timeForm = new SimpleDateFormat("MM/dd/yyyy:HH:mm a");
		Date dateF = timeForm.parse(ffStr);
		Date dateT = timeForm.parse(ttStr);
		from = Calendar.getInstance();
		to = Calendar.getInstance();
		from.setTime(dateF);
		to.setTime(dateT);
		System.out.println(from);
		System.out.println(to);
		
		String fn = request.getParameter("FNChannel");
		String oStat = request.getParameter("orderStat");
		String shipment = request.getParameter("shipmentLvl");
		request.setAttribute("orders", odel.getOrders(from,to,fname,lname,fn,oStat,shipment,bid));
		return "search";
	}
	
	@RequestMapping(value={"/search"}, method=RequestMethod.GET)
	public String home(HttpServletRequest request){
		request.setAttribute("desc", "This page allows user to get order items according the the order attributes.");
	    return "search";
	}
	
	@RequestMapping(value={"/get_items"}, method=RequestMethod.POST)
	public String getItems(HttpServletRequest req) {
		String title = req.getParameter("title");
		String category = req.getParameter("category");
		String type = req.getParameter("type");
		String size = req.getParameter("size");
		String thickness = req.getParameter("thickness");
		
		Set<OrderItem> oiL = odel.getOrderItemsByDescription(title, category, type, size, thickness);
		System.out.println("Size of set: " + oiL.size());
		req.setAttribute("oiL", oiL);
		return "listing";
	}
	
	@RequestMapping(value={"/get_items"}, method=RequestMethod.GET)
	public String getForm(HttpServletRequest req) {
		req.setAttribute("desc", "This page allows user to retrieve all orders according to the item properties.");
		return "listing";
	}
	
	
}
