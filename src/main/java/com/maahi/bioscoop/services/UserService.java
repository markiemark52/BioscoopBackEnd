package com.maahi.bioscoop.services;

import com.maahi.bioscoop.entities.User;
import com.maahi.bioscoop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User newUser) {
        return userRepository.save(newUser);
        //return new User();
    }

    public User login(User loginUser) {
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            if (user.getEmail().equals(loginUser.getEmail())) {
                return user;
            }
        }

        return new User();
    }
}
