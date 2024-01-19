package com.GreenStitchassignmnet.GreenStitchassignmnet.service;

import com.GreenStitchassignmnet.GreenStitchassignmnet.entity.User;
import com.GreenStitchassignmnet.GreenStitchassignmnet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsereinfoDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<User> userOptional = repository.findByUsername(username);

        if (userOptional.isEmpty()) {

            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        User user = userOptional.get();



        List<GrantedAuthority> authorities = getAuthoritiesForUser(user);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    private List<GrantedAuthority> getAuthoritiesForUser(User user) {

        List<GrantedAuthority> authorities = Arrays.stream(user.getroles().split(","))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                .collect(Collectors.toList());

        return authorities;
    }













}
