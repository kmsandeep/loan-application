package com.kmsandeep.loanApplication.service;

import com.kmsandeep.loanApplication.dto.Loan;
import com.kmsandeep.loanApplication.dto.request.LoanRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

public class LoanServiceUtils {
    public static Loan mapRequestToLoan(LoanRequest loanRequest){
        Loan loan = new Loan();
        loan.setApplicationId(UUID.randomUUID().toString());
        loan.setApplicantName( loanRequest.getApplicantName());
        loan.setTermInMonths(loanRequest.getTermInMonths());
        loan.setLoanAmount(loanRequest.getLoanAmount());
        loan.setInterestRate(loanRequest.getInterestRate());
        return loan;
    }
}
