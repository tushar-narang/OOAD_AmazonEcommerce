package ooad.amazon.com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import ooad.amazon.com.bean.Bank;
import ooad.amazon.com.bean.Card;
import ooad.amazon.com.bean.Order;
import ooad.amazon.com.bean.OrderedItem;
import ooad.amazon.com.bean.Product;
import ooad.amazon.com.bean.Seller;
import ooad.amazon.com.bean.User;
import ooad.amazon.com.resources.CommonSessionFactory;

public class OrderDAO {

public static String saveOrder(int custid, int prodid, int quantity, int freequantity, String cardno,String addr, String cvv) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		
		User buyer = (User)ses.load(User.class, custid);
//		Bank buyeracc = buyer.getBank();
//		Query query = ses.createQuery("from card where cardno='" + cardno + "'");
//		List<Card> cardsbuyer  = (List<Card>) query.list(); 
		
		List<Card> cardsbuyer  = buyer.getCardlist();
		Card buyercard = null;
		boolean cardfound = false;
		for(Card c: cardsbuyer) {
			if(c.getCardno().equals(cardno)) {
				buyercard = c;
				cardfound = true;
				break;
			}		
		}
				
		if(cardfound == false) {
			ses.getTransaction().commit();
			ses.close();
			return "Card not found";
		}
		if(!buyercard.getCvv().equals(cvv)) {
			ses.getTransaction().commit();
			ses.close();
			return "Invalid cvv";
		}
		
		int sellerid;
		
			Product product = (Product)ses.load(Product.class, prodid);
			sellerid = product.getSeller();

		
		int totalAmt = 0;
		
			totalAmt = (product.getDiscountedprice() * quantity);
		
		if(totalAmt > buyercard.getBalance() ) {
			ses.getTransaction().commit();
			ses.close();
			return "Insufficient Balance";
		}
		else {
			List<OrderedItem> itemlist = new ArrayList<>();
			
				User seller = (User)ses.load(User.class, sellerid);
				Bank selleracc = seller.getBank();
				
				Date todaysDate = new Date();
				int pricetoSet = 0;
				if(product.getOfferType().equals("discount") && product.getOfferEndDate().compareTo(todaysDate) == 1) {
					System.out.println("if part");
					pricetoSet = (int) (product.getDiscountedprice() * product.getOfferdiscpercent()/100) * quantity;

				}if(product.getOfferType().equals("buy1get1") && product.getOfferEndDate().compareTo(todaysDate) == 1) {
					System.out.println("if part");
					pricetoSet = product.getDiscountedprice() * quantity;
					freequantity = quantity;
				}
				else {
					System.out.println("else part");
					pricetoSet = product.getDiscountedprice() * quantity;
				}
				
			
				System.out.println(buyercard.toString() + " " + selleracc.toString() + " "  + product.toString());
		
				
					selleracc.setAmzamount(selleracc.getAmzamount() + pricetoSet);
					
					System.out.println("current balance : " +buyercard.getBalance());
					System.out.println("subtraction amount is : "+pricetoSet);
					
					buyercard.setBalance(buyercard.getBalance() - pricetoSet);
					
					System.out.println("final balance is: " + buyercard.getBalance());
					
					OrderedItem item = new OrderedItem(product.getId(),quantity, freequantity, product.getPrice(), pricetoSet/quantity, "PENDING");
					itemlist.add(item);
					
					
			
			Order order = new Order(new Date(), totalAmt );
			order.setBuyerid(custid);
			order.setCardusedid(buyercard.getId());
			order.setOrdereditemlist(itemlist);
			order.setDeliveryAddress(addr);
			ses.save(order);
			
			List<Order> orderlist = buyer.getOrderlist();
			orderlist.add(order);
			buyer.setOrderlist(orderlist);
		
			ses.getTransaction().commit();
			ses.close();
			return "Success";
		}
			
		
		
	}
	
	public static String confirmOrder(int sellerid, int orderid) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		
		//User seller = (User)ses.load(User.class, sellerid);
		
		Order order = (Order)ses.load(Order.class, orderid);
		List<OrderedItem> items = order.getOrdereditemlist();
		
		//System.out.println("transfer from" + order.getBuyerid());
		//int buyer = order.get
	
		for(OrderedItem item: items) {
			System.out.println(item.toString());
			System.out.println("amount: " + item.getUnitdiscountedamount());
			
			int prodid = item.getProductid();
			Product product = (Product)ses.load(Product.class, prodid);
			product.setQuantityleft(product.getQuantityleft()-(item.getQuantity() + item.getFreequantity()));
			item.setStatus("SHIPPED");
		}
	//	System.out.println("to:" + sellerid);
		
		
