package ooad.amazon.com.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class Product {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	 
	 @Column(unique = true)
	 private String productid;
	 
	private String productname;
	private int price;
	private int discountedprice;
	private int quantityleft;
	private String description;
	private float starratings = 0f;
	private int totalreviews = 0;
	private int seller;
	
	
	private String offerType;
	private float offerdiscpercent;
	private String offerMessage;
	private Date offerEndDate;
	
	private int stock;
	private String color;
	private String manufacturer;
	private Date manufactured_date;
	
	private String temp1_value;
	private String temp2_value;
	private String temp3_value;
	private String temp4_value;
	private String temp5_value;
	private String temp6_value;

	
	

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public float getOfferdiscpercent() {
		return offerdiscpercent;
	}

	public void setOfferdiscpercent(float offerdiscpercent) {
		this.offerdiscpercent = offerdiscpercent;
	}

	public String getOfferMessage() {
		return offerMessage;
	}

	public void setOfferMessage(String offerMessage) {
		this.offerMessage = offerMessage;
	}

	public Date getOfferEndDate() {
		return offerEndDate;
	}

	public void setOfferEndDate(Date offerEndDate) {
		this.offerEndDate = offerEndDate;
	}


	@OneToOne
	private Category category;
	
	
	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	public int getTotalreviews() {
		return totalreviews;
	}

	public void setTotalreviews(int totalreviews) {
		this.totalreviews = totalreviews;
	}

	@OneToMany
	@JoinColumn(name="prodId")
	List<ProductImages> product_images = new ArrayList<ProductImages>();
	
	@OneToMany
	@JoinColumn(name="proId")
	List<ProdReview> product_reviews = new ArrayList<ProdReview>();
	
	@OneToMany
	@JoinColumn(name="proId")
	List<Label> labels = new ArrayList<Label>();
	
	
	public Product() {
		super();
	}

	public Product(String productname, int price, int discountedprice, int quantityleft, String description) {
		super();
		this.productname = productname;
		this.price = price;
		this.discountedprice = discountedprice;
		this.quantityleft = quantityleft;
		this.description = description;
			}

	
	public List<ProductImages> getProduct_images() {
		return product_images;
	}

	public void setProduct_images(List<ProductImages> product_images) {
		this.product_images = product_images;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscountedprice() {
		return discountedprice;
	}

	public void setDiscountedprice(int discountedprice) {
		this.offerdiscpercent = 0;
		this.discountedprice = discountedprice;
	}

	public int getQuantityleft() {
		return quantityleft;
	}

	public void setQuantityleft(int quantityleft) {
		this.quantityleft = quantityleft;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getStarratings() {
		return starratings;
	}

	public void setStarratings(float starratings) {
		this.starratings = starratings;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ProdReview> getProduct_reviews() {
		return product_reviews;
	}

	public void setProduct_reviews(List<ProdReview> product_reviews) {
		this.product_reviews = product_reviews;
	}

	
	@Override
	public String toString() {
		return "Product [id=" + id + ", productname=" + productname + ", price=" + price + ", discountedprice="
				+ discountedprice + ", quantityleft=" + quantityleft + ", description=" + description + ", starratings="
				+ starratings + ", totalreviews=" + totalreviews + ", seller=" + seller + ", offerType=" + offerType
				+ ", offerdiscpercent=" + offerdiscpercent + ", offerMessage=" + offerMessage + ", offerEndDate="
				+ offerEndDate + ", stock=" + stock + ", color=" + color + ", manufacturer=" + manufacturer
				+ ", manufactured_date=" + manufactured_date + ", temp1_value=" + temp1_value + ", temp2_value="
				+ temp2_value + ", temp3_value=" + temp3_value + ", temp4_value=" + temp4_value + ", temp5_value="
				+ temp5_value + ", temp6_value=" + temp6_value + ", category=" + category + ", product_images="
				+ product_images + ", product_reviews=" + product_reviews + ", labels=" + labels + "]";
	}

	public int getSeller() {
		return seller;
	}

	public void setSeller(int seller) {
		this.seller = seller;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Date getManufactured_date() {
		return manufactured_date;
	}

	public void setManufactured_date(Date manufactured_date) {
		this.manufactured_date = manufactured_date;
	}

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	public String getTemp1_value() {
		return temp1_value;
	}

	public void setTemp1_value(String temp1_value) {
		this.temp1_value = temp1_value;
	}

	public String getTemp2_value() {
		return temp2_value;
	}

	public void setTemp2_value(String temp2_value) {
		this.temp2_value = temp2_value;
	}

	public String getTemp3_value() {
		return temp3_value;
	}

	public void setTemp3_value(String temp3_value) {
		this.temp3_value = temp3_value;
	}

	public String getTemp4_value() {
		return temp4_value;
	}

	public void setTemp4_value(String temp4_value) {
		this.temp4_value = temp4_value;
	}

	public String getTemp5_value() {
		return temp5_value;
	}

	public void setTemp5_value(String temp5_value) {
		this.temp5_value = temp5_value;
	}

	public String getTemp6_value() {
		return temp6_value;
	}

	public void setTemp6_value(String temp6_value) {
		this.temp6_value = temp6_value;
	}

	

	
	
	
	
	
}
