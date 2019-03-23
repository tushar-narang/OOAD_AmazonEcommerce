package ooad.amazon.com.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import ooad.amazon.com.bean.Admin;
import ooad.amazon.com.bean.Advertisment;
import ooad.amazon.com.bean.Card;
import ooad.amazon.com.bean.Customer;
import ooad.amazon.com.bean.Order;
import ooad.amazon.com.bean.Product;
import ooad.amazon.com.bean.User;
import ooad.amazon.com.resources.CommonSessionFactory;

public class AdminDAO {

	
	public static List<Admin> CheckLogin(String username, String password) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		try {
			System.out.println("select * from Admin where aName = "+"'"+username+"'"+" and password = "+"'"+password+"'");
			Query query = ses.createQuery("from Admin where aName = "+"'"+username+"'"+" and password = "+"'"+password+"'");
			List<Admin> lusers  = (List<Admin>) query.list(); 
			//List<User> lusers  = ses.createNativeQuery("select * from User where emailid = "+"'"+username+"'"+" and password = "+"'"+password+"'").list();
			System.out.println("lusers to string is  :"+lusers.toString());
			
			int k = lusers.size();
			System.out.println("size of list lusers is :"+lusers.size());
			if(k >= 1) {
				System.out.println("returning "+lusers.get(0).getaName());
				Query query1 = ses.createQuery("from Admin where aName = '"+lusers.get(0).getaName() + "'");
				List<Admin> lcustomers  = (List<Admin>) query1.list(); 
				
				
				System.out.println(lcustomers.get(0).toString());
				return lcustomers;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			ses.getTransaction().commit();
			ses.close();
		}
		return null;
	}
	
public static int registercustomer (Admin adm) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		ses.save(adm);
		ses.getTransaction().commit();
		ses.close();
		
		return 1;
	} 
	

public static List<Customer> displayuser() {
	Session ses = CommonSessionFactory.sf.openSession();
	ses.beginTransaction();
	try {
		Query query = ses.createQuery("from Customer");
		List<Customer> usr= (List<Customer>) query.list();
		for(Customer u :usr) {
			List<Product> prods = new ArrayList<>();
			List<Order> ord = new ArrayList<>();
			Hibernate.initialize(u.getProductlist());
			Hibernate.initialize(u.getOrderlist());
			Hibernate.initialize(u.getAddrlist());
			Hibernate.initialize(u.getCardlist());
			Hibernate.initialize(u.getCartlist());
			Hibernate.initialize(u.getWishlist());
			
			prods = u.getProductlist();
			for(Product p: prods)
				{Hibernate.initialize(p.getProduct_images());
				Hibernate.initialize(p.getProduct_reviews());
				Hibernate.initialize(p.getLabels());
				}
				ord=u.getOrderlist();
				for(Order o: ord)
					{Hibernate.initialize(o.getOrdereditemlist());
					//Hibernate.initialize(p.getProduct_reviews());
					}
		
		}
		return usr;
	}
	catch(Exception e) {
		
	}
	finally {
		ses.getTransaction().commit();
		ses.close();
	}
	return null;
}

public static List<Admin> displayadmin() {
	Session ses = CommonSessionFactory.sf.openSession();
	ses.beginTransaction();
	try {
		Query query = ses.createQuery("from Admin");
		List<Admin> usr= (List<Admin>) query.list();
		for(Admin u :usr) {
			/*Hibernate.initialize(u.getProductlist());
			Hibernate.initialize(u.getOrderlist());
			Hibernate.initialize(u.getAddrlist());
			Hibernate.initialize(u.getCardlist());
			Hibernate.initialize(u.getCartlist());
			Hibernate.initialize(u.getWishlist());*/
		}
		return usr;
	}
	catch(Exception e) {
		
	}
	finally {
		ses.getTransaction().commit();
		ses.close();
	}
	return null;
}


