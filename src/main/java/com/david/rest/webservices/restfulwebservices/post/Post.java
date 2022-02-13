package com.david.rest.webservices.restfulwebservices.post;

public class Post {
	private Integer userId;
	private Integer postId;
	private String content;
	
	public Post(int userId, int postId, String content) {
		super();
		this.userId = userId;
		this.postId = postId;
		this.content =  content;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Integer getUserId() {
		return userId;
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
		return "User [userId=" + userId + ", content=" + content + ", postId=" + postId + "]";
	}
}
