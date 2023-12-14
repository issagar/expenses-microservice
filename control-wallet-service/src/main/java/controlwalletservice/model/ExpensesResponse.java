package controlwalletservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpensesResponse {
    private String userId;
    private double currentBalance;
}
