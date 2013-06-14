package here.wait.photo.share.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppliationContextUtility
{
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private AppliationContextUtility() {}
	
	public static Object getBean(String name)
	{
		return ac.getBean(name);
	}
}
