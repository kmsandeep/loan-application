package com.kmsandeep.loanApplication.model.request;

import com.kmsandeep.loanApplication.model.Applicant;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class LoanRequest {
    @NotNull
    private Applicant applicant;
    @Min(1000)
    private BigDecimal loanAmount;
    @Min(3)
    private int termInMonths;
    @Min(5)
    private float interestRate;
}
