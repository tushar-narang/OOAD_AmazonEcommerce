package ooad.amazon.com.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ooad.amazon.com.dao.AdvertisementDAO;

public class mc {

	public static void main(String[] args) {
		System.out.println("ssup naru");
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session ses = sf.openSession();
		ses.beginTransaction();
		
		Product prod22 = new Product("Popye papaya", 33, 15, 22, "ice cream");
		prod22.setTotalreviews(0);
		
		Category cat13 = new Category("Icecream ");
	
//		List<Category> catl111 = prod22.getCategory();
		//catl111.add(cat13);
		
		
	//	prod22.setCategorylist(catl111);

		ProdReview re = new ProdReview();
		
		re.setRating(4.6f);
		re.setDescription("ek tarfa ");
		re.setHeadline("wassup");
		
		List<ProdReview> revlist = prod22.getProduct_reviews();
		//List<Category> revlist = prod22.getCategorylist();
		
		revlist.add(re);

		ses.save(prod22);
		ses.save(re);
		
		
			
		
		//ses.save(prod22);
		


		
		
		
		
		

		/*Advertisment ad1 = new Advertisment ();
		ad1.setUrl("images/advertisments/c4.jpg");
		ad1.setCategorytoshow("Prime");
		ses.save(ad1);
		
		Advertisment ad2 = new Advertisment ();
		ad2.setUrl("images/advertisments/c2.jpg");
		ad2.setCategorytoshow("Fashion");
		ses.save(ad2);
		
		Advertisment ad3 = new Advertisment ();
		ad3.setUrl("images/advertisments/c3.jpg");
		ad3.setCategorytoshow("Mobile");
		ses.save(ad3);*/
		
		
		System.out.println("ssup naru2");
		
		
//		 Customer fs = new Customer(); fs.setFname("Bhushan");
//		  fs.setLname("Singh"); fs.setDob(Calendar.getInstance().getTime());
//		  fs.setContact_no("9025412360"); fs.setPassword("9025412360");
//		  fs.setEmailid("pavesh@gmail.com"); fs.setIs_customer(1); ses.save(fs);
//    		fs.setPassword("9025412362");
//    	fs.setEmailid("bhavesh@gmail.com");
//    	fs.setIs_customer(1);
//		ses.save(fs);

		
		
		
		
		
		
		List<Advertisment> lk = AdvertisementDAO.getAllAdvertisments();
		for (Advertisment a : lk) {
			System.out.println(a.getUrl());
		}

		System.out.println("ssup naru 3");
		
	
//		
//		  Customer fs = new Customer(); fs.setFname("Bhushan");
//		  fs.setLname("Singh"); fs.setDob(Calendar.getInstance().getTime());
//		  fs.setContact_no("9025412360"); fs.setPassword("9025412360");
//		  fs.setEmailid("pavesh@gmail.com"); fs.setIs_customer(1); ses.save(fs);
//		  
		 
		/*Seller fs = new Seller("Sumeet Electronics", "12356166", "4464664", "9884669961", 1.4f, 7);
		fs.setPassword("9025412362");
		fs.setEmailid("bhavesh@gmail.com");
		fs.setIs_customer(2);
		ses.save(fs);
*/
//		Address addr = new Address();
//		addr.setAddressline1("250C");
//		addr.setAddressline2("infy 2");
//		addr.setCity("jalgon");
//		addr.setPincode(450001);
//		addr.setStreet("Mahesh nagar");
//		List<Address> lk = fs.getAddrlist();
//		lk.add(addr);
//		fs.setAddrlist(lk);
//
//		Card card = new Card("125423336644", true, 321, 2, 2022, "Tushar Jain");
//		List<Card> cardli = fs.getCardlist();
//		cardli.add(card);
//		fs.setCardlist(cardli);
//
		Product prod1 = new Product("Lakme", 20, 15, 10, "a fairness cream");
		Product prod2 = new Product("Garnier", 30, 14, 20, "a cream");
		Product prod3 = new Product("Loreal", 40, 17, 10, "not a cream");

		Category cat = new Category("Skin Care");
		Category cat1 = new Category("Garnier");
		cat1.setSuper_cat(cat);
		Category cat2 = new Category("Hair and Others");
		Category cat3 = new Category("Sunblock");
		cat3.setSuper_cat(cat1);
		Category cat4 = new Category("SPF15");
		cat4.setSuper_cat(cat3);
		Category cat5 = new Category("SPA+++");
		cat5.setSuper_cat(cat1);
		
		//List<Category> catl = prod1.getCategorylist();
		//catl.add(cat);
		//prod1.setCategorylist(catl);

		//List<Category> catl1 = prod2.getCategorylist();
		//catl1.add(cat);
		//catl1.add(cat1);
		//prod2.setCategorylist(catl1);

		//List<Category> catl2 = prod3.getCategorylist();
		//catl2.add(cat2);
		//prod3.setCategorylist(catl2);
		
		ses.save(cat1);
		ses.save(cat2);
		ses.save(cat);
		ses.save(cat3);
		ses.save(cat4);
		ses.save(cat5);

		ses.save(prod1);
		ses.save(prod2);
		ses.save(prod3);

		/*List<Product> lsp = fs.getProductlist();
		lsp.add(prod3);
		lsp.add(prod2);
		lsp.add(prod1);

		fs.setProductlist(lsp);
*/
		// ses.save(addr);
		/*
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
		ses.getTransaction().commit();
		ses.close();
	}
}
