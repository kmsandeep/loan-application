package com.kmsandeep.loanApplication.service;

import com.kmsandeep.loanApplication.dto.Loan;
import com.kmsandeep.loanApplication.dto.request.LoanRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    public Loan submitLoan(LoanRequest loanRequest);
    public Optional<Loan> findLoan(String applicationId);
    public List<Loan> findAllLoans();
    public Loan updateLoan(Loan loan);
    public List<Loan> findByApplicantName(String name);
}
