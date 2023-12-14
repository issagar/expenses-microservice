package com.project3.expensesservice.repository;

import com.project3.expensesservice.dto.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepository extends MongoRepository<Expense, Long> {
    List<Expense> findByUserId(String userId);
    List<Expense> findByCategory(String category);
    //double sumAmountByUserId(String userId);

}
