package ooad.amazon.com.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity

public class Customer extends User {

	private String fname;
	private String lname;
	private Date dob;
	private String contact_no;
	
	@ElementCollection
    @GenericGenerator(name="cartGenerator",strategy="sequence")
    @CollectionId(
        columns=@Column(name="cartid"),
        type=@Type(type="long"),
        generator="cartGenerator"
    )
	private List<CartItem> cartlist =   new ArrayList<CartItem>();
	@OneToMany
	@JoinTable(name="wishlist", joinColumns=@JoinColumn(name="custId"))
	private List<Product> wishlist  = new ArrayList<Product>();
	
	
	public List<CartItem> getCartlist() {
		return cartlist;
	}
	public void setCartlist(List<CartItem> cartlist) {
		this.cartlist = cartlist;
	}
	public List<Product> getWishlist() {
		return wishlist;
	}
	public void setWishlist(List<Product> wishlist) {
		this.wishlist = wishlist;
	}
	@OneToMany
	@JoinColumn(name="customer_id")
    private List<Order> orderlist = new ArrayList<Order>();

	
	public List<Order> getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(List<Order> orderlist) {
		this.orderlist = orderlist;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}

	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	

}
