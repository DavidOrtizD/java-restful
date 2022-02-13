package com.david.rest.webservices.restfulwebservices.post;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.david.rest.webservices.restfulwebservices.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PostJpa {

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	@Id
	@GeneratedValue
	private Integer postId;
	private String content;
	
	public PostJpa(){}
	
	public PostJpa(User user, int postId, String content) {
		super();
		this.user = user;
		this.postId = postId;
		this.content =  content;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public Integer getPostId() {
		return postId;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	@Override
	public String toString() {
		return "Post [content=" + content + ", postId=" + postId + "]";
	}
}

