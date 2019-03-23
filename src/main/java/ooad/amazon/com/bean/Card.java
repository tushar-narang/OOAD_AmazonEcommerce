package ooad.amazon.com.bean;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(uniqueConstraints = {
	      @UniqueConstraint(columnNames = "cardno", name = "uniquecardno")}
	)
public class Card{
	@Id
	@GenericGenerator(name="cardgenerator" , strategy="increment")
	@GeneratedValue(generator="cardgenerator")
	private int id;
	
	private String cardno;
	private String cvv;
	private String cardholdername;
	private int balance;
	
	

	public Card() {
		super();
	}
	
	public Card(String cardno, String cvv, String cardholdername, int balance) {
		super();
		this.cardno = cardno;
		this.cvv = cvv;
		this.cardholdername = cardholdername;
		this.balance = balance;
	}


	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getCardholdername() {
		return cardholdername;
	}
	public void setCardholdername(String cardholdername) {
		this.cardholdername = cardholdername;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", cardno=" + cardno + ", cvv=" + cvv + ", cardholdername=" + cardholdername
				+ ", balance=" + balance + "]";
	}


	
	
	

}
