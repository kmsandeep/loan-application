package com.kmsandeep.loanApplication.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "APPLICANT")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "APPLICANT_SEQ")
    @SequenceGenerator(name = "APPLICANT_SEQ",initialValue = 1000)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private int age;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
