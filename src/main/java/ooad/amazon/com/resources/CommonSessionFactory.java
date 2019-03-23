package ooad.amazon.com.resources;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class CommonSessionFactory {
	//public static Configuration config = new Configuration().configure();
	//public static ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
	//public static SessionFactory sf = config.buildSessionFactory(servReg);
	
	public static SessionFactory sf = new Configuration().configure().buildSessionFactory();	
}
