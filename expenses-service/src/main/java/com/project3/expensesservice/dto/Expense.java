package com.project3.expensesservice.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document("expenses")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Expense {
    @Id
    private String id;
    private String name;
    private String description;
    private String category;
    private Date date;
    private double amount;
    private String userId;
}
