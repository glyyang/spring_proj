package com.g128.businessdelegate;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.security.auth.login.Configuration;
import javax.servlet.ServletConfig;

import org.apache.jasper.tagplugins.jstl.core.Import;
import org.renjin.primitives.vector.RowNamesVector;
import org.renjin.script.RenjinScriptEngineFactory;
import org.renjin.sexp.DoubleArrayVector;
import org.renjin.sexp.IntArrayVector;
import org.renjin.sexp.ListVector;
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Symbols;
//import org.rosuda.JRI.*;
//import org.rosuda.JRI.REXP;
import org.rosuda.REngine.*;
import org.rosuda.REngine.JRI.*;
//import org.rosuda.JRI.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.g128.dao.Dao;
import com.g128.model.OrderItem;
import com.g128.model.Orders;


@Component
@ComponentScan("com.g128.businessdelegate")
public class AnalysisService {
	static {
	    System.loadLibrary("jri");      
	}
	
	
	@Autowired
	private OrderDelegation odel;
	private RenjinScriptEngineFactory rFact;
	private ScriptEngine rEng;
//	private Rengine jriEng;
	private REngine JRIEng;
	
	
	public AnalysisService() {
		System.out.println("JLP = " + System.getProperty("java.library.path"));
		System.out.println("Im here 2");
		
		System.out.println();
		rFact = new RenjinScriptEngineFactory();
		rEng = rFact.getScriptEngine();
//		jriEng = new Rengine(new String[]{ "--vanilla" }, false, null);
		System.out.println("Im here 3");
//		System.out.println(System.getProperty("user.dir"));
		try {
			JRIEng = REngine.engineForClass("org.rosuda.REngine.JRI.JRIEngine", new String[]{ "--vanilla" }, new REngineStdOutput (), false);
		} catch (UnsatisfiedLinkError | InvocationTargetException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			JRIEng = REngine.getLastEngine();
		}
//		System.out.println(System.getProperty("user.dir"));
		
	}
	
	public AnalysisService(int stat) {
		System.out.println("Im here 2");
		odel = new OrderDelegation();
		rFact = new RenjinScriptEngineFactory();
		rEng = rFact.getScriptEngine();
//		jriEng = new Rengine(new String[]{ "--vanilla" }, false, null);
		System.out.println("Im here 3");
		try {
			JRIEng = REngine.engineForClass("org.rosuda.REngine.JRI.JRIEngine", new String[]{ "--vanilla" }, new REngineStdOutput (), false);
		} catch (UnsatisfiedLinkError | InvocationTargetException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			JRIEng = REngine.getLastEngine();
		}
		
	}
	
	private List<OrderItem> fetchingItemsByDate(long dateStart, long dateEnd){
		Calendar from = new GregorianCalendar();
		Calendar to = new GregorianCalendar();
		from.setTimeInMillis(dateStart);
		to.setTimeInMillis(dateEnd);
		System.out.println(from);
		System.out.println(to);
		List<Orders> ord = odel.getOrders(from, to, "", "", "", "", "", "");
		List<OrderItem> oItem = new ArrayList<>();
		for(Orders o : ord) {
			oItem.addAll(o.getOrd_items());
		}
		return oItem;
		
	}

	public void testVal2() throws ScriptException, REXPMismatchException, REngineException {
//		long yr = 24*60*60;
//		yr *= 365;
//		yr*=1000;
		//List<OrderItem> oitems = fetchingItemsByDate(System.currentTimeMillis()-yr, System.currentTimeMillis());
		//List<Orders> ord;
		for(int yr = 0; yr < 4; yr++) {
			List<Double> orderItemPrice = new ArrayList<>();
			List<Integer> months = new ArrayList<>();
			List<String> cmt = new ArrayList<>();
			//IntArrayVector.Builder iVec = new IntArrayVector.Builder();
			
			
			
	//		for(int i =1; i <= 12; i++) {
	//			months.add(i);
	//			
	//			//iVec.add(i);
	//		}
			
			Calendar cal = new GregorianCalendar();
			cal.setTimeInMillis(System.currentTimeMillis());
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - yr);
			int startingMonth =  cal.get(Calendar.MONTH);
			
			System.out.println("starting month: " + startingMonth);
			
	//		Calendar cal = new GregorianCalendar();
	//		cal.setTimeInMillis(System.currentTimeMillis());
	//		
	//		int startingMonth =  cal.get(Calendar.MONTH);
	//		cal.set(Calendar.DAY_OF_MONTH, 1);
			
			for(int i = startingMonth; i <= startingMonth+12; i++) {
				
				cal.setTimeInMillis(System.currentTimeMillis());
				
				cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - yr);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				
				
				
				int monthHolder = i%12;
				
				
				Calendar end = (Calendar) cal.clone();
				
				if(i < 12) {
					cal.set(Calendar.MONTH, monthHolder);
					
//					System.out.println(cal.get(Calendar.MONTH));
					
					cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-1);
					
					//iVec.add(cal.get(Calendar.MONTH));
					if(monthHolder == 11) {
						end.set(Calendar.MONTH, 0);
					}else {
						end.set(Calendar.MONTH, monthHolder+1);
						end.set(Calendar.YEAR, end.get(Calendar.YEAR)-1);
					}
				}else {
					cal.set(Calendar.MONTH, monthHolder);
					
//					System.out.println(cal.get(Calendar.MONTH));
					
					
					//iVec.add(cal.get(Calendar.MONTH));
					
					if(monthHolder == 11) {
						end.set(Calendar.MONTH, 0);
						end.set(Calendar.YEAR, end.get(Calendar.YEAR)+1);
					}else {
						end.set(Calendar.MONTH, monthHolder+1);
						
					}
				}
				
