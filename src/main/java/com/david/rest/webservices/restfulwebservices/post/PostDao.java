package com.david.rest.webservices.restfulwebservices.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

@Controller
public class PostDao {

	private static List<Post> posts = new ArrayList<>();
	private static int postCount = 4;
	
	
	static {
		posts.add(new Post(1,1,"some text for testing user 1"));
		posts.add(new Post(1,2,"another test for testing user 1"));
		posts.add(new Post(2,3,"some text for testing user 2"));
		posts.add(new Post(3,4,"some text for testing user 3"));
	}
	
	public List<Post> getAllUserPosts(int userId) {
		List<Post> userPosts = new ArrayList<>();
		for(Post post:posts) {
			if(post.getUserId() == userId) {
				userPosts.add(post);
			}
		}
		
		return userPosts;
	}
	
	public int getUserPostsCount(int userId) {
		int postCounter = 0;
		if(getAllUserPosts(userId) != null) {
			postCounter = getAllUserPosts(userId).size();
		}
		return postCounter;
	}
	
	public Post getUserPostById(int userId, int postId) {
		List<Post> userPosts = getAllUserPosts(userId);
		Post selectedPost = null;
		for(Post post:userPosts) {
			if(post.getPostId() == postId ) {
				selectedPost = post;
			}
		}
		return selectedPost;
	}
	
	public Post savePost(int userId, Post post) {
		Post createdPost = new Post(userId, ++postCount, post.getContent());
		posts.add(createdPost);
		return createdPost;
	}
	
	public int getPostsCount(int userId) {
		return postCount; 
	}
	
	
}
