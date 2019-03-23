package ooad.amazon.com.controller;



import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import ooad.amazon.com.bean.Bank;
import ooad.amazon.com.bean.Customer;

import ooad.amazon.com.bean.User;
import ooad.amazon.com.dao.CustomerDAO;


//import example.model.QuadraticEquation;

@Path("/services/register")

public class Register {
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Path("src")
	public static ResponseBuilder RegisterForm(@FormParam("fullname") String fname,@FormParam("email") String emailid,
							@FormParam("phonenumber") String contact_no,@FormParam("password1") String password) {
		
		Customer u=new Customer();
		u.setFname(fname);
		u.setEmailid(emailid);
		u.setContact_no(contact_no);
		u.setPassword(password);
		
		Bank bankacc = new  Bank();
		bankacc.setAmount(0);
		bankacc.setAmzamount(0);
		
		u.setBank(bankacc);
		//System.out.println(fname+" "+contact_no);
		//CustomerDAO.registercustomer(u, bankacc);
		return Response.ok(u);
		
	}
}
