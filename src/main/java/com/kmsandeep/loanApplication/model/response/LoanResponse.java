package com.kmsandeep.loanApplication.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Map<String,String> errorMap;
    private List<Loan> result;
    public LoanResponse(){
        errorMap = new HashMap<>();
        result = new ArrayList<>();
    }
    public static LoanResponse one(Loan loan){
        LoanResponse response = new LoanResponse();
        response.result.add(loan);
        response.errorMap = null;
        return response;
    }
    public static LoanResponse multi(List<Loan> loans){
        LoanResponse response = new LoanResponse();
        response.result.addAll(loans);
        response.errorMap = null;
        return response;
    }
    public static LoanResponse error(Map<String,String> errorMap){
        LoanResponse response = new LoanResponse();
        response.errorMap= errorMap;
        response.result = null;
        return response;
    }

}
