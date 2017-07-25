package io.robusta.business;

import io.robusta.ForaDataSource;
import io.robusta.domain.Admin;
import io.robusta.domain.Comment;
import io.robusta.domain.Topic;
import io.robusta.domain.User;

import java.util.logging.Logger;

public class UserBusiness {

	private final static Logger logger = Logger.getLogger(UserBusiness.class
			.getName());

	ForaDataSource fora = ForaDataSource.getInstance();

	public User findByEmail(String email) {

		for (User u : fora.getUsers()) {
			if (u != null && u.getEmail() != null
					&& u.getEmail().equalsIgnoreCase(email)) {
				return u;
			}
		}
		return null;

	}
	
	
	public User findByName(String name) {

		for (User u : fora.getUsers()) {
			if (u != null && u.getName() != null
					&& u.getName().equalsIgnoreCase(name)) {
				return u;
			}
		}
		return null;

	}


	public User findById(Long id) {

		for (User u : fora.getUsers()) {
			if (u != null && u.getId() != null
					&& u.getId().equals(id)) {
				return u;
			}
		}
		return null;

	}


	public User createUser(String name){
		return createUser(name+"@rra.io", name, false, true);
	}
	
	public User createUser(String email, String name, boolean admin, boolean male) {
		
		User u;
		if (admin){
			u = new Admin();
		}else{
			u = new User();
		}
		
		u.setEmail(email);
		u.setName(name);
		if (!male){
			u.setFemale();
		}
		

		u.setId((long) fora.getUsers().size());
		fora.getUsers().add(u);

		logger.info("Created user " + email);
		return u;
	}




	public void updateUser(User user) {

		for (int i = 0; i < fora.getUsers().size(); i++) {
			User u = fora.getUsers().get(i);

			if (u != null && u.getId() == user.getId()) {
				// replacing
				fora.getUsers().remove(i);
				fora.getUsers().add(i, user);
			}
		}

	}

	public void deleteUser(User user) {
		ForaDataSource.getInstance().getUsers().remove(user);
		for (Topic topic : ForaDataSource.getInstance().getTopics()) {
			if (topic.getUser().equals(user)) {
				topic.setUser(null);
			}
		}

		for (Comment comment : ForaDataSource.getInstance().getComments()) {
			if (comment.getUser() != null && comment.getUser().equals(user)) {
				comment.setUser(null);
				comment.setAnonymous(true);
			}
		}
	}
	
	

}
