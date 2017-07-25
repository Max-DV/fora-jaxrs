package io.robusta.demo.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Place {

	int id;
	String address;
	
	public Place() {
	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	
	
	
}
