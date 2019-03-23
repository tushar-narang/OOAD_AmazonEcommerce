package ooad.amazon.com.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ooad.amazon.com.bean.Address;
import ooad.amazon.com.bean.Bank;
import ooad.amazon.com.bean.CartItem;
import ooad.amazon.com.bean.Customer;
import ooad.amazon.com.bean.Product;
import ooad.amazon.com.bean.User;
import ooad.amazon.com.resources.CommonSessionFactory;

public class CustomerDAO {
	
	public static int registercustomer (Customer cust, Bank bankacc, Address addr) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		Query query = ses.createQuery("from User where emailid = "+"'"+cust.getEmailid()+"'");
		List<User> lusers  = (List<User>) query.list(); 
		
		
		//Customer cu = getcustomerbyemailid(cust.getEmailid());
		if(lusers.size()==0) {
			ses.save(addr);
			ses.save(bankacc);
			ses.save(cust);
			ses.getTransaction().commit();
			ses.close();
		return 1;
		}
		else {
			return 0;
		}
	} 
	
	public static Customer getcustomerbyemailid (String emailid) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		System.out.println("select * from customer where id in (select id from user where emailid = "+"'"+emailid+"' )");
		List<User> lus = ses.createNativeQuery("select id from user where emailid = "+"'"+emailid+"'",User.class).list();
		int id = lus.get(0).getId();
		List<Customer> lcust  = (List<Customer>) ses.createNativeQuery("select * from customer where id = "+id,Customer.class).list();
		ses.close();
		if(lcust.size() > 0)
		return (Customer)lcust.get(0);
		else
			return null;
	} 
	
	
	
public static Customer getcustomerbyid (int customerid) {
	Session ses = CommonSessionFactory.sf.openSession();
	ses.beginTransaction();
	Customer customer = (Customer)ses.load(Customer.class, customerid);
	Hibernate.initialize(customer.getCartlist());
	ses.getTransaction().commit();
	ses.close();
	return customer;
	} 
	
	
public static List<Address> getaddressofuser (int userid) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		Query query = ses.createQuery("from Address where addr_id = "+"'"+userid+"'");
		List<Address> addrList  = (List<Address>) query.list(); 
		
		System.out.println("---------------"+query.toString());
		ses.close();
		
			return addrList;
	} 
	

public static int addprodtocustomercart (Customer cust, CartItem cartitem ) {
	Session ses = CommonSessionFactory.sf.openSession();
	
	ses.beginTransaction();
	System.out.println("wassssuppppp kklklkl ");
	
	List<CartItem> cartitemlist =cust.getCartlist();
 	//cartitemlist = cust.getCartlist();
    
 	
    cartitemlist.add(cartitem);
 	
    cust.setCartlist(cartitemlist);
	
	ses.update(cust);
	System.out.println("wassssuppppp 22 ");
	ses.getTransaction().commit();
	System.out.println("wassssuppppp 33 ");
	ses.close();
	return 1;
}





	/*public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session ses = sf.openSession();
		ses.beginTransaction();

		
		  Customer fs = new Customer(); fs.setFname("Bhushan");
		  fs.setLname("Singh"); fs.setDob(Calendar.getInstance().getTime());
		  fs.setContact_no("9025412360"); fs.setPassword("9025412360");
		  fs.setEmailid("pavesh@gmail.com"); fs.setIs_customer(1); ses.save(fs);
		  
		 
//		Seller fs = new Seller("Sumeet Electronics", "12356166", "4464664", "9884669961", 1.4f, 7);
//		fs.setPassword("9025412362");
//		fs.setEmailid("bhavesh@gmail.com");
//		fs.setIs_customer(2);
//		ses.save(fs);

		Address addr = new Address();
		addr.setAddressline1("250C");
		addr.setAddressline2("infy 2");
		addr.setCity("jalgon");
		addr.setPincode(450001);
		addr.setStreet("Mahesh nagar");
		List<Address> lk = fs.getAddrlist();
		lk.add(addr);
		fs.setAddrlist(lk);

		Card card = new Card("125423336644", true, 321, 2, 2022, "Tushar Jain");
		List<Card> cardli = fs.getCardlist();
		cardli.add(card);
		fs.setCardlist(cardli);

//		Product prod1 = new Product("Lakme", 20, 15, 10, "a fairness cream", 4.5f);
//		Product prod2 = new Product("Garnier", 30, 14, 20, "a cream", 4.2f);
//		Product prod3 = new Product("Loreal", 40, 17, 10, "not a cream", 4.3f);
//
//		Category cat = new Category("Skin Care", 1);
//		Category cat1 = new Category("Garnier", 2);
//		Category cat2 = new Category("Hair and Others", 1);
//
//		List<Category> catl = prod1.getCategorylist();
//		catl.add(cat);
//		prod1.setCategorylist(catl);
//
//		List<Category> catl1 = prod2.getCategorylist();
//		catl1.add(cat);
//		catl1.add(cat1);
//		prod2.setCategorylist(catl1);
//
//		List<Category> catl2 = prod3.getCategorylist();
//		catl2.add(cat2);
//		prod3.setCategorylist(catl2);
//
//		ses.save(prod1);
//		ses.save(prod2);
//		ses.save(prod3);
//
//		List<Product> lsp = fs.getProductlist();
//		lsp.add(prod3);
//		lsp.add(prod2);
//		lsp.add(prod1);
//
//		fs.setProductlist(lsp);

		// ses.save(addr);

	Order or = new Order(Calendar.getInstance().getTime(), 0, "Pending");
	List<OrderedItem> orditlist = or.getOrdereditemlist();
	
	OrderedItem item1 = new OrderedItem(2, 1, 2000, 1500);
	OrderedItem item2 = new OrderedItem(3, 1, 1000, 500);
	
	orditlist.add(item1);
	orditlist.add(item2);
	
	for (OrderedItem o : orditlist) {
		or.setTotalamount(or.getTotalamount()+o.getQuantity()*o.getUnitdiscountedamount());
	}
	
	ses.save(or);
    List<Order> orderlist = fs.getOrderlist();
    orderlist.add(or);
	fs.setOrderlist(orderlist);
	Product p1 = new Product("Jovan Musk", 40, 17, 10, "Deodorant...Attracts woodpecker", 4.3f);
	Product p2 = new Product("Parachute Jasmine", 40, 17, 10, "Hair oil... Surakshit kaale mere baal...Lal Lal rehte mere gaal", 4.3f);
	ses.save(p1);
	ses.save(p2);
	CartItem prod3 = new CartItem(3,p1);
	CartItem prod4  = new CartItem(7,p2);
    
	List<CartItem> njk = fs.getCartlist();
	njk.add(prod3);
    njk.add(prod4);
    fs.setCartlist(njk);

		ses.save(fs);
		
		List<Product> wishList = new ArrayList<Product>();
		//wishList.add(p1);
		wishList.add(p2);
		fs.setWishlist(wishList);
		ses.save(fs);
		
		ses.getTransaction().commit();
		ses.close();

	}*/

}