public static List<Advertisment> displayadvert() {
	Session ses = CommonSessionFactory.sf.openSession();
	ses.beginTransaction();
	try {
		Query query = ses.createQuery("from Advertisment");
		List<Advertisment> usr= (List<Advertisment>) query.list();
		for(Advertisment u :usr) {
			/*Hibernate.initialize(u.getProductlist());
			Hibernate.initialize(u.getOrderlist());
			Hibernate.initialize(u.getAddrlist());
			Hibernate.initialize(u.getCardlist());
			Hibernate.initialize(u.getCartlist());
			Hibernate.initialize(u.getWishlist());*/
		}
		return usr;
	}
	catch(Exception e) {
		
	}
	finally {
		ses.getTransaction().commit();
		ses.close();
	}
	return null;
}


public static List<Customer> displayseller() {
	Session ses = CommonSessionFactory.sf.openSession();
	ses.beginTransaction();
	try {
		Query query = ses.createQuery("from Customer");
		List<Customer> usr= (List<Customer>) query.list();
		//Product p = new Product();
		for(Customer u :usr) {
			List<Product> prods = new ArrayList<>();
			List<Order> ord = new ArrayList<>();
			Hibernate.initialize(u.getProductlist());
			Hibernate.initialize(u.getOrderlist());
			Hibernate.initialize(u.getAddrlist());
			Hibernate.initialize(u.getCardlist());
			//Hibernate.initialize(p.getProduct_images());
			Hibernate.initialize(u.getCartlist());
			Hibernate.initialize(u.getWishlist());
			prods = u.getProductlist();
			for(Product p: prods)
				{Hibernate.initialize(p.getProduct_images());
				Hibernate.initialize(p.getProduct_reviews());
				Hibernate.initialize(p.getLabels());
				}
			
			ord=u.getOrderlist();
			for(Order o: ord)
				{Hibernate.initialize(o.getOrdereditemlist());
				//Hibernate.initialize(p.getProduct_reviews());
				}

		}
		return usr;
	}
	catch(Exception e) {
		
	}
	finally {
		ses.getTransaction().commit();
		ses.close();
	}
	return null;
}

public static List<Customer> displayprodseller(int id) {
	Session ses = CommonSessionFactory.sf.openSession();
	ses.beginTransaction();
	try {
		Query query = ses.createQuery("from Customer where id="+id+"");
		List<Customer> usr= (List<Customer>) query.list();
		//Product p = new Product();
		for(Customer u :usr) {
			List<Product> prods = new ArrayList<>();
			Hibernate.initialize(u.getProductlist());
			Hibernate.initialize(u.getOrderlist());
			Hibernate.initialize(u.getAddrlist());
			Hibernate.initialize(u.getCardlist());
			//Hibernate.initialize(p.getProduct_images());
			Hibernate.initialize(u.getCartlist());
			Hibernate.initialize(u.getWishlist());
			prods = u.getProductlist();
			for(Product p: prods)
				{Hibernate.initialize(p.getProduct_images());
				Hibernate.initialize(p.getProduct_reviews());
				}
		}
		return usr;
	}
	catch(Exception e) {
		
	}
	finally {
		ses.getTransaction().commit();
		ses.close();
	}
	return null;
}


public static List<Card> bankdetails(int id) {
	Session ses = CommonSessionFactory.sf.openSession();
	ses.beginTransaction();
	try {
		Query query = ses.createQuery("from Card where id="+id+"");
		List<Card> usr= (List<Card>) query.list();
		for(Card u :usr) {
			/*Hibernate.initialize(u.getProductlist());
			Hibernate.initialize(u.getOrderlist());
			Hibernate.initialize(u.getAddrlist());
			Hibernate.initialize(u.getCardlist());
			Hibernate.initialize(u.getCartlist());
			Hibernate.initialize(u.getWishlist());*/
		}
		return usr;
	}
	catch(Exception e) {
		
	}
	finally {
		ses.getTransaction().commit();
		ses.close();
	}
	return null;
}

	
	
}
