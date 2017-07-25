package io.robusta.demo.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Loop {

	Loop friend;
	String name;
	
	public Loop() {
		// TODO Auto-generated constructor stub
	}

	public Loop getFriend() {
		return friend;
	}

	public void setFriend(Loop friend) {
		this.friend = friend;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
