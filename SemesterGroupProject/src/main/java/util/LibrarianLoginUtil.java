package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.LibrarianTable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class LibrarianLoginUtil
{
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
	   
	public static Boolean LibrarianLogin(String keyBranch, String keyPin)
	{
		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try
		{
			tx = session.beginTransaction();
			List<?> LL = session.createQuery("FROM LibrarianTable").list();
			
			for (Iterator<?> iterator = LL.iterator(); iterator.hasNext();)
			{
				LibrarianTable L = (LibrarianTable) iterator.next();
				
				if (L.getbranch().equals(keyBranch) &&  L.getPin().equals(keyPin))
				{
					return true;
				}				   
			}
			
			tx.commit();
		}
		catch (HibernateException e)
		{
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
		}
		finally
		{
	         session.close();
		}
		
		return false;
	}
	
	public static void createlibrarian(String branchName, String pinName)
	{
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		
		try
		{
			tx = session.beginTransaction();
			session.save(new LibrarianTable(branchName, pinName));
			tx.commit();
		}
		catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	public static List<LibrarianTable> listLibrarian()
	{
		List<LibrarianTable> resultList = new ArrayList<LibrarianTable>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try
		{
			tx = session.beginTransaction();
		    List<?> rsvps = session.createQuery("FROM LibrarianTable").list();
		    
		    for (Iterator<?> iterator = rsvps.iterator(); iterator.hasNext();)
		    {
		    	LibrarianTable rsvpIt = (LibrarianTable) iterator.next();
		        resultList.add(rsvpIt);
		    }
		    
		    tx.commit();
		}
		catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
		return resultList;
	}
}