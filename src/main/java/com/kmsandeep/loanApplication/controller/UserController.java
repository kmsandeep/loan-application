package com.kmsandeep.loanApplication.controller;

import com.kmsandeep.loanApplication.repository.AuthorityRepository;
import com.kmsandeep.loanApplication.repository.UserRepository;
import com.kmsandeep.loanApplication.security.userservice.Authority;
import com.kmsandeep.loanApplication.security.userservice.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            authorityRepository.saveAll(user.getAuthorities());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (RuntimeException e) {
            throw e;
        }
    }
    @PostMapping("/addUsers")
    public ResponseEntity<List<User>> addUsers(@RequestBody List<User> users) {
        try {
            List<User> savedAll = users.stream().map(user -> {
                authorityRepository.saveAll(user.getAuthorities());
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return userRepository.save(user);
            }).collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.CREATED).body(savedAll);
        } catch (RuntimeException e) {
            throw e;
        }
    }
    @GetMapping("/listUser")
    public ResponseEntity<List<User>> listUsers(){
        List<User> allUsers = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(allUsers);
    }


}
