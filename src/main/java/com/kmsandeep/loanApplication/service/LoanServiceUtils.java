package com.kmsandeep.loanApplication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmsandeep.loanApplication.model.Address;
import com.kmsandeep.loanApplication.model.Applicant;
import com.kmsandeep.loanApplication.model.Loan;
import com.kmsandeep.loanApplication.model.request.LoanRequest;

import java.util.UUID;

public class LoanServiceUtils {
    public static Loan mapRequestToLoan(LoanRequest loanRequest){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Loan loan = objectMapper.readValue(objectMapper.writeValueAsString(loanRequest), Loan.class);
            return loan;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
