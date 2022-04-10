package util;

import java.util.List;
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
	         List<?> bks = session.createQuery("FROM User").list();
	         for (Iterator<?> iterator = bks.iterator(); iterator.hasNext();) 
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
	
	
	
	
	
	
	
}
