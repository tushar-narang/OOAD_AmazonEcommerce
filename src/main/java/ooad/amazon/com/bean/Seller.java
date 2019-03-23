package ooad.amazon.com.bean;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class Seller {
	@Id
	@GenericGenerator(name="sellergenerator" , strategy="increment")
	@GeneratedValue(generator="sellergenerator")
	private int id;
	private int userid;
	private String companyname;
	private String gstin;
	private String pan;
	private String contact_no;
	private float rating;
	private int count_ratings;
	private int cardid;
	private String address;
	
	
	public int getCount_ratings() {
		return count_ratings;
	}
	public void setCount_ratings(int count_ratings) {
		this.count_ratings = count_ratings;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	@OneToMany
	@JoinColumn(name="seller_id")
    private List<Product> productlist = new ArrayList<Product>();
	
	public Seller() {
		super();
	}
	
	public Seller(String companyname, String gstin, String pan, String address, String contact_no, float rating, int count_ratings) {
		super();
		this.companyname = companyname;
		this.gstin = gstin;
		this.pan = pan;
		this.address = address;
		this.contact_no = contact_no;
		this.rating = rating;
		this.count_ratings = count_ratings;
	}
	public List<Product> getProductlist() {
		return productlist;
	}
	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getCardid() {
		return cardid;
	}
	public void setCardid(int cardid) {
		this.cardid = cardid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	
	
	
}
