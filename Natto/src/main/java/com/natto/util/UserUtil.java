package com.natto.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserUtil {
	
	private static SessionFactory sessionFactory=null;

	static {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}


