package io.robusta;

import io.robusta.domain.Admin;
import io.robusta.domain.Comment;
import io.robusta.domain.Tag;
import io.robusta.domain.Topic;
import io.robusta.domain.User;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class ForaDataSource {

	public static final String NOT_OK = "1";
	public static final long TROLL_ID = 1L;
	public static final String You_dont_know_enough = "2";
	protected static ForaDataSource instance;

	List<User> users = new ArrayList<User>();
	List<Topic> topics = new ArrayList<Topic>();
	List<Comment> comments = new ArrayList<Comment>();
	List<Tag> tags = new ArrayList<Tag>();
	Topic troll;
	Topic games;
	Topic spices;

	Tag violence;
	Tag fun;
	Tag science;
	
	Admin nicolas, leonard;

	public ForaDataSource() {

	}

	public void initDataSource() {

		initTags();
		initUsers();
		initTopics();
		initComments();
	}

	
	
	
	
	
	
	public static ForaDataSource getInstance() {
		if (instance == null) {
			instance = new ForaDataSource();
			instance.initDataSource();
		}

		// Files.readAllLines(Paths.get(new URL))
		return instance;


	}
	
	

	private void initTags() {
		violence = new Tag(1L, "Violence");
		fun = new Tag(2L, "Fun");
		science = new Tag(3L, "Science");

	}

	private void initUsers() {
		nicolas = new Admin(1L, "Nicolas","Star Wars rocks !");

		leonard = new Admin(2L,"Leonard","Star Trek rocks");
		
		User sheldon = new User();
		sheldon.setId(3L);
		sheldon.setName("Sheldon");
		sheldon.setEmail("sheldon@robusta.io");

		User raj = new User();
		raj.setId(4L);
		raj.setName("Raj");
		raj.setEmail("raj@robusta.io");

		User howard = new User();
		howard.setId(5L);
		howard.setName("Howard");
		howard.setEmail("howard@robusta.io");

		User penny = new User();
		penny.setId(6L);
		penny.setName("Penny");
		penny.setEmail("penny@robusta.io");

		User emy = new User();
		emy.setId(7L);
		emy.setName("Emy");
		emy.setEmail("emy@robusta.io");

		User bernie = new User();
		bernie.setId(8L);
		bernie.setName("Bernadette");
		bernie.setEmail("bernie@robusta.io");

		emy.setFemale();
		bernie.setFemale();
		penny.setFemale();

		Collections.addAll(this.users, nicolas, leonard, sheldon, raj, howard,
				penny, emy, bernie);
	}

	private void initTopics() {
		troll = new Topic();
		troll.setId(TROLL_ID);
		troll.setTitle("Star Trek > Star Wars");
		troll.setUser(leonard());

		games= new Topic();
		games.setId(2L);
		games.setTitle("American Football is the best game");		
		games.setUser(penny());

		
		spices = new Topic();
		spices.setId(3L);
		spices.setTitle("Ketchup is not a Spice");		
		spices.setUser(emy());
		// Usually, we would like to make business stuff with a addComment
		// method		
		Collections.addAll(this.topics, troll, games, spices);
	}

	public List<User> getUsers() {
		return this.users;
	}

	public Admin nicolas() {
		return nicolas;
	}

	public Admin leonard() {
		return leonard;
	}

	public User sheldon() {
		return this.getUsers().get(2);
	}
	
	public User raj() {
		return this.getUsers().get(3);
	}
	
	public User howard() {
		return this.getUsers().get(4);
	}
	
	//nicolas, leonard, sheldon, raj, howard, penny, emy, bernie
	
	public User penny() {
		return this.getUsers().get(5);
	}
	
	public User emy() {
		return this.getUsers().get(6);
	}
	
	public User bernie() {
		return this.getUsers().get(7);
	}
	
	

	public List<Topic> getTopics() {
		return this.topics;
	}

	public List<Comment> getComments() {

		return this.comments;
	}

	
	private void initComments() {
		initTrollComments();
		initGamesComments();
		initSpicesComments();
	}
	
	private void initTrollComments() {

		Comment c1 = new Comment();
		c1.setId(NOT_OK);
		c1.setUser(nicolas());
		c1.setContent("I'm not ok");

		Comment c2 = new Comment();
		c2.setId(You_dont_know_enough);
		c2.setUser(leonard());
		c2.setContent("You don't know enough about heroes");

		Comment c3 = new Comment();
		c3.setId("3");
		c3.setAnonymous(true);
		c3.setContent("What ? You stupid !");
		c3.getTags().add(violence);

		troll.addComments(c1, c2, c3);
		Collections.addAll(this.comments, c1, c2, c3);
	}
	
	private void initGamesComments() {

		Comment c1 = new Comment(penny(), "There are so many strategies");		
		Comment c2 = new Comment(leonard(), "What ? These guys are stupid !");
		Comment c3 = new Comment(penny(), "They know how to count to 4");
		Comment c4 = new Comment(sheldon(), "So why do they call it football and play with hands ?");
		
		
		
		games.addComments(c1, c2, c3, c4);
		Collections.addAll(this.comments, c1, c2, c3, c4);
	}

	private void initSpicesComments() {

		Comment c1 = new Comment(emy(), "It misses spices, let's add ketchup");		
		Comment c2 = new Comment(raj(), "What ? You stupid ! It's not a spice !");
		Comment c3 = new Comment(emy(), "But there is spicy vinegar");
		Comment c4 = new Comment(bernie(), "Vinegar is not a spice, it's a fruit");
		Comment c5 = new Comment(emy(), "A liquid fruit ? Doesn't make sense !");
			Comment c6 = new Comment(sheldon(), "And it is a bit <strong>violent</strong> <script type='text/javascript'>alert('you are hacked!')</script>");
		
		spices.addComments(c1, c2, c3, c4, c5, c6);
		Collections.addAll(this.comments, c1, c2, c3, c4, c5, c6);
	}
	
	public List<Comment> getTrollComments() {
		return troll.getComments();
	}

	public List<Tag> getTags() {
		return tags;
	}

	public int getTotalCommentsCount() {
		int count = 0;
		for (Topic s : this.getTopics()) {
			count += s.getComments().size();
		}
		return count;
	}

	// obviously not very interesting
	public Map<Comment, User> getUserByComment() {
		Map<Comment, User> map = new HashMap<>();
		for (Comment c : comments) {
			map.put(c, c.getUser());
		}
		return map;
	}

	// more interesting
	public Map<User, List<Comment>> getCommentsByUser() {

		Map<User, List<Comment>> map = new HashMap<User, List<Comment>>();
		for (Comment c : comments) {
			// can be null, no problem
			User u = c.getUser();
			if (!map.containsKey(u)) {
				List<Comment> comments = new ArrayList<>();
				map.put(u, comments);
			}

			// add the comment to the list
			List<Comment> comments = map.get(u);
			comments.add(c);
		}
		return map;
	}

	public void fillMany(int size) {
		// int size = 24;
		int userSize = size;
		int topicSize = userSize * 3;
		int commentSize = userSize * 12;
		int tagSize = userSize;

		for (int i = 0; i < userSize; i++) {
			User u = new User();
			u.setEmail("user" + i + "@fora.com");
			u.setName("John Doe - " + i);
			users.add(u);
		}

		// creating tags
		for (int i = 0; i < userSize; i++) {
			Tag tag = new Tag();
			tag.setId((long) tags.size());
			tag.setName("Tag " + i);
			tags.add(tag);
		}

		// create Topics
		for (int i = 0; i < topicSize; i++) {
			Topic t = new Topic();			
			t.setTitle("Topic " + i);
			User u = getRandomItem(User.class, users);
			t.setUser(u);
			topics.add(t);

			// adding tags
			if (getRandomTrue(70)) {
				Tag tag = getRandomItem(Tag.class, tags);
				t.getTags().add(tag);
			}

			if (getRandomTrue(50)) {
				Tag tag = getRandomItem(Tag.class, tags);
				t.getTags().add(tag);
			}

			if (getRandomTrue(20)) {
				Tag tag = getRandomItem(Tag.class, tags);
				t.getTags().add(tag);
			}
		}

		// Create comments
		for (int i = 0; i < commentSize; i++) {
			Comment c = new Comment();
			c.setUser(getRandomItem(User.class, users));
			c.setContent("My comment says " + i);
			Topic t = getRandomItem(Topic.class, topics);
			t.getComments().add(c);
			comments.add(c);

			// adding tags
			if (getRandomTrue(20)) {
				Tag tag = getRandomItem(Tag.class, tags);
				c.getTags().add(tag);
			}

			if (getRandomTrue(5)) {
				Tag tag = getRandomItem(Tag.class, tags);
				c.getTags().add(tag);
			}

		}

		for (int i = 0; i < tagSize; i++) {
			Tag tag = new Tag();
			Comment c = getRandomItem(Comment.class, comments);
			tag.setName("tag" + i);
			c.getTags().add(tag);
			tags.add(tag);
		}

	}

	public <T> T getRandomItem(Class<T> clazz, List<T> list) {

		int length = list.size();

		try {
			int index = new Random().nextInt(length);
			return list.get(index);
		} catch (RuntimeException e) {
			System.out.println("length is " + length);
			throw e;
		}

	}

	public boolean getRandomTrue(int percent) {
		float f = ((float) percent) / 100;
		return Math.random() < f;
	}
	
	
	

}
