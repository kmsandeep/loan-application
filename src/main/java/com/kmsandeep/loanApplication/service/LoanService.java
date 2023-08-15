package com.kmsandeep.loanApplication.service;

import com.kmsandeep.loanApplication.model.Loan;
import com.kmsandeep.loanApplication.model.request.LoanRequest;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    public Loan submitLoan(LoanRequest loanRequest);
    public Optional<Loan> findLoan(String applicationId);
    public List<Loan> findAllLoans();
    public Loan updateLoan(Loan loan);
//    public List<Loan> findByApplicantName(String name);
}
