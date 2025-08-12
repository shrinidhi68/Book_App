package com.stackroute.userfavoriteservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Favorite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
  
    
    private String favUrl;
   

    public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Favorite() {
    }

	

	public String getFavUrl() {
		return favUrl;
	}

	public void setFavUrl(String favUrl) {
		this.favUrl = favUrl;
	}

	
	public Favorite(String favUrl) {
		super();
	
		this.favUrl = favUrl;
		
	}

    
}
