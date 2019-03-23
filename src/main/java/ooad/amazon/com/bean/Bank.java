package ooad.amazon.com.bean;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int amount;
	
	private int amzamount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAmzamount() {
		return amzamount;
	}

	public void setAmzamount(int amzamount) {
		this.amzamount = amzamount;
	}

	@Override
	public String toString() {
		return "Bank [id=" + id + ", amount=" + amount + ", amzamount=" + amzamount + "]";
	}
	
	
	
}