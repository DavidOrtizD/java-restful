package com.david.rest.webservices.restfulwebservices.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

	@Autowired
	PostDao postService;
	
	@GetMapping("/users/{id}/posts")
	public List<Post> getAllPostsForUser(@PathVariable int id) {
		 List<Post> postList = postService.getAllUserPosts(id);
		 if(postList.size() < 1) {
			 throw new PostNotFoundException("userId-"+id);
		 }
		 return postList;
	}
	
	@PostMapping("/users/{id}/posts")
	public Post createPostForUser(@PathVariable int id, @RequestBody Post post) {
		return postService.savePost(id, post);
	}
	
	@GetMapping("/users/{userId}/posts/{postId}")
	public Post createPostForUser(@PathVariable int userId, @PathVariable int postId) {
		Post selectedPost = postService.getUserPostById(userId, postId);
		if(selectedPost == null) {
			throw new PostNotFoundException("postId-"+postId+" for userId-"+userId);
		}
		return selectedPost;
	}
}
