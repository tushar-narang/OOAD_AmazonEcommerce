package ooad.amazon.com.controller;

import java.util.List;

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

import ooad.amazon.com.bean.Category;
import ooad.amazon.com.dao.CategoryDAO;
import ooad.amazon.com.dao.ProductDAO;



@Path("/CategoryController")
public class CategoryController {

	 @GET
	 @Path("/categories/{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response getCategories(@PathParam("id") int id) {

		 GenericEntity<List<Category>> cats;
	
		 cats  = new GenericEntity<List<Category>>( CategoryDAO.getAllSubCategories(id)) { };
		
		 return Response.ok(cats).build();
	
	    }
	 
	 @GET
	 @Path("/categoryById/{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response getCategoryById(@PathParam("id") int id) {

		 GenericEntity<Category> cats;
	
		 cats  = new GenericEntity<Category>( CategoryDAO.getCategorybyid(id)) { };
		
		 return Response.ok(cats).build();
	
	    }
	 
	 
	 @GET
	 @Path("/rootcategories")
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response getrootCategories() {

		 GenericEntity<List<Category>> cats;
	
		 cats  = new GenericEntity<List<Category>>( CategoryDAO.getAllrootCategories()) { };
		
		 return Response.ok(cats).build();
	
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 @GET
	 @Path("/allcategories")
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response getallCategories() {

		 GenericEntity<List<Category>> cats;
	
		 cats  = new GenericEntity<List<Category>>( CategoryDAO.getAllCategories()) { };
		
		 return Response.ok(cats).build();
	
	    }
	 
	 
	 
	 
	
	 @POST 
		@Path("/addCategory")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public Response checkLogin(
				@FormParam("catname") String catname,
				@FormParam("supid") String supid,
				@FormParam("temp1_name") String temp1_name,
				@FormParam("temp2_name") String temp2_name,
				@FormParam("temp3_name") String temp3_name,
				@FormParam("temp4_name") String temp4_name,
				@FormParam("temp5_name") String temp5_name,
				@FormParam("temp6_name") String temp6_name){
			
	 		System.out.println("---------" + temp1_name + " " + temp2_name );
	 		int id = Integer.parseInt(supid);
	 		
	 		int resp = CategoryDAO.addCategory(catname, id, temp1_name, temp2_name, temp3_name, temp4_name, temp5_name, temp6_name);
	 		
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
	
	
	
	
	
}
