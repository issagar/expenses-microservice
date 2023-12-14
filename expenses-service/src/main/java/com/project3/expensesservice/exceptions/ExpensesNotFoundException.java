package com.project3.expensesservice.exceptions;

public class ExpensesNotFoundException extends RuntimeException{
    public ExpensesNotFoundException(String message) {
        super(message);
    }
    public ExpensesNotFoundException() {
        super("Not found this expense");
    }

}
