package ooad.amazon.com.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;

import ooad.amazon.com.bean.Admin;
import ooad.amazon.com.bean.Advertisment;
import ooad.amazon.com.bean.Card;
import ooad.amazon.com.bean.Customer;
import ooad.amazon.com.bean.Product;
import ooad.amazon.com.dao.AdminDAO;
import ooad.amazon.com.dao.CustomerDAO;
import ooad.amazon.com.dao.ProductDAO;
import ooad.amazon.com.dao.UserDAO;

@Path("/AdminController")
public class AdminController {
	
	@POST 
	@Path("/checkLogin")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkLogin(
			@FormDataParam("email") String username,
			@FormDataParam("password1") String password){
		
 		System.out.println(username + " " + password);
		 List<Admin> resp = AdminDAO.CheckLogin(username, password);
		 System.out.println("resp is "+resp);
		 if(!(resp == null))
		 {
			 GenericEntity<List<Admin>> cust  = new GenericEntity<List<Admin>>(resp){};
			 System.out.println("wassup 9090" + cust.toString());
			 return Response.status(200).entity(cust).build();			 
		 }
		 else
		 {
			 String result = "Customer not found";
			 return Response.status(404).entity(result).build();
		 }
		
	}
	
	@POST 
	@Path("/saveuser")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response registercus(
			@FormParam("email") String email,@FormParam("password1") String password
			 ){
		
 		//System.out.println("wassup1" + dob);
		
		
		Admin adm = new Admin();
		adm.setaName(email);
		adm.setPassword(password);
		
		
		 int resp = AdminDAO.registercustomer(adm);
			System.out.println("wassup2");

		 if(resp >0)
		 {
			 String result = resp+"";
			 return Response.status(201).entity(result).build();
		 }
		 else
		 {
			 String result = resp+"";
			 return Response.status(404).entity(result).build();
		 }
		
	}
		
	
	
	@GET
	@Path("/displayuser")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> displayuser(){
		List<Customer> usr =AdminDAO.displayuser();
		System.out.println("resp: "+usr.toString());
		return usr;
		
	}
	
	@GET
	@Path("/displayadmin")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Admin> displayadmin(){
		List<Admin> usr =AdminDAO.displayadmin();
		
		return usr;
		
	}
	
	@GET
	@Path("/displayadvert")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Advertisment> displayadvert(){
		List<Advertisment> usr =AdminDAO.displayadvert();
		
		return usr;
		
	}
	
	@GET
	@Path("/displayseller")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> displayseller(){
		List<Customer> usr =AdminDAO.displayseller();
		
		return usr;
		
	}
	
	@GET
	@Path("/displayprodseller/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> displayprodseller(@PathParam("id") int id){
		List<Customer> usr =AdminDAO.displayprodseller(id);
		
		return usr;
		
	}
	
	@GET
	@Path("/bankdetails/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Card> bankdetails(@PathParam("id") int id){
		List<Card> usr =AdminDAO.bankdetails(id);
		
		return usr;
		
	}
	
	
	
	
	
}
