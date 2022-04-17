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
	
	public static List<User> listUsers(Integer userID, String userName, String[] books) 
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
	         /*   if ((userID == null || u.getUserID() == userID) && (books == null || u.getBooks().equals(books)) && (overdue == "" || u.getUserID() == userID))
	            {
	            	
	            }
	            
	            */
	            
	            
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
	
	
	public static boolean checkUser(Integer userID)
	{
		//List<User> resultList = new ArrayList<User>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      Boolean out = false;

	      try {
	         tx = session.beginTransaction();
	         List<?> usr = session.createQuery("FROM User").list();
	         for (Iterator<?> iterator = usr.iterator(); iterator.hasNext();) 
	         {
	        	 User u = (User) iterator.next();
	        	 if (u.getUserID().equals(userID))
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
	
	
	
	
}
