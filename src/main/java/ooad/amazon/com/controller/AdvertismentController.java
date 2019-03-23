package ooad.amazon.com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import ooad.amazon.com.bean.Advertisment;
import ooad.amazon.com.bean.Category;
import ooad.amazon.com.dao.AdvertisementDAO;
import ooad.amazon.com.dao.CategoryDAO;

@Path("/AdvertismentController")
public class AdvertismentController {


	 @Path("/advertisments")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response getAdvertisments() {

		 GenericEntity<List<Advertisment>> ads;
	
		 ads  = new GenericEntity<List<Advertisment>>( AdvertisementDAO.getAllAdvertisments()) { };
		
		 return Response.ok(ads).build();
	
	    }
	 
	
	
	@Path("/addadvert")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response addAdvert(@FormDataParam("addImageSelect") InputStream file,
			@FormDataParam("addImageSelect") FormDataContentDisposition fileDetail,
			@FormDataParam("productname") String productname,
			@FormDataParam("category") String category,
			@FormDataParam("subcategory") String subcategory,
			@FormDataParam("bday") String bday,
			@FormDataParam("active") String active) {
		
		System.out.println("Hello..........................");
		
		//System.out.println("\nProduct Name: " + name + "\nCategory: " + category + "\nBirthday: " + bday);
 		
 		String uploadedFileLocation = "C:\\Users\\Rikki\\eclipse-workspace\\amazon.com\\src\\main\\webapp\\images\\advertisments\\" + productname + "." +  fileDetail.getFileName().split("\\.")[1];
		 //String uploadedFileLocation = "D:\\pics"  + productname + "." +  fileDetail.getFileName().split("\\.")[1];;
		 
		 String storeUrl = "images/advertisments/" +  productname + "." +  fileDetail.getFileName().split("\\.")[1];
			//st.setPicUrl(storeUrl);
		 
		 System.out.println("file details" + fileDetail.toString() + " -- " + storeUrl + " -- " + uploadedFileLocation);
		 
		writeToFile(file, uploadedFileLocation);
 		
 				
		Advertisment ad = new Advertisment();
		ad.setAdver_name(productname);
		ad.setCategorytoshow(category);
		
		Category cat;
        if(subcategory.equals("-1"))
        {
        	ad.setCategorytoshow(category);
        }
        
        else
        {
		    ad.setCategorytoshow(subcategory);
        }
		//ad.setIs_active(bday);
		if(bday.equals("1")) {
				ad.setIs_bday(true);
		}
		else {
			ad.setIs_bday(false);
		}
		
		if(active.equals("1")) {
			ad.setIs_active(true);
	}
	else {
		ad.setIs_active(false);
	}
		
		ad.setUrl(storeUrl);
		
		int id = AdvertisementDAO.addAdvertisment(ad);
//        
		return Response.ok(fileDetail.getFileName() + " uploaded successfully !!").build();
// 		
		 //return Response.status(404).entity("ok").build();
 			//return Response.status(201).entity("ok").build();
	}

 private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {
			try {
				OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
				int read = 0;
				byte[] bytes = new byte[1024];

				out = new FileOutputStream(new File(uploadedFileLocation));
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
			} catch (Exception e) {
				System.out.println("Exception "+e);
				//e.printStackTrace();
			}

		}
}

