package here.wait.photo.share.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility 
{
	private static SessionFactory factory = null;
	static
	{
		try
		{
			Configuration cfg = new Configuration().configure();
			factory = cfg.buildSessionFactory();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static SessionFactory getSessionFactory()
	{
		return factory;
	}
	
	public static Session getSession()
	{
		return factory.openSession();
	}
	
	public static void closeSession(Session session)
	{
		if(null != session)
			if(session.isOpen())
				session.close();
	}
}
