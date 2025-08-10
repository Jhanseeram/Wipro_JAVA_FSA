package com.wipro.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.wipro.hibernate.entity.Pan;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
        	
            sessionFactory = new Configuration()
            	.configure("hibernate.cfg.xml")
            	.addAnnotatedClass(Pan.class)
                .buildSessionFactory();
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
