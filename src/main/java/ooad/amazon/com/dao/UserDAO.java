package ooad.amazon.com.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import ooad.amazon.com.bean.Bank;
import ooad.amazon.com.bean.Card;
import ooad.amazon.com.bean.CartItem;
import ooad.amazon.com.bean.Customer;
import ooad.amazon.com.bean.Order;
import ooad.amazon.com.bean.Product;
import ooad.amazon.com.bean.Seller;
import ooad.amazon.com.bean.User;
import ooad.amazon.com.resources.CommonSessionFactory;

public class UserDAO {

	
	public static List<Customer> CheckLogin(String username, String password) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		try {
			System.out.println("select * from User u join fetch u.addrlist where emailid = "+"'"+username+"'"+" and password = "+"'"+password+"'");
			Query query = ses.createQuery("from User where emailid = "+"'"+username+"'"+" and password = "+"'"+password+"'");
			List<User> lusers  = (List<User>) query.list(); 
			//List<User> lusers  = ses.createNativeQuery("select * from User where emailid = "+"'"+username+"'"+" and password = "+"'"+password+"'").list();
			System.out.println("lusers to string is  :"+lusers.toString());
			
			int k = lusers.size();
			System.out.println("size of list lusers is :"+lusers.size());
			if(k >= 1) {
				System.out.println("returning "+lusers.get(0).getId());
				Query query1 = ses.createQuery("from Customer where id = "+lusers.get(0).getId());
				List<Customer> lcustomers  = (List<Customer>) query1.list(); 
				
				for (Customer c : lcustomers) {
					Hibernate.initialize(c.getAddrlist());
					Hibernate.initialize(c.getCardlist());
					Hibernate.initialize(c.getCartlist());
					Hibernate.initialize(c.getWishlist());
					Hibernate.initialize(c.getOrderlist());
					Hibernate.initialize(c.getProductlist());
					for(Product p : c.getProductlist()) {
						Hibernate.initialize(p.getProduct_images());
						Hibernate.initialize(p.getProduct_reviews());
						Hibernate.initialize(p.getLabels());

						//Hibernate.initialize(p.getCategory());
					}
					for(CartItem item:c.getCartlist()) {
						Hibernate.initialize(item.getProduct().getProduct_images());
						Hibernate.initialize(item.getProduct().getProduct_reviews());
						Hibernate.initialize(item.getProduct().getLabels());
					}
					for(Product item:c.getWishlist()) {
						Hibernate.initialize(item.getProduct_images());
						Hibernate.initialize(item.getProduct_reviews());
						Hibernate.initialize(item.getLabels());
					}
					
					for(Order o: c.getOrderlist()) {
						Hibernate.initialize(o.getOrdereditemlist());
					}
				}
				
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
	
	
	public static boolean isbirthday(int id) {
		
		try {
			
			Session ses = CommonSessionFactory.sf.openSession();
			//System.out.println("select * from customer where emailid = "+"'"+username+"'"+" and password = "+"'"+password+"'");
			List<Customer> lcust  = ses.createNativeQuery("select * from customer where id = "+id).list();
			ses.close();
			//System.out.println("size of list lusers is :"+lusers.size());
		//	System.out.println("lcust date is "+Calendar.lcust.get(0).getDob());
			if(lcust.size() == 1) {
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
public static String addsellerdetails(Seller seller, String cardno, String cvv) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		User selleruser = (User)ses.load(User.class, seller.getUserid());
		
		List<Card> cardsseller  = selleruser.getCardlist();
		Card sellercard = null;
		boolean cardfound = false;
		for(Card c: cardsseller) {
			if(c.getCardno().equals(cardno)) {
				sellercard = c;
				cardfound = true;
				break;
			}		
		}
				
		if(cardfound == false) {
			ses.getTransaction().commit();
			ses.close();
			return "Card not found";
		}
		if(!sellercard.getCvv().equals(cvv)) {
			ses.getTransaction().commit();
			ses.close();
			return "Invalid cvv";
		}
		
		seller.setCardid(sellercard.getId());
		ses.save(seller);
		ses.getTransaction().commit();
		ses.close();
		return "Success";
	}
	
	public static Seller checkifDetailsExist(int userid) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		
		Query query = ses.createQuery("from Seller where userid = " + userid);
		List<Seller> lusers  = (List<Seller>) query.list(); 
		if(lusers.size() > 0)
			return lusers.get(0);
		else
			return null;
	}
	
	public static int getAmountOwed(int userid) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		
		User user = (User)ses.load(User.class, userid);
		System.out.println("&&&&&&&&&" + user.toString());
		Hibernate.initialize(user.getBank());
		
		Query query = ses.createQuery("from Bank where id = " + user.getBank().getId());
		List<Bank> bankdetails  = (List<Bank>) query.list(); 
		if(bankdetails.size() > 0)
			return bankdetails.get(0).getAmzamount();
		else
			return 0;
	}
	
	

	public static List<CartItem> getUserCart(int userid) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		
		Customer user = (Customer)ses.load(Customer.class, userid);
		System.out.println("&&&&&&&&&" + user.toString());
		Hibernate.initialize(user.getCartlist());
	
		return user.getCartlist();
		
	}
	
	public static List<Product> getUserWishlist(int userid) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		
		Customer user = (Customer)ses.load(Customer.class, userid);
		System.out.println("&&&&&&&&&" + user.toString());
		Hibernate.initialize(user.getCartlist());
	
		return user.getWishlist();
		
	}
	
	public static int emptyUserCart(int userid) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		
		Customer user = (Customer)ses.load(Customer.class, userid);
		System.out.println("&&&&&&&&&" + user.toString());
		Hibernate.initialize(user.getCartlist());
		List <CartItem> njlist = new ArrayList<CartItem> ();
		System.out.println("update user after this point :");
		
		user.setCartlist(njlist);
		
		ses.getTransaction().commit();
		ses.close();
		
		return 1;
		
	}
	
	public static int removeCartItem(int userid, int productid) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		//Get All Items From Cart.
		List<CartItem> userCart = getUserCart(userid);
		List<CartItem> updatedCart = new ArrayList<CartItem> ();
		
		//Remove An Item With That Product Id
		for(CartItem item : userCart) {
			if(item.getProduct().getId() != productid) {
				updatedCart.add(item);
			}
		}
		
		//Re-Update The Cart?
		
		//Load The Custoemr Informration
		Customer user = (Customer)ses.load(Customer.class, userid);
		
		//Load The CartList For that given user
		Hibernate.initialize(user.getCartlist());
		
		user.setCartlist(updatedCart);
		ses.getTransaction().commit();
		ses.close();
		
		return 1;
		
	}
	
	
	
	
	
	
	
}
