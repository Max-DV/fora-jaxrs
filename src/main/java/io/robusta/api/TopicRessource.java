package io.robusta.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import io.robusta.ForaDataSource;
import io.robusta.business.CommentBusiness;
import io.robusta.business.TopicBusiness;
import io.robusta.business.UserBusiness;
import io.robusta.domain.Comment;
import io.robusta.domain.Topic;
import io.robusta.domain.User;

@Path("topics")
@Produces("application/json")
@Consumes("application/json")
public class TopicRessource {

	@GET
	public List<Topic> findTopics() throws Exception {

		return ForaDataSource.getInstance().getTopics();

	}

	@GET
	@Path("{id}")
	public Topic findUserById(@PathParam("id") Long id) throws Exception {
		Topic topic= new TopicBusiness().getTopicById(id);
		if(topic==null){
			throw new NotFoundException("No user"+topic+"found");
		}
		return topic;
	}

	@POST
	@Path("{idTopic}/comments")
	public Response createComment(
		@PathParam("idTopic")long idTopic, Comment representation){
		Topic topic= new TopicBusiness().getTopicById(idTopic);
		boolean anonymous= false;
		User user= null;
		if(representation.getUser()==null){
			anonymous=true;
		}else{ 
			user= new UserBusiness().findById(representation.getUser().getId());
			if (user== null){
				return Response.status(401).entity("Please authenticate in the App").build();
			}
		}
		Comment comment= new CommentBusiness().createComment(topic, representation.getContent(), user, anonymous);
		NewCookie cookie= new NewCookie("Hello","world");
		return Response.status(201).cookie(cookie).header("X-location", "aelion centaure").entity(comment).build();
	}
	
@POST
public long createTopic(Topic topic){
	new TopicBusiness().createTopic(topic);
	return topic.getId();
}
	}
