package com.spring.mvc.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connection {

	private static volatile Connection connection=null;
	private static final Object lock = new Object();
	private static  SessionFactory sessionFactory;
	
	

	 public static Connection getConnection(){
		 //only needed at time of create .addAnnotatedClass(cl)
		
			// sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(cl).buildSessionFactory();
			 
		
		   sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		  
		  Connection conn = connection;
			 if(conn==null) {
				 synchronized (lock) {//while getting lock theres a chance another class would try to inst
					 conn = connection;
					 if(conn==null) {
						 conn = new Connection();
					 }
				 }
		 }
			 return conn;
	   }

	   public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	   }
	
	
	
	
	
}