	//			if(monthHolder > 11) {
	//				cal.set(Calendar.MONTH, monthHolder%12);
	//				
	//			}else {
	//				cal.set(Calendar.MONTH, monthHolder);
	//				cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-1);
	//			}
				double sum = odel.getTotalByPurchaseTime(cal.getTimeInMillis(), end.getTimeInMillis());
	//			System.out.println("month: " + (monthHolder%12+1));
//				System.out.println("Year1: " + cal.get(Calendar.YEAR));
//				System.out.println("Month1: " + cal.get(Calendar.MONTH));
//				System.out.println("Day1: " + cal.get(Calendar.DAY_OF_MONTH));
//				System.out.println("Year2: " + end.get(Calendar.YEAR));
//				System.out.println("Month2: " + end.get(Calendar.MONTH));
//				System.out.println("Day2: " + end.get(Calendar.DAY_OF_MONTH));
//				System.out.println(sum);
				
				if(orderItemPrice.isEmpty()) {
					orderItemPrice.add(sum);
					months.add(cal.get(Calendar.MONTH));
					cmt.add((new Timestamp(cal.getTimeInMillis())).toString());
				}
				else{
					orderItemPrice.add(0, sum);
					months.add(0,cal.get(Calendar.MONTH));
					cmt.add(0,(new Timestamp(cal.getTimeInMillis())).toString());
				}
				
			}
			
//			System.out.println(months.size());
//			System.out.println(orderItemPrice.size());
			//DoubleArrayVector.Builder dVec = new DoubleArrayVector.Builder();
	//		for(double oit : orderItemPrice) {
	//			
	//			//dVec.add(oit);
	//		}
			
			int [] monthArr = new int[months.size()];
			double [] priceArr = new double[orderItemPrice.size()];
			String [] cmtArr = new String[cmt.size()];
	//		months.toArray(monthArr);
	//		orderItemPrice.toArray(priceArr);
			
	//		need spring 4, not working in spring 3
	//		monthArr = months.stream().mapToInt(i->i).toArray();
	//		priceArr = orderItemPrice.stream().mapToDouble(i->i).toArray();
			int idx = 0;
			for(int i : months) {
				monthArr[idx] = i;
				idx++;
			}
			
			idx = 0;
			for(double pr : orderItemPrice) {
				priceArr[idx] = pr;
				idx++;
			}
			
			idx = 0;
			for(String pr : cmt) {
				cmtArr[idx] = pr;
				idx++;
			}
			
			String [] nameArr = new String[] {"months", "price"};
			//yyyy-mm-dd hh:mm:ss.fffffffff
			
			org.rosuda.REngine.REXP mdf = org.rosuda.REngine.REXP.createDataFrame(new RList(new org.rosuda.REngine.REXP[] {new REXPString(cmtArr), new REXPDouble(priceArr)}, nameArr));
			
			JRIEng.assign("myframe", mdf);
