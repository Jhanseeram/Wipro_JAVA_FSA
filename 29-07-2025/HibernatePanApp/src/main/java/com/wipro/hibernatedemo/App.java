package com.wipro.hibernatedemo;

//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import com.wipro.hibernate.entity.Pan;
import com.wipro.hibernate.menu.PanMenu;
//import com.wipro.hibernate.util.HibernateUtil;

public class App {
    public static void main(String[] args) {
        // Create a Pan object and set values
//        Pan pan = new Pan();
//        pan.setId(101);
//        pan.setPanholderName("Janhvee Mahajan");
//        pan.setPanNumber("JKP9XXXX");
//
//        // Open session and begin transaction
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = null;
//
//        try {
//            transaction = session.beginTransaction();
//
//            // Save the Pan object
//            session.persist(pan);
//
//            transaction.commit();
//            System.out.println("Pan details saved successfully!");
//
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
    	
    	PanMenu.showMenu();
    }
}
