package com.GreenStitchassignmnet.GreenStitchassignmnet.service;

import com.GreenStitchassignmnet.GreenStitchassignmnet.entity.User;
import com.GreenStitchassignmnet.GreenStitchassignmnet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public String saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setroles("User");
        userRepository.save(user);
        System.out.println(user.getroles());
        return  "User is Succesfully created";
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
