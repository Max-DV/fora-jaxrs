package io.robusta.domain;

import io.robusta.business.EtagBusiness;
import io.robusta.business.UserBusiness;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class User implements Serializable{

	
	private static final long serialVersionUID = 3490373199478816786L;
	
	Long id;	
	String email;
	String name;
	
	boolean male = true;
	
	int version=1;
    protected boolean admin = false;
	
    public User() {
		
	}
    
    public User(Long id, String name) {
    	this.id = id;
		this.name = name;
		this.email = name.toLowerCase()+"@robusta.io";
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	
	//Using id and email
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (name != null){
			return name;
		}else{
			return "Anonymous";
		}
		
	}
 @XmlTransient
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}


    public boolean isAdmin() {
        return admin;
    }
    
    public void setFemale(){
    	this.male = false;
    }
    
    public boolean isMale() {
		return male;
	}
    
    
    public void speak(){
    	System.out.println("I am " + this.name);
    	
    }
    
    @XmlTransient
    public String getEtag(){
    	return new EtagBusiness().getFastEtag(this.name, this.email);
    }
}
