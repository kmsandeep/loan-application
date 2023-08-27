package com.kmsandeep.loanApplication.controller;

import com.kmsandeep.loanApplication.security.jwt.AuthRequest;
import com.kmsandeep.loanApplication.security.service.LoanJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class SecurityController {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private LoanJwtService jwtService;
    @PostMapping
    public String authenticateAndGetToken(@RequestBody AuthRequest request){
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(request.getUsername());
        }
        throw new UsernameNotFoundException("User does not exists!");
    }
}
