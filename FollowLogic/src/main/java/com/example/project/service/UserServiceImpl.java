package com.example.project.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.User;
import com.example.project.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User followUser(Long userId, Long followUserId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<User> followUser = userRepository.findById(followUserId);
        if (user.isPresent() && followUser.isPresent()) {
            user.get().getFollowing().add(followUser.get());
            followUser.get().getFollowers().add(user.get());
            userRepository.save(user.get());
            userRepository.save(followUser.get());
            return user.get();
        }
        return null;  // Return null if any of the users do not exist
    }

    @Override
    public User unfollowUser(Long userId, Long unfollowUserId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<User> unfollowUser = userRepository.findById(unfollowUserId);
        if (user.isPresent() && unfollowUser.isPresent()) {
            user.get().getFollowing().remove(unfollowUser.get());
            unfollowUser.get().getFollowers().remove(user.get());
            userRepository.save(user.get());
            userRepository.save(unfollowUser.get());
            return user.get();
        }
        return null;  // Return null if any of the users do not exist
    }

    @Override
    public List<User> getFollowers(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(User::getFollowers).orElse(null);
    }

    @Override
    public List<User> getFollowing(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(User::getFollowing).orElse(null);
    }
}
