package com.kmsandeep.loanApplication.service;

import com.kmsandeep.loanApplication.model.Address;
import com.kmsandeep.loanApplication.model.Applicant;
import com.kmsandeep.loanApplication.model.Loan;
import com.kmsandeep.loanApplication.model.request.LoanRequest;

import java.util.UUID;

public class LoanServiceUtils {
    public static Loan mapRequestToLoan(LoanRequest loanRequest){
        Loan loan = new Loan();
        Applicant applicant = new Applicant();
        applicant.setName(loanRequest.getApplicantName());
        Address address = new Address();
        address.setState("UP");
        applicant.setAddress(address);
        loan.setApplicant(applicant);
        loan.setTermInMonths(loanRequest.getTermInMonths());
        loan.setLoanAmount(loanRequest.getLoanAmount());
        loan.setInterestRate(loanRequest.getInterestRate());
        return loan;
    }
}
