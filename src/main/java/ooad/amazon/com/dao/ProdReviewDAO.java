package ooad.amazon.com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ooad.amazon.com.bean.Advertisment;
import ooad.amazon.com.bean.Category;
import ooad.amazon.com.bean.ProdReview;
import ooad.amazon.com.bean.Product;
import ooad.amazon.com.bean.ProductImages;
import ooad.amazon.com.bean.User;
import ooad.amazon.com.resources.CommonSessionFactory;

public class ProdReviewDAO {

	
	public static int addproductreview(String description, String headline, int productid, float ratings, int customerid){
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
	
		System.out.println("productid is  " +productid);
		Query query = ses.createQuery("from Product where id = "+productid);
		System.out.println("heyyyyyy " +query.toString());
		
		List<Product> lproducts  = (List<Product>) query.list(); 
		System.out.println("heyyyyyy v22222222    222 " +lproducts);
		
		
		Product p1 = lproducts.get(0);
		
		
		
		ProdReview prw = new ProdReview(); 
		prw.setDescription(description);
		prw.setHeadline(headline);
		prw.setRating(ratings);
		prw.setCustomerid(customerid);
		
		p1.setStarratings((p1.getStarratings()*p1.getTotalreviews() + ratings) / (p1.getTotalreviews() +1));
		p1.setTotalreviews(p1.getTotalreviews() +1);
		
		
		List<ProdReview> lprw = p1.getProduct_reviews();
		lprw.add(prw);
		p1.setProduct_reviews(lprw);
		
		Integer id = (Integer) ses.save(p1);
		ses.save(prw);
		
		ses.getTransaction().commit();
		ses.close();
		
		return id;
	}
	
	
	public static List<ProdReview> getReviewStatus(int customerid, int productid ){
	
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
	
		System.out.println("productid is  " +productid);
		Query query = ses.createQuery("from ProdReview where proId = "+productid +" and customerid = "+customerid);
		List<ProdReview> lprw  = (List<ProdReview>) query.list(); 

		ses.getTransaction().commit();
		ses.close();
		
		return lprw;	
		
		
	}
	
	
	public static List<ProdReview> getAllReviews(int productid ){
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
	
		System.out.println("productid is  " +productid);
		Query query = ses.createQuery("from ProdReview where proId = "+productid);
		List<ProdReview> lprw  = (List<ProdReview>) query.list(); 

		ses.getTransaction().commit();
		ses.close();
		
		return lprw;	
		
		
	}
	
	
}
