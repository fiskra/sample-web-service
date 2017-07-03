package com.fiskra.ws.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bookmark{

    @Id
    @GeneratedValue
    private Long id;
    
    public String uri;
    
    public String description;
    
    @ManyToOne
    private User user;

    public Bookmark() { 
    }

    public Bookmark(User user, String uri, String description) {
        this.uri = uri;
        this.description = description;
        this.user = user;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


 
}
