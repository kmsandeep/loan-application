package com.kmsandeep.loanApplication.repository;

import com.kmsandeep.loanApplication.security.userservice.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findByUsername(String username);
}

