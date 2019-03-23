package ooad.amazon.com.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import ooad.amazon.com.bean.Bank;
import ooad.amazon.com.bean.User;
import ooad.amazon.com.resources.CommonSessionFactory;

public class BankDAO {

	public static void addMoney(int userid, int amount) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		
		User buyer = (User)ses.load(User.class, userid);
		Bank buyeracc = buyer.getBank();
		buyeracc.setAmount(buyeracc.getAmount() + amount);
		
		ses.getTransaction().commit();
		ses.close();
	}
	
	public static Integer getamazonbalance() {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		SQLQuery query = ses.createSQLQuery("select sum(amzamount) from bank");
		List<Object[]> nlist = query.list();
		System.out.println("call sucessfull : "+nlist);
		ses.getTransaction().commit();
		ses.close();
		return Integer.parseInt(""+nlist.get(0));
	}
	
	
	
}