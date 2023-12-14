package com.project3.expensesservice.services.impl;

import com.project3.expensesservice.dto.Expense;
import com.project3.expensesservice.dto.WalletResponse;
import com.project3.expensesservice.exceptions.ExpensesNotFoundException;
import com.project3.expensesservice.exceptions.InsufficientBalanceException;
import com.project3.expensesservice.repository.ExpensesRepository;
import com.project3.expensesservice.services.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class ExpensesServiceImpl implements ExpensesService {
    @Autowired
    private ExpensesRepository expensesRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Expense create(Expense expense) {
        String randomUserId = UUID.randomUUID().toString();
        expense.setId(randomUserId);
        WalletResponse walletResponse = restTemplate.getForObject("http://localhost:8082/api/wallets/"+expense.getUserId(), WalletResponse.class);
        if (!walletResponse.isPositiveBalance()){
            throw new InsufficientBalanceException("El usuario no tiene saldo suficiente");
        }
        return expensesRepository.save(expense);
    }

    @Override
    public List<Expense> getExpenses() {
        return expensesRepository.findAll();
    }

    @Override
    public List<Expense> getExpensesByUserId(String userId) {
        List<Expense> expenseList = expensesRepository.findByUserId(userId);
        if (expenseList.isEmpty()){
            throw new ExpensesNotFoundException("No se encontraron gastos para el usuario: "+userId);
        }
        return expenseList;
    }

    @Override
    public List<Expense> getExpensesByCategory(String category) {
        List <Expense> expenseList = expensesRepository.findByCategory(category);
       if (expenseList.isEmpty()) {
           throw new ExpensesNotFoundException("No se encontraron gastos para la categoria: " + category);
       }
         return expenseList;
    }

  /*  @Override
    public double getCurrentBalanceByUserId(String userId) {
        return expensesRepository.sumAmountByUserId(userId);
    }*/
}
