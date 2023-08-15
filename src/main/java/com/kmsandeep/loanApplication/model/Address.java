package com.kmsandeep.loanApplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ADDRESS_SEQ")
    @SequenceGenerator(name = "ADDRESS_SEQ",initialValue = 1000)
    @Column(name = "id", nullable = false)
    private Long id;
    private String state;
    private String city;
    private String pinCode;
    @JsonIgnore
    @OneToOne(mappedBy = "address",fetch = FetchType.LAZY)
    private Applicant applicant;
}
