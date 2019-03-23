package ooad.amazon.com.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class Advertisment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String adver_name;
	@Type(type="yes_no")
	private boolean is_active = true;
	@Type(type="yes_no")
	private boolean is_bday = false;
	
	public boolean isIs_bday() {
		return is_bday;
	}
	public void setIs_bday(boolean is_bday) {
		this.is_bday = is_bday;
	}
	private String url;
	
	private String categorytoshow;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdver_name() {
		return adver_name;
	}
	public void setAdver_name(String adver_name) {
		this.adver_name = adver_name;
	}
	public boolean isIs_active() {
		return is_active;
	}
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	
	public String getCategorytoshow() {
		return categorytoshow;
	}
	public void setCategorytoshow(String categorytoshow) {
		this.categorytoshow = categorytoshow;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
