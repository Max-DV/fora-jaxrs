package io.robusta.demo.jaxb;

import io.robusta.ForaDataSource;
import io.robusta.domain.User;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class ThingResource {

	public static Thing t1 = new Thing(1, 1.0f, "bonbon", "Londres");  
	public static Thing t2 = new Thing(2, 2.0f, "CD", "Abbey Road");
	public static Thing t3 = new Thing(3, 3.0f, "jean", "Picadilly");
	public static Thing t4 = new Thing(4, 4.0f, "toy", "Harrod's");
	
	
	@GET
	public String test(){
		return "test";
	}
	
	@GET
	@Path("thing")
	@Produces(MediaType.APPLICATION_XML)
	public Thing getThing(){		
		return t1;
	}
	
	@GET
	@Path("emy")
	@Produces(MediaType.APPLICATION_XML)
	public User getEmy(){		
		return ForaDataSource.getInstance().emy();
	}
	
	@GET
	@Path("all")
	public List<Thing> getThings(){		
		return Arrays.asList(t1, t2, t3, t4);
	}
}
