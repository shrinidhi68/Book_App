package com.stackroute.userfavoriteservice.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Favorite {
  
    
    private String favUrl;
   

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