//		Bank selleracc = seller.getBank();
//		selleracc.setAmzamount(selleracc.getAmzamount() - (amt*quan) );
//		//selleracc.setAmount(selleracc.getAmount() + (amt*quan) );
//		sellercard.setBalance(sellercard.getBalance() + (amt*quan));
		
		ses.getTransaction().commit();
		ses.close();
		
		return "Success";
	}
	
	public static String setDelivered(int orderid) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		
		//User seller = (User)ses.load(User.class, sellerid);
		
		Order order = (Order)ses.load(Order.class, orderid);
		List<OrderedItem> items = order.getOrdereditemlist();
		
		//System.out.println("transfer from" + order.getBuyerid());
		//int buyer = order.get
	
		for(OrderedItem item: items) {
			System.out.println(item.toString());
			System.out.println("amount: " + item.getUnitdiscountedamount());
			item.setStatus("DELIVERED");
			
			Product p = ses.get(Product.class, item.getProductid());
			System.out.println(p.toString());
			
//			User s = ses.get(User.class, p.getSeller());
//			System.out.println(s.toString());
			
			Query query = ses.createQuery("from Seller where userid=" + p.getSeller());
			List<Seller> sellers  = (List<Seller>) query.list(); 
			
			
			
			Card card = ses.load(Card.class, sellers.get(0).getCardid());
			System.out.println(card.toString());
			
			card.setBalance(card.getBalance() + (item.getUnitdiscountedamount()*item.getQuantity()));
			
			User seller = (User)ses.load(User.class, p.getSeller());
			Bank selleracc = seller.getBank();
			selleracc.setAmzamount(selleracc.getAmzamount() - (item.getUnitdiscountedamount()*item.getQuantity()) );
		}
	//	System.out.println("to:" + sellerid);
		
		
//		Bank selleracc = seller.getBank();
//		selleracc.setAmzamount(selleracc.getAmzamount() - (amt*quan) );
//		//selleracc.setAmount(selleracc.getAmount() + (amt*quan) );
//		sellercard.setBalance(sellercard.getBalance() + (amt*quan));
		
		ses.getTransaction().commit();
		ses.close();
		
		return "Success";
	}
	
	
	public static List<Order> getOrdersofSeller(int sellerid) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		List<Order> returnitems = new ArrayList<>();
		
		try {
			//System.out.println("select * from customerorder where emailid = "+"'"+username+"'"+" and password = "+"'"+password+"'");
			Query query = ses.createQuery("from customerorder");
			List<Order> allorders  = (List<Order>) query.list(); 
			for(Order order: allorders) {
				Hibernate.initialize(order.getOrdereditemlist());	
			}
			
			for(Order order: allorders) {
				for(OrderedItem item: order.getOrdereditemlist()) {
					System.out.println("here---" + item.toString());
					Product prod = (Product)ses.load(Product.class, item.getProductid());
					
					System.out.println("prod--" + prod.toString());
					if(prod.getSeller() == sellerid) {
						returnitems.add(order);
					}
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		
		ses.getTransaction().commit();
		ses.close();
		
		return returnitems;
	}

	
	public static List<Order> getOrdersofCustomer(int customerid) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		List<Order> allorders =  new ArrayList<Order>();
		System.out.println("Here");
		try {
			//System.out.println("select * from customerorder where emailid = "+"'"+username+"'"+" and password = "+"'"+password+"'");
			//Query query = ses.createSQLQuery("select * from customerorder_ordereditemlist where customerorder_orderid in (select orderid from customerorder where buyerid = "+customerid+" )");
			Query query = ses.createQuery("from customerorder where customer_id = "+customerid );
			allorders  = (List<Order>) query.list();
			for(Order order: allorders) {
				Hibernate.initialize(order.getOrdereditemlist());	
			}
			
			//System.out.append(allorderitems.toString());
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		ses.getTransaction().commit();
		ses.close();
		
		return allorders;
	}
	
	public static void rejectOrder(int orderid) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		Order order = (Order)ses.load(Order.class, orderid);
		List<OrderedItem> items = order.getOrdereditemlist();
		
		Query query  = ses.createQuery("from Card where id=" + order.getCardusedid()); 
		List<Card> cards = (List<Card>) query.list();
		Card buyercard = cards.get(0);
		
		for(OrderedItem item: items) {
			Product p = ses.get(Product.class, item.getProductid());
			System.out.println(p.toString());
			
			Query query2 = ses.createQuery("from User where id=" + p.getSeller());
			List<User> sellers  = (List<User>) query2.list(); 
			Bank sellerBank = sellers.get(0).getBank();
			

			System.out.println("from seller bank: " + sellerBank.toString() + " to usercard:" + buyercard.toString());
			
		}
		
	}
	
	public static void main(String[] args) {
		confirmOrder(10, 13);
	}
	
	
}