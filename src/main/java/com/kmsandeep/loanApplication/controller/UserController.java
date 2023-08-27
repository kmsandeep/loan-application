package com.kmsandeep.loanApplication.controller;

import com.kmsandeep.loanApplication.repository.AuthorityRepository;
import com.kmsandeep.loanApplication.repository.UserRepository;
import com.kmsandeep.loanApplication.security.model.Authority;
import com.kmsandeep.loanApplication.security.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Transactional
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            List<Authority> authorities = user.getAuthorities().stream()
                    .map(authority -> authorityRepository.findByAuthority(authority.getAuthority()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
            user.setAuthorities(authorities);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.findByUsername(user.getUsername())
                    .ifPresent(u -> user.setId(u.getId()));
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
                List<Authority> authorities = user.getAuthorities().stream()
                        .map(authority -> authorityRepository.findByAuthority(authority.getAuthority()))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList());
                user.setAuthorities(authorities);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.findByUsername(user.getUsername())
                        .ifPresent(u -> user.setId(u.getId()));
                User savedUser = userRepository.save(user);
                return savedUser;
            }).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAll);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @PostMapping("/addRole")
    public ResponseEntity<Authority> addUser(@RequestBody Authority authority) {
        try {
            Authority saved = authorityRepository.save(authority);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @GetMapping("/listUser")
    public ResponseEntity<List<User>> listUsers() {
        List<User> allUsers = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(allUsers);
    }

}
