package io.robusta.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("hello")
public class LocatorResource {


    @Path("world")
    public SubResource getSubResource() {
        return new SubResource();
    }


    @GET
    public String getJava() {
        return "Hello Java from Locator";
    }

}
