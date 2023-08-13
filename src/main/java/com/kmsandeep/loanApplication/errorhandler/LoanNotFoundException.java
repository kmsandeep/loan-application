package com.kmsandeep.loanApplication.errorhandler;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String applicationId) {
        super(String.format("Loan application not found with applicationId: %s",applicationId));
    }
}
