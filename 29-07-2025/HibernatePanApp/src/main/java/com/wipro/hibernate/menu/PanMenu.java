package com.wipro.hibernate.menu;

import java.util.List;
import java.util.Scanner;
import com.wipro.hibernate.entity.Pan;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.wipro.hibernate.util.HibernateUtil;

public class PanMenu {

    private static Scanner scanner = new Scanner(System.in);

    public static void showMenu() {
        int choice;
        do {
            System.out.println("\n--- PAN Management Menu ---");
            System.out.println("1. Create PAN Record");
            System.out.println("2. Find PAN by ID");
            System.out.println("3. Update PAN Record");
            System.out.println("4. Delete PAN by ID");
            System.out.println("5. Find All PAN Records");
            System.out.println("6. Find All PAN Holder Names");
            System.out.println("7. Find PAN by PAN Number");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: createPan(); break;
                case 2: findPanById(); break;
                case 3: updatePan(); break;
                case 4: deletePanById(); break;
                case 5: findAllPans(); break;
                case 6: findAllPanHolderNames(); break;
                case 7: findPanByNumber(); break;
                case 9: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 9);
    }

    // Now create method stubs (will add logic later)
    public static void createPan() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Pan pan = new Pan();

            System.out.print("Enter PAN ID: ");
            pan.setId(scanner.nextInt());
            scanner.nextLine(); // flush newline

            System.out.print("Enter PAN Holder Name: ");
            pan.setPanholderName(scanner.nextLine());

            System.out.print("Enter PAN Number: ");
            pan.setPanNumber(scanner.nextLine());

            session.persist(pan);
            tx.commit();
            System.out.println("PAN record created successfully.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    
    public static void findPanById() {
    	System.out.print("Enter PAN ID: ");
        int id = scanner.nextInt();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Pan pan = session.get(Pan.class, id);
            if (pan != null) {
                System.out.println("PAN Found: " + pan.getId() + " | " + pan.getPanholderName() + " | " + pan.getPanNumber());
            } else {
                System.out.println("No PAN found with ID: " + id);
            }
        } finally {
            session.close();
        }
    }
    
    
    public static void updatePan() {
    	System.out.print("Enter PAN ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // flush

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            Pan pan = session.get(Pan.class, id);
            if (pan == null) {
                System.out.println("PAN with ID " + id + " not found.");
                return;
            }

            System.out.print("Enter new PAN Holder Name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new PAN Number: ");
            String newPanNumber = scanner.nextLine();

            tx = session.beginTransaction();
            pan.setPanholderName(newName);
            pan.setPanNumber(newPanNumber);
            session.merge(pan);
            tx.commit();

            System.out.println("PAN record updated successfully.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    
    public static void deletePanById() {
        System.out.print("Enter PAN ID to delete: ");
        int id = scanner.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            Pan pan = session.get(Pan.class, id);
            if (pan == null) {
                System.out.println("PAN with ID " + id + " not found.");
                return;
            }

            tx = session.beginTransaction();
            session.remove(pan);
            tx.commit();
            System.out.println("PAN record deleted successfully.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    
    public static void findAllPans() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<Pan> panList = session.createQuery("from Pan", Pan.class).list();
            if (panList.isEmpty()) {
                System.out.println("No PAN records found.");
            } else {
                System.out.println("All PAN Records:");
                for (Pan pan : panList) {
                    System.out.println(pan.getId() + " | " + pan.getPanholderName() + " | " + pan.getPanNumber());
                }
            }
        } finally {
            session.close();
        }
    }
    
    
    public static void findAllPanHolderNames() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<String> names = session.createQuery("select p.panholderName from Pan p", String.class).list();
            if (names.isEmpty()) {
                System.out.println("No PAN holder names found.");
            } else {
                System.out.println("PAN Holder Names:");
                for (String name : names) {
                    System.out.println(name);
                }
            }
        } finally {
            session.close();
        }
    }
    
    
    public static void findPanByNumber() {
    	System.out.print("Enter PAN Number to search: ");
        scanner.nextLine(); // flush
        String panNumber = scanner.nextLine();

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from Pan p where p.panNumber = :panNumber";
            Pan pan = session.createQuery(hql, Pan.class)
                             .setParameter("panNumber", panNumber)
                             .uniqueResult();

            if (pan != null) {
                System.out.println("PAN Found: " + pan.getId() + " | " + pan.getPanholderName() + " | " + pan.getPanNumber());
            } else {
                System.out.println("No PAN found with number: " + panNumber);
            }
        } finally {
            session.close();
        }
    }
}
