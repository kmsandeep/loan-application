package com.kmsandeep.loanApplication.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "APPLICANT")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APPLICANT_SEQ")
    @SequenceGenerator(name = "APPLICANT_SEQ", initialValue = 1000)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private int age;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Email> emails;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
