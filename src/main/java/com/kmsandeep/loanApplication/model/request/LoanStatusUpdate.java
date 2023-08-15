package com.kmsandeep.loanApplication.model.request;

import com.kmsandeep.loanApplication.constant.LoanStatus;

import java.math.BigDecimal;

public class LoanStatusUpdate {

    private LoanStatus loanStatus;
    private BigDecimal loanApprovedAmount;

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public BigDecimal getLoanApprovedAmount() {
        return loanApprovedAmount;
    }

    public void setLoanApprovedAmount(BigDecimal loanApprovedAmount) {
        this.loanApprovedAmount = loanApprovedAmount;
    }
}
