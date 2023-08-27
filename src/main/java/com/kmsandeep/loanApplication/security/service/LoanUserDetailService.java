package com.kmsandeep.loanApplication.security.service;

import com.kmsandeep.loanApplication.repository.UserRepository;
import com.kmsandeep.loanApplication.security.model.LoanUserDetails;
import com.kmsandeep.loanApplication.security.model.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@Log
public class LoanUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        return optionalUser.map(LoanUserDetails::new)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
