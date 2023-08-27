package com.kmsandeep.loanApplication.model.response;

import com.kmsandeep.loanApplication.model.Loan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class LoanResponse {
    private Map<String, String> errorMap;
    private List<Loan> result;

    public LoanResponse() {
        errorMap = new HashMap<>();
        result = new ArrayList<>();
    }

    public LoanResponse(List<Loan> loans) {
        this();
        result.addAll(loans);
        errorMap = null;
    }

    public static LoanResponse result(Loan loan) {
        return new LoanResponse(List.of(loan));
    }

    public static LoanResponse result(List<Loan> loans) {
        return new LoanResponse(loans);
    }

    public static LoanResponse error(Map<String, String> errorMap) {
        LoanResponse response = new LoanResponse();
        response.errorMap = errorMap;
        response.result = null;
        return response;
    }

}
