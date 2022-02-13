package com.david.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.david.rest.webservices.restfulwebservices.post.PostJpa;
import com.david.rest.webservices.restfulwebservices.post.PostJpaRepository;
import com.david.rest.webservices.restfulwebservices.post.PostNotFoundException;

@RestController
public class UserJPAResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostJpaRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retriveAllusers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		Optional<User> user =  userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		
		return user.get();
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);		
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<PostJpa> getUserPosts(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		} else {
			return user.get().getPosts();
		}
		
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public  ResponseEntity<Object> createPosts(@PathVariable int id, @RequestBody PostJpa post) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		} else {
			post.setUser(user.get());
			
			PostJpa savedPost = postRepository.save(post);
			
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(savedPost.getPostId()).toUri();
				
				return ResponseEntity.created(location).build();
		}
		
	}
	
	@DeleteMapping("/jpa/users/{id}/posts/{postid}")
	public  void createPosts(@PathVariable int id, @PathVariable int postid) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		} else {
			List<PostJpa> posts = user.get().getPosts();
			if(posts.size() <=0 ) {
				throw new PostNotFoundException("postid-"+postid+" not found for user"+id);
			} else {
				boolean postFoundInUser = false;
				for(PostJpa post:posts) {
					postFoundInUser = post.getPostId() == postid;
				}
				if(postFoundInUser) {
					postRepository.deleteById(postid);
				} else {
					throw new PostNotFoundException("postid-"+postid+" for user"+id);
				}
			}
		}
		
	}
}
