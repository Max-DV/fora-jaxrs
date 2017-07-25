package io.robusta.demo.cache;

import io.robusta.business.EtagBusiness;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PeopleStory {

	int id;
	String content;
	
	long timestamp;
	Date date;

	public PeopleStory() {
	}
	
	public PeopleStory(int id, String content) {
		this.id = id;
		this.content = content;
		Calendar c =Calendar.getInstance();
		//Document has been created in 2014
		c.set(2014, 1, 1, 10, 00, 00);
		this.date = c.getTime();
		this.timestamp = this.date.getTime();
	}

	public int getId() {
		return id;
	}

	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTimestamp() {
		return timestamp;
	}

	
	public Date getDate() {
		return date;
	}
	
	
	public String getEtag(){
		return new EtagBusiness().getFastEtag(this.content, this.id);
	}
	
}