//			JRIEng.parseAndEval("print(myframe)");
	//		ListVector.NamedBuilder df = new ListVector.NamedBuilder();
	//		df.setAttribute(Symbols.CLASS, StringVector.valueOf("data.frame"));
	//		df.setAttribute(Symbols.ROW_NAMES, new RowNamesVector(orderItemPrice.size()));
	//		df.add("Months", iVec.build());
	//		df.add("Monthly_total", dVec.build());
	//		
	//		rEng.eval("import(com.g128.model.OrderItem)");
	//		rEng.eval("import(java.util.List)");
	//		rEng.put("orderItemPrice", orderItemPrice);
	//		rEng.put("months", months);
	//		rEng.put("plot_frame", df);
	//		//rEng.eval("print(months$toString())");
	//		//rEng.eval("print(orderItemPrice$toString())");
	//		rEng.eval("print(df)");
			
			//JRIEng.parseAndEval("pdf(file=\"C:/Users/User/workspace/spring_proj/plot" + (""+yr) + ".pdf\")");
			JRIEng.parseAndEval("path <- paste(getwd(),\"plot" + (""+yr) + ".png\",sep=\"\")");
			JRIEng.parseAndEval("png(file=path)");
			JRIEng.parseAndEval("plot(strptime(myframe$months, \"%Y-%m-%d %H:%M:%S\"), myframe$price, ylab=\"Price\", xlab=\"Date\")");
			JRIEng.parseAndEval("dev.off()");
		}
	}
	public String analyze(Calendar begin, Calendar end, String asin, String sku, String title, String pathStr) throws REngineException, REXPMismatchException, IOException {
		List<Double> orderItemPrice = new ArrayList<>();
		List<Integer> months = new ArrayList<>();
		List<String> cmt = new ArrayList<>();
		//IntArrayVector.Builder iVec = new IntArrayVector.Builder();
		System.out.println("Current working dir.: " + System.getProperty("user.dir"));
		
		
//		for(int i =1; i <= 12; i++) {
//			months.add(i);
//			
//			//iVec.add(i);
//		}
		
		long start = begin.getTimeInMillis();
		long last = end.getTimeInMillis();
		long medVal = last - start;
		long sector = medVal/24;
		if(sector <= 60*60*1000) {
			sector = 60*60*1000;
		}else if(sector <= 7*60*60*1000) {
			sector = 7*60*60*1000;
		}else if(sector <= 30*60*60*1000) {
			sector = 30*60*60*1000;
		}else if(sector <= 90*60*60*1000) {
			sector = 90*60*60*1000;
		}else {
			sector = 365*60*60*1000;
		}
		
		long intervalNum = medVal / sector + 1;
		
		
//		
//		int startingMonth =  cal.get(Calendar.MONTH);
//		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		for(int i = 0; i < intervalNum; i++) {
			long cal = start + (medVal%sector) + (sector * i);
			long endCal = start + (medVal%sector) + (sector * (i+1));
			
//			System.out.println("Calendar begin: " + cal);
//			System.out.println("Calendar end: " + endCal);
//			if(monthHolder > 11) {
//				cal.set(Calendar.MONTH, monthHolder%12);
//				
//			}else {
//				cal.set(Calendar.MONTH, monthHolder);
//				cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-1);
//			}
			double sum = odel.getTotalByPurchaseTimeAndAsinSkuTitle(cal, endCal, asin, sku, title);
			//reimplement;
//			System.out.println("month: " + (monthHolder%12+1));
//			System.out.println("Year1: " + cal.get(Calendar.YEAR));
//			System.out.println("Month1: " + cal.get(Calendar.MONTH));
//			System.out.println("Day1: " + cal.get(Calendar.DAY_OF_MONTH));
//			System.out.println("Year2: " + endCal.get(Calendar.YEAR));
//			System.out.println("Month2: " + endCal.get(Calendar.MONTH));
//			System.out.println("Day2: " + endCal.get(Calendar.DAY_OF_MONTH));
//			System.out.println(sum);
			
			if(orderItemPrice.isEmpty()) {
				orderItemPrice.add(sum);
				months.add(i);
				cmt.add((new Timestamp(cal)).toString());
			}
			else{
				orderItemPrice.add(0, sum);
				months.add(0,i);
				cmt.add(0,(new Timestamp(cal)).toString());
			}
			
		}
		
		//System.out.println(months.size());
		//System.out.println(orderItemPrice.size());
		//DoubleArrayVector.Builder dVec = new DoubleArrayVector.Builder();
//		for(double oit : orderItemPrice) {
//			
//			//dVec.add(oit);
//		}
		
		int [] monthArr = new int[months.size()];
		double [] priceArr = new double[orderItemPrice.size()];
		String [] cmtArr = new String[cmt.size()];
//		months.toArray(monthArr);
//		orderItemPrice.toArray(priceArr);
		
//		need spring 4, not working in spring 3
//		monthArr = months.stream().mapToInt(i->i).toArray();
//		priceArr = orderItemPrice.stream().mapToDouble(i->i).toArray();
		int idx = 0;
		for(int i : months) {
			monthArr[idx] = i;
			idx++;
		}
		
		idx = 0;
		for(double pr : orderItemPrice) {
			priceArr[idx] = pr;
			idx++;
		}
		
		idx = 0;
		for(String pr : cmt) {
			cmtArr[idx] = pr;
			idx++;
		}
		
		String [] nameArr = new String[] {"months", "price"};
		//yyyy-mm-dd hh:mm:ss.fffffffff
		
		org.rosuda.REngine.REXP mdf = org.rosuda.REngine.REXP.createDataFrame(new RList(new org.rosuda.REngine.REXP[] {new REXPString(cmtArr), new REXPDouble(priceArr)}, nameArr));
		
		JRIEng.assign("myframe", mdf);
		//JRIEng.parseAndEval("print(myframe)");
