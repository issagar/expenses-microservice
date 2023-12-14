package com.project3.expensesservice.controller;

import com.project3.expensesservice.dto.Expense;
import com.project3.expensesservice.services.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expenses")
public class RatingController {
    @Autowired
    private ExpensesService expensesService;

    @PostMapping
    public ResponseEntity<Expense> saveExpenses(@RequestBody Expense expense){
        String id = UUID.randomUUID().toString();
        expense.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(expensesService.create(expense));
    }
    @GetMapping
    public ResponseEntity<List<Expense>> listExpenses(){
        return ResponseEntity.ok(expensesService.getExpenses());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Expense>> listExpensesByUserId(@PathVariable String userId){
        return ResponseEntity.ok(expensesService.getExpensesByUserId(userId));
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Expense>> listExpensesByCategory(@PathVariable String category){
        return ResponseEntity.ok(expensesService.getExpensesByCategory(category));
    }
    /*
    @GetMapping("/wallet/{userId}")
    public ResponseEntity <Double> getCurrentBalanceByUserId(@PathVariable String userId){
        return ResponseEntity.ok(expensesService.getCurrentBalanceByUserId(userId));
    }*/

}
