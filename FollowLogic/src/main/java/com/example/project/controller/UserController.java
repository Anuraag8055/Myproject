package com.example.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.entity.User;
import com.example.project.service.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		return userService.insert(user);
	}
	
	@GetMapping("/users")
	public List<User> getAllUser() {
		List<User> users = userService.getAll();
		return users;
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {
		return userService.getUserById(id);
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
		return "user delete with userId"+id;
		
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}
	
	  @PostMapping("/users/{userId}/follow/{followUserId}")
	    public User followUser(@PathVariable Long userId, @PathVariable Long followUserId) {
	        return userService.followUser(userId, followUserId);
	    }

	    @PostMapping("/users/{userId}/unfollow/{unfollowUserId}")
	    public User unfollowUser(@PathVariable Long userId, @PathVariable Long unfollowUserId) {
	        return userService.unfollowUser(userId, unfollowUserId);
	    }

	    @GetMapping("/users/{userId}/followers")
	    public List<User> getFollowers(@PathVariable Long userId) {
	        return userService.getFollowers(userId);
	    }

	    @GetMapping("/users/{userId}/following")
	    public List<User> getFollowing(@PathVariable Long userId) {
	        return userService.getFollowing(userId);
	    }
	
}
