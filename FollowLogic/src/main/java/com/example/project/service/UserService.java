package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.entity.User;



public interface UserService {
    public User insert(User user);
    public List<User> getAll();
    public Optional<User> getUserById(Long id);
    public void deleteUserById(Long id);
    public User updateUser(User user);
    public User followUser(Long userId, Long followUserId);
    public User unfollowUser(Long userId, Long unfollowUserId);
    public List<User> getFollowers(Long userId);
    public List<User> getFollowing(Long userId);
}