//		ListVector.NamedBuilder df = new ListVector.NamedBuilder();
//		df.setAttribute(Symbols.CLASS, StringVector.valueOf("data.frame"));
//		df.setAttribute(Symbols.ROW_NAMES, new RowNamesVector(orderItemPrice.size()));
//		df.add("Months", iVec.build());
//		df.add("Monthly_total", dVec.build());
//		
//		rEng.eval("import(com.g128.model.OrderItem)");
//		rEng.eval("import(java.util.List)");
//		rEng.put("orderItemPrice", orderItemPrice);
//		rEng.put("months", months);
//		rEng.put("plot_frame", df);
//		//rEng.eval("print(months$toString())");
//		//rEng.eval("print(orderItemPrice$toString())");
//		rEng.eval("print(df)");
//		System.out.println(System.getProperty("user.dir"));
//		System.out.println(System.getProperty("user.home"));
		//JRIEng.parseAndEval("pdf(file=\"C:/Users/User/workspace/spring_proj/plot" + ("Result") + ".png\")");
		
		//File f = new File("resources/images/plotResult.png");
		
//		JRIEng.parseAndEval("path <- paste(getwd(),\"plot" + ("Result") + ".png\",sep=\"\")");
		if(pathStr == null || pathStr.trim().equalsIgnoreCase("")) {
			pathStr = "plotResult.png";
		}
		JRIEng.parseAndEval("path <- \"" + pathStr + "\"");
		JRIEng.parseAndEval("png(file=path)");
		JRIEng.parseAndEval("plot(strptime(myframe$months, \"%Y-%m-%d %H:%M:%S\"), myframe$price, ylab=\"Price\", xlab=\"Date\")");
		
		//JRIEng.parseAndEval("abline(lm(myframe$price~strptime(myframe$months, \"%Y-%m-%d %H:%M:%S\")), col=\"red\")");
		JRIEng.parseAndEval("lines(lowess(strptime(myframe$months, \"%Y-%m-%d %H:%M:%S\"),myframe$price), col=\"blue\")");
		
		JRIEng.parseAndEval("dev.off()");
		
		File f = new File("plotResult.png");
		BufferedImage bfimg = ImageIO.read(f);
		
