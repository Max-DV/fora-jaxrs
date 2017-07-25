package io.robusta.demo.cache;

import io.robusta.business.EtagBusiness;
import io.robusta.demo.providers.Cacheable;
import io.robusta.demo.providers.NotFoundException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class CacheResource {

    StoryService service = new StoryService();

    @GET
    @Path("test")
    public Response test() {

        return Response.ok("test").build();

    }

    @GET
    @Path("{id}")
    public Response getStory(@PathParam("id") int id, @Context Request req) {

        PeopleStory story = service.getStory(id).orElseThrow(NotFoundException::new);
        return Response.ok(story.getContent()).build();

    }


    @GET
    @Path("etag/{id}")
    // @Cacheable : Does not use the filter, directly there
    public Response getCacheableStory(@PathParam("id") int id,
                                      @Context Request req) {
        CacheControl cc = new CacheControl();
        cc.setMaxAge(86000);

        System.out.println("inside Resource : " + req.toString());

        PeopleStory story = service.getStory(id).orElseThrow(NotFoundException::new);


        String etagValue = new EtagBusiness().getFastEtag(story.getContent());
        EntityTag etag = new EntityTag(etagValue);
        ResponseBuilder rb = req.evaluatePreconditions(etag);


        if (rb == null) {// Not found yet
            System.out.println("Etag not found");
            return Response.ok(story).cacheControl(cc).tag(etagValue).build();
        }

        System.out.println("Etag found, sends 304");
        return rb.status(304).cacheControl(cc).tag(etagValue).build();
        //return story;

    }


    @GET
    @Path("date/{id}")
    @Cacheable
    public PeopleStory getDateStory(@PathParam("id") int id) {
        System.out.println("in date story");
        PeopleStory story = service.getStory(id).orElseThrow(NotFoundException::new);

        return story;

    }


}
