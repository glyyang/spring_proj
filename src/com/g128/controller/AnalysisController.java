package com.g128.controller;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.g128.businessdelegate.AnalysisService;

@Controller
@ComponentScan("com.g128.businessdelegate")
public class AnalysisController {

	@Autowired
	private AnalysisService anServ;
	
	@RequestMapping(value={"/analytic"}, method = RequestMethod.POST)
	public String analyze(HttpServletRequest request) throws IOException, InterruptedException, DatatypeConfigurationException, REngineException, REXPMismatchException, ParseException{
		String asin = request.getParameter("asin");
		String sku = request.getParameter("sku");
		String title = request.getParameter("title");
		Calendar from;
		Calendar to;
		
		String ffStr = request.getParameter("ff");
		ffStr = ffStr + ":" + request.getParameter("ffHr");
		
		String ttStr = request.getParameter("tt");
		ttStr = ttStr + ":" + request.getParameter("ttHr");
		
		String pathStr = request.getParameter("fpath");
		
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
		
		String encodedImage = anServ.analyze(from, to, asin, sku, title, pathStr);
		
		
		request.setAttribute("imgSrc", encodedImage);
		
		
		return "analysis";
	}
	
	@RequestMapping(value={"/compare_statistic"}, method = RequestMethod.POST)
	public String analyzeComp(HttpServletRequest request) throws IOException, InterruptedException, DatatypeConfigurationException, REngineException, REXPMismatchException, ParseException{
		
		Calendar from;
		Calendar to;
		
		String ffStr = request.getParameter("ff2");
		ffStr = ffStr + ":" + request.getParameter("ffHr2");
		
		String ttStr = request.getParameter("tt2");
		ttStr = ttStr + ":" + request.getParameter("ttHr2");
		
		
		
		
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
		
		String encodedImage = anServ.analyze(from, to);
		
		
		request.setAttribute("imgSrc2", encodedImage);
		
		
		return "analysis";
	}
	
	@RequestMapping(value={"/analysis"}, method = RequestMethod.GET)
	public String analysisHome(HttpServletRequest request) {
		request.setAttribute("desc", "This page allows user to see sales' trend graph according to time and order item specifications.");
		return "analysis";
	}
}
