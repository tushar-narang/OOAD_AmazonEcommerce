package ooad.amazon.com.dao;

import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import ooad.amazon.com.bean.Card;
import ooad.amazon.com.bean.Customer;
import ooad.amazon.com.bean.User;
import ooad.amazon.com.resources.CommonSessionFactory;

public class CardDAO{

	public static void addCard(int userid, Card card) throws SQLIntegrityConstraintViolationException{
		Session ses = CommonSessionFactory.sf.openSession();
		
		
		User user = (User)ses.load(User.class, userid);
		List<Card> cards = user.getCardlist();
		try {
			ses.beginTransaction();
			cards.add(card);
			ses.save(card);
			ses.save(user);
		}
		finally {
			ses.getTransaction().commit();
			ses.close();
		}
		
		
	}
	
	public static List<Card> getCardsofUser(int userid) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		
		User user = (User)ses.load(User.class, userid);
		Hibernate.initialize(user.getCardlist());
		List<Card> cards = user.getCardlist();
		
		ses.getTransaction().commit();
		ses.close();
		return cards;
	}
	
	public static boolean addBalance(String cardno, int amount) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		try {
			Query query = ses.createQuery("from Card where cardno = '" + cardno + "'");
			List<Card> cards  = (List<Card>) query.list(); 
			
			
			if(cards.size() == 1) {
				cards.get(0).setBalance(cards.get(0).getBalance() + amount);
				ses.save(cards.get(0));
				return true;
			}
			else {
				return false;
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}finally {
			ses.getTransaction().commit();
			ses.close();
		}
		return false;
	}
	
	
	
	public static void main(String[] args) {
		Card c1 = new Card("123456", "005", "Tushar Narang", 500);
		
		try {
		addCard(2, c1);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(":errror");
		}
		
//		Card c2 = new Card("7891011", "006", "Tushar", 1000);
//		addCard(2, c2);
		
//		List<Card> clist = new ArrayList<>();
//		clist = getCardsofUser(2);
//		for(Card c: clist)
//			System.out.println(c.toString());
//		
//		addBalance("123456", 100);
//		
//		clist = getCardsofUser(2);
//		for(Card c: clist)
//			System.out.println(c.toString());
	}
}
