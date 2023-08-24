package com.kmsandeep.loanApplication.security.userservice;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loan_authorities")
@Data
@NoArgsConstructor
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "AUTHORITY", unique = true)
    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

}
