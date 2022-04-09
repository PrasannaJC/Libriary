package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.Book;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class BookUtil {
	static SessionFactory sessionFactory = null;

	   public static SessionFactory getSessionFactory() {
	      if (sessionFactory != null) {
	         return sessionFactory;
	      }
	      Configuration configuration = new Configuration().configure();
	      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	      sessionFactory = configuration.buildSessionFactory(builder.build());
	      return sessionFactory;
	   }

	   public static List<Book> listBooks() {
	      List<Book> resultList = new ArrayList<Book>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
 
	      try {
	         tx = session.beginTransaction();
	         List<?> bks = session.createQuery("FROM BookTable").list();
	         for (Iterator<?> iterator = bks.iterator(); iterator.hasNext();) {
	            Book b = (Book) iterator.next();
	               resultList.add(b);
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

	   public static List<Book> listBook(String keyTitle, String keyAuthor, String keyISBN, String keyCategory, String keyCopy, String keyYear) {
		   
		   List<Book> resultList = new ArrayList<Book>();
		   Session session = getSessionFactory().openSession();
		   Transaction tx = null;

		   try {
			   tx = session.beginTransaction();
			   List<?> bks = session.createQuery("FROM BookTable").list();
			   for (Iterator<?> iterator = bks.iterator(); iterator.hasNext();) {
				   Book b = (Book) iterator.next();
				   
				   if ((b.getTitle().startsWith(keyTitle) || keyTitle == null) && (b.getAuthor().startsWith(keyAuthor) || keyAuthor == null) &&
		            		(b.getISBN13().startsWith(keyISBN) || keyISBN == null) && (b.getCategory().startsWith(keyCategory) || keyCategory == null) && 
		            		(b.getCopies() == Integer.getInteger(keyCopy) || keyCopy == null) && (b.getPublicationYear() == Integer.getInteger(keyYear) || keyYear == null))
		            		{
					   			resultList.add(b);
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

	   public static void createBook(String title, String author, String isbn13, String category, String copies, String description, String publicationyear) {
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      try {
	         tx = session.beginTransaction();
	         session.save(new Book(title,author,isbn13,category,Integer.valueOf(copies),description,Integer.valueOf(publicationyear)));
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
