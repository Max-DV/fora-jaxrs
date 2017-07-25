package io.robusta.business;

import java.util.ArrayList;

import io.robusta.ForaDataSource;
import io.robusta.domain.Comment;
import io.robusta.domain.Topic;
import io.robusta.domain.User;

public class CommentBusiness {

	ForaDataSource fora = ForaDataSource.getInstance();

	public Comment getCommentById(String id) {

		for (Comment c : fora.getComments()) {
			if (c.getId().equals(id)) {
				return c;
			}
		}

		return null;// or throw exception

	}

	public Comment createComment(Topic s, String content, User u,
			boolean anonymous) {
		Comment c = new Comment();
		c.setAnonymous(anonymous);
		if (!anonymous) {
			c.setUser(u);
		}
		c.setContent(content);
		if (s.getComments() == null){
			s.setComments(new ArrayList<Comment>());
		}
		s.getComments().add(c);
		fora.getComments().add(c);

		return c;
	}
}
