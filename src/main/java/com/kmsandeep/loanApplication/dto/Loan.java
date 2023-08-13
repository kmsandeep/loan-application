package com.kmsandeep.loanApplication.dto;

import com.kmsandeep.loanApplication.constant.LoanStatus;
import com.kmsandeep.loanApplication.dto.request.LoanRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String applicationId;
    private String applicantName;
    private BigDecimal loanAmount;
    private BigDecimal loanApprovedAmount;

    private int termInMonths;
    private float interestRate;
    private LoanStatus status;

}
