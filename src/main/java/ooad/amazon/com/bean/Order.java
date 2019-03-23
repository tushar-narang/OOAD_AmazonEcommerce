package ooad.amazon.com.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity(name ="customerorder")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int orderid;
	public Order() {
		super();
	}
	private Date orderdate;
	private int totalamount;
	private int buyerid;
	private int cardusedid;
	private String deliveryAddress;
	
	
	@ElementCollection
    @GenericGenerator(name="itemGenerator",strategy="sequence")
    @CollectionId(
        columns=@Column(name="itemid"),
        type=@Type(type="long"),
        generator="itemGenerator"
    )
    private List<OrderedItem> ordereditemlist = new ArrayList<OrderedItem>();

	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public List<OrderedItem> getOrdereditemlist() {
		return ordereditemlist;
	}
	public void setOrdereditemlist(List<OrderedItem> ordereditemlist) {
		this.ordereditemlist = ordereditemlist;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public int getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(int totalamount) {
		this.totalamount = totalamount;
	}
	
	public Order(Date orderdate, int totalamount) {
		super();
		this.orderdate = orderdate;
		this.totalamount = totalamount;
		
	}
	public int getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
	}
	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", orderdate=" + orderdate + ", totalamount=" + totalamount + ", buyerid="
				+ buyerid + ", ordereditemlist=" + ordereditemlist + "]";
	}
	public int getCardusedid() {
		return cardusedid;
	}
	public void setCardusedid(int cardusedid) {
		this.cardusedid = cardusedid;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
	
	

}
