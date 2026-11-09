
package com.bankflow.bankflow.service;

import com.bankflow.bankflow.entity.User;
import com.bankflow.bankflow.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register a new user
    public User registerUser(User user) {

        // Check whether email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException(
                "Email is already registered!"
            );
        }

        // Save user in database
        return userRepository.save(user);
    }

    // Get user by ID
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("User not found!")
                );
    }

    // Get user by email
    public Optional<User> getUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }
}
