package ooad.amazon.com.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;

import ooad.amazon.com.bean.Card;
import ooad.amazon.com.bean.Customer;
import ooad.amazon.com.dao.CardDAO;
import ooad.amazon.com.dao.CustomerDAO;
import ooad.amazon.com.dao.UserDAO;

@Path("/CardController")
public class CardController {

	
	@POST 
	@Path("/addCard/{userid}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCard(@PathParam("userid") int userid,
			@FormDataParam("cardno") String cardno,
			@FormDataParam("cardholdername") String name,
			@FormDataParam("cvv") String cvv,
			@FormDataParam("balance") int balance){
		
		Card card = new Card(cardno, cvv, name, balance);
 		System.out.println(userid + " " + card.toString());
 		try {
 			CardDAO.addCard(userid, card);
 			return Response.status(200).entity("1").build();
		}catch (Exception e) {
			// TODO: handle exception
			return Response.status(200).entity("0").build();
		}
						 
		
		
	}
	
	@Path("/getCards/{userid}")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response getProducts(@PathParam("userid") int userid) {

		 GenericEntity<List<Card>> customer;
		 customer  = new GenericEntity<List<Card>>(CardDAO.getCardsofUser(userid)) { };
		 return Response.ok(customer).build();
		 
	    }
	
	 
	@Path("/addBalance/{cardno}/{amount}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBalance(@PathParam("cardno") String cardno, 
			@PathParam("amount") int amount){
		System.out.print("Here");
 		System.out.println(cardno + " " + amount);
 		
 			Boolean res = CardDAO.addBalance(cardno, amount);
 			if(res == true)
 				return Response.status(200).entity("1").build();
 			else
 				return Response.status(200).entity("0").build();
		
						 
		
		
	}
}
