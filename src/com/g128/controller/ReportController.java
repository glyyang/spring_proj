package com.g128.controller;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.g128.businessdelegate.OrderDelegation;

import io.netty.handler.codec.http.HttpRequest;

@Controller
@ComponentScan("com.g128.businessdelegate")
public class ReportController {
	@Autowired
	private OrderDelegation odel;
	
	@RequestMapping(value= {"/get_report"}, method=RequestMethod.POST)
	public String gtco(HttpServletRequest req) {
		DecimalFormat df2 = new DecimalFormat("#.00"); 

		DecimalFormat df4 = new DecimalFormat("#.0000");
		String stat = req.getParameter("dropdown");
		String asin = req.getParameter("asin");
		String sku = req.getParameter("sku");
		String title = req.getParameter("title");
		Calendar from, to;
		boolean setComp = false;
		if(stat.equalsIgnoreCase("Yesterday")) {
			from = new GregorianCalendar();
			to = new GregorianCalendar();
			to.setTimeInMillis(System.currentTimeMillis());
			
			to.set(Calendar.HOUR_OF_DAY, 0);
			to.set(Calendar.MINUTE, 0);
			to.set(Calendar.SECOND, 0);
			to.set(Calendar.MILLISECOND, 0);
			from.setTimeInMillis(to.getTimeInMillis()-(24*60*60*1000));
			to.setTimeInMillis(to.getTimeInMillis()-1);
			setComp = true;
			
		}else if(stat.equalsIgnoreCase("Last 24 Hours")) {
			from = new GregorianCalendar();
			to = new GregorianCalendar();
			to.setTimeInMillis(System.currentTimeMillis());
			from = (Calendar) to.clone();
			from.setTimeInMillis(from.getTimeInMillis()-24*60*60*1000);
			setComp = true;
						
		}else if(stat.equalsIgnoreCase("Today")) {
			from = new GregorianCalendar();
			to = new GregorianCalendar();
			to.setTimeInMillis(System.currentTimeMillis());
			from = (Calendar) to.clone();
			from.set(Calendar.HOUR_OF_DAY, 0);
			from.set(Calendar.MINUTE, 0);
			from.set(Calendar.SECOND, 0);
			from.set(Calendar.MILLISECOND, 0);
			setComp = true;
						
		}else if(stat.equalsIgnoreCase("This Seven Days")) {
			from = new GregorianCalendar();
			to = new GregorianCalendar();
			to.setTimeInMillis(System.currentTimeMillis());
			from.setTimeInMillis(to.getTimeInMillis()-(7*24*60*60*1000L));
			setComp = true;
		}
		else if(stat.equalsIgnoreCase("This Month")) {
			from = new GregorianCalendar();
			to = new GregorianCalendar();
			to.setTimeInMillis(System.currentTimeMillis());
			from = (Calendar) to.clone();
			from.set(Calendar.DAY_OF_MONTH, 1);
			from.set(Calendar.HOUR_OF_DAY, 0);
			from.set(Calendar.MINUTE, 0);
			from.set(Calendar.SECOND, 0);
			from.set(Calendar.MILLISECOND, 0);
			setComp = true;
			
		}else if(stat.equalsIgnoreCase("This Year")) {
			from = new GregorianCalendar();
			to = new GregorianCalendar();
			to.setTimeInMillis(System.currentTimeMillis());
			from = (Calendar) to.clone();
			from.set(Calendar.MONTH, 0);
			from.set(Calendar.DAY_OF_MONTH, 1);
			from.set(Calendar.HOUR_OF_DAY, 0);
			from.set(Calendar.MINUTE, 0);
			from.set(Calendar.SECOND, 0);
			from.set(Calendar.MILLISECOND, 0);
			setComp = true;
			
		}else if(stat.equalsIgnoreCase("Custom Input")) {
			setComp = true;
			try{
					from = new GregorianCalendar(Integer.parseInt(req.getParameter("fyear").toString()),
					Integer.parseInt(req.getParameter("fmonth").toString())-1,
					Integer.parseInt(req.getParameter("fday").toString()),
					Integer.parseInt(req.getParameter("fhour").toString()),
					Integer.parseInt(req.getParameter("fminute").toString()),
					Integer.parseInt(req.getParameter("fsecond").toString()));
			}catch(Exception e){
					from = null;
			}
			try{
					to = new GregorianCalendar(Integer.parseInt(req.getParameter("tyear").toString()),
					Integer.parseInt(req.getParameter("tmonth").toString())-1,
					Integer.parseInt(req.getParameter("tday").toString()),
					Integer.parseInt(req.getParameter("thour").toString()),
					Integer.parseInt(req.getParameter("tminute").toString()),
					Integer.parseInt(req.getParameter("tsecond").toString()));
			}catch(Exception e){
					to = null;
			}
		}else {
			from = new GregorianCalendar();
			to = new GregorianCalendar();
			to.setTimeInMillis(System.currentTimeMillis());
			from = (Calendar) to.clone();
			from.set(Calendar.YEAR, 2014);
			from.set(Calendar.MONTH, 0);
			from.set(Calendar.DAY_OF_MONTH, 1);
			from.set(Calendar.HOUR_OF_DAY, 0);
			from.set(Calendar.MINUTE, 0);
			from.set(Calendar.SECOND, 0);
			from.set(Calendar.MILLISECOND, 0);
		}
		
//		double total = odel.getTotalByPurchaseTime(from.getTimeInMillis(), to.getTimeInMillis());
		double total = odel.getTotalByPurchaseTimeAndAsinSkuTitle(from.getTimeInMillis(), to.getTimeInMillis(), asin, sku, title);
		total = Double.parseDouble(df2.format(total+0.005));
//		long totalCount = odel.getTotalItemsByPurchaseTime(from.getTimeInMillis(), to.getTimeInMillis());
		long totalCount = odel.getTotalItemsByPurchaseTimeAndAsinSkuTitle(from.getTimeInMillis(), to.getTimeInMillis(), asin, sku, title);
		
//		List<Map<String,Long>> lmt1 = odel.getAsinsSkuItemCountByPurchaseTime(from.getTimeInMillis(), to.getTimeInMillis());
		
//		Map<String, Long> asinC = lmt1.get(0);
//		Map<String, Long> skuC = lmt1.get(1);
//		Map<String, Long> titleC = lmt1.get(2);
//		
//		List<Map<String,Double>> lmt2 = odel.getAsinsSkuPriceCountByPurchaseTime(from.getTimeInMillis(), to.getTimeInMillis());
//		
//		Map<String, Double> asinP = lmt2.get(0);
//		Map<String, Double> skuP = lmt2.get(1);
//		Map<String, Double> titleP = lmt2.get(2);
		
		List<Map<String,Double>> lmt = odel.getAsinsSkuPriceCountByPurchaseTimeTitleAsinSku(from.getTimeInMillis(), to.getTimeInMillis(), title, asin, sku);
		Map<String, Double> titleP = lmt.get(0);
		Map<String, Double> titleC = lmt.get(1);
		
		
		req.setAttribute("total", total);
		req.setAttribute("totalCount", totalCount);
//		req.setAttribute("asinC", asinC);
//		req.setAttribute("skuC", skuC);
//		req.setAttribute("titleC", titleC);
//		req.setAttribute("asinP", asinP);
//		req.setAttribute("skuP", skuP);
		req.setAttribute("titleP", titleP);
		req.setAttribute("titleC", titleC);
		req.setAttribute("timeFrom", from.getTime());
		req.setAttribute("timeTo", to.getTime());
		
		
		double[] totalPERCL = new double[6];
		double[] totalCOUNTPERCL = new double[6];
		String[] listItems = new String[6];
		double[] totalL = new double[6];
		long[] totalCountL = new long[6];
		Calendar[] fromL = new Calendar[6];
		Calendar[] toL = new Calendar[6];
		 

		if(setComp) {
			
			
			
			
			for(int i = 0; i < totalL.length; i++) {
				fromL[i] = (Calendar) from.clone();
				toL[i] = (Calendar) to.clone();
				boolean skip = false;
				switch(i) {
				case 0:
					if(to.getTimeInMillis() - from.getTimeInMillis() <= (long)24*60*60*1000+1) {
						fromL[i].setTimeInMillis(from.getTimeInMillis()-(long)24*60*60*1000);
						toL[i].setTimeInMillis(to.getTimeInMillis()-(long)24*60*60*1000);
						
					}else {
						skip = true;
					}
					listItems[i] = "Comparing to the day before";
					break;
				case 1:
					if(to.getTimeInMillis() - from.getTimeInMillis() <= (long)7*24*60*60*1000+1) {
						fromL[i].setTimeInMillis(from.getTimeInMillis()-(long)7*24*60*60*1000);
						toL[i].setTimeInMillis(to.getTimeInMillis()-(long)7*24*60*60*1000);
						
					}else {
						skip = true;
					}
					listItems[i] = "Comparing to 7 days before";
					break;
				case 2:
					if(to.getTimeInMillis() - from.getTimeInMillis() <= (long)31*24*60*60*1000+1) {
						
						if(from.get(Calendar.DAY_OF_MONTH) > 30) {
							fromL[i].setTimeInMillis(from.getTimeInMillis()-(long)31*24*60*60*1000);
							toL[i].setTimeInMillis(fromL[i].getTimeInMillis() + to.getTimeInMillis()-from.getTimeInMillis());
						}else if(from.get(Calendar.MONTH) == 2 && from.get(Calendar.DAY_OF_MONTH) >= 29) {
							fromL[i].setTimeInMillis(from.getTimeInMillis());
							fromL[i].set(Calendar.MONTH, 1);
							fromL[i].set(Calendar.DAY_OF_MONTH,(fromL[i].get(Calendar.YEAR) % 4) == 0? 29 : 28);
							toL[i].setTimeInMillis(fromL[i].getTimeInMillis() + to.getTimeInMillis()-from.getTimeInMillis());
						}else {
							if(from.get(Calendar.MONTH) == 0) {
								fromL[i].set(Calendar.MONTH,11);
								fromL[i].set(Calendar.YEAR, from.get(Calendar.YEAR)-1);
							}else {
								fromL[i].set(Calendar.MONTH, from.get(Calendar.MONTH)-1);
							}
							toL[i].setTimeInMillis(fromL[i].getTimeInMillis() + to.getTimeInMillis()-from.getTimeInMillis());
						}
					}else {
						skip = true;
					}
					listItems[i] = "Comparing to a month before";
					break;
				case 3:
					if(to.getTimeInMillis() - from.getTimeInMillis() <= (long)366*24*60*60*1000+1) {
						if(from.get(Calendar.MONTH) == 1 && from.get(Calendar.DAY_OF_MONTH) == 29) {
							fromL[i].set(Calendar.YEAR, from.get(Calendar.YEAR)-1);
							fromL[i].set(Calendar.DAY_OF_MONTH, 28);
						}else {
							fromL[i].set(Calendar.YEAR, from.get(Calendar.YEAR)-1);
						}
						
						if(to.get(Calendar.MONTH) == 1 && to.get(Calendar.DAY_OF_MONTH) == 29) {
							toL[i].set(Calendar.YEAR, to.get(Calendar.YEAR)-1);
							toL[i].set(Calendar.DAY_OF_MONTH, 28);
						}else {
							toL[i].set(Calendar.YEAR, to.get(Calendar.YEAR)-1);
							
						}
						
					}else {
						skip = true;
					}
					listItems[i] = "Comparing to same day last year";
					break;
				case 4:
					if(to.getTimeInMillis() - from.getTimeInMillis() <= ((long)365*2+1)*24*60*60*1000+1) {
						if(from.get(Calendar.MONTH) == 1 && from.get(Calendar.DAY_OF_MONTH) == 29) {
							fromL[i].set(Calendar.YEAR, from.get(Calendar.YEAR)-2);
							fromL[i].set(Calendar.DAY_OF_MONTH, 28);
						}else {
							fromL[i].set(Calendar.YEAR, from.get(Calendar.YEAR)-2);
						}
						
						if(to.get(Calendar.MONTH) == 1 && to.get(Calendar.DAY_OF_MONTH) == 29) {
							toL[i].set(Calendar.YEAR, to.get(Calendar.YEAR)-2);
							toL[i].set(Calendar.DAY_OF_MONTH, 28);
						}else {
							toL[i].set(Calendar.YEAR, to.get(Calendar.YEAR)-2);
							
						}
						
					}else {
						skip = true;
					}
					listItems[i] = "Comparing to same day 2 years ago";
					break;
				case 5:
					if(to.getTimeInMillis() - from.getTimeInMillis() <= ((long)365*4+1)*24*60*60*1000+1) {
						if(from.get(Calendar.MONTH) == 1 && from.get(Calendar.DAY_OF_MONTH) == 29) {
							fromL[i].set(Calendar.YEAR, from.get(Calendar.YEAR)-4);
							fromL[i].set(Calendar.DAY_OF_MONTH, 28);
						}else {
							fromL[i].set(Calendar.YEAR, from.get(Calendar.YEAR)-4);
						}
						
						if(to.get(Calendar.MONTH) == 1 && to.get(Calendar.DAY_OF_MONTH) == 29) {
							toL[i].set(Calendar.YEAR, to.get(Calendar.YEAR)-4);
							toL[i].set(Calendar.DAY_OF_MONTH, 28);
						}else {
							toL[i].set(Calendar.YEAR, to.get(Calendar.YEAR)-4);
							
						}
						
					}else {
						skip = true;
					}
					listItems[i] = "Comparing to same day 4 years ago";
					break;
				default:
					break;
				}
				if(!skip) {
					totalL[i] = odel.getTotalByPurchaseTimeAndAsinSkuTitle(fromL[i].getTimeInMillis(), toL[i].getTimeInMillis(),asin,sku,title);
					totalCountL[i] = odel.getTotalItemsByPurchaseTimeAndAsinSkuTitle(fromL[i].getTimeInMillis(), toL[i].getTimeInMillis(),asin,sku,title);
					totalPERCL[i] = (total-totalL[i] )/ (totalL[i]+0.00001);
					totalCOUNTPERCL[i] = (0.00 + totalCount -totalCountL[i] )/ (0.00001+totalCountL[i]);
					totalL[i] = Double.parseDouble(df2.format(totalL[i]+0.005));
					System.out.println("number: " + totalL[i] + " at " + i);
					
					totalPERCL[i] = Double.parseDouble(df4.format(totalPERCL[i]+0.00005));
					totalCOUNTPERCL[i] = Double.parseDouble(df4.format(totalCOUNTPERCL[i]+0.00005));
					System.out.println(listItems[i] + " with Cost: " + totalL[i] + " and number: " + totalCountL[i]);
				}else {
					fromL[i] = null;
					toL[i] = null;
					totalL[i] = -1;
					totalPERCL[i] = -1;
					totalCOUNTPERCL[i] = -1;
					totalL[i] = -1;
				}
			}
			
			
		}
		req.setAttribute("fromL", fromL);
		req.setAttribute("toL", toL);
		req.setAttribute("costVice", totalL);
		req.setAttribute("countVice", totalCountL);
		req.setAttribute("costStat", totalPERCL);
		req.setAttribute("countStat", totalCOUNTPERCL);
		req.setAttribute("strStat", listItems);
		return "report";
		
	}
	
	@RequestMapping(value={"/report"}, method=RequestMethod.GET)
	public String getForm(HttpServletRequest req) {
		req.setAttribute("desc", "This page allows user to get order/items reports according to time frame specified by the user.");
		return "report";
	}
}
