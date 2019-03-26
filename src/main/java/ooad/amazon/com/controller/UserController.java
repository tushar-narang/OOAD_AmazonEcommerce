package ooad.amazon.com.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import ooad.amazon.com.bean.Address;
import ooad.amazon.com.bean.Bank;
import ooad.amazon.com.bean.CartItem;
import ooad.amazon.com.bean.Customer;
import ooad.amazon.com.bean.Product;
import ooad.amazon.com.bean.Seller;
import ooad.amazon.com.dao.CustomerDAO;
import ooad.amazon.com.dao.ProductDAO;
import ooad.amazon.com.dao.UserDAO;

@Path("/UserController")
public class UserController {
	
	@POST 
	@Path("/checkLogin")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkLogin(
			@FormDataParam("email") String username,
			@FormDataParam("password1") String password){
		
 		System.out.println(username + " " + password);
		 List<Customer> resp = UserDAO.CheckLogin(username, password);
		 System.out.println("resp is "+resp);
		 if(!(resp == null))
		 {
			 GenericEntity<List<Customer>> cust  = new GenericEntity<List<Customer>>(resp){};
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
			@FormParam("fullname") String fname,
			@FormParam("email") String email,
			@FormParam("phonenumber") String phone,
			@FormParam("password1") String password,
			@FormParam("dob") String dob,
			@FormParam("addr_line1") String addr_line1,
			@FormParam("addr_line2") String addr_line2,
			@FormParam("street") String street,
			@FormParam("city") String city,
			@FormParam("pincode") int pincode){
		
 		System.out.println("wassup1" + dob);
		
 		Address addr = new Address();
 		addr.setAddressline1(addr_line1);
 		addr.setAddressline2(addr_line2);
 		addr.setStreet(street);
 		addr.setCity(city);
 		addr.setPincode(pincode);
 		
 		List<Address> addrList = new ArrayList<>();
 		addrList.add(addr);
 		
		Customer cus = new Customer();
		cus.setFname(fname);
		cus.setEmailid(email);
		cus.setContact_no(phone);
		cus.setPassword(password);
		cus.setAddrlist(addrList);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = null;
		try {
			date = format.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date);
		
		cus.setDob(date);
		
		Bank bankacc = new  Bank();
		bankacc.setAmount(0);
		bankacc.setAmzamount(0);
		
		cus.setBank(bankacc);
		
		 int resp = CustomerDAO.registercustomer(cus, bankacc, addr);
			System.out.println("wassup2 resp value from dao is " +resp );

			
			
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
	
	@Path("/customerdetails/{email}")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response getProducts(@PathParam("email") String email) {

		 GenericEntity<Customer> customer;
	
		 customer  = new GenericEntity<Customer>(CustomerDAO.getcustomerbyemailid(email)) { };
		 return Response.ok(customer).build();
		 
	    }
	
	@Path("/getnoinusercart/{cusid}")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response getproductsincart(@PathParam("cusid") int cusid) {
		 GenericEntity<Customer> customer;
		 int size  = CustomerDAO.getnoofproductsincart(cusid);
		 return Response.ok(size).build();
	    }
	
	@POST 
	@Path("/saveSeller")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN )
	public Response saveseller(
			@FormDataParam("userid") int userid,
			@FormDataParam("companyname") String companyname,
			@FormDataParam("gstin") String gstin,
			@FormDataParam("pan") String pan,
			@FormDataParam("address") String address,
			@FormDataParam("contactno") String contactno,
			@FormDataParam("cardno") String cardno,
			@FormDataParam("cvv") String cvv){
		
		
		
		System.out.println("card from is :" +cardno );
		System.out.println("cvv from is :" +cvv );	
		
		
		
 		Seller seller = new Seller(companyname, gstin, pan, address, contactno, 0, 0);
 		seller.setUserid(userid);
		 String resp = UserDAO.addsellerdetails(seller, cardno, cvv);
	
		 System.out.println(""+resp);
		
		return Response.ok(resp).build();			 
		
		
	}	
	
	@Path("/checkSellerDetails/{userid}")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response checkseller(@PathParam("userid") int userid) {

		Seller seller = UserDAO.checkifDetailsExist(userid);
		if(seller == null)
			return Response.ok("0").build();
		else {
			 GenericEntity<Seller> s;
			 s  = new GenericEntity<Seller>(seller) { };
			 return Response.ok(s).build();
			}
	    }
	
	@Path("/getAmountOwed/{userid}")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response getamountowed(@PathParam("userid") int userid) {

		int amt = UserDAO.getAmountOwed(userid);
		return Response.ok(amt).build();
			
	    }
	

	@Path("/getUserCart/{userid}")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response getusercart(@PathParam("userid") int userid) {

		List<CartItem> lklist = UserDAO.getUserCart(userid);
		return Response.ok(lklist).build();
			
	    }

	@POST 
	@Path("/emptyCart/{uid}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN )
	public Response emptyCart(
			@PathParam("uid") int uid){
		int resp = UserDAO.emptyUserCart(uid);
		return Response.ok(resp).build();			 
	}
	
	@Path("/getUserWishlist/{userid}")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response getuserwishlist(@PathParam("userid") int userid) {

		List<Product> lklist = UserDAO.getUserWishlist(userid);
		return Response.ok(lklist).build();
			
	    }
	
	@GET
	@Path("/removeCartItem/{uid}/{prodid}")
	@Produces(MediaType.TEXT_PLAIN )
	public Response removeCartItem(
			@PathParam("uid") int uid,
			@PathParam("prodid") int prodid) {
		
		//get All Items From User's Cart
		//Remove The Selected Element
		//Re-Initialize The User's Cart
		int resp = UserDAO.removeCartItem(uid, prodid);
		return Response.ok(resp).build();
		
	}
	
	
	
	
	
	
	
	
}
