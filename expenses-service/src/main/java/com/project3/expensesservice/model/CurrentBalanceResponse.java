package com.project3.expensesservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentBalanceResponse {
    private String userId;
    private double currentBalance;
}
