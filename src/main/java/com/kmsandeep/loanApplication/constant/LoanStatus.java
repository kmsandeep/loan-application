package com.kmsandeep.loanApplication.constant;

public enum LoanStatus {
    INCOMPLETE("Incomplete"),
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private String status;

    private LoanStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
