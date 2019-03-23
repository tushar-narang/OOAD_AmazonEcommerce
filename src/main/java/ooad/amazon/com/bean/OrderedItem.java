package ooad.amazon.com.bean;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;

@Embeddable
public class OrderedItem {

	private int productid;
	private int quantity;
	private int freequantity;
	private int unitamount;
	private int unitdiscountedamount;
	private String status;
	
	public OrderedItem() {
		super();
	}
	
	public OrderedItem(int productid, int quantity, int freequantity, int unitamount, int unitdiscountedamount, String status) {
		super();
		this.productid = productid;
		this.quantity = quantity;
		this.freequantity = freequantity; 
		this.unitamount = unitamount;
		this.unitdiscountedamount = unitdiscountedamount;
		this.status = status;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getUnitamount() {
		return unitamount;
	}
	public void setUnitamount(int unitamount) {
		this.unitamount = unitamount;
	}
	public int getUnitdiscountedamount() {
		return unitdiscountedamount;
	}
	public void setUnitdiscountedamount(int unitdiscountedamount) {
		this.unitdiscountedamount = unitdiscountedamount;
	}
	

	public int getFreequantity() {
		return freequantity;
	}

	public void setFreequantity(int freequantity) {
		this.freequantity = freequantity;
	}

	@Override
	public String toString() {
		return "OrderedItem [productid=" + productid + ", quantity=" + quantity + ", freequantity=" + freequantity
				+ ", unitamount=" + unitamount + ", unitdiscountedamount=" + unitdiscountedamount + ", status=" + status
				+ "]";
	}

	
	
	
	
	
	
}
