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

import org.glassfish.jersey.media.multipart.FormDataParam;

import ooad.amazon.com.bean.Address;
import ooad.amazon.com.dao.AddressDAO;
import ooad.amazon.com.dao.CustomerDAO;

@Path("/AddressController")
public class AddressController {

		@Path("/customeraddress/{userid}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
	    public Response getAddrList(@PathParam("userid") int userid) {

		 GenericEntity<List<Address>> addrList;
	
		 addrList  = new GenericEntity<List<Address>>(CustomerDAO.getaddressofuser(userid)) { };
		 return Response.ok(addrList).build();
		 
	    }
		
		@POST 
		@Path("/addAddress/{userid}")
		@Consumes(MediaType.MULTIPART_FORM_DATA)
		public Response addAddress(
				@FormDataParam("addr_line1") String addr_line1,
				@FormDataParam("addr_line2") String addr_line2,
				@FormDataParam("street") String street,
				@FormDataParam("city") String city,
				@FormDataParam("pincode") int pincode,
				@PathParam("userid") int userid){
		
			Address addr = new Address();
			addr.setAddressline1(addr_line1);
			addr.setAddressline2(addr_line2);
			addr.setStreet(street);
			addr.setCity(city);
			addr.setPincode(pincode);
			int resp = AddressDAO.addAddress(userid, addr);
			return Response.status(201).entity(resp +"").build();
		}
			
}
