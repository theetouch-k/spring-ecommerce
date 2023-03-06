package com.example.urnshop.controller;

import com.example.urnshop.model.User;
import com.example.urnshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller // This means that this class is a Controller
@RequestMapping(path="/api/v1/users") // This means URL's start with /demo (after Application path)
public class UserController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping// Map ONLY POST Requests
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody String addNewUser (@RequestHeader("username") String username,
                                            @RequestHeader("email") String email,
                                            @RequestHeader("password") String password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User n = new User();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String encodedPassword = passwordEncoder.encode(password);

        n.setUsername(username);
        n.setPassword(encodedPassword);
        n.setEmail(email);
        n.setCreatedAt(timestamp);
        n.setRole("ROLE_USER");
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}