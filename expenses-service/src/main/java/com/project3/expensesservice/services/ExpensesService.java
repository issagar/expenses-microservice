package com.project3.expensesservice.services;

import com.project3.expensesservice.dto.Expense;

import java.util.List;

public interface ExpensesService {
    Expense create(Expense expense);
    List<Expense> getExpenses();
    List<Expense> getExpensesByUserId(String userId);
    List<Expense> getExpensesByCategory(String category);

    //double getCurrentBalanceByUserId(String userId);
}
