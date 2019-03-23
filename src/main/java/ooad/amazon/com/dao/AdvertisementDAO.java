package ooad.amazon.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import ooad.amazon.com.bean.Advertisment;
import ooad.amazon.com.resources.CommonSessionFactory;

public class AdvertisementDAO {

	
	public static List<Advertisment> getAllAdvertisments() {
		Session ses = CommonSessionFactory.sf.openSession();
		List<Advertisment> adlist = ses.createNativeQuery("select * from Advertisment where is_active='Y'",Advertisment.class).list();
		ses.close();
		return adlist;
		
	}
	
	public static int addAdvertisment(Advertisment ad) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		Integer id = (Integer) ses.save(ad);
		ses.getTransaction().commit();
		ses.close();
		return id;
	}
	
	public static int deleteAdvertisment (int ad) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		List<Advertisment> adlist = ses.createNativeQuery("select * from Advertisment where id = "+ ad ,Advertisment.class).list();
		ses.delete(adlist.get(0));
		ses.getTransaction().commit();
		ses.close();
		return 0;
	}
	
	
}
