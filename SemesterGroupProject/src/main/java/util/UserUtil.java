package util;

import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


import datamodel.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UserUtil {
	
	static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() 
	{
		if (sessionFactory != null) 
		{
			return sessionFactory;
		}
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}
	
	public static List<User> listUsers() 
	{
	      List<User> resultList = new ArrayList<User>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         List<?> usr = session.createQuery("FROM User").list();
	         for (Iterator<?> iterator = usr.iterator(); iterator.hasNext();) 
	         {
	            User u = (User) iterator.next();
	            resultList.add(u);
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	}	
	
	public static List<User> listUsers(Integer userID, String userName, String books) 
	{
	      List<User> resultList = new ArrayList<User>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

			System.out.println("=> " + userID);
			System.out.println("=> " + userName);
			System.out.println("=> " + books);
			
	      try {
	         tx = session.beginTransaction();
	         List<?> usr = session.createQuery("FROM User").list();
	         for (Iterator<?> iterator = usr.iterator(); iterator.hasNext();) 
	         {
	            User u = (User) iterator.next();
	            if ((userID == null || u.getUserID().equals(userID)) && (books == "" || books == null || u.getBooks().contains(books)) && (userName == "" || userName == null || u.getUsername().startsWith(userName)))
	            {
	            	resultList.add(u);
	            }

	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	}
	
	
	public static boolean checkUser(String userID)
	{
		//List<User> resultList = new ArrayList<User>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      Boolean out = false;
	      int x = Integer.parseInt(userID); 
	      try {
	         tx = session.beginTransaction();
	         List<?> usr = session.createQuery("FROM User").list();
	         for (Iterator<?> iterator = usr.iterator(); iterator.hasNext();) 
	         {
	        	 User u = (User) iterator.next();
	        	 if (u.getUserID() == x)
	        	 {
	        		 out = true;
	        		 break;
	        	 }
	        	 else
	        	 {
	        		 out = false;
	        	 }
	         }
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	         return out;
	}
	
	public static void createUser(String userName, String books, java.sql.Date checkout) {
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      try {
	         tx = session.beginTransaction();
	         //java.util.Date due = new java.util.Date(checkout.getTime());
	         LocalDate start = checkout.toLocalDate();
	         LocalDate due = start.plusDays(14);
	         java.sql.Date d = Date.valueOf(due);

	         session.save(new User(userName, books, checkout, d));
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	   }
	
	public static boolean updateUser(Integer UserID, String books, java.sql.Date checkout) {

        Session session = getSessionFactory().openSession();
          Transaction tx = null;

          try {
             tx = session.beginTransaction();
             List<?> usr = session.createQuery("FROM User").list();
             for (Iterator<?> iterator = usr.iterator(); iterator.hasNext();) 
             {
                 User u = (User) iterator.next();
                 if (u.getUserID().equals(UserID) && u.getDue() == null)
                 {
                    u.setBooks(books);
                    LocalDate start = checkout.toLocalDate();
                        LocalDate due = start.plusDays(14);
                        java.sql.Date d = Date.valueOf(due);
                        u.setCheckout(checkout);
                        u.setDue(d);
                        session.save(u);
                        return true;
                 }
             }
          } catch (HibernateException e) {
             if (tx != null)
                tx.rollback();
             e.printStackTrace();
          } finally {
             session.close();
          }
          return false;
       }
	public static void returnUserBook(Integer UserID, String book) {

        Session session = getSessionFactory().openSession();
          Transaction tx = null;

          try {
             tx = session.beginTransaction();
             List<?> usr = session.createQuery("FROM User").list();
             for (Iterator<?> iterator = usr.iterator(); iterator.hasNext();) 
             {
                 User u = (User) iterator.next();
                 String bookin[] = u.getBooks().split(", ");
                 String books = "";
                 if (u.getUserID().equals(UserID)){
		             for (int pos = 0; 0 < bookin.length; pos ++) {
		                 if (bookin[pos].equals(book)){
		                        bookin[pos] = "";
		                 } 
		                 if (!bookin[pos].equals("")) {
		                	 books = books + ", " + bookin[pos];
		                 }
		             } 
		             if(books.equals("")) { 
	                 	u.setBooks(null);
	                 	u.setDue(null);
	                 	u.setCheckout(null);
		             }else {
		            	u.setBooks(books);
		             }
                 }  
                 session.save(u);
             }  
             
          } catch (HibernateException e) {
             if (tx != null)
                tx.rollback();
             e.printStackTrace();
          } finally {
             session.close();
          }
       }
	
	public static List<User> overdue()
	{
		//java.time.LocalDate checkout = date.toLocalDate();
		java.time.LocalDate today = LocalDate.now();
		System.out.println("=> " + today);
		List<User> resultList = new ArrayList<User>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   List<?> usr = session.createQuery("FROM User").list();
		   java.time.LocalDate due;
		   for (Iterator<?> iterator = usr.iterator(); iterator.hasNext();) 
		   {
		      User u = (User) iterator.next();
		      System.out.println("=> " + u.getDue());
		      due = u.getDue().toLocalDate();
		      
		      if (due.compareTo(today) > 14)
		      {
		    	  resultList.add(u);
		      }
		   }
		   tx.commit();
		} catch (HibernateException e) {
		   if (tx != null)
		      tx.rollback();
		   e.printStackTrace();
		} finally {
		   session.close();
		}
		
		return resultList;

	}
	
	
	
	
	
}
