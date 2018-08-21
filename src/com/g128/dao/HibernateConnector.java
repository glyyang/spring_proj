/*
 * Backup Hibernate file, not used in this proj.
 */
package com.g128.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateConnector {
	private static HibernateConnector hc;
    private Configuration cfg;
    private SessionFactory sessionFactory;
    

	private HibernateConnector() throws HibernateException {
        // build the config
		cfg = new Configuration().configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                cfg.getProperties()).buildServiceRegistry();
        sessionFactory = cfg.buildSessionFactory(serviceRegistry);
    }
    
    public static synchronized HibernateConnector getInstance() throws HibernateException {
        if (hc == null) {
        	hc = new HibernateConnector();
        }
        return hc;
    }
    public Session getSession() throws HibernateException {
        Session session = sessionFactory.openSession();
        if (!session.isConnected()) {
            this.reconnect();
        }
        return session;
    }
    private void reconnect() throws HibernateException {
    	ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                cfg.getProperties()).buildServiceRegistry();
        this.sessionFactory = cfg.buildSessionFactory(serviceRegistry);
    }
}
