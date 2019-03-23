package ooad.amazon.com.bean;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Category{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cat_id ;
	

	private String categoryname;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="super_cat_id")
	private Category super_cat;
	
	private String temp1_name;
	private String temp2_name;
	private String temp3_name;
	private String temp4_name;
	private String temp5_name;
	private String temp6_name;

	
	public Category() {
		super();
	}
	public Category(String categoryname) {
		this.categoryname = categoryname;
	}
	
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public Category getSuper_cat() {
		return super_cat;
	}
	public void setSuper_cat(Category super_cat) {
		this.super_cat = super_cat;
	}
	public String getTemp1_name() {
		return temp1_name;
	}
	public void setTemp1_name(String temp1_name) {
		this.temp1_name = temp1_name;
	}
	public String getTemp2_name() {
		return temp2_name;
	}
	public void setTemp2_name(String temp2_name) {
		this.temp2_name = temp2_name;
	}
	public String getTemp3_name() {
		return temp3_name;
	}
	public void setTemp3_name(String temp3_name) {
		this.temp3_name = temp3_name;
	}
	public String getTemp4_name() {
		return temp4_name;
	}
	public void setTemp4_name(String temp4_name) {
		this.temp4_name = temp4_name;
	}
	public String getTemp5_name() {
		return temp5_name;
	}
	public void setTemp5_name(String temp5_name) {
		this.temp5_name = temp5_name;
	}
	public String getTemp6_name() {
		return temp6_name;
	}
	public void setTemp6_name(String temp6_name) {
		this.temp6_name = temp6_name;
	}
	
}
