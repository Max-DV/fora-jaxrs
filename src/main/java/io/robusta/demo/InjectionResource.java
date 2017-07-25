package io.robusta.demo;

import io.robusta.ForaDataSource;
import io.robusta.ForaQualifier;
import io.robusta.domain.User;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("")
@RequestScoped
public class InjectionResource {
	
	@QueryParam("hello")
	@DefaultValue("World")
	String hello;
	
	@GET
	public String test(){
		return hello;
	}
	
	
	@Inject
	@ForaQualifier
	ForaDataSource fora;
	
	@GET
	public List<User> findAll(){
		return fora.getUsers();
	}
	
	@GET
	@Path("emy")
	public User  emy(){
		return fora.emy();
	}
	
	
}
