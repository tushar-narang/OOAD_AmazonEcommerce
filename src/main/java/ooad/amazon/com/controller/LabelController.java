package ooad.amazon.com.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;

import ooad.amazon.com.bean.Label;
import ooad.amazon.com.dao.LabelDAO;
import ooad.amazon.com.dao.ProdReviewDAO;

@Path("/LabelController")
public class LabelController {
	
	
	@Path("/allLabels/{product_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Label> getLabels(@PathParam("product_id") int productid) {
		
		
		List<Label> list = LabelDAO.getLabels(productid);
		System.out.println( productid+ "  ");

		
		return list;
	}
	
	
	
	@Path("/addLabel/{product_id}")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response addLabel(@FormDataParam("name") String name,
							 @FormDataParam("value") String value,
							 @PathParam("product_id") int productid) {
		
		System.out.println(name+ "    "+ value+"   "+ productid+ "   ");

		LabelDAO.addLabel(name,value,productid);
		
		
		return Response.status(201).entity("Label added!").build();
	}
	
	
	@Path("/deleteLabel/{product_id}")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response deleteLabel(@FormDataParam("id") int id,
							 @PathParam("product_id") int productid) {
		
		//LabelDAO.deleteLabel(id,productid);
		System.out.println(id+"  "+ productid+ "  ");

		
		return Response.status(201).entity("Label deleted!").build();
	}
}
