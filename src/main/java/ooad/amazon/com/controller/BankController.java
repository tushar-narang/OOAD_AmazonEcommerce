package ooad.amazon.com.controller;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import ooad.amazon.com.dao.BankDAO;
import ooad.amazon.com.dao.ProductDAO;

@Path("/BankController")
public class BankController {

	@POST 
	@Path("/addMoney")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response registercus(
			@FormDataParam("userid") Integer userid,
			@FormDataParam("amount") Integer amount) {
		
		BankDAO.addMoney(userid, amount);
		return Response.status(201).entity("Added Successfully").build();
		
	}
	
	@GET 
	@Path("/getamazonbalance")
	public Response pp(
			) {
		
		int i = BankDAO.getamazonbalance();
		return Response.status(201).entity(""+i).build();
		
	}
	
	
}