package io.robusta.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


public class SubResource {
   
	@GET
	public String world(){
		return "Hello world";
	}
	
	@GET
	@Path("{location}")
	public String location(@PathParam("location") String location)
	{
		return "Hello "+location;
	}
	
}