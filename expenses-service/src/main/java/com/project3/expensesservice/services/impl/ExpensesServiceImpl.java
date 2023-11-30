package com.project3.expensesservice.services.impl;

import com.project3.expensesservice.dto.Expense;
import com.project3.expensesservice.repository.ExpensesRepository;
import com.project3.expensesservice.services.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExpensesServiceImpl implements ExpensesService {
    @Autowired
    ExpensesRepository expensesRepository;

    @Override
    public Expense create(Expense expense) {
        String randomUserId = UUID.randomUUID().toString();
        expense.setId(randomUserId);
        return expensesRepository.save(expense);
    }

    @Override
    public List<Expense> getExpenses() {
        return expensesRepository.findAll();
    }

    @Override
    public List<Expense> getExpensesByUserId(String userId) {
        return expensesRepository.findByUserId(userId);
    }

    @Override
    public List<Expense> getExpensesByCategory(String category) {
        return expensesRepository.findByCategory(category);
    }
}
