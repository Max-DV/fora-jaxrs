package io.robusta.demo.jaxb;

import java.beans.Transient;
import java.util.Random;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * TODO : Put the correct anotations
 * change "place" element name
 * set id as an attribute - does it work as json ?
 * Try a transient element.
 */
public class Thing{

	
	
	long id;
	
	float value;
	String name;
	
	Place place;
	
	public Thing() {
	
	}
	
	public Thing(float value, String name, String address) {
		
		this.id = new Random().nextLong();
		this.value = value;
		this.name = name;
		Place p = new Place();
		p.setId(Math.abs(new Random().nextInt()));
		p.setAddress(address);
		this.place = p;
	}
	
	
	public Thing(long id, float value, String name, String address) {	
		
		this(value, name, address);
		this.id = id;
	}


	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	
	public float getValue() {
		return value;
	}


	public void setValue(float value) {
		this.value = value;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	
	public Place getPlace() {
		return place;
	}


	public void setPlace(Place place) {
		this.place = place;
	}
	
	
	
	
}
