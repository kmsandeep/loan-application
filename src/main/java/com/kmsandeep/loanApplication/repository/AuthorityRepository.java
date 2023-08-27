package com.kmsandeep.loanApplication.repository;

import com.kmsandeep.loanApplication.security.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    public Optional<Authority> findByAuthority(String authority);
}
