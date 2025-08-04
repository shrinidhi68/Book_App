package com.stackroute.userfavoriteservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
public class User {
    @Id
    private String email;
    private String userName;
    private String password;
    private List<Favorite> favBookList;

    public User() {
    }

    public User(String email, String userName, String password, String phoneNumber, List<Favorite> favBookList) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.favBookList = favBookList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public List<Favorite> getFavBookList() {
		return favBookList;
	}

	public void setFavBookList(List<Favorite> favBookList) {
		this.favBookList = favBookList;
	}

	public User(String email, String userName, String password, List<Favorite> favBookList) {
		super();
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.favBookList = favBookList;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", userName=" + userName + ", password=" + password + ", favBookList="
				+ favBookList + "]";
	}

  

  
   
    
}
