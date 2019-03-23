package ooad.amazon.com.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import ooad.amazon.com.bean.Label;
import ooad.amazon.com.bean.ProdReview;
import ooad.amazon.com.bean.Product;
import ooad.amazon.com.resources.CommonSessionFactory;

public class LabelDAO {
	
	
public static List<Label> getLabels(int productid) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		Query query = ses.createQuery("from Product where id = "+productid);
		List<Product> lproducts  = (List<Product>) query.list();
		for(Product p: lproducts)
		{Hibernate.initialize(p.getProduct_images());
		Hibernate.initialize(p.getCategory());
		Hibernate.initialize(p.getProduct_reviews());
		Hibernate.initialize(p.getLabels());
		}
		Product p1 = lproducts.get(0);
		
		List<Label> list = p1.getLabels();
		
		
		ses.getTransaction().commit();
		ses.close();
		
		return list;
		
	}


	public static void addLabel(String name,String value,int productid) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		Query query = ses.createQuery("from Product where id = "+productid);
		List<Product> lproducts  = (List<Product>) query.list(); 
		for(Product p: lproducts)
		{Hibernate.initialize(p.getProduct_images());
		Hibernate.initialize(p.getCategory());
		Hibernate.initialize(p.getProduct_reviews());
		Hibernate.initialize(p.getLabels());
		}
		Product p1 = lproducts.get(0);
		System.out.print(p1.toString());
		Label label = new Label();
		label.setLname(name);
		label.setLvalue(value);
		
		List<Label> list = p1.getLabels();
		list.add(label);
		p1.setLabels(list);
		
		ses.save(label);
		ses.save(p1);
		ses.getTransaction().commit();
		ses.close();
		
	}
	
	public static void deleteLabel(int id,int productid) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		Query query = ses.createQuery("from Product where id = "+productid);
		List<Product> lproducts  = (List<Product>) query.list(); 
		Product p1 = lproducts.get(0);
		
		query = ses.createQuery("from Label where id = "+id); //id needed from UI not name so that list of labels in product can be updated
		List<Label> labels  = (List<Label>) query.list(); 
		Label l1 = labels.get(0);
		
		//l1.setLvalue(value);
		
		List<Label> list = p1.getLabels();
		list.remove(l1);
		p1.setLabels(list);
		
		ses.delete(l1);
		ses.getTransaction().commit();
		ses.close();
		
	}
}
