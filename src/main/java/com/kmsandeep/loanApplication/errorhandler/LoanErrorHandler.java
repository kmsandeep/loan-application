package com.kmsandeep.loanApplication.errorhandler;

import com.kmsandeep.loanApplication.dto.response.LoanResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class LoanErrorHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<LoanResponse> methodArgumentNotValid(MethodArgumentNotValidException exception){
        Map<String,String> errorMap = new HashMap<>();
        exception.getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(LoanResponse.error(errorMap));
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    ResponseEntity<LoanResponse> internalServerError(HttpServerErrorException.InternalServerError exception){
        return ResponseEntity.internalServerError().body(LoanResponse.error(
                Map.of("exception",exception.getMessage())));
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LoanNotFoundException.class)
    ResponseEntity<LoanResponse> loanNotFoundException(LoanNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(LoanResponse.error(Map.of("exception",exception.getMessage())));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    ResponseEntity<LoanResponse> loanNotFoundException(Exception exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(LoanResponse.error(Map.of("exception",exception.getMessage())));
    }

}
