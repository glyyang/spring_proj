package com.g128.servlet;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.ContextLoaderListener;

/**
 * Application Lifecycle Listener implementation class AppServletContextListener
 *
 */
public class AppServletContextListener extends ContextLoaderListener implements ServletContextListener {

	private volatile ScheduledExecutorService executor;
    /**
     * Default constructor. 
     */
    public AppServletContextListener() {
        // TODO Auto-generated constructor stub
    	super();
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) { 
         // TODO Auto-generated method stub
    	
    	System.out.println("\nInitialize context\n");
    	executor = Executors.newScheduledThreadPool(2);
    	Calendar dt =  new GregorianCalendar();
    	dt.setTimeInMillis(System.currentTimeMillis());
    	long hr = dt.get(Calendar.HOUR_OF_DAY);
    	long min = dt.get(Calendar.MINUTE);
    	long sec = dt.get(Calendar.SECOND);
    	int [] hrC = {5, 11, 17, 23};
    	for(int pick : hrC) {
    		if(pick - hr > 0) {
    			hr = pick-hr;
    			break;
    		}
    	}
    	
    	min = 60-min;
    	sec = 60-sec;
    	long waitTime = hr*60*60 + min * 60 + sec;
    	
    	//waitTime = 0;
    	
    	System.out.println("Time to wait: " + waitTime);
//		executor.scheduleAtFixedRate(new ScheduledRun(0), waitTime+5*60, 60*6*60, TimeUnit.SECONDS);
		executor.scheduleAtFixedRate(new ScheduledRun(0), 0, 60*3*60, TimeUnit.SECONDS);
		
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("Destroying context");
    	final ScheduledExecutorService executor = this.executor;

        if (executor != null)
        {
        	ScheduledRun.resetCount();
            executor.shutdown();
            this.executor = null;
        }
    }

	
	
}