//		WritableRaster raster = bfimg.getRaster();
//		DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
//		
//		byte [] dataByte = data.getData();
//		
//		String curPath = System.getProperty("user.dir");
//		
//		String path = request.getSession().getServletContext().getRealPath("/").replace('\\', '/');
//		String [] pathParts = path.split("\\.");
//		System.out.println(path);
//		System.out.println(pathParts[0]);
//		path = pathParts[0];
//		System.setProperty("user.dir", path.replace('\\', '/') + "spring_proj/src/main/webapp/resources/images");
//
//		
//		File f2 = new File("plotResult.png");
//		
//		
//		System.setProperty("user.dir", curPath);
		
		ByteArrayOutputStream byteIO = new ByteArrayOutputStream(); 
		ImageIO.write(bfimg, "png", byteIO); 
		//byte[] res=byteIO.toByteArray(); 
		String encodedImage = Base64.getEncoder().encodeToString(byteIO.toByteArray());
		
		byteIO.close();
		//f.delete();
		
		return encodedImage;
		
	}
	
	public String analyze(Calendar begin, Calendar end) throws REngineException, REXPMismatchException, IOException {
		List<Double> orderItemPrice = new ArrayList<>();
		List<Integer> months = new ArrayList<>();
		List<String> cmt = new ArrayList<>();
		//IntArrayVector.Builder iVec = new IntArrayVector.Builder();
		System.out.println("Current working dir.: " + System.getProperty("user.dir"));
		
		
//		for(int i =1; i <= 12; i++) {
//			months.add(i);
//			
//			//iVec.add(i);
//		}
		
		long start = begin.getTimeInMillis();
		long last = end.getTimeInMillis();
		long medVal = last - start;
		long sector = medVal/24;
		if(sector <= 60*60*1000) {
			sector = 60*60*1000;
		}else if(sector <= 7*60*60*1000) {
			sector = 7*60*60*1000;
		}else if(sector <= 30*60*60*1000) {
			sector = 30*60*60*1000;
		}else if(sector <= 90*60*60*1000) {
			sector = 90*60*60*1000;
		}else {
			sector = 365*60*60*1000;
		}
		
		long intervalNum = medVal / sector + 1;
//		
//		int startingMonth =  cal.get(Calendar.MONTH);
//		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		Map<String, Double> asinMap = new HashMap<>();
		Map<String, Double>	skuMap = new HashMap<>();
		Map<String, Double>	titleMap = new HashMap<>();
		Map<String, double[]> asinSegMap = new HashMap<>();
		Map<String, double[]> skuSegMap = new HashMap<>();
		Map<String, double[]> titleSegMap = new HashMap<>();
		List<Orders> ol = odel.getOrders(begin, end, "", "", "","","","");
		Set<List<String>> titleL = odel.getMapped();
		titleL.addAll(odel.getUnmapped());
		
		
		for(Orders ord : ol) {
			long idx = ord.getPurchaseDateConverted().getTime();
			idx -= start;
			idx /= sector;
			System.out.println(idx);
			System.out.println(intervalNum);
			
			List<OrderItem> oil = odel.getOrderItemsByOrderId(ord.getAmazonOrderId());
			for(OrderItem oi : oil) {
				if(oi.getItemPrice() != null) {
					asinMap.put(oi.getAsin(), asinMap.containsKey(oi.getAsin())?Double.parseDouble(oi.getItemPrice().getAmount())+asinMap.get(oi.getAsin()): Double.parseDouble(oi.getItemPrice().getAmount()));
					skuMap.put(oi.getSellerSKU(), skuMap.containsKey(oi.getSellerSKU())?Double.parseDouble(oi.getItemPrice().getAmount())+skuMap.get(oi.getSellerSKU()): Double.parseDouble(oi.getItemPrice().getAmount()));
					double[] asinM;
					double[] skuM;
					if(asinSegMap.containsKey(oi.getAsin())) {
						asinM = asinSegMap.get(oi.getAsin());
					}else {
						asinM = new double[(int) intervalNum];
						Arrays.fill(asinM, 0);
					}
					if(skuSegMap.containsKey(oi.getSellerSKU())) {
						skuM = skuSegMap.get(oi.getSellerSKU());
					}else {
						skuM = new double[(int) intervalNum];
						Arrays.fill(skuM, 0);
						
					}
					asinM[(int) idx] = asinM[(int) idx] + Double.parseDouble(oi.getItemPrice().getAmount());
					skuM[(int) idx] = skuM[(int) idx] + Double.parseDouble(oi.getItemPrice().getAmount());
					asinSegMap.put(oi.getAsin(), asinM);
					skuSegMap.put(oi.getSellerSKU(), skuM);
					for(List<String> lst : titleL) {
						if(lst.get(0).equals(oi.getAsin()) && lst.get(1).equals(oi.getSellerSKU())) {
							titleMap.put(lst.get(2), titleMap.containsKey(lst.get(2))?Double.parseDouble(oi.getItemPrice().getAmount())+titleMap.get(lst.get(2)): Double.parseDouble(oi.getItemPrice().getAmount()));
							double[] titleM;
							
							if(titleSegMap.containsKey(lst.get(2))) {
								titleM = titleSegMap.get(lst.get(2));
							}else {
								titleM = new double[(int) intervalNum];
								Arrays.fill(titleM, 0);
							}
							titleM[(int) idx] = titleM[(int) idx] + Double.parseDouble(oi.getItemPrice().getAmount());
							
							titleSegMap.put(lst.get(2), titleM);
							
						}
					}
				}
			}
		}
		
		
		String cmtArr [] = new String[(int) intervalNum];
		for(int i = 0; i < intervalNum; i ++) {
			cmtArr[i] = new Timestamp(begin.getTimeInMillis() + sector*(i+1)).toString();
			if(i+1 == intervalNum) {
				cmtArr[i] = new Timestamp(end.getTimeInMillis()).toString();
			}
		}
		
		
		int max = 10;
		
		
		double[][] asinHolder = new double[10][];
		double[][] skuHolder = new double[10][];
		double[][] titleHolder = new double[10][];
		String[] asinName = new String[11];
		String[] skuName = new String[11];
		String[] titleName = new String[11];
		
		asinName[0] = "month";
		skuName[0] = "month";
		titleName[0] = "month";
		
		for(int i = 0; i < max; i++) {
			Map.Entry<String, Double> maxEntry = null;
			
			for (Map.Entry<String, Double> entry : asinMap.entrySet())
			{
			    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
			    {
			        maxEntry = entry;
			    }
			}
			
			double[] maxAsin = asinSegMap.get(maxEntry.getKey());
			asinMap.remove(maxEntry.getKey());
			
			asinHolder[i] = maxAsin;
			asinName[i+1] = maxEntry.getKey();
			
			
			maxEntry = null;
			
			for (Map.Entry<String, Double> entry : skuMap.entrySet())
			{
			    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
			    {
			        maxEntry = entry;
			    }
			}
			
			double[] maxSku = skuSegMap.get(maxEntry.getKey());
			skuMap.remove(maxEntry.getKey());
			
			skuHolder[i] = maxSku;
			skuName[i+1] = maxEntry.getKey();
			
			
			maxEntry = null;
			
			for (Map.Entry<String, Double> entry : titleMap.entrySet())
			{
			    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
			    {
			        maxEntry = entry;
			    }
			}
			
			double[] maxTitle = titleSegMap.get(maxEntry.getKey());
			titleMap.remove(maxEntry.getKey());
			
			titleHolder[i] = maxTitle;
			titleName[i+1] = maxEntry.getKey();
		}
			
//		for(int i = 0; i <= rounds; i++) {
//			Calendar cal = new GregorianCalendar();
//			cal.setTimeInMillis(System.currentTimeMillis());
//			int yrHolder = startYear + i/12;
//			int monthHolder = (startMonth + i)%12;
//			
//			
//			cal.set(Calendar.YEAR, yrHolder);
//			cal.set(Calendar.MONTH, monthHolder);
//			cal.set(Calendar.DAY_OF_MONTH, 1);
//			
//			
//			Calendar endCal = (Calendar) cal.clone();
//			endCal.set(Calendar.MONTH, (monthHolder+1)% 12); 
//			
////			System.out.println("Calendar begin: " + cal);
////			System.out.println("Calendar end: " + endCal);
////			if(monthHolder > 11) {
////				cal.set(Calendar.MONTH, monthHolder%12);
////				
////			}else {
////				cal.set(Calendar.MONTH, monthHolder);
////				cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-1);
////			}
//			
//			
//			double sum = odel.getTotalByPurchaseTimeAndAsinSkuTitle(cal.getTimeInMillis(), endCal.getTimeInMillis(), asin, sku, title);
//			//reimplement;
////			System.out.println("month: " + (monthHolder%12+1));
////			System.out.println("Year1: " + cal.get(Calendar.YEAR));
////			System.out.println("Month1: " + cal.get(Calendar.MONTH));
////			System.out.println("Day1: " + cal.get(Calendar.DAY_OF_MONTH));
////			System.out.println("Year2: " + endCal.get(Calendar.YEAR));
////			System.out.println("Month2: " + endCal.get(Calendar.MONTH));
////			System.out.println("Day2: " + endCal.get(Calendar.DAY_OF_MONTH));
////			System.out.println(sum);
//			
//			if(orderItemPrice.isEmpty()) {
//				orderItemPrice.add(sum);
//				months.add(cal.get(Calendar.MONTH));
//				cmt.add((new Timestamp(cal.getTimeInMillis())).toString());
//			}
//			else{
//				orderItemPrice.add(0, sum);
//				months.add(0,cal.get(Calendar.MONTH));
//				cmt.add(0,(new Timestamp(cal.getTimeInMillis())).toString());
//			}
//			
//		}
		
		//System.out.println(months.size());
		//System.out.println(orderItemPrice.size());
		//DoubleArrayVector.Builder dVec = new DoubleArrayVector.Builder();
//		for(double oit : orderItemPrice) {
//			
//			//dVec.add(oit);
//		}
		
//		int [] monthArr = new int[months.size()];
//		double [] priceArr = new double[orderItemPrice.size()];
//		String [] cmtArr = new String[cmt.size()];
//		months.toArray(monthArr);
//		orderItemPrice.toArray(priceArr);
		
//		need spring 4, not working in spring 3
//		monthArr = months.stream().mapToInt(i->i).toArray();
//		priceArr = orderItemPrice.stream().mapToDouble(i->i).toArray();
//		int idx = 0;
//		for(int i : months) {
//			monthArr[idx] = i;
//			idx++;
//		}
//		
//		idx = 0;
//		for(double pr : orderItemPrice) {
//			priceArr[idx] = pr;
//			idx++;
//		}
		
//		idx = 0;
//		for(String pr : cmt) {
//			cmtArr[idx] = pr;
//			idx++;
//		}
		System.out.println("It is here!");
		
		//yyyy-mm-dd hh:mm:ss.fffffffff
		JRIEng.parseAndEval("require(ggplot2); require(reshape);");
		
		org.rosuda.REngine.REXP mdf1 = org.rosuda.REngine.REXP.createDataFrame(new RList(new org.rosuda.REngine.REXP[] {new REXPString(cmtArr), new REXPDouble(asinHolder[0]), new REXPDouble(asinHolder[1]), new REXPDouble(asinHolder[2]), new REXPDouble(asinHolder[3]), new REXPDouble(asinHolder[4]), new REXPDouble(asinHolder[5]), new REXPDouble(asinHolder[6]), new REXPDouble(asinHolder[7]), new REXPDouble(asinHolder[8]), new REXPDouble(asinHolder[9])}, asinName));
		
		JRIEng.assign("asinframe", mdf1);
		
		org.rosuda.REngine.REXP mdf2 = org.rosuda.REngine.REXP.createDataFrame(new RList(new org.rosuda.REngine.REXP[] {new REXPString(cmtArr), new REXPDouble(skuHolder[0]), new REXPDouble(skuHolder[1]), new REXPDouble(skuHolder[2]), new REXPDouble(skuHolder[3]), new REXPDouble(skuHolder[4]), new REXPDouble(skuHolder[5]), new REXPDouble(skuHolder[6]), new REXPDouble(skuHolder[7]), new REXPDouble(skuHolder[8]), new REXPDouble(skuHolder[9])}, skuName));
		
		JRIEng.assign("skuframe", mdf2);
		
		org.rosuda.REngine.REXP mdf3 = org.rosuda.REngine.REXP.createDataFrame(new RList(new org.rosuda.REngine.REXP[] {new REXPString(cmtArr), new REXPDouble(titleHolder[0]), new REXPDouble(titleHolder[1]), new REXPDouble(titleHolder[2]), new REXPDouble(titleHolder[3]), new REXPDouble(titleHolder[4]), new REXPDouble(titleHolder[5]), new REXPDouble(titleHolder[6]), new REXPDouble(titleHolder[7]), new REXPDouble(titleHolder[8]), new REXPDouble(titleHolder[9])}, titleName));
		
		JRIEng.assign("titleframe", mdf3);
		
		JRIEng.parseAndEval("print(asinframe)");
		JRIEng.parseAndEval("print(skuframe)");
		JRIEng.parseAndEval("print(titleframe)");
		
		
		//JRIEng.parseAndEval("print(myframe)");
//		ListVector.NamedBuilder df = new ListVector.NamedBuilder();
//		df.setAttribute(Symbols.CLASS, StringVector.valueOf("data.frame"));
//		df.setAttribute(Symbols.ROW_NAMES, new RowNamesVector(orderItemPrice.size()));
//		df.add("Months", iVec.build());
//		df.add("Monthly_total", dVec.build());
//		
//		rEng.eval("import(com.g128.model.OrderItem)");
//		rEng.eval("import(java.util.List)");
//		rEng.put("orderItemPrice", orderItemPrice);
//		rEng.put("months", months);
//		rEng.put("plot_frame", df);
//		//rEng.eval("print(months$toString())");
//		//rEng.eval("print(orderItemPrice$toString())");
//		rEng.eval("print(df)");
//		System.out.println(System.getProperty("user.dir"));
//		System.out.println(System.getProperty("user.home"));
		//JRIEng.parseAndEval("pdf(file=\"C:/Users/User/workspace/spring_proj/plot" + ("Result") + ".png\")");
		
		//File f = new File("resources/images/plotResult.png");
		
//		JRIEng.parseAndEval("path <- paste(getwd(),\"plot" + ("Result") + ".png\",sep=\"\")");
		
		JRIEng.parseAndEval("asinframe <- melt(asinframe, id.vars=\"month\")");
		
		JRIEng.parseAndEval("skuframe <- melt(skuframe, id.vars=\"month\")");
		
		JRIEng.parseAndEval("titleframe <- melt(titleframe, id.vars=\"month\")");
		
		JRIEng.parseAndEval("print(asinframe$variable)");
		JRIEng.parseAndEval("print(skuframe$variable)");
		JRIEng.parseAndEval("print(titleframe$variable)");
		
		
		JRIEng.parseAndEval("print(asinframe$month)");
		JRIEng.parseAndEval("print(skuframe$month)");
		JRIEng.parseAndEval("print(titleframe$month)");
		
		System.out.println("It reaches here!");
//		JRIEng.parseAndEval("dev.new()");
		JRIEng.parseAndEval("path <- \"plotResult1.png\"");
		JRIEng.parseAndEval("png(file=path)");
		JRIEng.parseAndEval("ggplot(asinframe, aes(x=month, y=value, group=variable)) +" + 
				"  geom_line()+" + 
				"  geom_point()");
		JRIEng.parseAndEval("dev.off()");
//		JRIEng.parseAndEval("dev.new()");
		System.out.println("It get to here!");
		
		JRIEng.parseAndEval("path <- \"plotResult2.png\"");
		JRIEng.parseAndEval("png(file=path)");
		JRIEng.parseAndEval("ggplot(skuframe, aes(x=month, y=value, group=variable)) +" + 
				"  geom_line()+" + 
				"  geom_point()");
		JRIEng.parseAndEval("dev.off()");
//		JRIEng.parseAndEval("dev.new()");
		JRIEng.parseAndEval("path <- \"plotResult3.png\"");
		JRIEng.parseAndEval("png(file=path)");
		JRIEng.parseAndEval("ggplot(titleframe, aes(x=month, y=value, group=variable)) +" + 
				"  geom_line()+" + 
				"  geom_point()");
		JRIEng.parseAndEval("dev.off()");
//		JRIEng.parseAndEval("ggplot(titleframe, aes(x=strptime(titleframe$month, \"%Y-%m-%d %H:%M:%S\"), y=value, group=variable, ylab=\"Cost\", xlab=\"Date\")) +" + 
//				"  geom_line(aes(linetype=variable))+" + 
//				"  geom_point(aes(shape=variable))");
		File f = new File("plotResult1.png");
		
		
		System.out.println("Images are done!");
		BufferedImage bfimg = ImageIO.read(f);
		
//		WritableRaster raster = bfimg.getRaster();
//		DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
//		
//		byte [] dataByte = data.getData();
//		
//		String curPath = System.getProperty("user.dir");
//		
//		String path = request.getSession().getServletContext().getRealPath("/").replace('\\', '/');
//		String [] pathParts = path.split("\\.");
//		System.out.println(path);
//		System.out.println(pathParts[0]);
//		path = pathParts[0];
//		System.setProperty("user.dir", path.replace('\\', '/') + "spring_proj/src/main/webapp/resources/images");
//
//		
//		File f2 = new File("plotResult.png");
//		
//		
//		System.setProperty("user.dir", curPath);
		
		ByteArrayOutputStream byteIO = new ByteArrayOutputStream(); 
		ImageIO.write(bfimg, "png", byteIO); 
		//byte[] res=byteIO.toByteArray(); 
		String encodedImage = Base64.getEncoder().encodeToString(byteIO.toByteArray());
		
		byteIO.close();
		//f.delete();
		
		return encodedImage;
		
	}
	
	public void testVal() throws ScriptException, InterruptedException {
		long yr = 24*60*60;
		yr *= 365;
		yr*=1000;
		//List<OrderItem> oitems = fetchingItemsByDate(System.currentTimeMillis()-yr, System.currentTimeMillis());
		//List<Orders> ord;
		List<Double> orderItemPrice = new ArrayList<>();
		List<Integer> months = new ArrayList<>();
		IntArrayVector.Builder iVec = new IntArrayVector.Builder();
//		for(int i =1; i <= 12; i++) {
//			months.add(i);
//			
//			iVec.add(i);
//		}
		
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(System.currentTimeMillis());
		int startingMonth =  cal.get(Calendar.MONTH);
		System.out.println("starting month: " + startingMonth);
		
		
		for(int i = startingMonth; i <= startingMonth+12; i++) {
			
			cal.setTimeInMillis(System.currentTimeMillis());
			
			cal.set(Calendar.DAY_OF_MONTH, 1);
			
			
			
			int monthHolder = i%12;
			
			
			Calendar end = (Calendar) cal.clone();
			
			
			if(i < 12) {
				cal.set(Calendar.MONTH, monthHolder);
				
				System.out.println(cal.get(Calendar.MONTH));
				months.add(cal.get(Calendar.MONTH));
				cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-1);
				
				iVec.add(cal.get(Calendar.MONTH));
				if(monthHolder == 11) {
					end.set(Calendar.MONTH, 0);
				}else {
					end.set(Calendar.MONTH, monthHolder+1);
					end.set(Calendar.YEAR, end.get(Calendar.YEAR)-1);
				}
			}else {
				cal.set(Calendar.MONTH, monthHolder);
				
				System.out.println(cal.get(Calendar.MONTH));
				months.add(cal.get(Calendar.MONTH));
				
				iVec.add(cal.get(Calendar.MONTH));
				
				if(monthHolder == 11) {
					end.set(Calendar.MONTH, 0);
					end.set(Calendar.YEAR, end.get(Calendar.YEAR)+1);
				}else {
					end.set(Calendar.MONTH, monthHolder+1);
					
				}
			}
			double sum = odel.getTotalByPurchaseTime(cal.getTimeInMillis(), end.getTimeInMillis());
//			System.out.println("month: " + (monthHolder%12+1));
			System.out.println("Year1: " + cal.get(Calendar.YEAR));
			System.out.println("Month1: " + cal.get(Calendar.MONTH));
			System.out.println("Day1: " + cal.get(Calendar.DAY_OF_MONTH));
			System.out.println("Year2: " + end.get(Calendar.YEAR));
			System.out.println("Month2: " + end.get(Calendar.MONTH));
			System.out.println("Day2: " + end.get(Calendar.DAY_OF_MONTH));
			
			System.out.println(sum);
			
			if(orderItemPrice.isEmpty()) {
				orderItemPrice.add(sum);
			
			}
			else{
				orderItemPrice.add(0, sum);
			}
			
		}
		DoubleArrayVector.Builder dVec = new DoubleArrayVector.Builder();
		for(double oit : orderItemPrice) {
			
			dVec.add(oit);
		}
		
		ListVector.NamedBuilder df = new ListVector.NamedBuilder();
		df.setAttribute(Symbols.CLASS, StringVector.valueOf("data.frame"));
		df.setAttribute(Symbols.ROW_NAMES, new RowNamesVector(orderItemPrice.size()));
		df.add("Months", iVec.build());
		df.add("Monthly_total", dVec.build());
		
		rEng.eval("import(com.g128.model.OrderItem)");
		rEng.eval("import(java.util.List)");
		rEng.put("orderItemPrice", orderItemPrice);
		rEng.put("months", months);
		rEng.put("plot_frame", df);
		//rEng.eval("print(months$toString())");
		//rEng.eval("print(orderItemPrice$toString())");
		rEng.eval("print(df)");
		
		Thread.sleep(30000);
		
	}
}
