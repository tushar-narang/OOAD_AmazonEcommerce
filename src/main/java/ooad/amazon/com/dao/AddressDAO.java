package ooad.amazon.com.dao;

import java.util.List;

import org.hibernate.Session;

import ooad.amazon.com.bean.Address;
import ooad.amazon.com.bean.Bank;
import ooad.amazon.com.bean.User;
import ooad.amazon.com.resources.CommonSessionFactory;

public class AddressDAO {

	public static int addAddress(int userid, Address addr) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		
		User user = (User)ses.load(User.class, userid);
		if(user != null) {
			ses.save(addr);
			List<Address> addrList = user.getAddrlist();
			addrList.add(addr);
			user.setAddrlist(addrList);
			
			ses.getTransaction().commit();
			ses.close();
			return 1;
		}else {
			ses.getTransaction().commit();
			ses.close();
			return 0;
		}
	}
}
