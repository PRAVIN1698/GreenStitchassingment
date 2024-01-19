package com.GreenStitchassignmnet.GreenStitchassignmnet.UserController;


import com.GreenStitchassignmnet.GreenStitchassignmnet.dto.AuthRequest;
import com.GreenStitchassignmnet.GreenStitchassignmnet.entity.User;
import com.GreenStitchassignmnet.GreenStitchassignmnet.service.UserService;
import com.GreenStitchassignmnet.GreenStitchassignmnet.auth.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService , AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody User user) {
        System.out.println(user);

       return userService.saveUser(user);

    }


    @PostMapping("/login")
    public String signUp(@RequestBody AuthRequest authrequest) throws Exception {
        try {
            System.out.println("Received AuthRequest: " + authrequest);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authrequest.getUsername(), authrequest.getPassword()));

            if (authentication.isAuthenticated()) {
                System.out.println("Authentication is successful.");
                return JwtTokenUtil.generateToken(authrequest.getUsername());
            } else {
                throw new BadCredentialsException("Invalid credentials");
            }
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed. Reason: " + e.getMessage());
            throw new BadCredentialsException("Invalid credentials", e);
        }
    }





    @GetMapping("/find/{username}")
    public Optional<User> findUser(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('ROLE_User')")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from GreenStitch");
    }








}

