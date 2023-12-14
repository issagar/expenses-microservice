package com.project3.expensesservice.exceptions;

import com.project3.expensesservice.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsController {
    @ExceptionHandler(ExpensesNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ExpensesNotFoundException expensesNotFoundException){
        String message = expensesNotFoundException.getMessage();
        ApiResponse response = new ApiResponse();
        response.setMessage(message);
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setSuccess(true);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ApiResponse> handlerInsufficientBalanceException(InsufficientBalanceException insufficientBalanceException){
        String message = insufficientBalanceException.getMessage();
        ApiResponse response = new ApiResponse();
        response.setMessage(message);
        response.setStatus(HttpStatus.NOT_ACCEPTABLE);
        response.setSuccess(true);
        return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
    }
}
