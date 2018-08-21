package com.g128.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g128.businessdelegate.OrderDelegation;
import com.g128.model.OrderItem;
import com.g128.model.Orders;


@Controller
@ComponentScan("com.g128.businessdelegate")
public class HomeController {

	@Autowired
	OrderDelegation odel;
	
	@Autowired
    private ServletContext servletContext;
	
	@RequestMapping(value={"/fetchOrder"}, method = RequestMethod.POST)
	public String fetchingAllOrder(HttpServletRequest request) throws IOException{
		List<Orders> oL = odel.getAllOrders();
		request.setAttribute("orders", oL);
//		List<List<OrderItem>> oiL = new ArrayList<>();
//		for(Orders o : oL) {
//			oiL.add(odel.getOrderItemsByOrderId(o.getAmazonOrderId()));
////			(odel.getOrderItemsByOrderId(o.getAmazonOrderId())).get(0).getTitle();
//		}
//		
//		request.setAttribute("oiL", oiL);
		return "home";
	}
	
	@RequestMapping(value={"/fetchServiceOrder"}, method = RequestMethod.POST)
	public String fetchingWSOrder(HttpServletRequest request) throws IOException, InterruptedException, DatatypeConfigurationException, ParseException{
		String ffStr = request.getParameter("ff");
		ffStr = ffStr + ":" + request.getParameter("ffHr");
		
		String ttStr = request.getParameter("tt");
		ttStr = ttStr + ":" + request.getParameter("ttHr");
		
		System.out.println(ffStr);
		System.out.println(ttStr);
		SimpleDateFormat timeForm = new SimpleDateFormat("MM/dd/yyyy:HH:mm a");
		Date dateF = timeForm.parse(ffStr);
		Date dateT = timeForm.parse(ttStr);
		Calendar ff = Calendar.getInstance();
		Calendar tt = Calendar.getInstance();
		ff.setTime(dateF);
		tt.setTime(dateT);
		System.out.println(ff);
		System.out.println(tt);
		String server = (String)request.getParameter("ws");
		
		request.setAttribute("orders", odel.storeOrderFromService(server, ff, tt, "", ""));
		return "home";
	}
	
	@RequestMapping({"/home"})
	public String home(HttpServletRequest request){
		request.setAttribute("desc", "This page enables user to manually update the order information in database from Amazon Server;</p><p>it also allows user to go to search page and check for specific orders in database.");
	    return "home";
	}
	@RequestMapping({"/index"})
	public String index(HttpServletRequest request){
	    return "index";
	}
	
	@PostConstruct
    public void showIt() {
        System.out.println("Context path: " + servletContext.getContextPath());
    }
}
