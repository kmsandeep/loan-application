package com.kmsandeep.loanApplication.model;

import com.kmsandeep.loanApplication.constant.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Entity
@Table(name = "LOAN")
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "applicant_id",referencedColumnName = "id")
    private Applicant applicant;
    private BigDecimal loanAmount;
    private BigDecimal loanApprovedAmount;
    private int termInMonths;
    private float interestRate;
    @Enumerated(EnumType.STRING)
    private LoanStatus status;

}
