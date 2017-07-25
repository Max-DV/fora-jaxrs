package io.robusta.business;

import io.robusta.ForaDataSource;
import io.robusta.domain.Comment;
import io.robusta.domain.Tag;

import java.util.logging.Logger;

public class TagBusiness {

	ForaDataSource fora = ForaDataSource.getInstance();
	CommentBusiness commentBusiness = new CommentBusiness();
	private final static Logger logger = Logger.getLogger(TagBusiness.class
			.getName());

	public synchronized void tagComment(String commentId, String tagName) {

		logger.info("tagging comment " + commentId + " with " + tagName);
		Comment comment = commentBusiness.getCommentById(commentId);

		Tag tag = new Tag();
		tag.setName(tagName);
		comment.getTags().add(tag);

		fora.getTags().add(tag);

	}
	
	
	
	

}
