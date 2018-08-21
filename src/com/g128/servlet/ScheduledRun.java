package com.g128.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.script.ScriptException;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.hibernate.HibernateException;
import org.hibernate.exception.JDBCConnectionException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.g128.amazonservice.mws.service.AmazonOrderItemService;
import com.g128.amazonservice.mws.service.AmazonOrderService;
import com.g128.businessdelegate.AnalysisService;
import com.g128.businessdelegate.OrderDelegation;
import com.g128.businessdelegate.ReportDelegation;
import com.g128.model.OffsetRecords;

import io.netty.handler.timeout.TimeoutException;

public class ScheduledRun implements Runnable{
	private OrderDelegation del;
	private ReportDelegation rpdel;
	private static long fetchingCount = 1;
	private int quad;
	private AnalysisService aly;
	public ScheduledRun() {
		// TODO Auto-generated constructor stub
	}
	
	public ScheduledRun(int quad) {
		// TODO Auto-generated constructor stub
		this.quad = quad;
		
	}
	
	@Override
	public void run(){
		System.out.println("\nExecuting runnable\n");
		del = new OrderDelegation();
		del.setCount(fetchingCount);
		rpdel = new ReportDelegation();
		
		System.out.println("Im here");
		aly = new AnalysisService(0);
		
		
		try {
			long timeorg = 0;
			long time = System.currentTimeMillis();
			
			try {
				System.out.println("Into try");
				del.upToday(time);
				fetchingCount++;
				
				
			} catch (RuntimeException | IOException | InterruptedException | DatatypeConfigurationException e) {
				try {
					System.out.println("Exception caught");
					e.printStackTrace();
					if(e.getClass().getSimpleName().equalsIgnoreCase("AmazonServiceException"))	{				
						Thread.sleep(120000);
						AmazonOrderItemService.reset();
						AmazonOrderService.reset();
					}
					System.out.println("count: " + fetchingCount);
					this.run();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					System.out.println("count: " + fetchingCount);
					AmazonOrderItemService.reset();
					AmazonOrderService.reset();
					this.run();
					
				}
			}
			
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
	}
	public static void resetCount() {
		ScheduledRun.fetchingCount = 0;
	}
}
