package com.project3.expensesservice.exceptions;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String message) {
        super(message);
    }
    public InsufficientBalanceException() {

    }
}
