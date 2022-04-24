package util;

import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.Book;
import datamodel.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
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
	         List<?> bks = session.createQuery("FROM Book").list();
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

	   public static List<Book> listBooks(String keyTitle, String keyAuthor, String keyISBN, String keyCategory, Integer keyCopy, Integer keyYear) {
		   
		   List<Book> resultList = new ArrayList<Book>();
		   Session session = getSessionFactory().openSession();
		   Transaction tx = null;

		   try {
			   tx = session.beginTransaction();
			   List<?> bks = session.createQuery("FROM Book").list();
			   /*System.out.println(" keytitle is : " + keyTitle.length() + " , keyauth: " + keyAuthor.length() + " , keyisbn: " + keyISBN.length() + " , keycat: " + 
					   keyCategory + " , keycopies: " + keyCopy + " , keyYear: " + keyYear);*/
			   for (Iterator<?> iterator = bks.iterator(); iterator.hasNext();) {
				   Book b = (Book) iterator.next();
				   //System.out.println(" keytitle is : " + keyTitle.length() + " , keyauth: " + keyAuthor.length() + " , keyisbn: " + keyISBN.length() + " , keycat: " + 
				   //keyCategory + " , keycopies: " + keyCopy + " , keyYear: " + keyYear);
				   //System.out.println(" Main title : " + b.getTitle() + " , Main author: " + b.getAuthor() + " , Main isbn: " + b.getISBN13() + " , Main cat: " + 
				   //b.getCategory() + " , Main copies: " + b.getCopies() + " , Main Year: " + b.getPublicationYear());
				   /*if ((keyTitle == "" || b.getTitle().startsWith(keyTitle)) && (keyAuthor == "" || b.getAuthor().startsWith(keyAuthor)) &&
		            		(keyISBN == "" || b.getISBN13().startsWith(keyISBN)) && (keyCategory == "" || b.getCategory().startsWith(keyCategory)) && 
		            		(keyCopy == 999 || b.getCopies() == keyCopy) && (keyYear == 10000 || b.getPublicationYear() == keyYear))
		            		{
					   			resultList.add(b);

		            		}	*/
				   if ((keyTitle == null || b.getTitle().startsWith(keyTitle)) && (keyAuthor == null || b.getAuthor().startsWith(keyAuthor)) &&
		            		(keyISBN == null || b.getISBN13().startsWith(keyISBN)) && (keyCategory.equals("Please") || b.getCategory().startsWith(keyCategory)) && 
		            		(keyCopy == 999 || b.getCopies().equals(keyCopy)) && (keyYear == 10000 || b.getPublicationYear().equals(keyYear)))
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

	   public static void createBook(String title, String author, String isbn13, String category, Integer copies, String description, Integer publicationyear) {
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      try {
	         tx = session.beginTransaction();
	         session.save(new Book(title, author, isbn13, category, copies, description, publicationyear));
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	   }
	   public static boolean checkCopies(String isbn) {
		   Session session = getSessionFactory().openSession();
		      Transaction tx = null;

		      try {
		         tx = session.beginTransaction();
		         List<?> bks = session.createQuery("FROM Book").list();
		         for (Iterator<?> iterator = bks.iterator(); iterator.hasNext();) 
		         {
		        	 Book b = (Book) iterator.next();
		        	 if (b.getISBN13().equals(isbn) && b.getCopies() > 0)
		        	 {
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
	   public static void updateCopies(String isbn, int updateCopiesNumber ) {
		   Session session = getSessionFactory().openSession();
		      Transaction tx = null;

		      try {
		         tx = session.beginTransaction();
		         List<?> bks = session.createQuery("FROM Book").list();
		         for (Iterator<?> iterator = bks.iterator(); iterator.hasNext();) 
		         {
		        	 Book b = (Book) iterator.next();
		        	 if (b.getISBN13().equals(isbn) && b.getCopies() >= 0)
		        	 {
		        		 b.setCopies(b.getCopies() + updateCopiesNumber);
		        		 session.update(b);
		        	 }
		         }
		      } catch (HibernateException e) {
		         if (tx != null)
		            tx.rollback();
		         e.printStackTrace();
		      } finally {
		    	 tx.commit();
		         session.close();
		      }
	   }
}
