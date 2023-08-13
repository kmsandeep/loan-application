package com.kmsandeep.loanApplication.service;

import com.kmsandeep.loanApplication.dto.Loan;
import com.kmsandeep.loanApplication.dto.request.LoanRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanService {
    public Loan submitLoan(LoanRequest loanRequest);
    public Loan findLoan(String applicationId);
    public List<Loan> findAllLoans();
    public Loan updateLoan(Loan loan);

}
