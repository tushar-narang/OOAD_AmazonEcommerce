package ooad.amazon.com.controller;

import java.io.InputStream;
import java.util.ArrayList;
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

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import ooad.amazon.com.bean.Advertisment;
import ooad.amazon.com.bean.Category;
import ooad.amazon.com.bean.ProdReview;
import ooad.amazon.com.bean.Product;
import ooad.amazon.com.bean.ProductImages;
import ooad.amazon.com.dao.AdvertisementDAO;
import ooad.amazon.com.dao.ProdReviewDAO;
import ooad.amazon.com.dao.ProductDAO;

@Path("/ProdReviewController")
public class ProdReviewController {

	
	 @POST 
		@Path("/savereview/{product_id}/{customer_id}")
		@Consumes(MediaType.MULTIPART_FORM_DATA)
		public Response savereview(
				@FormDataParam("description") String description,
				@FormDataParam("headline")String headline,
				@FormDataParam("rating") float rating,
			    @PathParam("product_id") int product_id, 
			    @PathParam("customer_id") int customer_id)
	 {
					    
		    int resp = ProdReviewDAO.addproductreview(description, headline, product_id, rating, customer_id);
			
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
	
	 
	 @Path("/hasreviewed/{product_id}/{customer_id}")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response getReviewstatus(@PathParam("customer_id") int customer_id , @PathParam("product_id") int product_id) {

		 GenericEntity<List<ProdReview>> lprs;
		 lprs  = new GenericEntity<List<ProdReview>>(ProdReviewDAO.getReviewStatus(customer_id,product_id)) {};
		 return Response.ok(lprs).build();
	
	    }
	
	 @Path("/allreviews/{product_id}")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response getAllReviews (@PathParam("product_id") int product_id) {

		 GenericEntity<List<ProdReview>> lprs;
		 lprs  = new GenericEntity<List<ProdReview>>(ProdReviewDAO.getAllReviews(product_id)) {};
		 return Response.ok(lprs).build();
	
	    }
	 
	 
	 
	 
	 
}
